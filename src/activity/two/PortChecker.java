package activity.two;

import java.net.UnknownHostException;
import java.net.Socket;

import java.io.IOException;

public class PortChecker {
    public static void main(String[] args) {
        String host = args[0];
        for (int i = 1; i < 2; i++) {
            try {
                System.out.printf("Examining port: %d\n", i);
                Socket con = new Socket(host, i);
                System.out.printf("%s : %d is open!\n", host, i);
                con.close();
            } catch (UnknownHostException uhe) {
                System.out.printf("Cannot Connect to Host!\n");
            } catch (IOException ioe) {
            } catch (SecurityException se) {
                //System.out.printf("%s : %d is closed!\n", host, i);
            }
        }
        System.out.println("bye");
    }
}

