package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_R32 extends DataCache_ChannelBase {
    public DataCache_Channel_R32(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new float[ch.getLength()];
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
    }

    @Override
    public int get(int idx) throws Exception
    {
        throw new Exception("DataCache_Channel_R64.get - not supported operation!");
    }

    @Override
    public double getDouble(int idx) throws Exception
    {
        return dataBuffer[idx];
    }

    @Override
    protected void set(int idx) throws Exception {
        dataBuffer[idx] = (float)ch.getValueDouble();
    }

    DiaDat_ChannelBase ch;
    float[] dataBuffer;
}
