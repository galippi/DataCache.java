package DataCache;

abstract public class DataCache_ChannelBase
{
    DataCache_ChannelBase(DataCache_File _parent)
    {
        parent = _parent;
    }
    abstract public int get(int idx);
    abstract public int getDouble(int idx);
    DataCache_File parent;
}
