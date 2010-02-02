package vision.srepo.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ERVJO
 * Date: 2010-jan-31
 * Time: 10:59:52
 */
public class FileEntry {
  private String name;
  private List<FileEntry> children;


  public void build(File thisFile) {
    name = thisFile.getName();
    System.out.println("FileEntry.build : " + thisFile.getAbsolutePath());
    if (thisFile.isDirectory()) {
      final File[] files = thisFile.listFiles();
      ArrayList<FileEntry> tmpList = new ArrayList(files.length);
      for (File file : files) {
        FileEntry fileEntry = new FileEntry();

        fileEntry.build(file);
        tmpList.add(fileEntry);
      }
      tmpList.trimToSize();
      children = tmpList;
    } else {
      children = Collections.EMPTY_LIST;
    }
  }

  public void print(int indent) {

    for (int m = 0; m < indent; m++) {
      System.out.print("  ");
    }
    System.out.println(name);
    for (FileEntry child : children) {
      child.print(indent + 1);
    }
  }
}
