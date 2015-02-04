
package Control;

import CharacterProfiles.Dwarf;

public class Player {

	//constructor
	Player(){
		
		//for simplicity we will set the first character to 
		Dwarf profile = new Dwarf();
		System.out.println("Building a " + profile.getType() + " Player");
		//pick you character
		//Character.pickCharacter();
	}
}
