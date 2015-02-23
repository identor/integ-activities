package quiz.one;

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
				dos.writeBytes("addition expression evaluator\r\n");
				String res;
				while (!(res = dis.readLine()).equals("exit")) {
					System.out.println(res.trim());
					String line = "\r\n";
					try {
						String[] tokens = res.split("\\+");
						if (tokens.length > 2) {
							dos.writeBytes("invalid expression" + line);
							continue;
						}
						Double result = new Double(0);
						for (int i = 0; i < 2; i++) {
							result += Double.parseDouble(tokens[i].trim());
						}
						System.out.println(result);
						dos.writeBytes(result + line);
					} catch (NumberFormatException nfe) {
						System.out.println("invalid expression");
						dos.writeBytes("invalid expression" + line);
					}
				}
				dis.close();
				dos.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
