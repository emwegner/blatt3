package Ab03;

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

        ringpuffer.add(1);
        ringpuffer.add(2);
        ringpuffer.add(3);
        ringpuffer.add(4);

        Iterator<Integer> it = new Ringpuffer(10).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
