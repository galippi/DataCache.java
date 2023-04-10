package dataCache;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JDialog;

class DataCache_FileBaseActionEvent extends ActionEvent {
    DataCache_FileBaseActionEvent(Object source, int id, String command) {
        super(source, id, command);
    }
    public String toString() {
        return this.paramString();
    }
    private static final long serialVersionUID = 3904205432553161640L;
}

abstract public class DataCache_FileBase
{
    public DataCache_FileBase()
    {
    }

    public DataCache_FileBase(String _filename)
    {
        filename = _filename;
        open(_filename);
    }
    String filename; // it's required for error state

    abstract public void open(String _filename);

    public void getRecord(int idx)
    {
        
    }

    public void add(DataCache_ChannelBase ch) {
        channels.add(ch);
        channelsTree.put(ch.getName(), ch);
    }

    public int getChannelNumber() {
        return channels.size();
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

    void executeActionListener(String errorMsg) {
        if (errorMsg == null)
            errorMsg = "file is loaded";
        for (ActionListener l : listeners) {
            ActionEvent e = new DataCache_FileBaseActionEvent (this, 0, errorMsg);
            l.actionPerformed(e);
          }
      }

    public String getName() {
        return filename;
    }

    abstract public int getLength() throws Exception;

    public boolean isReady() {
        return state == DataCache_State.DataCache_Ready;
    }

    public JDialog getDataSourceConfigDlg(Object o) {
        return null;
    }

    public boolean setLayout(Object dvlf) {
        return false; // layout info is not used by default
    }

    public Vector<Integer> getDataSourceChannelIndexArray() {
        return null;
    }

    DataCache_State state = DataCache_State.DataCache_Loading;
    TreeMap<String, DataCache_ChannelBase> channelsTree = new TreeMap<String, DataCache_ChannelBase>();
    Vector<DataCache_ChannelBase> channels = new Vector<>();
    DataCache_ChannelBase pointIndex;
}
