import java.util.Iterator;
import java.util.function.Consumer;

public class AwesomeListIterator<T> implements Iterator {

    private Object pointer;
    private AwesomeList list;
    private int index;
    public AwesomeListIterator (AwesomeList obj) {
        list = obj;
        index=-1;
    }

    public boolean hasNext() {
        if(index+1 == list.size())
                return false;
        return true;
    }


    public Object next() {
        pointer = list.get(index+1);
        index++;
        return pointer;
    }
}
