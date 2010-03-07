package vision.srepo.filesystem;

import vision.srepo.BasicEntry;

import java.nio.file.Path;
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

    public Entry(String name, BasicEntry parent, long modified) {
        super(name, parent);
        this.modified = modified;
    }

    public Entry(String name, BasicEntry parent, FileTime modifiedFileTime) {
        this(name, parent, modifiedFileTime.toMillis());
    }

    public abstract boolean isFile();

    public Entry getParentEntry() {
        return (Entry) getParent();
    }

    public Path getPath() {
        if (isRoot()) {
            return getFileSystem().getRootPath();
        }
        final Path parentPath = getParentEntry().getPath();
        final Path path = parentPath.resolve(getName());
        return path;
    }

    public FileSystem getFileSystem() {
        return getParentEntry().getFileSystem();
    }

    public boolean isDirectory() {
        return !isFile();
    }

    public long getModified() {
        return modified;
    }

}
