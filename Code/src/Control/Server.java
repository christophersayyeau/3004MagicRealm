package Control;

public class Server {
	static Game game;
	
	public static void main(String args[]){
		//We 'll eventually be networking so the client's will have to connect with server which will call the game
		//FOr now the server will create the players
		
		//Player player1 = new Player();//no longer needed since gui does it
				
		game = new Game();	
		game.createPlayers();
		
		//now start the game
		game.startGame();
		
	}
	
}
