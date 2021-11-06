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
        dataBuffer[idx] = ch.getValueRaw();
    }

    DiaDat_ChannelBase ch;
    int[] dataBuffer;
    double factor, offset;
}
