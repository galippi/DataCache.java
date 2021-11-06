package dataCache;

abstract public class DataCache_ChannelBase
{
    DataCache_ChannelBase(DataCache_File _parent)
    {
        parent = _parent;
    }
    abstract public int get(int idx) throws Exception;
    abstract public double getDouble(int idx) throws Exception;
    DataCache_File parent;
    public abstract String getName();
    protected abstract void set(int i) throws Exception;

    public int getRaw(int i) throws Exception {
        throw new Exception("DataCache_ChannelBase.getRaw - not supported operation!");
    }
}
