package vision.srepo.diffsystem;

import vision.srepo.filesystem.FileSystem;

/**
 * User: ervjo
 * Date: 2010-feb-28
 * Time: 10:42:50
 * To change this template use File | Settings | File Templates.
 */
public class DiffSystem {
    private FileSystem sourceFileSystem;
    private FileSystem targetFileSystem;

    private DiffEntry rootDiffEntry;

    public DiffSystem(FileSystem sourceFileSystem, FileSystem targetFileSystem) {
        this.sourceFileSystem = sourceFileSystem;
        this.targetFileSystem = targetFileSystem;
    }

    public void build() {

        rootDiffEntry = new DiffEntry(null, sourceFileSystem.getRootFileEntry(),
                targetFileSystem.getRootFileEntry());

//        rootDiffEntry.build(path);
    }

    public void print() {
        //To change body of created methods use File | Settings | File Templates.
    }
}
