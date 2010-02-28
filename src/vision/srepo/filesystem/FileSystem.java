package vision.srepo.filesystem;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:57:31
 */
public class FileSystem {
    private DirEntry rootFileEntry;
    private final Path path;

    public FileSystem(String rootFilePath) {
        final java.nio.file.FileSystem fileSystem = FileSystems.getDefault();
        path = fileSystem.getPath(rootFilePath);
    }

    public void build() {
        rootFileEntry = new DirEntry(path);

        // rootFileEntry.build(rootFile);
        try {
            rootFileEntry.build(path);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public DirEntry getRootFileEntry() {
        return rootFileEntry;
    }

    public void print() {
        rootFileEntry.print(0);
    }

}
