package dataCache;

public abstract class DataPointBase {
    public int pointIdx;
    public double t;
    public abstract int getInt();
    public abstract double getDouble();
}