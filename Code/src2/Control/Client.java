package Control;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Scanner;

import View.GUI;

//go SEE A TA's office hours and get this finished properly and quickly in an afternoon

public class Client implements Runnable{
	Socket SOCK;
	Scanner INPUT;
	GUI gui = null;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	public Player player = null;
	ArrayList<String> players;
	
	public Client(Socket X, Player p){
		this.SOCK = X;
		this.player = p;
		players = new ArrayList<String>();
		gui = new GUI(null);
		chooseStart();
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
			}else if(MESSAGE.contains("STARTGAME")){
				gui.startButton.setEnabled(false);
				SEND("CHOOSESTART"+player.getCurrentLocation());
			}
			else if(MESSAGE.contains("MOVE")){
				String cha = MESSAGE.substring(0, MESSAGE.indexOf(":")-1);
				MESSAGE = MESSAGE.substring(MESSAGE.indexOf(":") +1 + 4);
				for(int i=0;i<gui.players.size();++i){
					if(gui.players.get(i).getProfile().getType().equals(cha)){
						gui.players.get(i).setCurrentLocation(Integer.valueOf(MESSAGE.substring(MESSAGE.indexOf(":")+1)));
					}
				}
			}
			else if(MESSAGE.contains("ATTACK")){
				
			}
			else if(MESSAGE.contains("DEFEND")){
				
			}
			else if(MESSAGE.contains("CHOOSESTART")){
				
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
		OUT.println(player.getProfile().getType() + ":" + X);
		OUT.flush();
	}
	
	@SuppressWarnings("unused")
	public void createPlayer(String message){
		String s = GUI.displayMessage("Please select a different character.");
		player = null;
		player = new Player(GUI.createPlayer());
		
		SEND("ADDPLAYER:"+ player.getProfile().getType());
	}
	
	public void chooseStart(){
		int i = GUI.chooseStart(player);
		player.setCurrentLocation(i);
	}
}

///do not use this class until second iteration
//will have main class that connects to server
	//server will start the game