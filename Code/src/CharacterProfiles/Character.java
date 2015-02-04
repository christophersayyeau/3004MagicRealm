package CharacterProfiles;

import Model.CombatChit;
import Model.Weapon;


//this is the generic class
public class Character {
	private String type;	//this is just their name, will be used to compare between them
	
	Location [] startSpot = new Location[3];			//this will be the location on the map where this character starts, it will be specified in its relevent class
//!!!!!!!!!startSpot should be changed to different type once we know how board is organized
	
	int weight;		//this represents character's weight/vulnerability. 1=Light, 2=Medium, 3=Heavy
	
	
	//Trading Relationships
	String [] allyTrading = new String[1];
	String [] friendlyTrading = new String[4];
	String [] unfriendlyTrading = new String[2];
	String [] enemyTrading = new String[1];
//!!!!!!!!might be able to change to type of character later	
	
	//Development are the stages of the characters life, only used in the optional rules
	//Weapon weapon;		//weapon moved to individual profiles
	Defense [] defense = new Defense[3];//armor
	
	//Combat chits, should have 12, 4 of each
	CombatChit action1 = new CombatChit();
	CombatChit action2 = new CombatChit();
	CombatChit action3 = new CombatChit();
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
