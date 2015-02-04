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
		System.out.println("Built the Map, don't forget to populate later");
		//map.populateNatives();
		//map.populateTreasures();
		//map.populateETC
	}

	
	
	public void startGame(Player player1) {
		System.out.println("STARTING THE GAME");
		map.moveCharacters();	//put the characters at their starting positions
		
		//update GUI for all players
		player1.view.Refresh();
		
	}
}
