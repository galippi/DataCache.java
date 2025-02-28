package dataCache;

import lippiWare.blfHandler.CanMessage;
import measData.dbc.DbcSignal;

public class DataCache_Channel_CAN  extends DataCache_Channel_CAN_Base {

    DataCache_Channel_CAN(DataCache_FileBase _parent, DbcSignal _signal, CANMessageIndexed ms) {
        super(_parent);
        signal = _signal;
        messages = ms;
    }

    @Override
    public DataPointBase getPoint(double t) {
        throw new Error("Not yet implemented!");
    }

    long extractRawVal(CanMessage msg) {
        long rawVal = 0;
        int len = signal.bitLen;
        int pos = signal.bitPos;
        int byteIdx = pos / 8;
        pos = pos % 8;
        int shift = 0;
        while (len > 0) {
            int val = msg.get(byteIdx);
            val = val >> pos;
            if (len < 8) {
                val = val & ((1 << len) - 1);
                rawVal = rawVal + (val << shift);
                shift = shift + 8 - pos;
                len = 0;
            }else {
                val = val & 0xFF;
                rawVal = rawVal + (val << shift);
                shift = shift + 8;
                len = len - 8;
            }
            pos = 0;
        }
        return rawVal;
    }

    @Override
    public DataPointBase getPoint(int pointIdx) {
        CanMessage msg = messages.get(pointIdx);
        long rawVal = extractRawVal(msg);
        //if ((rawVal < signal.minRaw) || (rawVal > signal.maxRaw)) - TODO
        //    throw new Error("CAN signal is out of double range!");
        double val = (rawVal * signal.factor) + signal.offset;
        DataPointBase pt = new DataPointDouble(msg.getTime(), val);
        return pt;
    }

    @Override
    public int getPointIdx(double t) {
        int leftIdx = 0, rightIdx = messages.size() - 1;
        CanMessage msgLeft = messages.get(leftIdx);
        if (t < msgLeft.getTime())
            return -1;
        CanMessage msgRight = messages.get(rightIdx);
        if (t >= msgRight.getTime())
            return rightIdx;
        while (leftIdx != rightIdx) {
            int middleIdx = (leftIdx + rightIdx) / 2;
            if (middleIdx == leftIdx)
                return leftIdx;
            CanMessage msgMiddle = messages.get(middleIdx);
            if (t < msgMiddle.getTime())
                rightIdx = middleIdx;
            else
                leftIdx = middleIdx;
        }
        return leftIdx;
    }

    @Override
    public int size() {
        return messages.size();
    }

    @Override
    public int getGlobal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public int getLocal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDoubleGlobal(int idx) throws Exception {
        CanMessage msg = messages.getGlobal(idx);
        long rawVal = extractRawVal(msg);
        if ((rawVal < signal.minScaledRaw) || (rawVal > signal.maxScaledRaw))
            throw new Exception("CAN signal is out of double range!");
        double val = (rawVal * signal.factor) + signal.offset;
        return val;
    }

    @Override
    public double getDoubleGlobal(int idx, double t, double dt) throws Exception {
        CanMessage msg = messages.getGlobal(idx);
        long rawVal = extractRawVal(msg);
        if ((rawVal < signal.minScaledRaw) || (rawVal > signal.maxScaledRaw))
            throw new Exception("CAN signal is out of double range!");
        double val = (rawVal * signal.factor) + signal.offset;
        return val;
    }

    @Override
    public double getDoubleLocal(int idx) throws Exception {
        CanMessage msg = messages.getLocal(idx);
        long rawVal = extractRawVal(msg);
        return (rawVal * signal.factor) + signal.offset;
    }

    @Override
    public String getName() {
        return signal.name;
    }

    @Override
    protected void set(int i) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDoubleMin() throws Exception {
        return signal.min;
    }

    @Override
    public double getDoubleMax() throws Exception {
        return signal.max;
    }

    DbcSignal signal;
}
