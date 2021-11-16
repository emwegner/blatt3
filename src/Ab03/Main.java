package Ab03;

import java.util.ArrayList;
import java.util.Iterator;
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
        list.add(1);
        list.add(6);


        System.out.println("Add test");
        ringpuffer.add(1);
        ringpuffer.add(2);
        ringpuffer.add(3);
        ringpuffer.forEach(t -> System.out.println(t));

        System.out.println("____");
        System.out.println("Remove test");
        ringpuffer.remove();
        ringpuffer.forEach(t -> System.out.println(t));

        System.out.println("____");
        System.out.println("Offer test");
        ringpuffer.offer(4);
        ringpuffer.offer(5);
        ringpuffer.forEach(t -> System.out.println(t));

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
        System.out.println("empty test");
        System.out.println(ringpuffer.isEmpty());

        System.out.println("____");
        System.out.println("contains test");
        Integer i = 6;
        System.out.println(ringpuffer.contains(i));

        System.out.println("____");
        System.out.println("containsAll test");
        System.out.println(ringpuffer.containsAll(list));

        System.out.println("____");
        System.out.println("addAll test");
        ringpuffer.addAll(list);

        System.out.println("____");
        System.out.println("removeAll test");
        ringpuffer.removeAll(list);

        System.out.println("____");
        System.out.println("RetainAll test");
        ringpuffer.retainAll(list);

        System.out.println("____");
        System.out.println("clear test");
        ringpuffer.clear();

        //offer poll element peek

    }
}

