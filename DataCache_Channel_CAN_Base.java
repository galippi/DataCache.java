package dataCache;

public abstract class DataCache_Channel_CAN_Base extends DataCache_ChannelBasePointBased {

    DataCache_Channel_CAN_Base(DataCache_FileBase _parent) {
        super(_parent);
    }

    @Override
    public int getIdxLess(int ptIdx) {
        Integer result = messages.index.floorKey(Integer.valueOf(ptIdx));
        if (result == null)
            return -1;
        else
            return result.intValue();
    }

    @Override
    public int getIdxGreater(int ptIdx) {
        Integer result = messages.index.ceilingKey(Integer.valueOf(ptIdx));
        if (result == null)
            return -1;
        else
            return result.intValue();
    }

    @Override
    public DataPointBase getPointGlobal(int idx) {
        Integer idxLocal = messages.index.get(Integer.valueOf(idx));
        return getPoint(idxLocal.intValue());
    }

    CANMessageIndexed messages;
}
