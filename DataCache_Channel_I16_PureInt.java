package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_I16_PureInt extends DataCache_ChannelBasePureInt {

    DataCache_Channel_I16_PureInt(DataCache_File _parent, DiaDat_ChannelBase chBase) {
        super(_parent, chBase);
        dataBuffer = new Byte[ch.getLength()];
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
        if (raw < rawMin)
            rawMin = raw;
        if (raw > rawMax)
            rawMax = raw;
        dataBuffer[idx] = (byte)raw;
    }

    Byte[] dataBuffer;
}
