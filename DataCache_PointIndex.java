package dataCache;

public class DataCache_PointIndex extends DataCache_ChannelBase
{
    public DataCache_PointIndex(DataCache_File _parent, String _chName)
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
    public int getDouble(int idx)
    {
        return get(idx);
    }

    @Override
    public String getName() {
        return chName;
    }

    String chName;
}
