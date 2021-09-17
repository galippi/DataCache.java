package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_Implicit extends DataCache_ChannelBase
{
    public DataCache_Channel_Implicit(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch = chBase;
    }

    @Override
    public int get(int idx) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDouble(int idx) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName() {
        return ch.getName();
    }

    DiaDat_ChannelBase ch;
}
