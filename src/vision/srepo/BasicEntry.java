package vision.srepo;

import vision.srepo.filesystem.Entry;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-feb-28
 * Time: 16:41:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicEntry {
    protected final String name;

    public BasicEntry(String name) {
        this.name = name;
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

    public abstract String toPrettyPrint();
}
