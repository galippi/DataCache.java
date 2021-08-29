package DataCache;

import java.util.TreeMap;

import diaDat.DiaDat_ChannelBase;
import diaDat.DiaDat_DataFileBase;
import diaDat.DiaDat_Direction;
import diaDat.DiaDat_File;
import diaDat.DataTypesEnum;
import utils.Util;
import utils.dbg;

public class DataCache_File
{
    public DataCache_File(String _filename)
    {
        filename = _filename;
        try
        {
            file = new DiaDat_File(filename);
            load();
        }catch (Exception e)
        {
            dbg.dprintf(1, "Error loading file %s (e=%s)!", filename, e.toString());
            state = DataCache_State.DataCache_Error;
        }
    }
    String filename; // it's required for error state

    DataCache_File(DiaDat_File _file) throws Exception
    {
        file = _file;
        load();
    }

    void load() throws Exception
    {
        if (file.getDir() != DiaDat_Direction.e_DiaDat_Dir_Read)
        {
            file = null;
            throw new Exception(Util.sprintf("DataCache_File.ctor - invalid file direction of file %s!", file.getName()));
        }
    }

    public void getRecord(int idx)
    {
        
    }

    public DataCache_ChannelBase getChannel(String chName) throws Exception
    {
        DiaDat_ChannelBase chBase = file.getChannel(chName);
        switch (chBase.getType())
        {
            case e_DataType_u8:
                return new DataCache_Channel_U8(this, chName);
            default:
                throw new Exception(Util.sprintf("DataCache_File.getChannel - not implemented channel type %s in file %s!", chName, file.getName()));
        }
    }

    public String getStateString() {
        switch (state)
        {
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

    DataCache_State state = DataCache_State.DataCache_Loading;
    DiaDat_File file;
    TreeMap<String, DataCache_ChannelBase> dataFiles = new TreeMap<String, DataCache_ChannelBase>();
}
