import java.util.ListIterator;

public class AwesomeListListIterator<T> implements ListIterator {

    private Object pointer;
    private AwesomeList list;
    private int nextIndex;
    private int prevIndex;
    private int lastCall;
    private int calls;

    public AwesomeListListIterator(AwesomeList obj, int start) {
        list = obj;
        nextIndex = start;
        prevIndex = start-1;
        lastCall = -1;
        calls = 0;
    }


    public boolean hasNext() {
        if(nextIndex == list.size())
            return false;
        return true;
    }


    public Object next() {
        pointer = list.get(nextIndex);
        nextIndex++;
        prevIndex++;
        lastCall = 0;
        calls++;
        return pointer;
    }


    public boolean hasPrevious() {
        if(prevIndex == -1)
            return false;
        return true;
    }


    public Object previous() {
        pointer = list.get(prevIndex);
        nextIndex--;
        prevIndex--;
        lastCall = 1;
        calls++;
        return pointer;
    }


    public int nextIndex() {
        return nextIndex;
    }


    public int previousIndex() {
        return prevIndex;
    }


    public void remove() {
        if (calls > 0) {
            if (lastCall == 0) {
                list.remove(prevIndex);
                nextIndex--;
                prevIndex--;
            }
        if (lastCall == 1)
            list.remove(nextIndex);
        calls--;
        lastCall = 3;

        }
    }

    public void set(Object o) {
        if (lastCall == 0)
            list.set(prevIndex, o);

        if (lastCall == 1)
            list.set(nextIndex, o);
    }


    public void add(Object o) {
        lastCall = 2;
        list.add(nextIndex,o);
        nextIndex++;
        prevIndex++;
    }
}
