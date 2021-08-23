package DataCache;

import diaDat.DiaDat_ChannelBase;

public class DataCache_Channel_U8 extends DataCache_ChannelBase
{
    public DataCache_Channel_U8(DataCache_File _parent, String chName)
    {
        super(_parent);
        DiaDat_ChannelBase chTime = parent.getChannel(chName);
        parent.
    }

    @Override
    public int get(int idx)
    {
        parent.getRecord(idx);
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDouble(int idx)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
