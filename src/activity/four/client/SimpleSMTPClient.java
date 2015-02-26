package activity.four.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSMTPClient {
	
	// Test
	public static void main(String[] args) {
		try {
			System.out.println(send("irvin@localhost",
					"irvin@wiwi.ninja", "Test Email from Java",
					"This is just a test...", "mbox.slu.edu.ph", 25
					));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public static String send(String senderEmail, String receipientEmail, String subject, String body, String smtpServer, int port) throws IOException, UnknownHostException, SecurityException, SMTPException {
		String connectLog = "";
		String serverResponse = "";
		Socket s = new Socket(smtpServer, port);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		connectLog += serverResponse = dis.readLine() + "\n";
		if (SMTPResponse.parse(serverResponse).getResponseType() == SMTPResponse.ERROR) {
		}
		dos.writeBytes("helo localhost");
		dos.writeBytes("\r\n");
		connectLog += serverResponse = dis.readLine() + "\n";
		SMTPResponse.parse(serverResponse);
		dos.writeBytes(String.format("mail from: %s", senderEmail));
		dos.writeBytes("\r\n");
		dos.writeBytes(String.format("rcpt to: %s", receipientEmail));
		dos.writeBytes("\r\n");
		dos.writeBytes("data");
		dos.writeBytes("\r\n");
		dos.writeBytes(String.format("subject: %s", subject));
		dos.writeBytes("\r\n");
		dos.writeBytes("\r\n");
		connectLog += serverResponse = dis.readLine() + "\n";
		SMTPResponse.parse(serverResponse);
		connectLog += serverResponse = dis.readLine() + "\n";
		SMTPResponse.parse(serverResponse);
		dos.writeBytes(String.format("%s", body));
		dos.writeBytes("\r\n.\r\n");
		connectLog += serverResponse = dis.readLine() + "\n";
		SMTPResponse.parse(serverResponse);
		connectLog += serverResponse = dis.readLine() + "\n";
		SMTPResponse.parse(serverResponse);
		s.close();
		return connectLog;
	}
}
