package vision.srepo.diffsystem;

import vision.srepo.filesystem.FileSystem;

/**
 * User: ervjo
 * Date: 2010-feb-28
 * Time: 10:42:50
 * To change this template use File | Settings | File Templates.
 */
public class DiffSystem {
    private FileSystem baseFileSystem; // TODO: add common ancestor and therefore deleted support 
    private FileSystem sourceFileSystem;
    private FileSystem targetFileSystem;

    private DiffEntry rootDiffEntry;

    public DiffSystem(FileSystem sourceFileSystem, FileSystem targetFileSystem) {
        this.sourceFileSystem = sourceFileSystem;
        this.targetFileSystem = targetFileSystem;

        rootDiffEntry = new DiffEntry(null, sourceFileSystem.getRootFileEntry(),
                targetFileSystem.getRootFileEntry());
    }

    /**
     * Find out what needs to be done in this path
     */
    public void process() {
        rootDiffEntry.process(this);
    }

    /**
     * Do updates (receive / send / delete) files in this path
     */
    public void doUpdates() {
    }

    public void print() {
        rootDiffEntry.print(0);
    }
}
