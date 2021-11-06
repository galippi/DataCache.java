package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U8 extends DataCache_ChannelBase
{
    public DataCache_Channel_U8(DataCache_File _parent, String chName)
    {
        super(_parent);
        //DiaDat_ChannelBase chTime = parent.getChannel(chName);
        ch = parent.getRawChannel(chName);
        dataBuffer = new Byte[ch.getLength()];
        factor = ch.getFactor();
        offset = ch.getOffset();
    }

    public DataCache_Channel_U8(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new Byte[ch.getLength()];
        factor = ch.getFactor();
        offset = ch.getOffset();
    }

    @Override
    public int get(int idx)
    {
        //parent.getRecord(idx);
        return (int)(dataBuffer[idx] & 0xFF);
    }

    @Override
    public double getDouble(int idx)
    {
        // TODO Auto-generated method stub
        return get(idx) * factor + offset;
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
        dataBuffer[idx] = (byte)ch.getValueRaw();
    }
    double factor, offset;
}
