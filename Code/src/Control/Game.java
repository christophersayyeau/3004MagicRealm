package Control;

import Model.Map;
import View.*;

public class Game {
	
	//need these for hotseat play
	GUI view;
	ServerGUI sgui;
	public int numOfPlayers;
	
	Map map;
	
		
	//constructor
	Game(){
		numOfPlayers = 0;
		//build the map
		map = new Map();
		map.build();
		
		//get number of players + set gui
		view = new GUI();
		sgui = new ServerGUI(this);
		sgui.getPlayers();
		System.out.println(numOfPlayers);
		
		
		//population for dwellings and ghosts handled in each valley's constructor
		//placing of characters handled at game start
		
				System.out.println("HANDLE THE VISITORS LATER");
				//setUp6Visitors(); //DONT HANDLE UNTIL GAME WORKS===EXTRA
				/*
				 * Visitors are really not essential but if you have the time, we will you the following: 
						- warlock appears with the company
						- crone appears with the woodfolk
						- shaman appears with the patrol
						- scholar appears with the lancers
				 */
		
		//putting the counters will be handled in the mapTiles
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
		
		//game lasts for 28 days(month)
		while(day<=28){
			//using the 3rd edition rules for the contents of a day
		System.out.println("BIRDSONG");
					
			/*
			all of the characters secretly and simultaneously
			record what they will do during their turns. When each character does his
			turn, he must do it exactly as he recorded it.
			He can use his turn to	move, hide, search, trade and rest.
			When each character does his turn, he must do it exactly as he recorded it.
			 */
			player1.recordTurn();
			
		System.out.println("SUNRISE");
			//if it is a weekday
			if( day%7 != 0){
				//die determines which denizen is prowling
				map.denizensProwling();//technically it is a row thing on a chart we don't have, but we will give it a 1/6 chance
			
			//after 7 days	
			}else{
				//System.out.println("Return monsters and natives to start positions");
				map.returnDenizensToStart();	//return monsters and ghosts to starting clearing
			}
			
		player1.view.Refresh();
			
		System.out.println("DAYLIGHT");
			//players go in random order
			System.out.println("player1 is first character today");
			player1.doTurn(map);
			player1.view.Refresh();	
			
		System.out.println("SUNSET");
			//determine which clearings have characters
					//FLOWERS OF REST people wake up
					//all day spells expire
			
		System.out.println("EVENING");
			//randomize which clearings with characters go first
			//combat is resolved//does not apply in first iteration
			
			player1.rearangeBelongings();
			player1.trade(map);//trade with other characters in clearing
			player1.view.Refresh();
			
		System.out.println("MIDNIGHT");
			/*
			 All face up map chits (except the �LOST CITY� and �LOST
			CASTLE� chits) turn face down. Face up Site chits are put in their clearings
			before they turn face down.
			*/
			//System.out.println("Hide Map chits");
			player1.view.hideMapChits();
			
			//System.out.println("Weapons become unalerted");
			player1.getProfile().getWeapon().setUnAlert();
			
			System.out.println("active Potions need to be expired here");
				//System.out.println("Chapel removes curses");
				//System.out.println("Combat Spells expire");
				//System.out.println("Permanent spells fall inert, terms of hire expire, mission and campaign chits expire. ");
			//turn off monsters
			map.denizensProwlingStop();//not sure if needed, couldnt hurt though
			player1.view.Refresh();

			
			//end of day
			System.out.println("Day " + day + "is now over.");
			day++;	
		}
		
		//end game and calculate score
		System.out.println("Game is now over");
		
		int finalScore = player1.calculateScore();
		System.out.println("Player 1 got " + finalScore);
	}
}
