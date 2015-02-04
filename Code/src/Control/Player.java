
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
		
		//create window foor user
		view = new GUI();
	}
}
