package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerController implements Runnable{
	public static ArrayList<Player> CurrentPlayers = new ArrayList<Player>();
	Socket SOCK;
	private Scanner INPUT;
	private PrintWriter OUT;
	String MESSAGE = "";
	Game g;
	
	ServerController(Socket X){
		this.SOCK = X;
	}
	
	public void CheckConnection() throws IOException
	{
		if(!SOCK.isConnected())
		{
			for(int i=1; i<=Server.ConnectionArray.size(); ++i)
			{
				if(Server.ConnectionArray.get(i) == SOCK)
				{
					Server.ConnectionArray.remove(i);
				}
			}
			
			for(int i=1; i<=Server.ConnectionArray.size();++i)
			{
				Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
				PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
				TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + " disconnected!");
				TEMP_OUT.flush();
				System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + " disconnected!");
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void run()
	{
		try
		{
			try
			{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				
				while(true)
				{
					CheckConnection();
					
					if(!INPUT.hasNext()) {
						System.out.println(!INPUT.hasNext());
						return;}
					
					MESSAGE = INPUT.nextLine();
					
					System.out.println("Client said: " + MESSAGE);
					handleMessage(MESSAGE);
					
					for(int i=1; i <=Server.ConnectionArray.size(); ++i)
					{
						Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
						PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
						TEMP_OUT.println(MESSAGE);
						TEMP_OUT.flush();
						System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getLocalHost());
						
					}
				}
			}
			finally
			{
				System.out.println("Closing SOcket");
				SOCK.close();
				System.exit(-1);
			}
		}
		catch(Exception X) { System.out.print(X);}
	}
	
	void handleMessage(String message){
		if(message.contains("STARTGAME"))
		{
			startGame();
			
		}
		else if(message.contains("ADDPLAYER"))
		{
			String c = (message.substring(message.indexOf(":")+1));
			if(CurrentPlayers.size() == 0){
				CurrentPlayers.add(new Player(c));
				return;
			}
			for(int i=0; i < CurrentPlayers.size(); ++i)
			{
				if(CurrentPlayers.get(i).getProfile().getType().equals(c))
				{
					OUT.println("ChooseChar:");
					OUT.flush();
				}
				if(CurrentPlayers.size() == i)
				{
					CurrentPlayers.add(new Player(c));
					++i;
				}
			}
		}
	}
	
	void startGame(){
		g = new Game(CurrentPlayers.size());
		String s = "";
		for(int i=0; i < CurrentPlayers.size(); ++i)
		{
			s += CurrentPlayers.get(i).getProfile().getType() + ",";
		}
		s = s.substring(0, s.length()-1);
		
		for(int i=1; i <=Server.ConnectionArray.size(); ++i)
		{
			OUT.println("PLAYERS:"+s);
			OUT.flush();
		}
		
	}
}
