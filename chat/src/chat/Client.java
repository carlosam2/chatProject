package chat;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class Client {

	public String username;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket socket;
	public Window wn;
	
	public Client(String username, Socket socket, Window wn) {
		
		this.wn=wn;
		
		this.username=username;
		this.socket=socket;
		try {
			this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			disconnect(socket, reader, writer);
		}
	}

	public void sendMsg() {
		// TODO Auto-generated method stub
		try {
			writer.write(username);
			writer.newLine();
			writer.flush();
			while(socket.isConnected()) {
				if(wn.getMsg() != null) {
					String msg = wn.getMsg();
					writer.write(username + ": " +msg);
					writer.newLine();
					writer.flush();
					wn.textArea_4.append(username+": "+msg+"\n");
					wn.setMsg(null); 
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			disconnect(socket, reader, writer);
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

	public void receive() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String msg;
				while(socket.isConnected()) {
					try {
						msg=reader.readLine();
						wn.textArea_4.append(msg+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						disconnect(socket, reader, writer);
					}
					
				}
				
			}
			
		}).start();
		
	}
	
}
