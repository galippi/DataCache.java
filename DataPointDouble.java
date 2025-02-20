package dataCache;

public class DataPointDouble extends DataPointBase {

    DataPointDouble(double _t, double _val) {
        t = _t;
        val = _val;
        //pointIdx = ???;
    }

    double val;

    @Override
    public int getInt() {
        // TODO Auto-generated method stub
        throw new Error("Not yet implemented");
        //return 0;
    }

    @Override
    public double getDouble() {
        return val;
    }
}
