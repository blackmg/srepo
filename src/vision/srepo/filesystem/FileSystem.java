package vision.srepo.filesystem;

import vision.srepo.basicsystem.BasicEntry;
import vision.srepo.basicsystem.BasicSystem;
import vision.srepo.basicsystem.RepoPath;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:57:31
 */
public class FileSystem extends BasicSystem<FileEntry> {
    private DirEntry rootFileEntry;
    private final Path path;
    private java.nio.file.FileSystem fileSystem;

    public final static List<BasicEntry> EMPTY_ENTRY_LIST = new ArrayList<BasicEntry>(0);
    public final static List<BasicFileEntry> EMPTY_FILE_ENTRY_LIST = new ArrayList<BasicFileEntry>(0);

    public FileSystem(String rootFilePath) {
        fileSystem = FileSystems.getDefault();
        path = fileSystem.getPath(rootFilePath);
    }

    public java.nio.file.FileSystem getFileSystem() {
        return fileSystem;
    }

    public Path getRootPath() {
        return path;
    }

    public void build() {
        rootFileEntry = new DirEntry(path);

        setRootBasicEntry(rootFileEntry);

        try {
            rootFileEntry.build(path, this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public DirEntry getRootFileEntry() {
        return rootFileEntry;
    }

    public Path getPath(RepoPath repoPath) {
        final FileEntry fileEntry = resolve(repoPath);
        if (fileEntry == null) {
            return null;
        }
        return fileEntry.getPath();
    }
}
