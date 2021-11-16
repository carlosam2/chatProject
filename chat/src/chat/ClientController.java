package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ClientController implements Runnable {
	
	private String username;
	private Socket client;
	private BufferedReader reader;
	private BufferedWriter writer;
	public static ArrayList<ClientController> list = new ArrayList<>();

	public ClientController(Socket client) {
		// TODO Auto-generated constructor stub
		this.client=client;
		try {
			this.reader= new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.writer= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			this.username = reader.readLine();
			list.add(this);
			send(username + " has connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			disconnect(client, reader, writer);
		}
	}
	
	public void send(String msg) {
		for (int i=0;i<list.size();i++){
	        if (list.get(i).username != username){
	        	try {
	        		list.get(i).writer.write(msg);
	        		list.get(i).writer.newLine();
	        		list.get(i).writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					disconnect(client, reader, writer);
				}
	        	
	        }
	     }
	}
	
	public synchronized void removeClient(String username) {
		for (int i=0;i<list.size();i++){
	        if (list.get(i).username != username){
	        	try {
					list.get(i).writer.write(username + "left the chat");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	     }
	}

	public void disconnect(Socket socket,BufferedReader reader, BufferedWriter writer) {
		// TODO Auto-generated method stub
		
		try {
			writer.close();
			reader.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg;
		while(client.isConnected()) {
			try {
				msg=reader.readLine();
				send(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				disconnect(client, reader, writer);
			}
			
		}
	}

}
