package vision.srepo;

import vision.srepo.filesystem.FileSystem;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:49:55
 * <p/>
 * This will do:
 * Clone/Synch an repo from source. All files must be in local file system
 * <p/>
 * 1. Read Repo status from last time it was synched -> pull
 * 2. Create a comple tree of how source looks like right now
 * 3. Take in updates from source not affected by local changes : Copy in files from tree diff, delete deleted
 * 4. *Push updates from this repo to source -> push
 * 5. Store new repo status for coming pulls
 */
public class SRepo {
  private FileSystem fileSystem;

  public SRepo(String sourcePath) {

    FileSystem sourceFileSystem = new FileSystem(sourcePath);

    sourceFileSystem.build();

    sourceFileSystem.print();

  }


  public static void main(String args[]) {
    String path = "C:\\extra\\3pps\\apache-ant-1.7.1";

    SRepo sRepo = new SRepo(path);

  }
}
