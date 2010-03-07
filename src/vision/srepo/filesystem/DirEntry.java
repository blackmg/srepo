package vision.srepo.filesystem;

import vision.srepo.BasicEntry;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.attribute.Attributes;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ervjo
 * Date: 2010-feb-13
 * Time: 11:51:51
 * To change this template use File | Settings | File Templates.
 */
public class DirEntry extends Entry {
    private List<FileEntry> files;
    private List<DirEntry> dirs;
    private FileSystem fileSystem;

    public DirEntry(String name, BasicEntry parent, long modified) {
        super(name, parent, modified);
    }

    public DirEntry(String name, BasicEntry parent, FileTime modifiedFileTime) {
        super(name, parent, modifiedFileTime);
    }

    public DirEntry(Path path) {
        super(path.getName().toString(), null, 0);
    }

    private class DirInfo {
        Path path;
        BasicFileAttributes attributes;

        private DirInfo(Path path, BasicFileAttributes attributes) {
            this.path = path;
            this.attributes = attributes;
        }
    }

    @Override
    public List<Entry> getChildren() {
        List<Entry> ret = new ArrayList<Entry>();
        if (files != null) {
            ret.addAll(files);
        }
        if (dirs != null) {
            ret.addAll(dirs);
        }
        return ret;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public String toPrettyPrint() {
        return "<D> " + getName() + " -> " + getPath().toAbsolutePath();
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public void build(Path path, FileSystem fileSystem) throws IOException {
        this.fileSystem = fileSystem;

        DirectoryStream<Path> directoryStream = path.newDirectoryStream();

        List<DirInfo> dirsFound = new ArrayList<DirInfo>();

        try {
            for (Path childPath : directoryStream) {
                BasicFileAttributes attributes = Attributes.readBasicFileAttributes(childPath);

                if (attributes.isOther() || attributes.isSymbolicLink()) {
                    // skip all strange stuff
                    continue;
                }

                if (attributes.isDirectory()) {
                    dirsFound.add(new DirInfo(childPath, attributes));
                } else {
                    if (files == null) {
                        files = new ArrayList<FileEntry>();
                    }
                    final FileTime modifiedAttribute = attributes.lastModifiedTime();
                    final String name = childPath.getName().toString();

                    FileEntry fileEntry = new FileEntry(name, this, modifiedAttribute);
                    files.add(fileEntry);
                    fileEntry.added(fileSystem);
                }
            }
        } finally {
            directoryStream.close();
        }

        if (!dirsFound.isEmpty()) {
            dirs = new ArrayList<DirEntry>(dirsFound.size());
            for (DirInfo dirInfo : dirsFound) {
                final String name = dirInfo.path.getName().toString();

                DirEntry dirEntry = new DirEntry(name, this, dirInfo.attributes.lastModifiedTime());
                dirEntry.build(dirInfo.path, fileSystem);
                dirs.add(dirEntry);
            }
        }
        /*
        System.out.println("FileEntry.build : " + thisFile.getAbsolutePath());


        if (thisFile.isDirectory()) {
            Path dir = thisFile.toPath();

            DirectoryStream<Path> directoryStream = dir.newDirectoryStream();
            try {
                for (Path entry : directoryStream) {
                    ;
                }
            } finally {
                directoryStream.close();
            }

            final File[] files = thisFile.listFiles();
            ArrayList<FileEntry> tmpList = new ArrayList(files.length);
            for (File file : files) {
                FileEntry fileEntry = new FileEntry();

                fileEntry.build(file);
                tmpList.add(fileEntry);
            }
            tmpList.trimToSize();
            files = tmpList;
        } else {
            files = Collections.EMPTY_LIST;
        }
        */
    }

    /*
    Path dir = ...
  *   DirectoryStream&lt;Path&gt; stream = dir.newDirectoryStream();
  *   try {
  *       for (Path entry: stream) {
  *         ..
  *       }
  *   } finally {
  *       stream.close();
  *   }
    */

}
