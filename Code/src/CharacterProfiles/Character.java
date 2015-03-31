package CharacterProfiles;

import Model.*;


//this is the generic class
public class Character {
	
	public enum Special {AIM, STAMINA, FEAR, REPUTATION, SHORT_LEGS, CAVE_KNOWLEDGE, ELUSIVENESS, ARCHER, BARTER, CLEVE};
	
	private String type;	//this is just their name, will be used to compare between them
	
	String [] startSpots = new String[1];			//this will be the location on the map where this character starts, it will be used to choose where to start
	
	
	//this will be the startSpot at the beginning
	protected int currentLocation;//integer value of mapTile/clearing
	
	protected int weight;		//this represents character's weight/vulnerability. 1=Light, 2=Medium, 3=Heavy
	public int getVulnerability(){
		return weight;
	}
	
	
	int fame = 0;		//players fame value, can be negative
	int notoriety = 0;	//players notoriety, can be negative
	private int gold = 10;	//players start out with 10 gold, can't be negative
	
	//this will hold all items character is holding but not using: treasure, deadCharacters weapons and armors
	public Items [] belongings = new Items[0];
	
	//Trading Relationships
	String [] allyTrading = new String[1];
	String [] friendlyTrading = new String[4];
	String [] unfriendlyTrading = new String[2];
	String [] enemyTrading = new String[1];
	
	//Development are the stages of the characters life, only used in the optional rules
	protected Weapon weapon = new Weapon();		//weapon moved to individual profiles
	protected Armor [] defense = new Armor[0];//armor
	public boolean shieldActive = false;//used in combat to determine if ask direction
	
	protected Special [] specials = new Special[2];
	
	//Combat chits, should have 12, 4 of each
	public CombatChit action1 = new CombatChit();
	public int action1Num = 4;
	public CombatChit action2 = new CombatChit();
	public int action2Num = 4;
	public CombatChit action3 = new CombatChit();
	public int action3Num = 4;

	private boolean foughtToday = false;
	public boolean getFoughtToday(){
		return foughtToday;
	}
	public void setFoughtToday(boolean s){
		foughtToday = s;
	}
	
	public boolean amazonUsed = false;//only affects amazons
	public boolean elfUsed = false;
	public boolean cloakUsed = false;
	public boolean glassesUsed = false;
	
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
	public String[] getStartLocations() {
		return startSpots;
	}
	
	//following used for final score
	public int getGreatTreasure() {
		//count total number of great treasures not their value
		Treasure temp = new Treasure();
			
		int num = 0;
		for(int a=0; a< belongings.length; a++){
			System.out.println("The Class is " + belongings[a].getClass());
			System.out.println("Compared to " + temp.getClass());
			//is it a treasure
			if(belongings[a].getClass().equals(temp)){
				temp = (Treasure) belongings[a];
				if(temp.isGt_Treasure())
					num++;
			}
		}
		return num;	//returns total number of greatTreasures
	}
	public int getFame() {
		return fame;
	}
	public void setFame(int newFame){
		fame = newFame;
	}
	public int getNotoriety() {
		return notoriety;
	}
	public void setNotoriety(int newNotoriety){
		notoriety = newNotoriety;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void resetFight() {
		foughtToday = false;
		
		//actionChits are reset during rest in Player.java
		
		//reset the used values here for new day
		amazonUsed = false;
		elfUsed = false;
		cloakUsed = false;
		glassesUsed = false;
	}
	
	public boolean haveCloak() {
		//loop through his stuff
		for(int a=0; a<belongings.length; a++){
			if(belongings[a].name.compareTo("Cloak_of_Mist") == 0){
				return true;//found it
			}
		}
		return false;//could not find them
	}
	public boolean haveGlasses() {
		//loop through his stuff
		for(int a=0; a<belongings.length; a++){
			if(belongings[a].name.compareTo("Magic_Spectacles") == 0){
				return true;//found it
			}
		}
		return false;//could not find them
	}

}
