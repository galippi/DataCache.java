package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U16 extends DataCache_ChannelBase
{
    public DataCache_Channel_U16(DataCache_File _parent, String chName)
    {
        super(_parent);
        //DiaDat_ChannelBase chTime = parent.getChannel(chName);
        ch = parent.getRawChannel(chName);
    }

    public DataCache_Channel_U16(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
    }

    @Override
    public int get(int idx)
    {
        parent.getRecord(idx);
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDouble(int idx)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    DiaDat_ChannelBase ch;

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
    }
}
