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

    public int getRawMin() throws Exception {
        throw new Exception("DataCache_ChannelBase.getRawMin - not supported operation!");
    }
    public int getRawMax() throws Exception {
        throw new Exception("DataCache_ChannelBase.getRawMax - not supported operation!");
    }
    public double getDoubleMin() throws Exception {
        throw new Exception("DataCache_ChannelBase.getDoubleMin - not supported operation!");
    }
    public double getDoubleMax() throws Exception {
        throw new Exception("DataCache_ChannelBase.getDoubleMax - not supported operation!");
    }

    public int getRawPartialMin(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getRawMin - not supported operation!");
        return getRawMin();
    }
    public int getRawPartialMax(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getRawMax - not supported operation!");
        return getRawMax();
    }
    public double getDoublePartialMin(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getDoubleMin - not supported operation!");
        return getDoubleMin();
    }
    public double getDoublePartialMax(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getDoubleMax - not supported operation!");
        return getDoubleMax();
    }
}
