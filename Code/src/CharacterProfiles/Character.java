package CharacterProfiles;

import Model.*;


//this is the generic class
public class Character {
	
	public enum Special {AIM, STAMINA, FEAR, REPUTATION, SHORT_LEGS, CAVE_KNOWLEDGE, ELUSIVENESS, ARCHER, BARTER, CLEVE};
	
	private String type;	//this is just their name, will be used to compare between them
	
//	Location [] startSpot = new Location[3];			//this will be the location on the map where this character starts, it will be specified in its relevent class
//!!!!!!!!!startSpot should be changed to different type once we know how board is organized
	//this will be the startSpot at the beginning
	protected int currentLocation;//integer value of mapTiles
	
	int weight;		//this represents character's weight/vulnerability. 1=Light, 2=Medium, 3=Heavy
	
	
	//Trading Relationships
	String [] allyTrading = new String[1];
	String [] friendlyTrading = new String[4];
	String [] unfriendlyTrading = new String[2];
	String [] enemyTrading = new String[1];
//!!!!!!!!might be able to change to type of character later	
	
	//Development are the stages of the characters life, only used in the optional rules
	protected Weapon weapon = new Weapon();		//weapon moved to individual profiles
	protected Armor [] defense = new Armor[3];//armor
	
	//Combat chits, should have 12, 4 of each
	CombatChit action1 = new CombatChit();
	CombatChit action2 = new CombatChit();
	CombatChit action3 = new CombatChit();
	
	
	//Auto generated
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public int getCurrentLocation() {
		return currentLocation;
	}
	
	public Armor [] getDefense() {
		return defense;
	}
	public Armor getDefense(int i) {
		return defense[i];
	}
	
	public void setDefense(Armor [] defense) {
		this.defense = defense;
	}
	

}
