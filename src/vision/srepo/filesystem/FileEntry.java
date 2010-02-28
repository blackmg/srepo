package vision.srepo.filesystem;

import java.nio.file.attribute.FileTime;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:59:52
 */
public class FileEntry extends Entry {


    public FileEntry(String name, long modified) {
        super(name, modified);
    }

    public FileEntry(String name, FileTime modifiedFileTime) {
        super(name, modifiedFileTime);
    }

    @Override
    public String toPrettyPrint() {
        return "<F> " + getName();
    }

}
