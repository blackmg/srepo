package vision.srepo.basicsystem;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-mar-08
 * Time: 14:37:44
 * To change this template use File | Settings | File Templates.
 */
public interface RepoPath extends Iterable<String> {

    int getDepth();

    String getName();

    @Override
    String toString();

    Collection<String> getNames();
}
