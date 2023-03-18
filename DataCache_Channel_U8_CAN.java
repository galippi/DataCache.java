package dataCache;

import java.util.Vector;

import lippiWare.blfHandler.CanMessage;

public class DataCache_Channel_U8_CAN extends DataCache_ChannelBasePointBased {
    public DataCache_Channel_U8_CAN(DataCache_FileBase _parent,
                                    String _chName,
                                    Vector<CanMessage> ms,
                                    int byteIdx) {
        super(_parent);
        chName = _chName;
        dataBuffer = new byte[ms.size()];
        for(int i = 0; i < dataBuffer.length; i++) {
            dataBuffer[i] = ms.get(i).get(byteIdx);
        }
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

    String chName;
    byte[] dataBuffer;
}
