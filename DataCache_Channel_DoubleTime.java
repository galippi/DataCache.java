package dataCache;

public class DataCache_Channel_DoubleTime extends DataCache_Channel_Double {
    public DataCache_Channel_DoubleTime(DataCache_FileBase _parent, String _chName, double[] values) {
        super(_parent, _chName, values);
    }

    @Override
    public boolean isStrictMonotonic() {
        return true;
    }

    public double getDoubleLocal(int idx) throws Exception {
        return dataBuffer[idx];
    }
}
