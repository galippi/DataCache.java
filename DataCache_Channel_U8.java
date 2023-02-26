package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U8 extends DataCache_ChannelBaseInt
{
    public DataCache_Channel_U8(DataCache_FileBase _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent, chBase);
        dataBuffer = new Byte[ch.getLength()];
        rawMin = 256;
        rawMax = -1;
    }

    @Override
    public int get(int idx)
    {
        //parent.getRecord(idx);
        return (int)(dataBuffer[idx] & 0xFF);
    }

    Byte[] dataBuffer;

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
    public double getDoubleMin() throws Exception {
        return getRawMin() * ch.getFactor() + ch.getOffset();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax() * ch.getFactor() + ch.getOffset();
    }
}
