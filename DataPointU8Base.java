package dataCache;

import lippiWare.blfHandler.CanMessage;

public class DataPointU8Base extends DataPointBase {

    public DataPointU8Base(int _pointIdx, CanMessage msg, int byteIdx) {
        pointIdx = _pointIdx;
        t = msg.getTime();
        val = msg.get(byteIdx);
    }


    @Override
    public int getInt() {
        return ((int)val) & 0xFF;
    }

    @Override
    public double getDouble() {
        return ((int)val) & 0xFF;
    }

    byte val;
}
