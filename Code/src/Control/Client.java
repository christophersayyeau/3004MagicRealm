package Control;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import View.GUI;

//go SEE A TA's office hours and get this finished properly and quickly in an afternoon

public class Client implements Runnable{
	Socket SOCK;
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	
	public Client(Socket X){
		this.SOCK = X;
	}
	
	@Override
	public void run() {
		try
		{
			try
			{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				OUT.flush();
				CheckStream();
			}
			finally
			{
				SOCK.close();
			}
		}
		catch(Exception X) {System.out.print(X);}
	}
	
	public void CheckStream()
	{
		while(true)
		{
			RECEIVE();
		}
	}
	
	//function to receive messages from server
	public void RECEIVE()
	{
		//evaluate message from the client then do stuff
		if(INPUT.hasNext())
		{
			String MESSAGE = INPUT.nextLine();
			System.out.println(MESSAGE);
			if(MESSAGE.contains("#?!"))
			{
				String TEMP1 = MESSAGE.substring(3);
				TEMP1 = TEMP1.replace("[", "");
				TEMP1 = TEMP1.replace("]", "");
				
				String[] CurrentUsers = TEMP1.split(", ");
				
				GUI.jlPlayers.setListData(CurrentUsers);
				//TODO add player to players array
				
			}
			else if(MESSAGE.contains("Start Game"))
			{
				//StartGame();
			}
			
		}
	}
	
	//function to send message to the server
	public void SEND(String X)
	{
		OUT.println(/*UserName + ": " +*/ X);
		OUT.flush();
	}
}

///do not use this class until second iteration
//will have main class that connects to server
	//server will start the game