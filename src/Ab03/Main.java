package Ab03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int capacity;
        Scanner scan = new Scanner(System.in);

        System.out.println("Ringpuffer");
        System.out.print("Geben sie die Kapazität des Ringpuffers ein: ");
        capacity = scan.nextInt();
        Ringpuffer<Integer> ringpuffer = new Ringpuffer(capacity);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(4);

        System.out.println("Add test");
        ringpuffer.add(1);
        ringpuffer.add(2);
        ringpuffer.add(3);
        ringpuffer.add(4);
        ringpuffer.add(5);
        for(int i : ringpuffer) System.out.println(i);
        System.out.println("____");
        System.out.println("empty test");
        System.out.println(ringpuffer.isEmpty());

        System.out.println("____");
        System.out.println("contains test für 4");
        Integer i = 4;
        System.out.println(ringpuffer.contains(i));

        System.out.println("____");
        System.out.println("containsAll test, liste enthält 4,5");
        System.out.println(ringpuffer.containsAll(list));


        System.out.println("____");
        System.out.println("Remove test");
        ringpuffer.remove();
        for(int o : ringpuffer) System.out.println(o);

        System.out.println("____");
        System.out.println("Offer test");
        ringpuffer.offer(4);
        ringpuffer.offer(5);
        for(int p : ringpuffer) System.out.println(p);

        System.out.println("____");
        System.out.println("to Array test");
        Object[] temparr = ringpuffer.toArray();
        for (Object t : temparr) {
            System.out.println(t);
        }
        System.out.println("____");
        System.out.println("size test");
        System.out.println(ringpuffer.size());


        System.out.println("____");
        System.out.println("removeAll test");
        ringpuffer.removeAll(list);
        for(int l : ringpuffer) System.out.println(l);


        System.out.println("____");
        System.out.println("addAll test");
        ringpuffer.addAll(list);
        for(int k : ringpuffer) System.out.println(k);


        System.out.println("____");
        System.out.println("RetainAll test");
        ringpuffer.retainAll(list);

        System.out.println("____");
        System.out.println("removeAll test");
        ringpuffer.removeAll(list);
        for(int m : ringpuffer) System.out.println(m);


    }


    }

