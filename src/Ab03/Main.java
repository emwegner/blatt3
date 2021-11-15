package Ab03;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int capacity;
        boolean fixed = false;
        boolean discarding = false;
        Scanner scan = new Scanner(System.in);

        System.out.println("Ringpuffer");
        System.out.print("Geben sie die Kapazität des Ringpuffers ein: ");
        capacity = scan.nextInt();
        Ringpuffer<Integer> ringpuffer = new Ringpuffer(capacity);

        /*
        Iterator test
        Iterator<Integer> it = new Ringpuffer().iterator();
        while(it.hasNext()) {
        System.out.println(it.next());

        for(Integer i : new Ringpuffer()) {
        System.out.println("--> " + i);
         */
    }
}
