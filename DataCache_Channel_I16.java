package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_I16 extends DataCache_ChannelBaseInt {

    public DataCache_Channel_I16(DataCache_FileBase _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent, chBase);
        dataBuffer = new Byte[ch.getLength() * 2];
    }

    @Override
    public int get(int idx)
    {
        int val =   (int)(dataBuffer[idx * 2    ] & 0xFF) + 
                  (((int)(dataBuffer[idx * 2 + 1] & 0xFF)) * 256);
        if (val >= 32768)
            val = val - 65536;
        return val;
    }

    @Override
    protected void set(int idx) throws Exception {
        int raw = ch.getValueRaw();
        if (raw < 0)
            raw = raw + 65536;
        if (raw < rawMin)
            rawMin = raw;
        if (raw > rawMax)
            rawMax = raw;
        dataBuffer[idx * 2] = (byte)(raw & 255);
        dataBuffer[idx * 2 + 1] = (byte)(raw / 256);
    }

    Byte[] dataBuffer;
}
