package vision.srepo.filesystem;

import vision.srepo.BasicEntry;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ervjo
 * Date: 2010-feb-13
 * Time: 11:48:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicFileEntry extends BasicEntry {
    private final long modified;  //millisecs

    public BasicFileEntry(String name, BasicEntry parent, long modified) {
        super(name, parent);
        this.modified = modified;
    }

    public BasicFileEntry(String name, BasicEntry parent, FileTime modifiedFileTime) {
        this(name, parent, modifiedFileTime.toMillis());
    }

    public abstract boolean isFile();

    public BasicFileEntry getParentEntry() {
        return (BasicFileEntry) getParent();
    }

    public List<BasicFileEntry> getFileChildren() {
        return FileSystem.EMPTY_FILE_ENTRY_LIST;
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

    public long getSize() {
        return 0;
    }

}
