package Ab03;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;
import java.util.*;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private int writePos = 0;
    private int readPos = 0;
    private int size;
    private int capacity;
    private boolean fixedCapacity = true;
    private boolean discarding = false;
    ArrayList<T> elements;

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
    public boolean contains(Object o) {
        boolean contain = false;
        for(T current : this) {
            if (current == o) {
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
                return elements.get(pointer++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elements.size()];
        int i = 0;
        for (T current : this) {
            array[i]= current;
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        //kein plan lmao
        return a;
    }

    @Override
    public boolean add(T t) {
        if (size == capacity) aenderung();
        if ( size < capacity) {
            elements.add(writePos, t);
            writePos++;
            return true;
        }
       return false;
    }

    @Override
    public boolean remove(Object o) {
     /*   „Entfernte“ Elemente
        sollen physisch in der
        ArrayList<T> verbleiben.
        Sie werden nur „logisch“
        gelöscht, indem die
        Lesen-Position
        verschoben wird
      */
        readPos++;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean contain = false;
        int all = c.size();
        int soFar = 0;
        for(T current : this) {
            if(c.contains(current)) soFar++;
        }
        if (soFar == all) contain = true;
        return contain;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T current : c) {
            if (size == capacity) aenderung();
            if(size < capacity) {
                elements.add(writePos, current);
                writePos++;
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //alle elemente in c werden aus dem ring gelöscht
        for(T current : this) {
            if(c.contains(current)) {
                elements.remove(current);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //elemente die nicht in c sind werden im ring gelöscht
        for(T current : this) {
            if(!(c.contains(current))) {
                elements.remove(current);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        elements.clear();
        size=0;
        writePos=0;
        readPos=0;
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
        if (size == capacity) aenderung();
        if (size < capacity) {
                elements.add(writePos, t);
                writePos++;
                size++;
                return true;

        }
        return false;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        else {
            readPos++;
            size--;
            return elements.get(0);
        }
       /*
    Retrieves and removes the head of this queue. This method differs from poll only in
    that it throws an exception if this queue is empty.
    Returns:
    the head of this queue
    Throws:
    NoSuchElementException - if this queue is empty
        */
    }

    @Override
    public T poll() {
        /*
        E poll()
        Retrieves and removes the head of this queue, or returns null if this queue is empty.
        Returns:
        the head of this queue, or null if this queue is empty
         */
        if (size == 0) return null;
        else {
            T head = elements.get(readPos);
            readPos++;
            size--;
            return head;
        }
    }

    @Override
    public T element() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();
        else {
            T head = elements.get(readPos);
            readPos++; //wird ja trotzdem ausgelesen
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
            readPos++; //wird ja trotzdem ausgelesen
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
        int eingabe = scan.nextInt();
        switch (eingabe) {
            case 1:
                System.out.print("Um wie viel wollen sie die Kapazität erhöhen?: ");
                fixedCapacity = false;
                int increase = scan.nextInt();
                capacity += increase;
            case 2:
                writePos = 0;
                discarding = true;
                System.out.print("Elemente werden nun überschrieben");
            case 3:
                System.out.println("Es werden keine neuen Elemente angenommen");

            default:
                System.out.println("Ungültige Eingabe");
        }
    }
}
