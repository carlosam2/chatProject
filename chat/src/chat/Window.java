package chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private final JTextArea textArea_1 = new JTextArea("IP:");
	private JTextField textField_1;
	private JTextArea textArea_2;
	private JTextField textField_2;
	private JPanel panel_1;
	public JTextField textField_3;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton button;
	public static String username;
	public static String ip;
	public static String port;
	public JTextArea textArea_4;
	volatile static String msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void runClient(int port) {
		Window wn = this;
		
		new Thread(new Runnable() {
			Socket socket;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						socket = new Socket(ip, port);
						//socket = new Socket("localhost", 9000);
						Client client = new Client(username, socket, wn);
				        client.receive();
				        client.sendMsg();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		}).start();
        
	}
	
	

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{67, 130, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{26, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JTextArea textArea = new JTextArea("Username:");
		textArea.setBackground(SystemColor.window);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.anchor = GridBagConstraints.WEST;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel.add(textArea, gbc_textArea);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		button = new JButton("Connect");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 4;
		gbc_button.gridy = 0;
		panel.add(button, gbc_button);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 1;
		textArea_1.setBackground(SystemColor.window);
		panel.add(textArea_1, gbc_textArea_1);
		
		textField_1 = new JTextField("192.168.100.5");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textArea_2 = new JTextArea("PORT:");
		textArea_2.setBackground(SystemColor.window);
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_2.gridx = 0;
		gbc_textArea_2.gridy = 2;
		panel.add(textArea_2, gbc_textArea_2);
		
		textField_2 = new JTextField("9000");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea_4 = new JTextArea();
		
		DefaultCaret caret = (DefaultCaret)textArea_4.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		 JScrollPane sp = new JScrollPane(textArea_4);
		panel_1.add(sp);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("Send");
		panel_2.add(btnNewButton);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() != "" && textField_1.getText() != "" && textField_2.getText() != "") {
					username = textField.getText();
					ip = textField_1.getText();
					port = textField_2.getText();
					int aux = Integer.parseInt(port);
					runClient(aux);
				}
				
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				msg = textField_3.getText();
				textField_3.setText("");

			}
		});
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
