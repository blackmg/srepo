package vision.srepo.basicsystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * User: ervjo
 * Date: 2010-mar-11
 * Time: 16:11:11
 * To change this template use File | Settings | File Templates.
 */
public class RepoPathParent implements RepoPath {

    private RepoPathParent parent;
    private String name;
    private int depth = 0;

    public RepoPathParent(RepoPathParent parent, String name) {
        this.parent = parent;
        this.name = name;
        depth = parent.getDepth() + 1;
    }

    public RepoPathParent() {
        this.name = "";
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> getNames() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(name);
        RepoPathParent tmp = parent;
        while (tmp != null) {
            tmp.getName();
            tmp = tmp.parent;
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Iterator<String> iterator() {
        return getNames().iterator();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        final Collection<String> strings = getNames();
        for (Iterator<String> iterator = strings.iterator(); iterator.hasNext();) {
            String s = iterator.next();
            builder.append(s);
            if (!iterator.hasNext()) {
                builder.append('/');
            }
        }
        return builder.toString();
    }
}
