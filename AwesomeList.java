import java.util.*;
import java.util.function.Consumer;

public class AwesomeList<T> implements List<T> {

    private static int def = 750;
    private aArray<aArray<T>> list = new aArray<>(def);
    private aArray<Integer> startIndexes = new aArray<>(def);
    private int lastArray = 0;
    private int size = 0;


    public AwesomeList () {
        aArray<T> a = new aArray<T>(def);
        startIndexes.add(0);
        list.add(a);

    }

    public boolean add (T value) {
       aArray<T> temp = list.get(lastArray);
       if (temp.openIndex == def)
       {
           aArray<T> a = new aArray<T>(def);
           a.add(value);
           list.add(list.openIndex,a);
           lastArray++;
           startIndexes.add(startIndexes.openIndex,lastArray*def);
       }
       else
           list.get(lastArray).add(value);
       size++;

       return true;
    }


    public void add (int index, T value) {

        int x = 0, y = 0;
        boolean whileloop = true;

        if (index > startIndexes.get(startIndexes.size()-1)) {
            x = startIndexes.size() - 1;
            y =  startIndexes.get(startIndexes.size() - 1);
            whileloop = false;
        }

        int start = 0;
        int end = startIndexes.size();
        int mid = startIndexes.size() / 2;

        while (whileloop) {

            if ((startIndexes.get(mid) > index && startIndexes.get(mid - 1) <= index))
                {
                    x = mid-1;
                    y = startIndexes.get(mid-1);
                    break;
                }

            if (startIndexes.get(mid)==index) {
                x = mid;
                y = index;
                break;
            }

            if (startIndexes.get(mid) > index) {
                end = mid;
                mid = (start+end)/2;
               continue;
            }
            if (index > startIndexes.get(mid)) {
                start = mid;
                mid = (start+end)/2;
                continue;
            }
        }

        list.get(x).add(index-y, value);
        for (int i = x + 1; i < startIndexes.size(); i++)
            startIndexes.set(i, startIndexes.get(i) + 1);

        size++;
    }

    public boolean addAll(Collection<? extends T> collection) {
        for (Object o: collection)
            add((T) o);
        return true;
    }


    public boolean addAll(int i, Collection<? extends T> collection) {
        int index = i;
        for (Object o: collection) {
            add(index, (T) o);
            index++;
        }

        return true;
    }

    public void clear() {
        aArray<aArray<T>> list = new aArray<>(def);
        aArray<Integer> startIndexes = new aArray<>(def);
        lastArray = 0;
        size = 0;
        aArray<T> a = new aArray<T>(def);
        startIndexes.add(0);
        list.add(a);
    }

