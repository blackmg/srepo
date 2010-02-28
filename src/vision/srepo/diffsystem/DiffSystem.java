package vision.srepo.diffsystem;

import vision.srepo.filesystem.DirEntry;
import vision.srepo.filesystem.FileSystem;

import java.io.IOException;

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

        rootDiffEntry = new rootDiffEntry(sourceFileSystem.getRootFileEntry(),
                targetFileSystem.getRootFileEntry());

            rootFileEntry.build(path);
    }

}
