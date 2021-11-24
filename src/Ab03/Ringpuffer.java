package Ab03;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;
import java.util.*;
import java.util.function.IntFunction;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;
    private ArrayList<T> elements;

    public Ringpuffer(int groesse, boolean fixed, boolean discardable) {
        fixedCapacity = fixed;
        discarding = discardable;
        capacity = groesse;
        elements = new ArrayList<T>();
        for (int i = 0; i < capacity; i++) {
            elements.add(null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    @Override
    public boolean add(T t) { //geht
        if (!testInsertionRules(t)) throw new IllegalStateException();

        return true;
    }

    private boolean testInsertionRules(T t) {
        if (size() == capacity) {
            if (fixedCapacity) {
                if (discarding) {
                    remove();
                } else {
                    return false;
                }
            } else {
                ArrayList<T> newElements = new ArrayList<>(capacity);
                newElements.addAll(this);
                readPos = 0;
                writePos = size;
                elements = newElements;
                capacity *= 2;
                for (int i = 0; i < capacity - size; i++) {
                    elements.add(null);
                }
            }
        }
        elements.set(writePos, t);
        size++;
        writePos = (writePos + 1) % capacity;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        boolean contain = false;
        for (T current : this) {
            if (current == o) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    @Override
    public Iterator<T> iterator() { //geht
        Iterator<T> it = new Iterator<T>() {
            private int count = 0;
            private int tempReader = readPos;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T temp = elements.get(tempReader);
                tempReader = (tempReader + 1) % capacity;
                count++;
                return temp;
            }
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (T current : this) {
            array[i] = current;
            i++;
        }
        return array;
    }


    @Override
    public <T1> T1[] toArray(T1[] a) {
        return a;
    }




    @Override
    public boolean remove(Object o) throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        else {
            readPos = (readPos + 1) % capacity;
            size--;
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object t : c) {
            if (!this.contains(t)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //alle elemente in c werden aus dem ring gelöscht
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //elemente die nicht in c sind werden im ring gelöscht
        for (T current : this) {
            if (!(c.contains(current))) {
                elements.remove(current);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        elements.clear();
        size = 0;
        writePos = 0;
        readPos = 0;
        discarding = false;
        fixedCapacity = true;
    }

    @Override
    public boolean offer(T t) {
        return testInsertionRules(t);
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        else {
            T temp = elements.get(readPos);
            readPos = (readPos + 1) % capacity;
            this.size--;
            return temp;
        }
    }

    @Override
    public T poll() {
        if (size == 0) return null;
        else {
            T head = elements.get(readPos);
            readPos = (readPos + 1) % capacity;
            size--;
            ;
            return head;
        }
    }

    @Override
    public T element() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        else {
            T head = elements.get(readPos);
            readPos = (readPos + 1) % capacity;
            size--;
            return head;
        }
        /*
        Retrieves, but does not remove, the head of this queue. This method differs from peek only in that it throws an exception if this queue is empty.
        Returns:
        the head of this queue
        Throws:
        NoSuchElementException - if this queue is empty
         */
    }

    @Override
    public T peek() {
        if (size == 0) return null;
        else {
            T head = elements.get(readPos);
            readPos = (readPos + 1) % capacity;
            //wird ja trotzdem ausgelesen
            size--;
            return head;
        }

        /*
        Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
        Returns:
        the head of this queue, or null if this queue is empty
         */
    }
}





