package activity.one;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InetLookup {
    public static void main(String[] args) {
        boolean run = true;
        for (int runNum = 1; run; runNum++)  {
            try {
                System.out.printf("Host %d - ", runNum);
                InetAddress[] ias = prompt();
                System.out.printf("Number of Hosts/IPs: %d%n", ias.length);
                for (InetAddress ia : ias) {
                    promptResult(ia);
                }
                run = promptLookupAgain();
            } catch (UnknownHostException uhe) {
                System.err.println("Host unknown!");
                run = promptLookupAgain();
            }
        }
        System.out.println("BYE!!!");
    }
    
    public static void promptResult(InetAddress ia) {
        System.out.printf("Host name: %s%n", ia.getHostName());        
        System.out.printf("IP Address: %s%n", ia.getHostAddress());
    }

    public static InetAddress[] prompt() throws UnknownHostException {
        System.out.print("Type IP address/Hostname ");
        Scanner kbd = new Scanner(System.in);
        kbd.close();
        return InetAddress.getAllByName(kbd.nextLine());
    }
    
    public static boolean promptLookupAgain() {
        System.out.print("Search another [y/n]? ");
        Scanner kbd = new Scanner(System.in);
        String input = kbd.nextLine();
        kbd.close();
        return input.charAt(0) == 'y' || input.charAt(0) == 'Y';
    }
}