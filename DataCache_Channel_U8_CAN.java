package dataCache;

import java.util.Vector;

import lippiWare.blfHandler.CanMessage;

public class DataCache_Channel_U8_CAN extends DataCache_ChannelBasePointBased {
    public DataCache_Channel_U8_CAN(DataCache_FileBase _parent,
                                    String _chName,
                                    Vector<CanMessage> ms,
                                    int _byteIdx) {
        super(_parent);
        chName = _chName;
        messages = ms;
        byteIdx = _byteIdx;
    }

    @Override
    public int get(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDouble(int idx) throws Exception {
        //throw new Error("Not yet implemented!");
        // TODO: 
        return idx % 256;
    }

    @Override
    public String getName() {
        return chName;
    }

    @Override
    protected void set(int i) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDoubleMin() throws Exception {
        return 0;
    }

    @Override
    public double getDoubleMax() throws Exception {
        return 255;
    }

    @Override
    public DataPointBase getPoint(int pointIdx) {
        DataPointBase dpb = new DataPointU8Base(pointIdx, messages.get(pointIdx), byteIdx);
        return dpb;
    }

    @Override
    public int getPointIdx(double t) {
        int leftIdx = 0, rightIdx = messages.size() - 1;
        CanMessage msgLeft = messages.get(leftIdx);
        if (t < msgLeft.getTime())
            return -1;
        CanMessage msgRight = messages.get(rightIdx);
        if (t > msgRight.getTime())
            return rightIdx;
        while (leftIdx != rightIdx) {
            int middleIdx = (leftIdx + rightIdx) / 2;
            CanMessage msgMiddle = messages.get(middleIdx);
            if (t < msgMiddle.getTime())
                rightIdx = middleIdx;
            else
                leftIdx = middleIdx;
        }
        return leftIdx;
    }

    @Override
    public DataPointBase getPoint(double t) {
        int idx = getPointIdx(t);
        if (t >= 0)
            return getPoint(idx);
        return null;
    }

    String chName;
    Vector<CanMessage> messages;
    int byteIdx;
}
