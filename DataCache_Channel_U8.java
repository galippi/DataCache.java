package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U8 extends DataCache_ChannelBase
{
    public DataCache_Channel_U8(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new Byte[ch.getLength()];
        factor = ch.getFactor();
        offset = ch.getOffset();
        rawMin = 256;
        rawMax = -1;
    }

    @Override
    public int get(int idx)
    {
        //parent.getRecord(idx);
        return (int)((int)(dataBuffer[idx] & 0xFF) * factor + offset);
    }

    @Override
    public double getDouble(int idx)
    {
        // TODO Auto-generated method stub
        return get(idx);
    }

    DiaDat_ChannelBase ch;
    Byte[] dataBuffer;

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
    }

    @Override
    protected void set(int idx) throws Exception {
        int raw = ch.getValueRaw();
        if (raw < rawMin)
            rawMin = raw;
        if (raw > rawMax)
            rawMax = raw;
        dataBuffer[idx] = (byte)raw;
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
        return getRawMin() * ch.getFactor() + ch.getOffset();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax() * ch.getFactor() + ch.getOffset();
    }

    int rawMin, rawMax;
    double factor, offset;
}
