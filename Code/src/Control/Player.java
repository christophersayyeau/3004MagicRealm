
package Control;

import View.GUI;
import CharacterProfiles.*;
import CharacterProfiles.Character;

public class Player {

	GUI view;
	Character profile;
	Boolean hidden = true;//whether the character is hidden or not
	
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
		
		//create window for user
		view = new GUI();
	}

	public void doTurn() {
		System.out.println("Start Turn");
		hidden = false;
		
		//now do turn as based on what he recorded
		//go through each phase that he recorded
		while(phasesForToday > 0 ){
			rearangeBelongings();
			trade();
			
			doAction();//playing action chits as needed
			
			System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
			System.out.println("if not player can block monsters that appear or move to his clearing");			
		
			phasesForToday--; //go to the next phase
		}
		
		
		System.out.println("Turn over");//because he finished or was blocked
		
		System.out.println("Prowling monsters in tile who have not yet blocked or been blocked move to his clearing");
		System.out.println("Summon new denizens");
		System.out.println("Dwelling Summon new prowling natives");
		System.out.println("IF native leader, site card or faceup site chit in clearing = summon prowling visistro");
		System.out.println("If unhidden, mapchits in tile ->face up, substitue chits exchanged, other map chits summon new monsters from apperance chart");
		
		System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
		System.out.println("if not player can block monsters that appear or move to his clearing");
	}

	
	
	//handles the action recorded during birdsong and activated during daylight
	private void doAction() {
		System.out.println("DO THE ACTION HERE");
		
	}

	public int getCurrentLocation() {
		return profile.getCurrentLocation();
	}

	public void recordNumPointsWinGame() {
		// TODO Auto-generated method stub
		System.out.println("Usre now picks scoring method");
	}

	public void recordTurn() {
		//get 2 phases standard
		//if not in caves get an extra 2, unless your a dwarf
		
		// TODO Auto-generated method stub
		System.out.println("Usre now makes a turn");
	}

	public void rearangeBelongings() {
		// TODO Auto-generated method stub
		System.out.println("User can now fix belonging");
	}

	public void trade() {
		// TODO Auto-generated method stub
		System.out.println("User can now trade with others in clearing");
	}

	public int calculateScore() {
		// TODO Auto-generated method stub
		System.out.println("Now calculate score based on all those factors");
		return -1;
	}

	public Character getProfile() {
		return profile;
	}

	public void setCurrentLocation(int newLocation) {
		profile.setCurrentLocation(newLocation);
	}
}
