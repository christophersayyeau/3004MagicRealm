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
		//population for dwellings and ghosts handled in each valley's constructor
		//placing of characters handled at game start
		
		System.out.println("HANDLE THE VISITORS");
		//setUp6Visitors(); //DONT HANDLE UNTIL GAME WORKS===EXTRA
		
		//putting the counters will be handled in the mapTiles
		
		//map.populateNatives();
		//map.populateDenizens();
		//map.populateETC
	}

	
	
	public void startGame(Player player1) {
		System.out.println("STARTING THE GAME");
			
		map.moveCharacters(player1, player1.getCurrentLocation());//start position
		
		//map.moveCharacters(player1, 1);//testing moving
		
		//update GUI for all players
		player1.view.Refresh();
		
		System.out.println("Starting FIRST ENCOUNTER: TREASURE HUNT");
		/*The FIRST ENCOUNTER introduces moving, hiding, searching and
		trading. Monsters and natives can appear on the map, but there is no
		combat. see page 10 of 2nd edition*/
		
		
		//For the sake of specifying how the game ends in this first Iteration, we will NOT have players specify victory points but instead I am making up the following rule: the game ends after one month (28 days) and the winner is the player with the highest number of victory points (where we score victory points as per the rules):
						//player1.recordNumPointsWinGame();
						/*1.1 Each player plays the part of one character in the game. He controls
						that character�s pieces and uses that character�s character counter to
						represent him on the map. The characters compete in accumulating �Great
						Treasures�, FAME points, NOTORIETY points and GOLD points. Before
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
			System.out.println("BIRDSONG");
			
			player1.recordTurn();
			/*
			all of the characters secretly and simultaneously
			record what they will do during their turns. When each character does his
			turn, he must do it exactly as he recorded it.
			
			He can use his turn to	move, hide, search, trade and rest.

			When each character does his turn, he must do it exactly as he recorded it.
			 */
			
			System.out.println("SUNRISE");
			//if it is a weekday
			if( day%7 != 0){
				//map.populateDenizens(); not sure if supposed to create new ones or just move the curent ones
				//map.denizensProwling();
			
				System.out.println("Who is Prowling today?");
			//after 7 days	
			}else{
				System.out.println("Return monsters and natives to start positions");
				//map.returnNatives();
			}
			
			System.out.println("DAYLIGHT");
			//players go in random order
			System.out.println("player1 is first characeer today");
			player1.doTurn();
			
			
			System.out.println("SUNSET");
			//determine wich clearings have characters
			//FLOWERS OF REST people wake up
			//all day spells expire
			
			System.out.println("EVENING");
			//randomize which clearings with characters go first
			//combat is resolved//does not apply in first iteration
			
			player1.rearangeBelongings();
			player1.trade();//trade with other characters in clearing
			
			System.out.println("MIDNIGHT");
			//all map chits go face down?
			/*
			 All face up map chits (except the �LOST CITY� and �LOST
			CASTLE� chits) turn face down. Face up Site chits are put in their clearings
			before they turn face down. If it is the last day of the month, the game ends and
			the players calculate their scores.
			*/
			System.out.println("Do something with map chits");
			System.out.println("Weapons become unalerted");
			System.out.println("active Potions need to be expired here");
			System.out.println("Chapel removes curses");
			System.out.println("Combat Spells expire");
			System.out.println("Permanent spells fall inert, terms of hire expire, mission and campaign chits expire. ");
			
			System.out.println("Day " + day + "is now over.");
			day++;	//end of day
		}
		System.out.println("Game is now over");
		
		int finalScore = player1.calculateScore();
		System.out.println("Player 1 got " + finalScore);
	}
}
