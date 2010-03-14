package vision.srepo.basicsystem;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: ervjo
 * Date: 2010-mar-11
 * Time: 16:11:40
 * To change this template use File | Settings | File Templates.
 */
public class RepoPathComplete implements RepoPath {
    private List<String> paths;
    private String path;

    public RepoPathComplete(String path) {
        this.path = path;
    }

    @Override
    public int getDepth() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public Collection<String> getNames() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterator iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
