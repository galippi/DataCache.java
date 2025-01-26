package dataCache;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import dataVisualizer.DataVisualizer;

import lippiWare.blfHandler.CanLogReader;
import lippiWare.blfHandler.CanMessage;
import lippiWare.utils.BusyDialog;
import lippiWare.utils.FileNameExtension;
import lippiWare.utils.dbg;

class CANMessageIndexed {
    public void add(CanMessage msg, int idx) {
        index.put(Integer.valueOf(idx), Integer.valueOf(messages.size()));
        messages.add(msg);
    }

    /**
     * @param idx - point index of complete measurement (e.g. index of all CAN messages, but this message may not contain
     *  valid data in this message - e.g. message on different bus or message with different ID)
     * @return globally indexed message, if it's in this set
     * @throws Exception if the message is not for this set (different ID or different CAN channel)
     */
    public CanMessage getGlobal(int idx) throws Exception {
        return messages.get(index.get(Integer.valueOf(idx)));
    }

    /**
     * Give back the indexed message
     * @param idx point index of this set of points (CAN messages on same bus with same ID)
     * @return selected message
     */
    public CanMessage getLocal(int idx) {
        return messages.get(idx);
    }

    /**
     * Give back the number of points of this type of message
     * @return number of points of this type of message
     */
    public int size() {
        return messages.size();
    }

    /**
     * CAN message getter
     * @param idx - local index of CAN message
     * @return - selected CAN message
     */
    public CanMessage get(int idx) {
        return messages.get(idx);
    }

    Vector<CanMessage> messages = new Vector<>();
    TreeMap<Integer, Integer> index = new TreeMap<>();
}

public class DataCache_FileCanBlf extends DataCache_FileCan {

    static public boolean fastCheck(String filename) {
        if (FileNameExtension.get(filename).equalsIgnoreCase("blf"))
            return true;
        if (FileNameExtension.get(filename).equalsIgnoreCase("asc"))
            return true;
        return false;
    }

    @Override
    public void open(String _filename) {
        filename = _filename;
        dbg.println(9, "DataCache_FileCanBlf.open filename=" + _filename);
        BusyDialog bd = new BusyDialog(DataVisualizer.getMainFrame());
        String errorMsg = null;
        try {
            blf = CanLogReader.read(_filename);
            messages = new TreeMap<>();
            timeVal = new double[blf.size()];
            TreeSet<Integer> channelIndexesTreeSet = new TreeSet<>();
            for (int i = 0; i < blf.size(); i++) {
                CanMessage msg = blf.get(i);
                timeVal[i] = msg.getTime();
                long storeId = msg.getIdRaw() | (((long)msg.getChannel()) << 32);
                Long oStoreId = Long.valueOf(storeId);
                CANMessageIndexed messagesArray = messages.get(oStoreId);
                if (messagesArray == null)
                {
                    messagesArray = new CANMessageIndexed();
                    messages.put(oStoreId, messagesArray);
                }else
                { // ToDo: size check - to be sure all messages have the same DLC
                    
                }
                messagesArray.add(msg, i);
                channelIndexesTreeSet.add(Integer.valueOf(msg.getChannel()));
            }

            channelsInit();
            channelsUpdate(timeVal, messages);

            channelIndexes.clear();
            Iterator<Integer> i = channelIndexesTreeSet.iterator();
            while (i.hasNext())
                channelIndexes.add(i.next());

            state = DataCache_State.DataCache_Ready;
        } catch (Exception e) {
            e.printStackTrace();
            dbg.println(1, "BlfReader exception e=" + e.toString());
            state = DataCache_State.DataCache_Error;
            errorMsg = e.toString();
        }
        bd.close();
        dbg.println(19, "DataCache_FileCanBlf.open done");
        executeActionListener(errorMsg);
    }

    @Override
    public int getLength() throws Exception {
        return blf.size();
    }

    public Vector<Integer> getDataSourceChannelIndexArray() {
        return channelIndexes;
    }

    @Override
    public boolean isPointBasedFile() {
        return true;
    }

    Vector<Integer> channelIndexes = new Vector<>();
    CanLogReader blf;
}
