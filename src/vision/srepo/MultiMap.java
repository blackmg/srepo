package vision.srepo;

import java.util.*;

/**
 * User: ervjo
 * Date: 2010-mar-06
 * Time: 11:33:33
 * To change this template use File | Settings | File Templates.
 */
public class MultiMap<E, T> {
    private Map<E, List<T>> store = new HashMap<E, List<T>>();


    public MultiMap() {
    }

    public List<T> get(E e) {
        List<T> ts = store.get(e);
        if (ts == null) {
            return Collections.EMPTY_LIST;
        }
        return ts;
    }

    public void put(E e, T t) {
        List<T> list = store.get(e);
        if (list == null) {
            list = new ArrayList<T>();
            store.put(e, list);
        }
        list.add(t);
    }

    public void remove(E e, T t) {
        List<T> list = store.get(e);
        if (list != null) {
            list.remove(t);
        }
    }

    public Set<Map.Entry<E, List<T>>> getEntries() {
        final Set<Map.Entry<E, List<T>>> entries = store.entrySet();

        return entries;

    }
}
