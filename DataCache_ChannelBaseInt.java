package dataCache;

import diaDat.DiaDat_ChannelBase;

abstract public class DataCache_ChannelBaseInt extends DataCache_ChannelBase {

    DataCache_ChannelBaseInt(DataCache_File _parent, DiaDat_ChannelBase chBase) {
        super(_parent);
        ch = chBase;
        factor = ch.getFactor();
        offset = ch.getOffset();
    }

    @Override
    public String getName() {
        return ch.getName();
    }

    @Override
    public String getUnit() {
        return ch.getUnit();
    }

    @Override
    public double getDouble(int idx) throws Exception {
        return get(idx) * factor + offset;
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
        return getRawMin() * factor + offset;
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax() * factor + offset;
    }

    DiaDat_ChannelBase ch;
    int rawMin = 0x7FFFFFFF, rawMax = -0x80000000;
    double factor;
    double offset;
}
