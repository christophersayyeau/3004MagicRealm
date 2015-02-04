package Control;

public class Server {

	
	void main(){
		//We 'll eventually be networking so the client's will have to connect with server which will call the game
		//FOr now the server will create the players
		
		Player player1 = new Player();
		
		Game game = new Game();
		
		game.startGame(player1);
	}
	
}
