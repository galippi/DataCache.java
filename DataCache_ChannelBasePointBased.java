package dataCache;

public abstract class DataCache_ChannelBasePointBased extends DataCache_ChannelBase {

    DataCache_ChannelBasePointBased(DataCache_FileBase _parent) {
        super(_parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isTimeBasedChannel() {
        return false;
    }
}
