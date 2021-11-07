package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U8_PureInt extends DataCache_ChannelBasePureInt {

    DataCache_Channel_U8_PureInt(DataCache_File _parent, DiaDat_ChannelBase chBase) {
        super(_parent, chBase);
        dataBuffer = new Byte[ch.getLength()];
    }

    @Override
    public int get(int idx)
    {
        return (int)(dataBuffer[idx] & 0xFF);
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
