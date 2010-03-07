package vision.srepo;

import vision.srepo.filesystem.BasicFileEntry;
import vision.srepo.filesystem.FileSystem;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-feb-28
 * Time: 16:41:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicEntry {
    private final String name;
    private final BasicEntry parent;

    public BasicEntry(String name, BasicEntry parent) {
        this.name = name;
        this.parent = parent;
    }

    public BasicEntry getParent() {
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
        for (BasicFileEntry child : getChildren()) {
            child.print(indent + 1);
        }
    }

    public List<BasicFileEntry> getChildren() {
        return FileSystem.EMPTY_ENTRY_LIST;
    }

    public String getName() {
        return name;
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
}
