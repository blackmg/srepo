package vision.srepo.filesystem;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:57:31
 */
public class FileSystem {
  private File rootFile;
  private FileEntry rootFileEntry;

  public FileSystem(File rootFile) {
    this.rootFile = rootFile;
  }

  public void build() {
    rootFileEntry = new FileEntry();
    rootFileEntry.build(rootFile);
  }

  public void print() {
    rootFileEntry.print(0);
  }

}
