package Control;

import java.io.PrintWriter;
import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Scanner;

import View.GUI;

//go SEE A TA's office hours and get this finished properly and quickly in an afternoon

public class Client implements Runnable{
	Socket SOCK;
	Scanner INPUT;
	GUI gui;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	Player player;
	
	public Client(Socket X, Player p){
		this.SOCK = X;
		this.player = p;
		gui = new GUI(null);
		//gui.initilizeWindow();
		//gui.buildMap();
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
	@SuppressWarnings("unchecked")
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
				//TODO STEFAN, add player to players array
				
			}
			else if(MESSAGE.contains("ChooseChar"))
			{
				createPlayer(MESSAGE);
			}
			else if(MESSAGE.contains("PLAYERS"))
			{
				//gui.initilizeWindow();
				System.out.println(MESSAGE);
				System.out.println(MESSAGE);
				//createPlayer(MESSAGE);
			}
		}
	}
	
	//function to send message to the server
	public void SEND(String X)
	{
		OUT.println(/*UserName + ": " +*/ X);
		OUT.flush();
	}
	
	@SuppressWarnings("unused")
	public void createPlayer(String message){
		String s = GUI.displayMessage("Please select a different character.");
		player = null;
		player = new Player(GUI.createPlayer(), -1);
		
		SEND("ADDPLAYER:"+ player.getProfile().getType());
	}
}

///do not use this class until second iteration
//will have main class that connects to server
	//server will start the game