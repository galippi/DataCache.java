package dataCache;

public class DataCache_PointIndex extends DataCache_ChannelBase
{
    public DataCache_PointIndex(DataCache_FileBase _parent, String _chName)
    {
        super(_parent);
        chName = _chName;
    }

    @Override
    public int get(int idx)
    {
        return idx;
    }

    @Override
    public double getDouble(int idx)
    {
        return idx;
    }

    @Override
    public String getName() {
        return chName;
    }

    String chName;

    @Override
    protected void set(int i) throws Exception {
        throw new Exception("DataCache_PointIndex.set - not yet implemented!");
    }
}
