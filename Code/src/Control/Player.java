
package Control;

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
	
	public int getPhasesForToday() {
		return phasesForToday;
	}
	public void setPhasesForToday(int phasesForToday) {
		this.phasesForToday = phasesForToday;
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
		System.out.println("Stats: " + profile.getWeapon() + "  "+ profile.getDefense(0));
	}

		
	void doAction() {
		//handles the action recorded during birdsong and activated during daylight
		//If he is unable to do an activity, it is cancelled and the phase is treated as a blank phase. The rest of his turn is not affected. An activity is cancelled if it violates the rules governing that activity.
		//When he does a blank phase, he does no activity. The blank phase can still be used for trading, rearranging items, and blocking.
		// TODO finish record action first
		System.out.println("DO THE ACTION HERE");
		
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
