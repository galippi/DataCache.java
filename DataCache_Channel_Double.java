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

    @Override
    public int getPointIdx(double val) {
        // this works only for ordered (e.g. time) values
        if (val <= dataBuffer[0])
            return 0;
        int leftIdx = 0;
        int rightIdx = dataBuffer.length - 1;
        if (val >= dataBuffer[rightIdx])
            return rightIdx;
        while(true) {
            int middleIdx = leftIdx + (rightIdx - leftIdx) / 2;
            if (val <= dataBuffer[middleIdx])
                if (middleIdx == rightIdx)
                    return middleIdx;
                else
                    rightIdx = middleIdx;
            else
                if (middleIdx == leftIdx)
                    return middleIdx;
                else
                    leftIdx = middleIdx;
        }
    }

    String chName;
    double[] dataBuffer;
}
