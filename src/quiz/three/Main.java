package quiz.three;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	/**
	 * @param args
	 */
	private static String STRING = "string";
	private static String INT = "int";
	private static String DOUBLE = "double";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// telnet 192.168.75.3 52157
		try (ServerSocket ss = new ServerSocket(8002)) {
			while(true) {
				System.out.println("Server started");
				Socket client = ss.accept();
				DataInputStream dis = new DataInputStream(client.getInputStream());;
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				String line = "\r\n";
				String res;
				while (!(res = dis.readLine()).equals("exit")) {
					System.out.println(res.trim());
					dos.writeBytes(evaluate(res) + line);
					System.out.println(evaluate(res) + line);
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
	
	public static String evaluate(String res) {
		String result = STRING;
		try {
			if (res.indexOf(".") < 0) {
				throw new NumberFormatException("TEST");
			}
			Double.parseDouble(res);
			result = DOUBLE;
			return result;
		} catch (NumberFormatException nfe) {
		}
		try {
			Integer.parseInt(res);
			result = INT;
			return result;
		} catch (NumberFormatException nfe) {
		}
		return result;
	}
}
