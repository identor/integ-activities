package quiz.two;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// telnet 192.168.75.3 52157
		try (ServerSocket ss = new ServerSocket(8002)) {
			while(true) {
				System.out.println("Server started");
				Socket client = ss.accept();
				DataInputStream dis = new DataInputStream(client.getInputStream());;
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				String res;
				while (!(res = dis.readLine()).equals("bye")) {
					System.out.println(res.trim());
					String line = "\r\n";
					try {
						boolean palindrome = evaluate(res.trim());
						if (palindrome) {
							dos.writeBytes("pareho" + line);
						} else {
							dos.writeBytes("haan nga agpada" + line);
						}
						int i = Integer.parseInt(res);
						if (palindrome && (i > 0 || i <= 0)) {
							dos.writeBytes("bye" + line);
						}						
					} catch (NumberFormatException nfe) {
						System.out.println("NFE expression: " + res);
					}
				}
				dis.close();
				dos.close();
			}			
		} catch (IOException e) {
			System.exit(1);
			System.out.println("Connection Closed...");
		} catch (SecurityException se) {
			System.exit(1);
			System.out.println("Unable to open port...");
		} catch (Exception e) {
			System.exit(1);
			System.out.println("Exit due to generic exception...");
		}
	}
	
	public static boolean evaluate(String res) {
		StringBuilder sb = new StringBuilder(res);
		sb.reverse();
		String reverse = sb.toString();
		return reverse.equals(res);
	}
}
