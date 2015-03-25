package Control;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import View.GUI;

//go SEE A TA's office hours and get this finished properly and quickly in an afternoon

public class Client implements Runnable{
	Socket SOCK;
	Scanner INPUT;
	Scanner SEND = new Scanner(System.in);
	PrintWriter OUT;
	Player player;
	
	public Client(Socket X, Player p){
		this.SOCK = X;
		this.player = p;
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
			else if(MESSAGE.contains("ChooseChar"))
			{
				createPlayer(MESSAGE);
			}
		}
	}
	
	//function to send message to the server
	public void SEND(String X)
	{
		OUT.println(/*UserName + ": " +*/ X);
		OUT.flush();
	}
	
	public void createPlayer(String message){
		/*List<String> n = new ArrayList<String>();
		
		String s = (message.substring(message.indexOf(";")+ 1));
		s = s.replace("[", "");
		s = s.replace("]", "");
		s = s.replace(" ", "");

		n = Arrays.asList(s.split(","));
		
		/*for(int x=0; x < n.size(); ++x){
			GUI.possibilities[x] = n.get(x);
		}
		*/
		String s = GUI.displayMessage("Please select a different character.");
		player = null;
		player = new Player(GUI.createPlayer());
		
		SEND("AddPlayer:"+ player.getProfile().getType());
	}
}

///do not use this class until second iteration
//will have main class that connects to server
	//server will start the game