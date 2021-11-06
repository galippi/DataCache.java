package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U16 extends DataCache_ChannelBase {

    public DataCache_Channel_U16(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new Byte[ch.getLength() * 2];
        factor = ch.getFactor();
        offset = ch.getOffset();
    }

    @Override
    public int get(int idx)
    {
        //parent.getRecord(idx);
        return   (int)(dataBuffer[idx * 2    ] & 0xFF) + 
               (((int)(dataBuffer[idx * 2 + 1] & 0xFF)) * 256);
    }

    @Override
    public double getDouble(int idx)
    {
        // TODO Auto-generated method stub
        return get(idx) * factor + offset;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
    }

    @Override
    protected void set(int idx) throws Exception {
        int val = ch.getValueRaw();
        dataBuffer[idx * 2] = (byte)(val % 256);
        dataBuffer[idx * 2 + 1] = (byte)(val / 256);
    }

    DiaDat_ChannelBase ch;
    Byte[] dataBuffer;
    double factor, offset;
}
