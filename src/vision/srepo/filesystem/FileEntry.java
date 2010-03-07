package vision.srepo.filesystem;

import vision.srepo.BasicEntry;
import vision.srepo.Checksum;

import java.nio.file.attribute.FileTime;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:59:52
 */
public class FileEntry extends BasicFileEntry {
    private final long size;
    private Checksum checksum;

    public FileEntry(String name, BasicEntry parent, long modified, long size) {
        super(name, parent, modified);
        this.size = size;
    }

    public FileEntry(String name, BasicEntry parent, FileTime modifiedFileTime, long size) {
        super(name, parent, modifiedFileTime);
        this.size = size;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public Checksum getChecksum() {
        if (checksum == null) {
            checksum = new Checksum(getPath(), size);
        }
        return checksum;
    }

    @Override
    public String toPrettyPrint() {
        return "<F> " + getName() + " : " + getChecksum();
    }

}
