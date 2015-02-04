package Control;

import Model.Map;

public class Game {

	Map map;
		
	//constructor
	Game(){
		//build the map
		map = new Map();
		map.build();
		
		//put the counters, monsters, etc..
		map.populateNatives();
		map.populateTreasures();
		//map.populateETC
		
		//update GUI
		view.Refresh();
	}

	
	
	public void startGame(Player player1) {
		System.out.println("STARTING THE GAME");
		map.moveCharacters();	//put the characters at their starting positions
		
		//update GUI
		player1.view.Refresh();
		
	}
}
