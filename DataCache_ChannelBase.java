package dataCache;

abstract public class DataCache_ChannelBase
{
    DataCache_ChannelBase(DataCache_FileBase _parent)
    {
        parent = _parent;
    }

    /** Get measured value in integer format
     *  @param idx point index of complete measurement (e.g. index of all CAN messages, but this message may not contain valid data
     *    in this message - e.g. message on different bus or message with different ID)
     * @return
     * @throws Exception
    */
    public int getGlobal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    /** Get measured value in integer format
     * @param idx point index of this set of points (e.g. CAN messages on same bus with same ID)
     * @return
     * @throws Exception
     */
    public int getLocal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    /**
     * Point getter
     * @param idx - index of point in the series
     * @return - selected point
     * @throws Exception
     */
    @Deprecated
    public int get(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    /** Get measured value in double (R64) format
     * @param idx point index of complete measurement (e.g. index of all CAN messages, but this message may not contain valid data
     *  in this message - e.g. message on different bus or message with different ID)
     * @return
     * @throws Exception
     */
    public double getDoubleGlobal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    /** Get measured value in double (R64) format
     * @param idx point index of this set of points (e.g. CAN messages on same bus with same ID)
     * @return
     * @throws Exception
     */
    public double getDoubleLocal(int idx) throws Exception {
        throw new Error("Not yet implemented!");
    }

    /** Get measured value in double (R64) format
     * Deprecated - do not use it
     * @param idx point index of this set of points (e.g. CAN messages on same bus with same ID)
     * @return
     * @throws Exception
     */
    @Deprecated
    public double getDouble(int idx) throws Exception {
        Long x = new Long(11);
        throw new Error("Not yet implemented!");
    }

    DataCache_FileBase parent;
    public abstract String getName();
    protected abstract void set(int i) throws Exception;

    public int getRaw(int i) throws Exception {
        throw new Exception("DataCache_ChannelBase.getRaw - not supported operation!");
    }

    public int getRawMin() throws Exception {
        throw new Exception("DataCache_ChannelBase.getRawMin - not supported operation!");
    }
    public int getRawMax() throws Exception {
        throw new Exception("DataCache_ChannelBase.getRawMax - not supported operation!");
    }
    public double getDoubleMin() throws Exception {
        throw new Exception("DataCache_ChannelBase.getDoubleMin - not supported operation!");
    }
    public double getDoubleMax() throws Exception {
        throw new Exception("DataCache_ChannelBase.getDoubleMax - not supported operation!");
    }

    public int getRawPartialMin(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getRawMin - not supported operation!");
        return getRawMin();
    }
    public int getRawPartialMax(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getRawMax - not supported operation!");
        return getRawMax();
    }
    public double getDoublePartialMin(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getDoubleMin - not supported operation!");
        return getDoubleMin();
    }
    public double getDoublePartialMax(int idxLow, int idxHigh) throws Exception {
        //throw new Exception("DataCache_ChannelBase.getDoubleMax - not supported operation!");
        return getDoubleMax();
    }
    public String getUnit() {
        return "";
    }
    public boolean isTimeBasedChannel() {
        return true;
    }
    public abstract int getPointIdx(double val);
}
