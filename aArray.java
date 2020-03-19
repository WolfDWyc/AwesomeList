import java.lang.reflect.Array;
import java.util.Arrays;

public class aArray<T> {

    public T[] array;
    public int openIndex;
    public int totalStartIndex;

    public aArray (int capacity) {
        array = (T[]) new Object[capacity];
        openIndex = 0;
    }

    public void add (T value) {
            array[openIndex] = value;
            openIndex++;
    }


    public void remove (int index) {
        int count = index;
        while (count+1 < array.length&&array[count] != null) {
            array[count] = array[count+1];
            count++;
        }
        array[count] = null;
        openIndex--;

    }

    public void add (int index, T value) {
            if (openIndex == array.length)
            {
                T[] newarr = (T[]) Array.newInstance(value.getClass(), (int) ((int) array.length*1.5));
                for (int i = 0; i < array.length;i++)
                    newarr[i] = array[i];
                array = newarr;
            }


        T temp;
        T prevtemp = array[index];
        for (int i = index; i < openIndex; i++) {
            temp = array[i+1];
            array[i+1] = prevtemp;
            prevtemp  = temp;
        }
        array[index] = value;
        openIndex++;

    }

    public T get (int index) {
        return array[index];
    }

    public void set (int index, T value) {
        array[index] = value;
    }

    public int size() {
        return openIndex;
    }

    public boolean isEmpty() {
        return openIndex==0;
    }

    public String toString () {
        return Arrays.toString(array);
    }

}