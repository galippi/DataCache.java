package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U32 extends DataCache_ChannelBase {

    DataCache_Channel_U32(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new int[ch.getLength()];
        factor = ch.getFactor();
        offset = ch.getOffset();
    }

    @Override
    public int get(int idx) {
        return dataBuffer[idx];
    }

    @Override
    public double getDouble(int idx) throws Exception {
        return get(idx) * factor + offset;
    }

    @Override
    public String getName() {
        return ch.getName();
    }

    @Override
    protected void set(int idx) throws Exception {
        long raw = ch.getValueRaw();
        if (raw < rawMin)
            rawMin = raw;
        if (raw > rawMax)
            rawMax = raw;
        dataBuffer[idx] = (int)raw;
    }

    @Override
    public int getRawMin() throws Exception {
        return (int)rawMin;
    }

    @Override
    public int getRawMax() throws Exception {
        return (int)rawMax;
    }

    @Override
    public double getDoubleMin() throws Exception {
        return getRawMin() * ch.getFactor() + ch.getOffset();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax() * ch.getFactor() + ch.getOffset();
    }

    DiaDat_ChannelBase ch;
    int[] dataBuffer;
    double factor, offset;
    long rawMin, rawMax;
}
