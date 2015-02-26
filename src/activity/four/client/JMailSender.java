package activity.four.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class JMailSender extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fromTF;
	private JTextField toTF;
	private JTextField serverTF;
	private JTextField portTF;
	private JTextField subjectTF;
	private JTextArea bodyTA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMailSender frame = new JMailSender();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void getMessageAndSend() {
		String senderEmail = fromTF.getText();
		String fromEmail = toTF.getText();
		String subject = subjectTF.getText();
		String body = bodyTA.getText();
		String server = serverTF.getText();
		try {
			int port = Integer.parseInt(portTF.getText());
			JOptionPane.showMessageDialog(contentPane, 
					SimpleSMTPClient.send(senderEmail, fromEmail, subject, body, server, port));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(contentPane, "Connection to " + server + " refused..");
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(contentPane, "Port not an integer: " + nfe.getMessage());
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		} catch (SMTPException e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
	}

	private void closeApplication() {
		this.dispose();
		System.out.println("Closing the Application...");
	}

	/**
	 * Create the frame.
	 */
	public JMailSender() {
		createFrame();
	}

	private void createFrame() {
		setTitle("JMailSender");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setBorder(new TitledBorder(null, "Server Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel = new JLabel("Server");
		panel_3.add(lblNewLabel);
		
		serverTF = new JTextField();
		panel_3.add(serverTF);
		serverTF.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		panel_3.add(lblPort);
		
		portTF = new JTextField();
		panel_3.add(portTF);
		portTF.setColumns(5);
		
		JPanel south = new JPanel();
		FlowLayout fl_south = (FlowLayout) south.getLayout();
		fl_south.setAlignment(FlowLayout.LEADING);
		contentPane.add(south, BorderLayout.SOUTH);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "Sending... It may take a while.");
				getMessageAndSend();
			}
		});
		south.add(btnSend);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApplication();
			}
		});
		south.add(btnExit);
		
		JPanel center = new JPanel();
		center.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		center.add(panel);
		panel.setLayout(new BorderLayout(0, 10));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		GridLayout gl_panel_1 = new GridLayout();
		gl_panel_1.setRows(2);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_5);
		
		JLabel lblFrom = new JLabel("From");
		panel_5.add(lblFrom);
		
		fromTF = new JTextField();
		panel_5.add(fromTF);
		fromTF.setToolTipText("");
		fromTF.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		panel_5.add(lblTo);
		
		toTF = new JTextField();
		panel_5.add(toTF);
		toTF.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_4);
		
		JLabel lblSubject = new JLabel("Subject");
		panel_4.add(lblSubject);
		
		subjectTF = new JTextField();
		panel_4.add(subjectTF);
		subjectTF.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		bodyTA = new JTextArea();
		scrollPane.setViewportView(bodyTA);
	}
}
