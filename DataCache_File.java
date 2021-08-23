package DataCache;

import java.util.TreeMap;

import diaDat.DiaDat_ChannelBase;
import diaDat.DiaDat_DataFileBase;
import diaDat.DiaDat_Direction;
import diaDat.DiaDat_File;
import util.Util;

public class DataCache_File
{
    public DataCache_File(DiaDat_File _file) throws Exception
    {
        file = _file;
        if (file.getDir() != DiaDat_Direction.e_DiaDat_Dir_Read)
        {
            file = null;
            throw new Exception(Util.sprintf("DataCache_File.ctor - invalid file direction of file %s!", file.getName()));
        }
    }

    public void getRecord(int idx)
    {
        
    }

    public DataCache_ChannelBase getChannel(String chName)
    {
        DiaDat_ChannelBase chBase = file.getChannel(chName);
        switch (chBase.getType())
        {
            default:
                throw new Exception(Util.sprintf("DataCache_File.getChannel - not implemented channel type %s in file %s!", chName, file.getName()));
        }
        DataCache_ChannelBase ch = new DataCache_ChannelBase(chBase);
        return ch;
    }

    DiaDat_File file;
    TreeMap<String, DataCache_ChannelBase> dataFiles = new TreeMap<String, DataCache_ChannelBase>();
}
