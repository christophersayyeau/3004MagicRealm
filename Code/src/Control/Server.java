package Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import View.ServerGUI;

public class Server {
	
	public static ArrayList<String> characters;
	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<Player> CurrentPlayers = new ArrayList<Player>();
	static int PORT = 9073;
	
	
	/*public static void main(String args[]){
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
	}*/
	
	//networking code
	public static void main(String args[]) throws IOException {
		characters = new ArrayList<String>();
		characters.add("Amazon");
		characters.add("Black Knight");
		characters.add("Captain");
		characters.add("Dwarf");
		characters.add("Elf");
		characters.add("Swordsman");
		try
		{
			ServerSocket SERVER = new ServerSocket(PORT);
			System.out.println("Waiting for clients...");
			
			InetAddress.getLocalHost();
			System.out.print(InetAddress.getLocalHost());
			ServerGUI.showServerIP(""+InetAddress.getLocalHost());
			
			while(true)
			{
				Socket SOCK = SERVER.accept();
				ConnectionArray.add(SOCK);
				
				PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
				OUT.println(characters);
				OUT.flush();
			
				System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());
				
				//AddPlayer(SOCK);
				
				
				ServerController gameServer = new ServerController(SOCK);
				Thread X = new Thread(gameServer);
				X.start();
			}
		}
		catch(Exception X) { System.out.print(X);}
	}
	
	public static String AddPlayer(Socket X) throws IOException
	{
		Scanner INPUT = new Scanner(X.getInputStream());
		//while(!INPUT.hasNext()){}
		String s = INPUT.nextLine();
		Player player = new Player(s);
		CurrentPlayers.add(player);
		characters.remove(player.getProfile().getType());
		
		for(int i=1; i <= Server.ConnectionArray.size(); ++i)
		{
			Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
			OUT.println("#?!"+ CurrentPlayers);
			OUT.flush();
		}
		INPUT.close();
		return player.getProfile().getType();
	}
}
