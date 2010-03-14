package vision.srepo.diffsystem;

import vision.srepo.basicsystem.RepoPath;
import vision.srepo.filesystem.FileSystem;

import java.io.IOException;
import java.nio.file.Path;

/**
 * User: ervjo
 * Date: 2010-mar-10
 * Time: 17:33:43
 * To change this template use File | Settings | File Templates.
 */
public class MkDirOp extends Operation {

    public MkDirOp(RepoPath repoPath, Type type) {
        super(repoPath, type);
    }

    @Override
    public String getDescription() {
        return "Create dir in " + getRepoPath();
    }

    @Override
    public void execute(FileSystem sourceFileSystem, FileSystem targetFileSystem) {
        final Path path = targetFileSystem.getPath(getRepoPath());
        try {
            path.createDirectory();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
