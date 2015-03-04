package Control;

import View.GUI;

public class Server {
	
	
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
	
}
