package dataCache;

import lippiWare.utils.FileNameExtension;

public class DataCache_FileCanBlf extends DataCache_FileCan {

    static public boolean fastCheck(String filename) {
        if (FileNameExtension.get(filename).equalsIgnoreCase("blf"))
            return true;
        return false;
    }

    @Override
    public void open(String _filename) {
        throw new Error("Not yet implemented!");
    }

    @Override
    public int getLength() throws Exception {
        throw new Error("Not yet implemented!");
        //return 0;
    }

}
