package vision.srepo.filesystem;

import vision.srepo.BasicEntry;

import java.nio.file.attribute.FileTime;

/**
 * Created by IntelliJ IDEA.
 * User: ervjo
 * Date: 2010-feb-13
 * Time: 11:48:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entry extends BasicEntry {
    private final long modified;  //millisecs

    public Entry(String name, long modified) {
        super(name);
        this.modified = modified;
    }

    public Entry(String name, FileTime modifiedFileTime) {
        this(name, modifiedFileTime.toMillis());
    }

    public abstract boolean isFile();

    public boolean isDirectory() {
        return !isFile();
    }

    public long getModified() {
        return modified;
    }

}
