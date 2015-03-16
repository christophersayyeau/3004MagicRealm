package Control;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

//go SEE A TA's office hours and get this finished properly and quickly in an afternoon

public class Client{
	static String serverIP = "192.168.56.1";
	private static ClientController cController;
	
	Client(){
		serverIP = JOptionPane.showInputDialog(null, "What is the server's IP? ");
	}
	
	public static void Connect()
	{
		try
		{
			final int PORT = 9073;
			String HOST = serverIP;
			System.out.print(HOST);
			Socket SOCK = new Socket(HOST, PORT);
			System.out.println("You connected to: " + HOST);
			
			cController = new ClientController(SOCK);
			
			PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
			//OUT.println(UserName);
			//OUT.flush();
			
			Thread X = new Thread(cController);
			X.start();			
		}
		catch(Exception X)
		{
			System.out.print(X);
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	public static void main(String args[])
	{
		String s = JOptionPane.showInputDialog(null, "What is the server's IP? ");
		//serverIP = JOptionPane.showInputDialog(null, "What is the server's IP? ");
		//System.out.print(serverIP);
		Connect();
	}
}

///do not use this class until second iteration
//will have main class that connects to server
	//server will start the game