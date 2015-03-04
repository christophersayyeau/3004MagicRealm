
package Control;

import Model.Die;
import Model.Map;
//import View.GUI;
import CharacterProfiles.*;
import CharacterProfiles.Character;

public class Player {

	//commented out view for hotseat game
	//GUI view;
	
	Character profile;
	boolean hidden = true;//whether the character is hidden or not
	
	private int phasesForToday = -1;//determined in recordTurn
	int numPhases = 0;
	
	public int getPhasesForToday() {
		return phasesForToday;
	}
	public void setPhasesForToday(int phasesForToday) {
		this.phasesForToday = phasesForToday;
	}

	//these will store the actions the user wants to do in order
	private String [] phaseActions = new String[5];//we can increase it later
	public String [] getPhaseActions() {
		return phaseActions;
	}
	public String  getPhaseAction(int a) {
		return phaseActions[a];
	}
	public void setPhaseActions(String phaseActions1) {
		this.phaseActions[numPhases] = phaseActions1;
		numPhases++;
	}
	public void setPhaseActions(String phaseActions1, int a) {
		numPhases++;
		this.phaseActions[a] = phaseActions1;
	}
	//constructor
	/*public Player(){
		
		//for simplicity we will set the first character to 
		Dwarf profile1 = new Dwarf();
		profile = profile1;
		
		System.out.println("Built a " + profile1.getType() + " Player");
		System.out.println("Stats: " + profile1.getWeapon() + "  "+ profile1.getDefense(0));
		
		//pick you character
		//Character.pickCharacter();
		//build later when game works better
		
		//create window for user, then display it
		//view = new GUI();	
	}*/
	
	//constructor for choosing type
	public Player(String s){
		if(s == "Amazon"){
			profile = new Amazon();
		}
		else if(s == "Black Knight"){
			profile = new BlackKnight();
		}
		else if(s == "Captain"){
			profile = new Captain();
		}
		else if(s == "Dwarf"){
			profile = new Dwarf();
		}
		else if(s == "Elf"){
			profile = new Elf();
		}
		else if(s == "Swordsman"){
			profile = new Swordsman();
		}
		
		System.out.println("Built a " + profile.getType() + " Player");
		//System.out.println("Stats: " + profile.getWeapon() + "  "+ profile.getDefense(0));
	}

		
	void doAction(String action, Map map, Game game) {
		
		//handles the action recorded during birdsong and activated during daylight
		//If he is unable to do an activity, it is cancelled and the phase is treated as a blank phase.
		//When he does a blank phase, he does no activity.
		if(action == null){
			return;
		}
		
		//determine what the action is
		//if((action.substring(0, 4)).compareTo("Move")==0){//if move action
		if(action.compareTo("Move") == 0){//if move action
			//THere are rules to handle moving through mountains+caves
			
			//int newLocation = Integer.parseInt(action.substring(5));
			int newLocation = game.view.getNewLocation();
			
			//check to see if they can
			if( map.canHeMove(profile.getCurrentLocation(), newLocation, this) ){
				//there are rules about how much weight
				map.moveCharacters(this, newLocation);//if yes then move
			}else{
				System.out.println("Can't Move There, phase wasted");
			}
			
		}else if(action.compareTo("Hide")==0){//if hide action
			//roll on hide table, only a 6 does nothing
			if(Die.dieRoll() != 6)	this.hidden = true;
			
			
		}else if(action.compareTo("Trade")==0){//if Trade action
			//call of trade function
			game.view.trading(map, this);
			
		}else if(action.compareTo("Search")==0){//if search action
			//where are you searching//can only search his own clearing using locate
			int currentTile = profile.getCurrentLocation()/10-1;
			
			if(map.getMapTile(currentTile).treasure != null){//this to check oif there is actually a treasure there to find
				
				//with which table
				String choice = game.view.whichSearchTable();//locate+loot
				
				
				if(choice.compareTo("Locate") == 0){//using locate table
					int result = Die.dieRoll();
					switch (result){
						case 1:  	game.view.displayTreasure(currentTile);//technically you can choose but that is dumb
									map.getMapTile(currentTile).treasure.found = true;
						break;
						case 2:  	//display all passages and mentally note that treasure
						break;
						case 3:  	//display all passages
						break;
						case 4:  	game.view.displayTreasure(currentTile);
									map.getMapTile(currentTile).treasure.found = true;
						break;
						//5 and 6 do nothing
					}
					//When he discovers a roadway or treasure site, he is the only one who discovers it; it remains concealed from others, who must discover it on their own if they wish to use it.  He does not have to admit whether he actually discovers a treasure site. He must reveal what he rolled, but he does not have to reveal whether there is a treasure site chit in his clearing.
					//Once an individual discovers a hidden path, secret passage or treasure site, he never has to discover it again. He keeps a record of each discovery by crossing it off the Discoveries list on his Personal History sheet.
					//Once he has discovered a treasure site, he can search it for treasure whenever he is in its clearing.
					
				}else if(choice.compareTo("Looting") == 0){//using loot table
	
					//need to have located it first before trying to loot
					if (map.getMapTile(currentTile).treasure.found){
						//if you roll over the number of treasures there you get nothing
						int result = Die.dieRoll();
						switch (result){
							case 1:  	map.giveTreasure(this, map.getMapTile(currentTile).treasure);
							break;
	//						case 2:  	2nd//since there is only going to be one treasure
	//						break;
	//						case 3:  	3rd
	//						break;
	//						case 4:  	4th
	//						break;
	//						case 5:  	5th
	//						break;
	//						case 6:  	6th
	//						break;
						}
					}
				}
			}else{
				System.out.println("There is no treasure to find");
			}
			
		}else if(action.compareTo("Rest")==0){//if rest action
			System.out.println("Nothing is Done here in this iteration");
			
		}	
	}

	
					/*
					 * will not be used in this iteration
						public void recordNumPointsWinGame() {
							System.out.println("Usre now picks scoring method");
						}
					*/
	
