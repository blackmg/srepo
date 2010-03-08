package vision.srepo.basicsystem;

import vision.srepo.Checksum;
import vision.srepo.MultiMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: ervjo
 * Date: 2010-mar-06
 * Time: 11:25:29
 * To change this template use File | Settings | File Templates.
 */
public class BasicSystem<E extends BasicEntry> {
    private BasicEntry rootBasicEntry;

    private MultiMap<String, E> byNameIndex = new MultiMap<String, E>();
    private MultiMap<Checksum, E> byChecksumIndex = new MultiMap<Checksum, E>();


    public void setRootBasicEntry(BasicEntry rootBasicEntry) {
        this.rootBasicEntry = rootBasicEntry;
    }

    public void print() {
        rootBasicEntry.print(0);
        printSameChecksum();
        printSameName();
    }

    public void printSameChecksum() {
        System.out.println("Same CheckSum");
        System.out.println("---------------------");
        final Set<Map.Entry<Checksum, List<E>>> entries = byChecksumIndex.getEntries();
        for (Map.Entry<Checksum, List<E>> entry : entries) {
            final List<E> values = entry.getValue();
            if (values.size() > 1) {
                System.out.println("CheckSum : " + entry.getKey());
                for (E value : values) {
                    System.out.println("  " + value.getName());
                }
            }
        }
    }

    public void printSameName() {
        System.out.println("Same Name");
        System.out.println("---------------------");

        final Set<Map.Entry<String, List<E>>> entries = byNameIndex.getEntries();
        for (Map.Entry<String, List<E>> entry : entries) {
            final List<E> values = entry.getValue();
            if (values.size() > 1) {
                System.out.println("Name : " + entry.getKey());
                for (E value : values) {
                    System.out.println("  " + value.toPrettyPrint());
                }
            }
        }
    }

    public void fileAdded(E entry) {
        if (entry.isFile()) {
            addChecksum(entry, entry.getChecksum());
            byNameIndex.put(entry.getName(), entry);
        }
    }

    private void addChecksum(E entry, final Checksum checksum) {
        if (checksum != null) {
            byChecksumIndex.put(checksum, entry);
        }
    }

    private void removeChecksum(E entry, final Checksum checksum) {
        if (checksum != null) {
            byChecksumIndex.remove(checksum, entry);
        }
    }

    public void checksumUpdate(E entry, Checksum checksum) {
        if (!entry.isFile()) {
            return;
        }
        final Checksum current = entry.getChecksum();
        if (current != null) {
            removeChecksum(entry, checksum);
        }
        addChecksum(entry, checksum);
    }

    public void fileRemoved(E entry) {
        if (entry.isFile()) {
            removeChecksum(entry, entry.getChecksum());
            byNameIndex.remove(entry.getName(), entry);
        }
    }


    public List<E> getByName(String name) {
        return byNameIndex.get(name);
    }

    public List<E> getByChecksum(Checksum checksum) {
        return byChecksumIndex.get(checksum);
    }

}
