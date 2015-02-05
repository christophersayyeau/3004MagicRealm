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
		
		System.out.println("Starting FIRST ENCOUNTER: TREASURE HUNT");
		/*The FIRST ENCOUNTER introduces moving, hiding, searching and
		trading. Monsters and natives can appear on the map, but there is no
		combat. see page 10 of 2nd edition*/
		
		player1.recordNumPointsWinGame();
		/*1.1 Each player plays the part of one character in the game. He controls
		that character’s pieces and uses that character’s character counter to
		represent him on the map. The characters compete in accumulating “Great
		Treasures”, FAME points, NOTORIETY points and GOLD points. Before
		the start of play, each character records the number of points he needs in
		each category to win the game. He gains these points by owning or selling
		weapons, armor, horses and Treasure cards. NOTE: Other ways of earning
		points are introduced in later ENCOUNTERS.*/
		
		//map already built so don't need to build it here, see constructor
		
		//map already populated, see constructor
		
		//each turn represents a day of time
		//each day is made of 6 periods(Birdsong, Sunrise, Daylight, Sunset, Evening and Midnight)
		int day = 1;	//will use to count how many days have passed
		//7 days a week, 28 days a month
		
		//game lasts for 28 days(month)
		while(day<=28){
			//using the 3rd edition rules for the contents of a day
			
			
			
			
			System.out.println("Day " + day + "is now over.");
			day++;	//end of day
		}
		System.out.println("Game is now over");
		
		int finalScore = player1.calculateScore();
		System.out.println("Player 1 got " + finalScore);
	}
}
