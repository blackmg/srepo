package vision.srepo.diffsystem;

import vision.srepo.BasicEntry;
import vision.srepo.filesystem.BasicFileEntry;

/**
 * User: ervjo
 * Date: 2010-feb-28
 * Time: 10:44:36
 * To change this template use File | Settings | File Templates.
 */
public class DiffEntry extends BasicEntry {
    private BasicFileEntry sourceEntry;
    private BasicFileEntry targetEntry;

    public DiffEntry(BasicFileEntry parent, BasicFileEntry sourceEntry, BasicFileEntry targetEntry) {
        super(sourceEntry != null ? sourceEntry.getName() : targetEntry.getName(), parent);
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
