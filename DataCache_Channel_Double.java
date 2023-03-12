package dataCache;

public class DataCache_Channel_Double extends DataCache_ChannelBase {
    public DataCache_Channel_Double(DataCache_FileBase _parent,
            String _chName,
            double[] values) {
        super(_parent);
        chName = _chName;
        dataBuffer = values;
    }

    @Override
    public int get(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    @Override
    public double getDouble(int idx) throws Exception {
        return dataBuffer[idx];
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
        return dataBuffer[0];
    }

    @Override
    public double getDoubleMax() throws Exception {
        return dataBuffer[dataBuffer.length - 1];
    }

    String chName;
    double[] dataBuffer;
}
