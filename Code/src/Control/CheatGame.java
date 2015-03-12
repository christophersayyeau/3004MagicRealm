package Control;

import Model.Map;
import View.GUI;

public class CheatGame extends Game {
	//place the dwellings and ghosts manually
	//place sounds and warnings manually each time character ends in a tile whose counters not yet assigned.
		//must be capable of distinguishing between assigned counters and not assigned
	//capable of picking roll of dice
	
	//need these for hotseat play
	GUI view;
		
	//player related
	Player players[];
	public int numOfPlayers;
		
	Map map;
	
	
	public CheatGame(){
		//Constructor
		super(true);//call the useless constructor so that nothing happens
		//now get to the constructing
		
		numOfPlayers = 0;
		//build the map
		view = new GUI(this, map);
		map = new Map(view);
		map.buildCheat();
		view.updateMap(map);
		
		//get number of players + set gui
		numOfPlayers = view.numOfPlayers();
	}
	
	
	public void startCheatGame() {
		System.out.println("STARTING THE CHEAT GAME");
		
		for(int a =0 ; a<numOfPlayers; a++){
			map.moveCharacters(players[a], players[a].getCurrentLocation());//start position, TODO test if characters appear in Inn			
		}
		
		//update GUI for all players
		view.Refresh("Cheating Day 1 BirdSong");	
		
		System.out.println("Starting FIRST ENCOUNTER: TREASURE HUNT");
		
		int day = 1;	//will use to count how many days have passed
		
		//game lasts for 28 days(month)
		while(day<=28){
		
			System.out.println("BIRDSONG");
			
			for(int a =0 ; a<numOfPlayers; a++){
				int phasesToday = 2;//get 2 phases standard
				//if not in caves get an extra 2, unless your a dwarf
				//compare the type, if it is not a cave
				if(map.getMapTile( players[a].getCurrentLocation()/10-1).getType().compareTo("C") != 0 ){
					//if not a dwarf
					if(players[a].getProfile().getType().compareTo("Dwarf") != 0){
						phasesToday = 4;
					}
				}
				players[a].setPhasesForToday( phasesToday );//figured out the number of phases
				
				//now build the turn in the GUI
				view.recordTurn(players[a], phasesToday, map);
			}
			
			System.out.println("SUNRISE");
			//if it is a weekday
			if( day%7 != 0){
				//System.out.println("Not Day"+day%7);
				//die determines which denizen is prowling
				map.denizensProwlingCheat();//technically it is a row thing on a chart we don't have, but we will give it a 1/6 chance
			
			//after 7 days	
			}else{
				//System.out.println("Return monsters and natives to start positions"+day%7);
				//map.returnDenizensToStartCheat();	//return monsters and ghosts to starting clearing
				map.returnDenizensToStart();
			}
			
			view.Refresh("Cheating Day "+day+ ": Daylight");					
			System.out.println("DAYLIGHT");
			
			//players go in random order
			shufflePlayers(players);
			
			for(int a =0 ; a<numOfPlayers; a++){
				//System.out.println("player1 is first character today");
				doTurnCheat(players[a]);
				view.Refresh("Cheating Day "+day+ ": Daylight /n\nPlayer "+(a+1) + "'s turn");	
				
				players[a].numPhases = 0;//reset the phases
			}
			
			System.out.println("SUNSET");
			System.out.println("EVENING");
			//randomize which clearings with characters go first
			//combat is resolved TODO copy game version
			
			//handle the rest of acitivites
			for(int a =0 ; a<numOfPlayers; a++){
				players[a].rearangeBelongings();
				view.trading(map, players[a]);//trade with other characters in clearing
			}
			
			view.Refresh("Cheating Day "+day+ ": Midnight");	
			System.out.println("MIDNIGHT");
			
			view.hideMapChits();		
			
			//System.out.println("Weapons become unalerted");
			for(int a =0 ; a<numOfPlayers; a++){
				players[a].getProfile().getWeapon().setUnAlert();
			}
				
			//turn off monsters
			map.denizensProwlingStop();//not sure if needed, couldnt hurt though
			view.Refresh("Cheating Day "+(day+1)+ ": BirdSong");	
			
			//end of day
			System.out.println("Day " + day + "is now over.");
			day++;				
		}
		//end game and calculate score
		System.out.println("Game is now over");
		
		for(int a =0 ; a<numOfPlayers; a++){
			int finalScore = players[a].calculateScore();
			System.out.println("Player " + a + " got " + finalScore);
			
		}
		//display on main screen
		view.displayScore(players);
	}


	private void doTurnCheat(Player player) {
		System.out.println("Start Turn");
		player.hidden = false;
		
		int numPhases = 0;
		
		while(numPhases < player.getPhasesForToday() ){
			
			player.rearangeBelongings();
			view.trading(map, player);
			
			player.doActionCheat(player.getPhaseActions()[numPhases], map, this);//playing action chits as needed
			
			//blocking handled in iteration 2
			//System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
			//System.out.println("if not player can block monsters that appear or move to his clearing");			
		
			numPhases++; //go to the next phase
		}

		System.out.println("Turn over");//because he finished or was blocked
		
		//blocking in iteration 2
		//System.out.println("Prowling monsters in tile who have not yet blocked or been blocked move to his clearing");
		int currentTileNum = player.profile.getCurrentLocation()/10-1;
		//cycle the monsters in a tile
		for(int a = 0; a< map.getMapTile(currentTileNum).monstersInTile.length; a++){
			if(map.getMapTile(currentTileNum).monstersInTile[a] != null){
				//check to see if prowling		
				if(map.getMapTile(currentTileNum).monstersInTile[a].prowling){
					//move to the new clearing
					map.moveDenizen(map.getMapTile(currentTileNum).monstersInTile[a], currentTileNum, currentTileNum);
				}
			}
		}
		
		//if the user has not yet picked the value of sound and warning
		if(map.getMapTile(currentTileNum).getWarning() == null) {
			//set the new values
			map.getMapTile(currentTileNum).setSoundTreasureCheat(   view.getSoundTreasureCheat(  map.getMapTile(currentTileNum).getType() )   );
			map.getMapTile(currentTileNum).setWarning( view.getWarningCheat( map.getMapTile(currentTileNum).getType() )  );
		System.out.println("TESTING THE BUILDING OF TILECHITS IN CHEAT MODE: warning is "+ map.getMapTile(currentTileNum).getWarning().type);
		}
		
		view.revealMapChits(player.profile.getCurrentLocation()/10-1);//now reveal and replace chits
			//System.out.println("Dwelling Summon new prowling natives");
			//System.out.println("IF native leader, site card or faceup site chit in clearing = summon prowling visistro");

		//blocking in iteration 2
		//System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
		//System.out.println("if not player can block monsters that appear or move to his clearing");
		
	}
	
	/*Create players for hotseat
	 */
	public void createPlayers(){
		players = new Player[numOfPlayers];
		for(int i = 0; i < numOfPlayers; ++i){
			//ask user for which player type
			String s = view.createPlayer();		
			
			//create player
			players[i] = new Player(s);
			
			//handle start location
			int locale = view.chooseStart(players[i]);
			players[i].setCurrentLocation(locale);
		}
	}
}
