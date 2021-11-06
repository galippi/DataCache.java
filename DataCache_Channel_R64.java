package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_R64 extends DataCache_ChannelBase
{
    public DataCache_Channel_R64(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
        dataBuffer = new double[ch.getLength()];
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
        dataBuffer[idx] = ch.getValueDouble();
    }

    DiaDat_ChannelBase ch;
    double[] dataBuffer;
}
