package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U16 extends DataCache_ChannelBaseInt {

    public DataCache_Channel_U16(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent, chBase);
        dataBuffer = new Byte[ch.getLength() * 2];
    }

    @Override
    public int get(int idx)
    {
        //parent.getRecord(idx);
        return   (int)(dataBuffer[idx * 2    ] & 0xFF) + 
               (((int)(dataBuffer[idx * 2 + 1] & 0xFF)) * 256);
    }

    @Override
    protected void set(int idx) throws Exception {
        int val = ch.getValueRaw();
        if (val < rawMin)
            rawMin = val;
        if (val > rawMax)
            rawMax = val;
        dataBuffer[idx * 2] = (byte)(val % 256);
        dataBuffer[idx * 2 + 1] = (byte)(val / 256);
    }

    Byte[] dataBuffer;
}
