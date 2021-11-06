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
    public int get(int idx) throws Exception {
        throw new Exception("DataCache_Channel_Implicit.get - not supported operation!");
    }

    @Override
    public double getDouble(int idx) {
        return idx * ch.getFactor() + ch.getOffset();
    }

    public int getRaw(int idx) throws Exception {
        return idx;
    }

    @Override
    public String getName() {
        return ch.getName();
    }

    @Override
    protected void set(int i) throws Exception {
        throw new Exception("DataCache_Channel_Implicit.set - not yet implemented!");
    }

    DiaDat_ChannelBase ch;
}
