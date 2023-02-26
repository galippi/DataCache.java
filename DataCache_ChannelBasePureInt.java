package dataCache;

import diaDat.DiaDat_ChannelBase;

abstract public class DataCache_ChannelBasePureInt extends DataCache_ChannelBaseInt {

    DataCache_ChannelBasePureInt(DataCache_FileBase _parent, DiaDat_ChannelBase chBase)
    {
        super(_parent, chBase);
    }

    @Override
    public double getDouble(int idx) throws Exception {
        return get(idx);
    }

    @Override
    public double getDoubleMin() throws Exception {
        return getRawMin();
    }

    @Override
    public double getDoubleMax() throws Exception {
        return getRawMax();
    }
}
