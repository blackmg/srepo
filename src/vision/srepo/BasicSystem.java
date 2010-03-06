package vision.srepo;

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
    }


    public void fileAdded(E entry) {
        if (entry.isFile()) {
            final Checksum checksum = entry.getChecksum();
            if (checksum != null) {
                byChecksumIndex.put(checksum, entry);
            }
            byNameIndex.put(entry.getName(), entry);
        }
    }

    public void fileRemoved(E entry) {
        if (entry.isFile()) {
            final Checksum checksum = entry.getChecksum();
            if (checksum != null) {
                byChecksumIndex.remove(checksum, entry);
            }
            byNameIndex.remove(entry.getName(), entry);
        }

    }
}
