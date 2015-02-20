package Control;

public class Server {
	static Game game;
	
	public static void main(String args[]){
		//We 'll eventually be networking so the client's will have to connect with server which will call the game

		//build the game and select the players
		game = new Game();	
		game.createPlayers();
		
		//now start the game
		game.startGame();
		
	}
	
}
