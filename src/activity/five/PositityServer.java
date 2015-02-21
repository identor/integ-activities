package activity.five;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PositityServer {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(8001)) {
			while(true) {
				System.out.println("Server started");
				Socket client = ss.accept();
				DataInputStream dis = new DataInputStream(client.getInputStream());;
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				String res;
				while (!(res = dis.readLine()).equals("exit")) {
					System.out.println(res);
					String line = "\r\n";
					int num;
					try { 
						num = Integer.parseInt(res);
						if (isPositive(num)) {
							dos.writeBytes("the number is positive" + line);
						} else if (num == 0) {
							dos.writeBytes("the number is zero" + line);
						} else {
							dos.writeBytes("the number is negative" + line);
						}
					} catch (NumberFormatException nfe) {
						dos.writeBytes("not a number" + line);
					}
				}
				dis.close();
				dos.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isPositive(int num) {
		return num > 0;
	}
}
