package vision.srepo.basicsystem;

import vision.srepo.Checksum;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-feb-28
 * Time: 16:41:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicEntry<E extends BasicEntry> {
    private final String name;
    private final E parent;
    private final RepoPathParent repoPath;

    public BasicEntry(String name, E parent) {
        this.name = name;
        this.parent = parent;

        if (parent == null) {
            repoPath = new RepoPathParent();
        } else {
            repoPath = new RepoPathParent(parent.getRepoPath(), name);
        }
    }

    public E getParent() {
        return parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public void print(int indent) {

        for (int m = 0; m < indent; m++) {
            System.out.print("  ");
        }
        System.out.println(toPrettyPrint());
        for (BasicEntry child : getChildren()) {
            child.print(indent + 1);
        }
    }

    public List<E> getChildren() {
        return Collections.EMPTY_LIST;
    }

    public String getName() {
        return name;
    }

    public RepoPathParent getRepoPath() {
        return repoPath;
    }

    public abstract boolean isFile();


    public void added(BasicSystem basicSystem) {
        if (isFile()) {
            basicSystem.fileAdded(this);
        }
    }

    public void removed(BasicSystem basicSystem) {
        if (isFile()) {
            basicSystem.fileRemoved(this);
        }
    }

    public Checksum getChecksum() {
        return null;
    }

    public abstract String toPrettyPrint();

    public E getChild(String name) {
        final List<E> entries = getChildren();
        for (E entry : entries) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }
}
