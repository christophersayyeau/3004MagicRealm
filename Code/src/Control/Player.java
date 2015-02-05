
package Control;

import View.GUI;
import CharacterProfiles.*;

public class Player {

	GUI view;
	
	//constructor
	Player(){
		
		//for simplicity we will set the first character to 
		Swordsman profile = new Swordsman();
		System.out.println("Building a " + profile.getType() + " Player");
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
		while(){
			rearangeBelongings();
			trade();
			
			doAction();//playing action chits as needed
			
			System.out.println("if player unhidden all monsters who move to his clearing/apear auto block player");
			System.out.println("if not player can block monsters that appear or move to his clearing");			
		
			phase++ //go to the next phase
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
}
