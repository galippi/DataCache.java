package dataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_R64 extends DataCache_ChannelBase
{
    public DataCache_Channel_R64(DataCache_File _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent);
        ch =chBase;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return ch.getName();
    }

    @Override
    public int get(int idx)
    {
        parent.getRecord(idx);
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getDouble(int idx) throws Exception
    {
        throw new Exception("DataCache_Channel_R64.get - not yet implemented!");
    }

    @Override
    protected void set(int i) throws Exception {
        throw new Exception("DataCache_Channel_R64.set - not yet implemented!");
    }

    DiaDat_ChannelBase ch;
}
