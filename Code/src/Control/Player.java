
package Control;

import CharacterProfiles.Dwarf;
import CharacterProfiles.Swordsman;

public class Player {

	//constructor
	Player(){
		
		//for simplicity we will set the first character to 
		Swordsman profile = new Swordsman();
		System.out.println("Building a " + profile.getType() + " Player");
		//pick you character
		//Character.pickCharacter();
	}
}
