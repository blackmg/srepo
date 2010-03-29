package vision.srepo;

import vision.srepo.diffsystem.DiffSystem;
import vision.srepo.filesystem.FileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

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

    private java.nio.file.FileSystem repoFileSystem;
    private Path rootPath;
    private Path sRepoPath;

    private long id;

    public SRepo(String repoPath) {
        repoFileSystem = FileSystems.getDefault();
        rootPath = repoFileSystem.getPath(repoPath);
        sRepoPath = makeSureExists(rootPath, ".srepo");
        sRepoPath = makeSureExists(rootPath, ".srepo");

    }

    public static Path makeSureExists(Path parent, String name) {
        Path child = parent.resolve(name);
        if (!child.exists()) {
            try {
                child.createDirectory();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return child;
    }

    public SRepo(String serverPath, String clientPath) {

        System.out.println("Server in " + serverPath);
        System.out.println("-------------------------------");
        FileSystem serverFileSystem = new FileSystem(serverPath);

        serverFileSystem.build();

        serverFileSystem.print();

        System.out.println("Client in " + serverPath);
        System.out.println("-------------------------------");

        File clientRoot = new File(clientPath);
        if (!clientRoot.exists()) {
            clientRoot.mkdir();
        }

        FileSystem clientFileSystem = new FileSystem(clientPath);

        clientFileSystem.build();

        clientFileSystem.print();

        System.out.println("DiffSystem in " + serverPath);
        System.out.println("-------------------------------");


        DiffSystem diffSystem = new DiffSystem(serverFileSystem, clientFileSystem);

        diffSystem.process();

        diffSystem.print();
    }


    public static void main(String args[]) {
        String serverPath = "testfiles\\serverrepo";
        String clientPath = "testfiles\\clientrepo";

        SRepo sRepo = new SRepo(serverPath, clientPath);

    }
}
