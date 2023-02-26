package dataCache;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import diaDat.DiaDat_ChannelBase;
import diaDat.DiaDat_Direction;
import diaDat.DiaDat_File;

import lippiWare.utils.Sprintf;
import lippiWare.utils.dbg;

abstract public class DataCache_FileBase
{
    public DataCache_FileBase()
    {
    }

    public DataCache_FileBase(String _filename)
    {
        open(_filename);
    }
    String filename; // it's required for error state

    abstract public void open(String _filename);

    public DataCache_ChannelBase addChannel(DiaDat_ChannelBase chBase) throws Exception
    {
        DataCache_ChannelBase ch;
        if (chBase.isExplicit())
        {
            switch (chBase.getType())
            {
                case e_DataType_u8:
                    if (chBase.isPureInt())
                        ch = new DataCache_Channel_U8_PureInt(this, chBase);
                    else
                        ch = new DataCache_Channel_U8(this, chBase);
                    //ch = new DataCache_ChannelBaseInt(this, chBase);
                    //ch = new DataCache_ChannelBasePureInt(this, chBase);
                    break;
                case e_DataType_i16:
                    ch = new DataCache_Channel_I16(this, chBase);
                    break;
                case e_DataType_u16:
                    ch = new DataCache_Channel_U16(this, chBase);
                    break;
                case e_DataType_i32:
                    ch = new DataCache_Channel_I32(this, chBase);
                    break;
                case e_DataType_u32:
                    ch = new DataCache_Channel_U32(this, chBase);
                    break;
                case e_DataType_Real64:
                    ch = new DataCache_Channel_R64(this, chBase);
                    break;
                case e_DataType_Real32:
                    ch = new DataCache_Channel_R32(this, chBase);
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
    public boolean isReady() {
        return state == DataCache_State.DataCache_Ready;
    }
}
