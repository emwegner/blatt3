package Ab03;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;
import java.util.*;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private int writePos = 0;
    private int readPos = 0;
    private int size = 0;
    private int capacity;
    private boolean fixedCapacity = true;
    private boolean discarding = false;
    private ArrayList<T> elements = new ArrayList<T>();

    public Ringpuffer(int groesse) {
        capacity = groesse;
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
    public boolean add(T t) {
        if (size == capacity && !discarding) aenderung();
        if (size < capacity && !discarding) {
            elements.add(writePos, t);
            if (discarding) writePos = (writePos + 1) % capacity;
            else writePos++;
            size++;
            return true;
        } else if (size == capacity && discarding) {
            elements.set(writePos, t);
            writePos++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        boolean contain = false;
        for (T current : this) {
            if (current== o) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public T next() {
                if (readPos < size) {
                    T temp = elements.get(readPos);
                    if (discarding) readPos = (pointer + 1) % capacity;
                    else readPos++;
                    ;
                    pointer++;
                    return temp;
                }
                return null;
            }
        };
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
        boolean contain = false;
        int all = c.size();
        int soFar = 0;
        for (T current : this) {
            if (c.contains(current)) soFar++;
        }
        if (soFar == all) contain = true;
        return contain;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T current : c) {
            if (size == capacity && !discarding) aenderung();
            if (size < capacity && !discarding) {
                elements.add(writePos, current);
                if (discarding) writePos = (writePos + 1) % capacity;
                else writePos++;
                ;
                size++;
                return true;
            } else if (size == capacity && discarding) {
                elements.set(writePos, current);
                writePos++;
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //alle elemente in c werden aus dem ring gelöscht
        for (T current : this) {
            if (c.contains(current)) {
                elements.remove(current);
            }
            return true;
        }
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
    public boolean offer(T t) throws ClassCastException, NullPointerException, IllegalArgumentException {
        /*
    inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
    When using a capacity-restricted queue, this method is generally preferable to add(E), which can fail to insert an element only by throwing an exception.

    Parameters:
    e - the element to add
    Returns:
    true if the element was added to this queue, else false
    Throws:
    ClassCastException - if the class of the specified element prevents it from being added to this queue
    NullPointerException - if the specified element is null and this queue does not permit null elements
    IllegalArgumentException - if some property of this element prevents it from being added to this queue
         */
        if (size == capacity && !discarding) aenderung();
        if (size < capacity && !discarding) {
            elements.add(writePos, t);
            if (discarding) writePos = (writePos + 1) % capacity;
            else writePos++;
            ;
            size++;
            return true;
        } else if (size == capacity && discarding) {
            elements.set(writePos, t);
            writePos++;
            return true;
        }
        return false;
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

    public void aenderung() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Die maximale Kapazität ist erreicht. " +
                "Möchten sie die Kapazität erhöhen, Elemente überschreiben oder sollen keine Elemente mehr angenommen werden? (1/2/3)");
        String eingabe = scan.next();
        switch (eingabe) {
            case "1":
                System.out.print("Um wie viel wollen sie die Kapazität erhöhen?: ");
                fixedCapacity = false;
                int increase = scan.nextInt();
                capacity += increase;
                break;
            case "2":
                writePos = 0;
                discarding = true;
                System.out.println("Elemente werden nun überschrieben");
                break;
            case "3":
                System.out.println("Es werden keine neuen Elemente angenommen");
                break;
            default:
                System.out.println("Ungültige Eingabe");
                break;
        }
    }
}


