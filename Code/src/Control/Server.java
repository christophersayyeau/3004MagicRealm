package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import View.GUI;

public class Server {
	
	public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	public static ArrayList<String> CurrentPlayers = new ArrayList<String>();
	static int PORT = 9073;
	static int x;
	
	
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
	game.view.selectFightGear(game.players[0]);		//TODO erase this line after we know it is working
			//now start the game
			game.startGame();
		}
	}*/
	
	//networking code
	public static void main(String args[]) throws IOException {
		x = 0;
		try
		{
			ServerSocket SERVER = new ServerSocket(PORT);
			System.out.println("Waiting for clients...");
			
			InetAddress.getLocalHost();
			System.out.print(InetAddress.getLocalHost());
			GUI.showServerIP(""+InetAddress.getLocalHost());
			
			while(true)
			{
				Socket SOCK = SERVER.accept();
				ConnectionArray.add(SOCK);
			
				System.out.println("Client connected from: " + SOCK.getLocalAddress().getHostName());
				String s = AddPlayer(SOCK);
				
				
				ServerController gameServer = new ServerController(SOCK);
				Thread X = new Thread(gameServer);
				X.start();
			}
		}
		catch(Exception X) { System.out.print(X);}
	}
	
	public static String AddPlayer(Socket X) throws IOException
	{
		//Scanner INPUT = new Scanner(X.getInputStream());
		//String player = INPUT.nextLine();
		String player = Integer.toString(x++);
		CurrentPlayers.add(player);
		
		for(int i=1; i <= Server.ConnectionArray.size(); ++i)
		{
			Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
			OUT.println("#?!"+ CurrentPlayers);
			OUT.flush();
		}
		return player;
	}
	
}
