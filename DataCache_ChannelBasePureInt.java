package dataCache;

import diaDat.DiaDat_ChannelBase;

abstract public class DataCache_ChannelBasePureInt extends DataCache_ChannelBase {

    DataCache_ChannelBasePureInt(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch = chBase;
    }

    @Override
    public double getDouble(int idx) throws Exception {
        return get(idx);
    }

    @Override
    public int getRawMin() throws Exception {
        return rawMin;
    }

    @Override
    public int getRawMax() throws Exception {
        return rawMax;
    }

    @Override
    public double getDoubleMin() throws Exception {
        return getRawMin();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax();
    }

    @Override
    public String getName() {
        return ch.getName();
    }

    public String getUnit() {
        return ch.getUnit();
    }

    DiaDat_ChannelBase ch;
    int rawMin = 0x7FFFFFFF, rawMax = -0x80000000;
}
