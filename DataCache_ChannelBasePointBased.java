package dataCache;

public abstract class DataCache_ChannelBasePointBased extends DataCache_ChannelBase {

    DataCache_ChannelBasePointBased(DataCache_FileBase _parent) {
        super(_parent);
    }

    @Override
    public boolean isTimeBasedChannel() {
        return false;
    }

    public abstract DataPointBase getPoint(double t);

    public abstract DataPointBase getPoint(int pointIdx);

    public abstract int getPointIdx(double t);

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
}