	public void rearangeBelongings() {
		//System.out.println("User can now rearrange belonging, this is meainingless in this iteration");
	}

	public boolean isThereOthersInCLearing(Map map, int currentTile, int currentClearing) {
		//use this to determine if there are other characters in the clearing, remebere we only have a single character right now
		// map.getMapTile(currentTile).clearing[currentClearing].playersInClearing;
		
		//then check if there are natives
		if(map.getMapTile(currentTile).clearing[currentClearing].guardHouse) return true;
		if(map.getMapTile(currentTile).clearing[currentClearing].chapel) return true;
		if(map.getMapTile(currentTile).clearing[currentClearing].house) return true;
		if(map.getMapTile(currentTile).clearing[currentClearing].inn) return true;
		//guardhouse has guards in darkvalley
		//chapel has order in awfulvalley
		//house has soldiers in curstvalley
		//inn has rogues in badvalley
		return false;
	}

	public int calculateScore() {
		/*
	For the sake of specifying how the game ends in this first Iteration, 
	we will NOT have players specify victory points but instead I am making up the following rule: 
		the game ends after one month (28 days) and the winner is the player with the highest number of victory points 
		(where we score victory points as per the rules):

    1 point per great treasure, 1 point per 2 learnt spells (but I repeat I think you should forget about learning spells)

    1 point for each 10 points of fame, 1 point for each 20 points of notoriety, and 1 point for each 30 gold
		*/
		System.out.println("Now Calculating Score");
		int score = 0;
		
		score += this.getProfile().getGreatTreasure();//1 point for each	
		score += this.getProfile().getFame()/10;//1 point for every 10 points of fame
		score += this.getProfile().getNotoriety()/20;//1 point for every 20 points of notoriety
		score += this.getProfile().getGold()/30;//1 point for every 30 points of gold
		return score;
	}

	public Character getProfile() {
		return profile;
	}

	public int getCurrentLocation() {
		return profile.getCurrentLocation();
	}
	public void setCurrentLocation(int newLocation) {
		profile.setCurrentLocation(newLocation);
	}
}
