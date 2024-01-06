package dataCache;

import java.util.Vector;

import lippiWare.blfHandler.CanMessage;
import measData.dbc.DbcSignal;

public class DataCache_Channel_CAN  extends DataCache_ChannelBasePointBased {

    DataCache_Channel_CAN(DataCache_FileBase _parent, DbcSignal _signal, Vector<CanMessage> ms) {
        super(_parent);
        signal = _signal;
        messages = ms;
    }

    @Override
    public DataPointBase getPoint(double t) {
        throw new Error("Not yet implemented!");
    }

    @Override
    public DataPointBase getPoint(int pointIdx) {
        throw new Error("Not yet implemented!");
    }

    @Override
    public int getPointIdx(double t) {
        throw new Error("Not yet implemented!");
    }

    @Override
    public int size() {
        throw new Error("Not yet implemented!");
    }

    @Override
    public int get(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDouble(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public String getName() {
        return signal.name;
    }

    @Override
    protected void set(int i) throws Exception {
        throw new Error("Not yet implemented!");
    }

    DbcSignal signal;
    Vector<CanMessage> messages;
}
