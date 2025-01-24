package dataCache;

import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JDialog;

import dataVisualizer.DataSourceCanConfigDialog;
import dataVisualizer.DataVisualizerLayoutFileLoader;
import lippiWare.blfHandler.CanMessage;
import lippiWare.utils.dbg;
import measData.dbc.DbcFile;
import measData.dbc.DbcMessage;
import measData.dbc.DbcSignal;

public abstract class DataCache_FileCan extends DataCache_FileBase {

    @Override
    public boolean setLayout(Object o) {
        dvlf = (DataVisualizerLayoutFileLoader)o;
        return true; // layout info is used by CAN data file
    }

    @Override
    public JDialog getDataSourceConfigDlg(Object o) {
        return new DataSourceCanConfigDialog(o);
    }

    void channelsUpdate(double[] timeVal, TreeMap<Long, Vector<CanMessage>> messages) {
        if (dvlf == null)
            dvlf = new DataVisualizerLayoutFileLoader(this.getName());
        channelsInit();
        add(new DataCache_Channel_Double(this, "time", timeVal));
        int signalMode = dvlf.getDbcSignalMode();
        if ((signalMode == dvlf.SignalModeRaw) || (signalMode == dvlf.SignalModeBoth)) {
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
        }
        if (signalMode != dvlf.SignalModeRaw) {
            TreeMap<Integer, Vector<String>> dbcMap = dvlf.getDbcNames();
            for (Map.Entry<Long, Vector<CanMessage>> entry : messages.entrySet()) {
                Vector<CanMessage> ms = entry.getValue();
                long key = entry.getKey();
                long msgId = key & 0x9FFFFFFFl;
                int chId = (int)((key >> 32) & 0xFF);
                boolean found = false;
                Vector<String> dbcList = dbcMap.get(chId);
                if (dbcList != null) {
                    for(int i = 0; i < dbcList.size(); i++) {
                        String dbcName = dbcList.get(i);
                        try {
                            DbcFile dbc = dvlf.getDbcFile(chId, i);
                            if (dbc == null)
                                dbg.println(9, "Error: unable to get dbc " + dbcName);
                            else {
                                DbcMessage msg = dbc.get(msgId);
                                for (Map.Entry<String, DbcSignal> signalEntry : msg.entrySet())
                                {
                                    DbcSignal signal = signalEntry.getValue();
                                    DataCache_ChannelBase ch = new DataCache_Channel_CAN(this, signal, ms);
                                    add(ch);
                                    found = true;
                                }
                            }
                        } catch (Exception e) {
                            dbg.println(2, "Error loading dbcName! e=" + e.toString());
                        }
                    }
                }
                if ((!found) && (signalMode == dvlf.SignalModeLogical)) {
                    int dlc = ms.get(0).getDlc();
                    for (int i = 0; i < dlc; i++) {
                        //CanMessageHandler cmh = new CanMessageHandler(ms, i);
                        String chName = "Ch" + chId + "_Id" + Long.toHexString(msgId) + "_b" + i;
                        DataCache_ChannelBase ch = new DataCache_Channel_U8_CAN(this, chName, ms, i);
                        add(ch);
                    }
                }
            }
        }
    }

    @Override
    public int getChannelNumber() {
        if (dvlf == null)
            throw new Error("Missing implementation!");
        return channels.size();
    }

    @Override
    public DataCache_ChannelBase getChannel(int i) {
        if (dvlf == null)
            throw new Error("Missing implementation!");
        return channels.get(i);
    }

    @Override
    public void updateChannelList(DataVisualizerLayoutFileLoader dvlf) {
        channelsUpdate(timeVal, messages);
    }

    DataVisualizerLayoutFileLoader dvlf;
    TreeMap<Long, Vector<CanMessage>> messages;
    double[] timeVal;
}
