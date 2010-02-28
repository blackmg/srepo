package vision.srepo.filesystem;

import java.nio.file.attribute.FileTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ervjo
 * Date: 2010-feb-13
 * Time: 11:48:30
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entry {
    private final String name;
    private final long modified;  //millisecs

    public Entry(String name, long modified) {
        this.name = name;
        this.modified = modified;
    }

    public Entry(String name, FileTime modifiedFileTime) {
        this(name, modifiedFileTime.toMillis());
    }

    public void print(int indent) {

        for (int m = 0; m < indent; m++) {
            System.out.print("  ");
        }
        System.out.println(toPrettyPrint());
        for (Entry child : getChildren()) {
            child.print(indent + 1);
        }
    }

    public List<Entry> getChildren() {
        return Collections.EMPTY_LIST;
    }

    public String getName() {
        return name;
    }

    public long getModified() {
        return modified;
    }

    public abstract String toPrettyPrint();
}