    public boolean contains (Object value) {
        for (int i = 0; i < list.size(); i++) {
            aArray<T> templist = list.get(i);
            for (int j = 0; j < templist.openIndex; j++) {
                if (list.get(i).array[j].equals(value))
                    return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object o: collection)
            if (!contains(o))
                return false;
        return true;
    }

    public boolean equals (Object o) {
        if (!o.getClass().isAssignableFrom(o.getClass()))
            return false;

        List<T> newList = (List<T>) o;
        List<T> oldList = (List<T>) this;
        if (size != newList.size())
            return false;

        for (int i = 0; i < size; i++) {
            if (!oldList.get(i).equals(newList.get(i))) {
                return false;
            }
        }

        return true;
    }

    public T get (int index) {
        if (index >= size)
            throw new NoSuchElementException();

        if (index > startIndexes.get(startIndexes.size()-1))
            return list.get(startIndexes.size()-1).array[index-startIndexes.get(startIndexes.size()-1)];

        int start = 0;
        int end = startIndexes.size();
        int mid = startIndexes.size() / 2;
        while (true) {

            if ((startIndexes.get(mid) > index && startIndexes.get(mid - 1) <= index))
                return list.get(mid-1).array[index-startIndexes.get(mid-1)];

            if (startIndexes.get(mid)==index)
                return list.get(mid).array[0];

            if (startIndexes.get(mid) > index) {
                end = mid;
                mid = (start+end)/2;
                continue;
            }
            if (index > startIndexes.get(mid)) {
                start = mid;
                mid = (start+end)/2;
                continue;
            }
        }
    }

    public int hashCode() {
        return Arrays.hashCode(toArray());
    }

    public int indexOf(Object o) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            aArray<T> templist = list.get(i);
            for (int j = 0; j < templist.openIndex; j++) {
                if (list.get(i).array[j].equals(o))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public Iterator<T> iterator() {
        return new AwesomeListIterator<T>(this);
    }

    public int lastIndexOf(Object o) {
        int index = 0;
        int lastIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            aArray<T> templist = list.get(i);
            for (int j = 0; j < templist.openIndex; j++) {
                if (list.get(i).array[j].equals(o))
                    lastIndex = index;
                index++;
            }
        }
        return lastIndex;
    }


    public ListIterator<T> listIterator() {
        return new AwesomeListListIterator<T>(this, 0);
    }

    public ListIterator<T> listIterator(int i) {
        return new AwesomeListListIterator<T>(this, i);
    }

    public T remove (int index) {

        int x = 0, y = 0;
        boolean whileloop = true;
        if (index > startIndexes.get(startIndexes.size()-1)) {
            x = startIndexes.size() - 1;
            y =  startIndexes.get(startIndexes.size() - 1);
            whileloop = false;
        }
        int start = 0;
        int end = startIndexes.size();
        int mid = startIndexes.size() / 2;

        while (whileloop) {
            if ((startIndexes.get(mid) > index && startIndexes.get(mid - 1) <= index))
            {
                x = mid-1;
                y = startIndexes.get(mid-1);
                break; }
            if (startIndexes.get(mid)==index) {
                x = mid;
                y = index;
                break; }
            if (startIndexes.get(mid) > index) {
                end = mid;
                mid = (start+end)/2;
                continue; }
            if (index > startIndexes.get(mid)) {
                start = mid;
                mid = (start+end)/2;
                continue; }
        }

        T temp = list.get(x).array[index-y];
        list.get(x).remove(index-y);

        for (int i = x + 1; i < startIndexes.size(); i++)
            startIndexes.set(i, startIndexes.get(i) - 1);

        if (!(list.get(x).openIndex > 0)) {
            list.remove(x);
            startIndexes.remove(x);
            lastArray--;
        }
        size--;

        return temp;
    }


    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }



    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        for (Object o: collection) {
            T temp = (T) o;
            while (contains(temp)) {
                if (remove(temp))
                    removed = true;
            }
        }
        return removed;
    }


    public boolean retainAll(Collection<?> collection) {
        List<T> toRemove = new ArrayList<T>();
        for (int i = 0; i < list.size();i++) {
            aArray<T> templist = list.get(i);
            for (int j =  0; j < templist.openIndex;j++) {
                if (templist.array[j] != null) {
                        if (!collection.contains(list.get(i).array[j]))
                            toRemove.add(list.get(i).array[j]);
                }
            }
        }
        return removeAll(toRemove);
    }


    public T set(int index, T value) {
        T temp = this.remove(index);
        this.add(index, value);
        return temp;
    }

    public int size() {
        return size;
    }

    public List<T> subList(int i, int i1) {
        int index = 0;
        List<T> toReturnList = new AwesomeList<>();
        for (int j = 0; j < list.size();j++) {
            aArray<T> templist = list.get(j);
            for (int k =  0; k < templist.openIndex;k++) {
                if (templist.array[k] != null) {
                    if (index >= i&&index < i1)
                        toReturnList.add(list.get(j).array[k]);
                    index++;
                }
            }
        }
        return toReturnList;
    }

    public Object[] toArray() {
        int index = 0;
        Object[] o = new Object[size];
        for (int i = 0; i < list.size();i++) {
            aArray<T> templist = list.get(i);
            for (int j =  0; j < templist.openIndex;j++) {
                if (templist.array[j] != null) {
                    o[index] = list.get(i).array[j];
                    index++;
                }
            }
        }
        return o;

    }

    public <T> T[] toArray(T[] t1s) {
        if (t1s.length >= size)
        {
            int index = 0;
            for (int i = 0; i < list.size();i++) {
                aArray<T> templist = (aArray<T>) list.get(i);
                for (int j =  0; j < templist.openIndex;j++) {
                    if (templist.array[j] != null) {
                        t1s[index] = (T) list.get(i).array[j];
                        index++;
                    }
                }
            }
            return t1s;
        }
        else
            return (T[]) toArray();
    }

    public String toString () {
        return Arrays.toString(toArray());
    }

    public void forEach(Consumer<? super T> action) {
        for (T t : this)
            action.accept(t);
    }
}
