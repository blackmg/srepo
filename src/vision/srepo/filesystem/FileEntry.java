package vision.srepo.filesystem;

import vision.srepo.BasicEntry;

import java.nio.file.attribute.FileTime;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:59:52
 */
public class FileEntry extends Entry {

    public FileEntry(String name, BasicEntry parent, long modified) {
        super(name, parent, modified);
    }

    public FileEntry(String name, BasicEntry parent, FileTime modifiedFileTime) {
        super(name, parent, modifiedFileTime);
    }

    @Override
    public boolean isFile() {
        return true;
    }


    @Override
    public String toPrettyPrint() {
        return "<F> " + getName();
    }

}
