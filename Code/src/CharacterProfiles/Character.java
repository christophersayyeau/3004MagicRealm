package CharacterProfiles;

import Model.*;


//this is the generic class
public class Character {
	
	public enum Special {AIM, STAMINA, FEAR, REPUTATION, SHORT_LEGS, CAVE_KNOWLEDGE, ELUSIVENESS, ARCHER, BARTER, CLEVE};
	
	private String type;	//this is just their name, will be used to compare between them
	
//	Location [] startSpot = new Location[3];			//this will be the location on the map where this character starts, it will be specified in its relevent class

	//this will be the startSpot at the beginning
	protected int currentLocation;//integer value of mapTile/clearing
	
	int weight;		//this represents character's weight/vulnerability. 1=Light, 2=Medium, 3=Heavy
	int fame = 0;		//players fame value, can be negative
	int notoriety = 0;	//players notoriety, can be negative
	private int gold = 10;	//players start out with 10 gold, can't be negative
	
	//Trading Relationships
	String [] allyTrading = new String[1];
	String [] friendlyTrading = new String[4];
	String [] unfriendlyTrading = new String[2];
	String [] enemyTrading = new String[1];
	
	//Development are the stages of the characters life, only used in the optional rules
	protected Weapon weapon = new Weapon();		//weapon moved to individual profiles
	protected Armor [] defense = new Armor[3];//armor
	protected Special [] specials = new Special[2];
	
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

	public void setCurrentLocation(int i) {
		currentLocation = i;
	}
	
	
	//following used for final score
	public int getGreatTreasure() {
		return 0;	//returns the total number of great treasures not their value
	}
	public int getFame() {
		return fame;
	}
	public int getNotoriety() {
		return notoriety;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
}
