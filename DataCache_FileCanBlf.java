package dataCache;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import lippiWare.blfHandler.BlfReader;
import lippiWare.blfHandler.CanMessage;
import lippiWare.utils.FileNameExtension;
import lippiWare.utils.dbg;

public class DataCache_FileCanBlf extends DataCache_FileCan {

    static public boolean fastCheck(String filename) {
        if (FileNameExtension.get(filename).equalsIgnoreCase("blf"))
            return true;
        return false;
    }

    @Override
    public void open(String _filename) {
        filename = _filename;
        dbg.println(9, "DataCache_FileCanBlf.open filename=" + _filename);
        try {
            blf = new BlfReader(_filename);
            TreeMap<Long, Vector<CanMessage>> messages = new TreeMap<>();
            for (int i = 0; i < blf.size(); i++) {
                CanMessage msg = blf.get(i);
                long storeId = msg.getIdRaw() | (((long)msg.getChannel()) << 32);
                Long oStoreId = Long.valueOf(storeId);
                Vector<CanMessage> messagesArray = messages.get(oStoreId);
                if (messagesArray == null)
                {
                    messagesArray = new Vector<CanMessage>();
                    messages.put(oStoreId, messagesArray);
                }else
                { // ToDo: size check - to be sure all messages have the same DLC
                    
                }
                messagesArray.add(msg);
            }
            for (Map.Entry<Long, Vector<CanMessage>> entry : messages.entrySet()) {
                long id = entry.getKey();
                Vector<CanMessage> ms = entry.getValue();
                int dlc = ms.get(0).getDlc();
                for (int i = 0; i < dlc; i++) {
                    //CanMessageHandler cmh = new CanMessageHandler(ms, i);
                    String chName = "Ch" + ((id >> 32) & 0xFF) + "_Id" + Long.toHexString(id & 0x9FFFFFFFl) + "_b" + i;
                    DataCache_ChannelBase ch = new DataCache_Channel_U8_CAN(this, chName, ms, i);
                    add(ch);
                }
            }
            state = DataCache_State.DataCache_Ready;
            executeActionListener();
        } catch (Exception e) {
            e.printStackTrace();
            dbg.println(1, "BlfReader exception e=" + e.toString());
        }
        dbg.println(19, "DataCache_FileCanBlf.open done");
    }

    @Override
    public int getLength() throws Exception {
        return blf.size();
    }

    BlfReader blf;
}