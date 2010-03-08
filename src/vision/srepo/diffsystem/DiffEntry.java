package vision.srepo.diffsystem;

import vision.srepo.BasicEntry;
import vision.srepo.Checksum;
import vision.srepo.filesystem.BasicFileEntry;
import vision.srepo.filesystem.FileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: ervjo
 * Date: 2010-feb-28
 * Time: 10:44:36
 * To change this template use File | Settings | File Templates.
 */
public class DiffEntry extends BasicEntry {
    final private BasicFileEntry sourceEntry;
    final private BasicFileEntry targetEntry;

    private List<DiffEntry> children;

    public enum DiffStatus {
        CONFLICT,
        SAME,
        MAY_BE_SAME,
        IN_SOURCE, DELETED_IN_SOURCE,
        IN_TARGET, DELETED_IN_TARGET,
    }

    private final DiffStatus diffStatus;

    public DiffEntry(DiffEntry parent, BasicFileEntry sourceEntry, BasicFileEntry targetEntry) {
        super(sourceEntry != null ? sourceEntry.getName() : targetEntry.getName(), parent);
        this.sourceEntry = sourceEntry;
        this.targetEntry = targetEntry;

        if (targetEntry == null) {
            // TODO: check for exist in base, it could be deleted in target
            diffStatus = DiffStatus.IN_SOURCE;
        } else if (sourceEntry == null) {
            // TODO: check for deleted in source
            diffStatus = DiffStatus.IN_TARGET;
        } else if (sourceEntry.isFile() != targetEntry.isFile()) {
            diffStatus = DiffStatus.CONFLICT;
        } else if (sourceEntry.isDirectory() && targetEntry.isDirectory()) {
            diffStatus = DiffStatus.SAME;
        } else if (sourceEntry.getSize() != targetEntry.getSize()) {
            diffStatus = DiffStatus.CONFLICT;
        } else {
            final Checksum sourceChecksum = sourceEntry.getChecksum();
            final Checksum targetChecksum = targetEntry.getChecksum();
            if (sourceChecksum == null || targetChecksum == null) {
                diffStatus = DiffStatus.MAY_BE_SAME;
            } else {
                if (sourceChecksum.equals(targetChecksum)) {
                    diffStatus = DiffStatus.SAME;
                } else {
                    diffStatus = DiffStatus.MAY_BE_SAME;
                }
            }
        }
    }

    public boolean isConflict() {
        return diffStatus == DiffStatus.CONFLICT;
    }

    public void process(DiffSystem diffSystem) {
        final List<BasicFileEntry> sourceChildren = sourceEntry == null ? FileSystem.EMPTY_FILE_ENTRY_LIST :
                sourceEntry.getFileChildren();
        final List<BasicFileEntry> targetChildren = targetEntry == null ? FileSystem.EMPTY_FILE_ENTRY_LIST :
                targetEntry.getFileChildren();
        if (sourceChildren.isEmpty() && targetChildren.isEmpty()) {
            return;
        }
        children = new ArrayList<DiffEntry>(Math.max(sourceChildren.size(), targetChildren.size()));

        final Map<String, BasicFileEntry> sourceEntries = toMap(sourceChildren);
        final Map<String, BasicFileEntry> targetEntries = toMap(targetChildren);

        for (BasicFileEntry sourceChild : sourceChildren) {
            final String name = sourceChild.getName();
            final BasicFileEntry targetChild = targetEntries.get(name);
            final DiffEntry diffEntry = new DiffEntry(this, sourceChild, targetChild);
            children.add(diffEntry);
        }
        for (BasicFileEntry targetChild : targetChildren) {
            final String name = targetChild.getName();
            final BasicFileEntry sourceChild = sourceEntries.get(name);
            if (sourceChild == null) {
                final DiffEntry diffEntry = new DiffEntry(this, sourceChild, targetChild);
                children.add(diffEntry);
            }
        }
        for (DiffEntry child : children) {
            child.process(diffSystem);
        }
    }

    private Map<String, BasicFileEntry> toMap(List<BasicFileEntry> entries) {
        final Map<String, BasicFileEntry> ret = new HashMap<String, BasicFileEntry>();

        for (BasicFileEntry entry : entries) {
            ret.put(entry.getName(), entry);
        }
        return ret;
    }

    @Override
    public List<BasicEntry> getChildren() {
        if (children == null) {
            return FileSystem.EMPTY_ENTRY_LIST;
        }
        List<BasicEntry> ret = new ArrayList<BasicEntry>();
        for (DiffEntry child : children) {
            ret.add(child);
        }
        return ret;
    }


    @Override
    public boolean isFile() {
        boolean ret = sourceEntry != null && sourceEntry.isFile();
        ret |= targetEntry != null && targetEntry.isFile();

        return ret;
    }

    @Override
    public String toPrettyPrint() {
        return diffStatus + " " + getRepoPath();
    }
}
