package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_Implicit extends DataCache_ChannelBase
{
    public DataCache_Channel_Implicit(DataCache_FileBase _parent, DiaDat_ChannelBase chBase)
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

    @Override
    public int getRawMin() throws Exception {
        return 0;
    }

    @Override
    public int getRawMax() throws Exception {
        return ch.getLength() - 1;
    }

    @Override
    public double getDoubleMin() throws Exception {
        return getRawMin() * ch.getFactor() + ch.getOffset();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax() * ch.getFactor() + ch.getOffset();
    }

    public String getUnit() {
        return ch.getUnit();
    }

    DiaDat_ChannelBase ch;
}
