package vision.srepo.diffsystem;

import vision.srepo.BasicEntry;
import vision.srepo.filesystem.Entry;

/**
 * User: ervjo
 * Date: 2010-feb-28
 * Time: 10:44:36
 * To change this template use File | Settings | File Templates.
 */
public class DiffEntry extends BasicEntry {
    private Entry sourceEntry;
    private Entry targetEntry;

    public DiffEntry(Entry sourceEntry, Entry targetEntry) {
        super(sourceEntry != null ? sourceEntry.getName() : targetEntry.getName());
        this.sourceEntry = sourceEntry;
        this.targetEntry = targetEntry;
    }

    @Override
    public boolean isFile() {
        boolean ret = sourceEntry != null && sourceEntry.isFile();
        ret |= targetEntry != null && targetEntry.isFile();

        return ret;
    }

    @Override
    public String toPrettyPrint() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
