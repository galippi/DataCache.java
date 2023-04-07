package dataCache;

import javax.swing.JDialog;

import dataVisualizer.DataSourceCanConfigDialog;
import dataVisualizer.DataVisualizerLayoutFileLoader;

public abstract class DataCache_FileCan extends DataCache_FileBase {

    @Override
    public boolean setLayout(Object o) {
        DataVisualizerLayoutFileLoader dvlf = (DataVisualizerLayoutFileLoader)o;
        return true; // layout info is used by CAN data file
    }

    @Override
    public JDialog getDataSourceConfigDlg(Object o) {
        return new DataSourceCanConfigDialog(o);
    }
}
