package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_R64 extends DataCache_ChannelBase
{
    public DataCache_Channel_R64(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
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
}
