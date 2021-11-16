package chat;

import java.io.* ;

import java.net.* ;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.*;



public class Server {

	 public Server(int port) throws IOException {
		 
		 JFrame frame = new JFrame();
		 JPanel panel = new JPanel();
		 
		 panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		 panel.setLayout(new BorderLayout(0,0));
		 
		 frame.add(panel,BorderLayout.CENTER);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setTitle("Server");
		 frame.setBounds(100, 100, 450, 300);
		 
		 JTextArea jTextArea = new JTextArea();
		 DefaultCaret caret = (DefaultCaret)jTextArea.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		 jTextArea.setEnabled(false);
		 JScrollPane sp = new JScrollPane(jTextArea);
		
		 frame.add( sp );
		

		 frame.setPreferredSize(new Dimension(400, 300));
		 frame.pack();
		 frame.setVisible(true);
		 
		 ServerSocket server;
		 server = new ServerSocket(port);
		try {
			
			 while(true) {
				 jTextArea.append( "Waiting connections\n" );
				 Socket socket = server.accept();
				 jTextArea.append( "New user connected\n" );
				 ClientController clientC = new ClientController(socket);
	             Thread thread = new Thread(clientC);
	             thread.start();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			server.close();
		}
	 }
	
	public static void main(String[] args) throws IOException {
		int port = 9000;
		new Server(port);
	}
	
}






