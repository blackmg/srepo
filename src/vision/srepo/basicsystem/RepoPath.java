package vision.srepo.basicsystem;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-mar-08
 * Time: 14:37:44
 * To change this template use File | Settings | File Templates.
 */
public class RepoPath {
    private String path;

    public RepoPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
