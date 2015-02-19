package Control;

public class Server {
	static Game game;
	
	public static void main(String args[]){
		//We 'll eventually be networking so the client's will have to connect with server which will call the game
		//FOr now the server will create the players
		
		Player player1 = new Player();
				
		game = new Game();	
		game.createPlayers();
		//game.startGame(player1);
		
		//TODO not sure what you guys did but the game needs to start here somehow
	}
	
}
