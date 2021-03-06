package vision.srepo.diffsystem;

import vision.srepo.basicsystem.RepoPath;
import vision.srepo.filesystem.FileSystem;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-mar-08
 * Time: 14:34:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class Operation {

    enum Type {
        PULL, PUSH
    }

    private Type type;
    private RepoPath repoPath;

    public Operation(RepoPath repoPath, Type type) {
        this.repoPath = repoPath;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public RepoPath getRepoPath() {
        return repoPath;
    }

    abstract public String getDescription();

    abstract public void execute(FileSystem sourceFileSystem,
                                 FileSystem targetFileSystem);
}
