package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import View.GUI;

public class Server {
	
	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>(4);
	public static ArrayList<String> CurrentPlayers = new ArrayList<String>();
	
	
	public static void main(String args[]){
		//We 'll eventually be networking so the client's will have to connect with server which will call the game

		if(GUI.cheatMode()){//using cheatMode
			System.out.println("PLaying Game with Cheats");
			
			//build the game and select the players
			CheatGame game = new CheatGame();	
			game.createPlayers();
			
			//now start the game
			game.startCheatGame();
			
			
		}else{//play normally

			System.out.println("PLaying Game Normally");
			//build the game and select the players
			Game game = new Game();
			
			game.createPlayers();
			
			//now start the game
			game.startGame();
		}
	}
	
	//networking code
	/*public static void main(String args[]) throws IOException {
		try
		{
			int PORT = 9073;
			ServerSocket SERVER = new ServerSocket(PORT);
			System.out.println("Waiting for clients...");
			
			while(true)
			{
				Socket SOCK = SERVER.accept();
				ConnectionArray.add(SOCK);
			
				System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());
				String s = AddPlayer(SOCK);
				
				//create an instance of the game and start
				//Thread X = new Thread(GAME);
				//X.start();
			}
		}
		catch(Exception X) { System.out.print(X);}
	}
	
	public static String AddPlayer(Socket X) throws IOException
	{
		Scanner INPUT = new Scanner(X.getInputStream());
		String player = INPUT.nextLine();
		CurrentPlayers.add(player);
		
		for(int i=1; i <= Server.ConnectionArray.size(); ++i)
		{
			Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
			OUT.println("#?!"+ CurrentPlayers);
			OUT.flush();
		}
		return player;
	}*/
	
}
