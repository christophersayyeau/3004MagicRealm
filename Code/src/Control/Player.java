
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
	
	int phasesForToday = -1;//determined in recordTurn
	//constructor
	public Player(){
		
		//for simplicity we will set the first character to 
		Dwarf profile1 = new Dwarf();
		profile = profile1;
		
		System.out.println("Built a " + profile1.getType() + " Player");
		System.out.println("Stats: " + profile1.getWeapon() + "  "+ profile1.getDefense(0));
		
		//pick you character
		//Character.pickCharacter();
		//TODO later when game works better
		
		//create window for user, then display it
		//view = new GUI();	
	}
	
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

	public void doTurn(Map map) {
		System.out.println("Start Turn");
		hidden = false;
		
		//now do turn as based on what he recorded
		//go through each phase that he recorded
		while(phasesForToday > 0 ){
			rearangeBelongings();
			trade(map);
			
			doAction();//playing action chits as needed
			
			//blocking handled in iteration 2
			//System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
			//System.out.println("if not player can block monsters that appear or move to his clearing");			
		
			phasesForToday--; //go to the next phase
		}
		
		
		System.out.println("Turn over");//because he finished or was blocked
		
		//blocking in iteration 2
		//System.out.println("Prowling monsters in tile who have not yet blocked or been blocked move to his clearing");
		int currentTileNum = (this.profile.getCurrentLocation()/10)-1;
		//cycle the monsters in a tile
		for(int a = 0; a< map.getMapTile(currentTileNum).monstersInTile.length; a++){
			//check to see if prowling
			if(map.getMapTile(currentTileNum).monstersInTile[a].prowling){
				//move to the new clearing
				map.moveDenizen(map.getMapTile(currentTileNum).monstersInTile[a], this.profile.getCurrentLocation()%10-1, currentTileNum);
			}
		}
		
		view.revealMapChits(profile.getCurrentLocation()/10-1);//now reveal and replace chits
				
				//System.out.println("Dwelling Summon new prowling natives");
				//System.out.println("IF native leader, site card or faceup site chit in clearing = summon prowling visistro");
		
		//blocking in iteration 2
		//System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
		//System.out.println("if not player can block monsters that appear or move to his clearing");
	}

		
	private void doAction() {
		//handles the action recorded during birdsong and activated during daylight
		// TODO finish record action first
		System.out.println("DO THE ACTION HERE");
		
	}

	
					/*
					 * will not be used in this iteration
						public void recordNumPointsWinGame() {
							System.out.println("Usre now picks scoring method");
						}
					*/
	
	public void recordTurn() {
		//TODO
		/*
		all of the characters secretly and simultaneously
		record what they will do during their turns. When each character does his
		turn, he must do it exactly as he recorded it.
		He can use his turn to	move, hide, search, trade and rest.
		When each character does his turn, he must do it exactly as he recorded it.
		 */
		//get 2 phases standard
		//if not in caves get an extra 2, unless your a dwarf
		
		System.out.println("User now builds his turn");	
		view.recordTurn();
		//phasesForToday; //needs to be set to the number of phases for the turn
	}

	public void rearangeBelongings() {
		//System.out.println("User can now rearrange belonging, this is meainingless in this iteration");
	}

	public void trade(Map map) {
		// For now we assume he trades meaningless baubles and get some gold
		
		int currentTile = profile.getCurrentLocation()/10-1;
		int currentClearing = profile.getCurrentLocation()%10-1;
		
		//first determine if there is anyone else in clearing
		if(isThereOthersInCLearing(map, currentTile, currentClearing)){
			//ask user if he wants to trade
			if(view.trading()){
				System.out.println("User traded some stuff and got 10 gold");
				profile.setGold(profile.getGold()+10);
			}
		}
	}

	private boolean isThereOthersInCLearing(Map map, int currentTile, int currentClearing) {
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
