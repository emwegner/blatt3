package Ab03;

import jdk.dynalink.NamedOperation;

import java.io.Serializable;
import java.util.*;

public class Ringpuffer<T> implements Queue<T>, Serializable,Cloneable {
    private int writePos = 0;
    private int readPos = 0;
    private int size;
    private int capacity;
    private boolean fixedCapacity =false;
    private boolean discarding=false;
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
        if(elements.contains(o)) return true;
        else return false;
    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elements.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = elements.get(i);
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
      return null;
    }

    @Override
    public boolean add(T t) {
        if(size == capacity) aenderung();
        elements.add(writePos,t);
        writePos++;
        return true;
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
      return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T current : c) {
            if(size == capacity) aenderung();
            elements.add(writePos,current);
            writePos++;
            size++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(int i =0;i < c.size(); i++) {

        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(int i=0; i < c.size(); i++) {

        }
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) throws ClassCastException,NullPointerException,IllegalArgumentException {
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

    return false;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if(size == 0) throw new NoSuchElementException();
        else {

        }
       /*
      Retrieves and removes the head of this queue. This method differs from poll only in
     that it throws an exception if this queue is empty.
     Returns:
     the head of this queue
    Throws:
    NoSuchElementException - if this queue is empty

        */
        return new <T>;
    }

    @Override
    public T poll() {
        /*
        E poll()
        Retrieves and removes the head of this queue, or returns null if this queue is empty.
        Returns:
        the head of this queue, or null if this queue is empty
         */


        return null;
    }

    @Override
    public T element() {
        //?
        return null;
    }

    @Override
    public T peek() {
        //?
        return null;
    }

    public void aenderung(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Die maximale Kapazität ist erreicht. " +
        "Möchten sie die Kapazität erhöhen, Elemente überschreiben oder sollen keine Elemente mehr angenommen werden? (1/2/3)");
        int eingabe = scan.nextInt();
        switch(eingabe){
            case 1:
                System.out.print("Um wie viel wollen sie die Kapazität erhöhen?: ");
                int increase = scan.nextInt();
                capacity += increase;
            case 2:
                writePos = 0;
                System.out.print("Elemente werden nun überschrieben");
            case 3:
                System.out.println("Es werden keine neuen Elemente angenommen");

            default:
                System.out.println("Ungültige Eingabe");
        }
    }
}
