package dataCache;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import diaDat.DiaDat_ChannelBase;
import diaDat.DiaDat_Direction;
import diaDat.DiaDat_File;
import utils.Sprintf;
import utils.dbg;

public class DataCache_File
{
    public DataCache_File()
    {
        
    }
    public DataCache_File(String _filename)
    {
        open(_filename);
    }
    String filename; // it's required for error state

    DataCache_File(DiaDat_File _file) throws Exception
    {
        file = _file;
        load();
    }

    public void open(String _filename)
    {
        filename = _filename;
        try
        {
            file = new DiaDat_File(filename);
            load();
        }catch (Exception e)
        {
            dbg.dprintf(1, "Error loading file %s (e=%s)!\n", filename, e.toString());
            state = DataCache_State.DataCache_Error;
        }
    }

    void load() throws Exception
    {
        if (file.getDir() != DiaDat_Direction.e_DiaDat_Dir_Read)
        {
            file = null;
            throw new Exception(Sprintf.sprintf("DataCache_File.ctor - invalid file direction of file %s!", file.getName()));
        }
        pointIndex = new DataCache_PointIndex(this, "__pointIndex");
        channels.add(pointIndex);
        channelsTree.put(pointIndex.getName(), pointIndex);
        file.getChannelInit();
        DiaDat_ChannelBase chBase;
        while((chBase = file.getChannelNext()) != null)
        {
            addChannel(chBase);
        }
        state = DataCache_State.DataCache_Ready;
        executeActionListener();
    }

    public DataCache_ChannelBase addChannel(DiaDat_ChannelBase chBase) throws Exception
    {
        DataCache_ChannelBase ch;
        if (chBase.isExplicit())
        {
            switch (chBase.getType())
            {
                case e_DataType_u8:
                    ch = new DataCache_Channel_U8(this, chBase);
                    break;
                case e_DataType_Real64:
                    ch = new DataCache_Channel_R64(this, chBase);
                    break;
                default:
                    throw new Exception(Sprintf.sprintf("DataCache_File.getChannel - not implemented channel type %s in file %s!", chBase.getName(), file.getName()));
            }
        }else
        {
            ch = new DataCache_Channel_Implicit(this, chBase);
        }
        channels.add(ch);
        channelsTree.put(ch.getName(), ch);
        return ch;
    }

    public void getRecord(int idx)
    {
        
    }

    public int getChannelNumber() {
        return channels.size();
    }

    public DiaDat_ChannelBase getRawChannel(String chName) {
        return file.getChannel(chName);
    }

    public DiaDat_ChannelBase getRawChannel(int i) {
        return file.getChannel(i);
    }

    public DataCache_ChannelBase getChannel(int i) {
        return channels.get(i);
    }

    public DataCache_ChannelBase getChannel(String chName)
    {
        return channelsTree.get(chName);
    }

    public DataCache_ChannelBase getIndexChannel() {
        return channels.get(0);
    }

    public String getStateString() {
        switch (state)
        {
            case DataCache_Init:
                return "DataCache is initialized";
            case DataCache_Loading:
                return "Loading file " + filename;
            case DataCache_Ready:
                return "File " + filename + " is loaded";
            case DataCache_Error:
                return "Error loading file " + filename;
            default:
                return "Unknown state of file " + filename;
        }
    }

    public DataCache_State getState() {
        return state;
    }

    private final ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

    public void addActionListener(ActionListener l) {
      listeners.add(l);
    }

    void executeActionListener() {
        for (ActionListener l : listeners) {
            ActionEvent e = new ActionEvent (this, 0, "file is loaded");
            l.actionPerformed(e);
          }
      }

    public String getName() {
        return file.getName();
    }

    DataCache_State state = DataCache_State.DataCache_Loading;
    DiaDat_File file;
    TreeMap<String, DataCache_ChannelBase> channelsTree = new TreeMap<String, DataCache_ChannelBase>();
    Vector<DataCache_ChannelBase> channels = new Vector();
    DataCache_ChannelBase pointIndex;

    public int getLength() throws Exception {
        return file.getLength();
    }
}
