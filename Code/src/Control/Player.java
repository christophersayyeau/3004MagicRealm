
package Control;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.ArrayUtils;
import Model.CombatChit;
import Model.Die;
import Model.Map;
import Model.MapTiles;
import Model.PlayerActions;
import View.GUI;
//import View.GUI;
import CharacterProfiles.*;
import CharacterProfiles.Character;

public class Player {

	public boolean startplacement = true;
	//victory point values
	int greatTreasureVicPoint;
	int fameVicPoint;
	int notorietyVicPoint;
	int goldVicPoint;
	//vicPoint Needed(above*#)
	int greatTreasureVicPointNeeded;
	int fameVicPointNeeded;
	int notorietyVicPointNeeded;
	int goldVicPointNeeded;
	
	static int instanceCounter = 0;
	int playernum;
	
	//commented out view for hotseat game
	//GUI view;
	
	//Players select their character icon
	ImageIcon amazonIcon = new ImageIcon("res/characters/amazon.png");
	ImageIcon bknightIcon = new ImageIcon("res/characters/black_knight.png");
	ImageIcon captainIcon = new ImageIcon("res/characters/captain.png");
	ImageIcon dwarfIcon = new ImageIcon("res/characters/dwarf.png");
	ImageIcon elfIcon = new ImageIcon("res/characters/elf.png");
	ImageIcon swordsmanIcon = new ImageIcon("res/characters/swordsman.png");
	
	Character profile;
	boolean hidden = true;//whether the character is hidden or not
	public boolean alive = true;//whether he is dead or not
	
//use these for combat
	public int effortThisRound = 0;//used to determine if they are too tired
	
	private String combatAttackDirection = null;	
	public String getCombatAttackDirection() {
		return combatAttackDirection;
	}
	public void setCombatAttackDirection(String combatAttackDirection) {
		this.combatAttackDirection = combatAttackDirection;
	}
	
	private CombatChit attack = null;
	public CombatChit getAttack() {
		return attack;
	}
	public void setAttack(CombatChit at) {
		this.attack = at;
	}

	private String shieldDirection = null;//will remain null if they have no shield to use
	public void setShieldDirection(String direct) {
		this.shieldDirection = direct;
	}
	public String getShieldDirection(){
		return shieldDirection;
	}
	
	private String evadeDirection = null;	
	public String getEvadeDirection() {
		return evadeDirection;
	}
	public void setEvadeDirection(String evadeDirection) {
		this.evadeDirection = evadeDirection;
	}
	private CombatChit evade = null;
	public CombatChit getEvade() {
		return evade;
	}
	public void setEvade(CombatChit ev) {
		this.evade = ev;
	}
	public void setPlayerNum(int n){
		playernum = n;
	}
	public int getPlayerNum(){
		return playernum;
	}
	
	
	//use the following for determining actions recordTUrn
	private int phasesForToday = -1;//determined in recordTurn
	int numPhases = 0;
	public int getPhasesForToday() {
		return phasesForToday;
	}
	public void setPhasesForToday(int phasesForToday) {
		this.phasesForToday = phasesForToday;
	}

	//these will store the actions the user wants to do in order
	private String [] phaseActions = new String[8];//we can increase it later
	
	public String [] getPhaseActions() {
		return phaseActions;
	}
	public String  getPhaseAction(int a) {
		return phaseActions[a];
	}
	public void setPhaseActions(String phaseActions1) {
		this.phaseActions[numPhases] = phaseActions1;
		numPhases++;
	}
	public void setPhaseActions(String phaseActions1, int a) {
		numPhases++;
		this.phaseActions[a] = phaseActions1;
	}
	
	//constructor for choosing type
	public Player(String s, int i){
		if(i >= 0){
			playernum = i;
		}
		if(s.equals("Amazon")){
			profile = new Amazon();
			GUI.player[playernum].setIcon(amazonIcon);
		}
		else if(s.equals("Black Knight")){
			profile = new BlackKnight();
			GUI.player[playernum].setIcon(bknightIcon);
		}
		else if(s.equals("Captain")){
			profile = new Captain();
			GUI.player[playernum].setIcon(captainIcon);
		}
		else if(s.equals("Dwarf")){
			profile = new Dwarf();
			GUI.player[playernum].setIcon(dwarfIcon);
		}
		else if(s.equals("Elf")){
			profile = new Elf();
			GUI.player[playernum].setIcon(elfIcon);
		}
		else if(s.equals("Swordsman")){
			profile = new Swordsman();
			GUI.player[playernum].setIcon(swordsmanIcon);
		}
		
		GUI.player[playernum].setVisible(true);

		//instanceCounter++;
		//playernum = instanceCounter;
		System.out.println("Built a " + profile.getType() + " Player");
		//System.out.println("Stats: " + profile.getWeapon() + "  "+ profile.getDefense(0));
	}

		
	@SuppressWarnings("static-access")
	void doAction(String action, Map map, Game game) {
		
		//handles the action recorded during birdsong and activated during daylight
		//If he is unable to do an activity, it is cancelled and the phase is treated as a blank phase.
		//When he does a blank phase, he does no activity.
		if(action == null){
			return;
		}
		
		//determine what the action is
		//if((action.substring(0, 4)).compareTo("Move")==0){//if move action
		
		if(action.compareTo("Move") == 0){//if move action
			
			PlayerActions.moveAction(this, false, map, game.view);
	
		}else if(action.compareTo("Hide")==0){//if hide action
			//roll on hide table, only a 6 does nothing
			if(Die.dieRoll() != 6)	this.hidden = true;
			
			
		}else if(action.compareTo("Trade")==0){//if Trade action
			//call of trade function
			game.view.trading(map, this);
			
		}else if(action.compareTo("Search")==0){//if search action
			//where are you searching//can only search his own clearing using locate
			GUI.moveLabel.setText("Searching");
			
			int currentTile = profile.getCurrentLocation()/10;
			int currentClearing = profile.getCurrentLocation()%10;
			
			String[] pos = new String[2];
			pos[0] = Integer.toString(currentTile);
			pos[1] = Integer.toString(currentClearing);
			int[] temp = new int[2];
			temp = GUI.convertNameToPosition(pos);
			
			if(map.getMapTile(temp[0]).treasure != null || map.getMapTile(temp[0]).clearing[temp[1]].isDrop 
					|| temp[0] == 10 || temp[0] == 11 || temp[0] == 12 || temp[0] == 13 || temp[0] == 14 || temp[0] == 15 || temp[0] == 16 || temp[0] == 17 || temp[0] == 18 || temp[0] == 19){//this to check oif there is actually a treasure there to find
				System.out.println("Now Searching");
				
				//with which table
				String choice = GUI.whichSearchTable();//locate+loot
				
				if(choice.compareTo("Locate") == 0){//using locate table
					PlayerActions.locatingAction(this, false, map, currentTile);
					
				}else if(choice.compareTo("Looting") == 0){//using loot table
					PlayerActions.lootingAction(this, false, map, currentTile, currentClearing, map.getMapTile(currentTile));
					
				}else{
					System.out.println("ERROR");
				}
			}else{
				JOptionPane.showMessageDialog(GUI.MainWindow,
					    "There is no treasure or passages to find.",
					    "Search results",
					    JOptionPane.PLAIN_MESSAGE);
				System.out.println("There is no treasure or passages to find");
				
			}
			
		}else if(action.compareTo("Rest")==0){//if rest action
			//if Dwarf do twice
			if(this.getProfile().getType().compareTo("Dwarf") == 0){
				System.out.println("Dwarves Rest Twice");
				PlayerActions.restingAction(this);
				PlayerActions.restingAction(this);
			}else	//not a Dwarf
				PlayerActions.restingAction(this);
		}	
	}

	
					/*
					 * will not be used in this iteration
						public void recordNumPointsWinGame() {
							System.out.println("Usre now picks scoring method");
						}
					*/
	
	public void rearangeBelongings() {
		//System.out.println("User can now rearrange belonging, this is meainingless in this iteration");
	}

	public boolean isThereOthersInCLearing(Map map, int currentTile, int currentClearing) {
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

	
	public void recordVictoryRequirments(){
		//set at beginning of game, used at end
		/*
		 *First assign value of 0 to 5 vic points to each category(no spell category)
		 *then multiply points by factor in each category = needed for that category
		 *					GreatTreasures is 1, fame is 10, notoriety is 20, gold is 30(in addition to start amount)
		 */
		
		//only ever removes the last value, so it will always list 0,1,2,3,4 if anything is chosen no matter the amount
		int[] values = {0,1,2,3,4,5};
		
		greatTreasureVicPoint = GUI.victoryPoints(getProfile().getType() +": Great Treasure Amount", values);
		//remove the amount of options
		for(int a=0; a<greatTreasureVicPoint; a++){	//will remove the last item of list
			values = (int[]) ArrayUtils.remove(values, values.length-1, -1);//need -1
		}
		fameVicPoint = GUI.victoryPoints(getProfile().getType() +": Fame Amount", values);
		//remove the amount of options
		for(int a=0; a<fameVicPoint; a++)	//will remove the last item of list
			values = (int[]) ArrayUtils.remove(values, values.length-1, -1);
		
		notorietyVicPoint = GUI.victoryPoints(getProfile().getType() +": Amount of Notoriety", values);
		//remove the amount of options
		for(int a=0; a<notorietyVicPoint; a++)	//will remove the last item of list
			values = (int[]) ArrayUtils.remove(values, values.length-1, -1);
		
		goldVicPoint =  values[values.length-1];//gets the last value
		GUI.displayMessage(getProfile().getType() +": Gold Amount Will be "+ goldVicPoint);
		
		//now multiply to find out how much is needed
		greatTreasureVicPointNeeded = greatTreasureVicPoint;//same value
		fameVicPointNeeded = fameVicPoint*10;//need 10 times amount of fame
		notorietyVicPointNeeded = notorietyVicPoint*20;
		goldVicPointNeeded = goldVicPoint*30;
	}
	
	public int calculateScore() {
		/*
	For the sake of specifying how the game ends in this first Iteration, 
	we will NOT have players specify victory points but instead I am making up the following rule: 
		the game ends after one month (28 days) and the winner is the player with the highest number of victory points 
		(where we score victory points as per the rules):

    1 point per great treasure, 1 point per 2 learnt spells (but I repeat I think you should forget about learning spells)

    1 point for each 10 points of fame, 1 point for each 20 points of notoriety, and 1 point for each 30 gold
		
		//System.out.println("Now Calculating Score");
		
		score += this.getProfile().getGreatTreasure();//1 point for each	
		score += this.getProfile().getFame()/10;//1 point for every 10 points of fame
		score += this.getProfile().getNotoriety()/20;//1 point for every 20 points of notoriety
		score += this.getProfile().getGold()/30;//1 point for every 30 points of gold
		*/
		
		//iteration 2
				
		int greatTreasureScore = profile.getGreatTreasure() - greatTreasureVicPointNeeded;
		int fameScore = profile.getFame() - fameVicPointNeeded;
		int notorietyScore = profile.getNotoriety() - notorietyVicPointNeeded;
		int goldScore = profile.getGold() - goldVicPointNeeded - profile.getStartGold();
		
		int basicScore = greatTreasureScore + (fameScore/10) + (notorietyScore/20) + (goldScore/30);
		int bonusScore = (greatTreasureScore)*greatTreasureVicPoint + (fameScore/10)*fameVicPoint + (notorietyScore/20)*notorietyVicPoint + (goldScore/30)*goldVicPoint;
		
		return basicScore + bonusScore;//if positive than it is a win //most points is victor
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
	
	
	
	public void doActionCheat(String action, Map map, CheatGame cheatGame) {//same as doAction only moving into new tile creates the warnings, rolls are determined
		//handles the action recorded during birdsong and activated during daylight
		//If he is unable to do an activity, it is cancelled and the phase is treated as a blank phase.
		//When he does a blank phase, he does no activity.
		if(action == null){
			return;
		}
		
		//determine what the action is
				//if((action.substring(0, 4)).compareTo("Move")==0){//if move action
		if(action.compareTo("Move") == 0){//if move action
			PlayerActions.moveAction(this, false, map, cheatGame.view);
			
		}else if(action.compareTo("Hide")==0){//if hide action
			//roll on hide table, only a 6 does nothing
			if(Die.dieRollCheat() != 6)	this.hidden = true;


		}else if(action.compareTo("Trade")==0){//if Trade action
			//call of trade function
			cheatGame.view.trading(map, this);

		}else if(action.compareTo("Search")==0){//if search action
			//where are you searching//can only search his own clearing using locate
			GUI.moveLabel.setText("Searching");
			
			int currentTile = profile.getCurrentLocation()/10;
			int currentClearing = profile.getCurrentLocation()%10;
			
			String[] pos = new String[2];
			pos[0] = Integer.toString(currentTile);
			pos[1] = Integer.toString(currentClearing);
			int[] temp = new int[2];
			temp = GUI.convertNameToPosition(pos);
			
			if(map.getMapTile(temp[0]).treasure != null || map.getMapTile(temp[0]).clearing[temp[1]].isDrop 
					|| temp[0] == 10 || temp[0] == 11 || temp[0] == 12 || temp[0] == 13 || temp[0] == 14 || temp[0] == 15 || temp[0] == 16 || temp[0] == 17 || temp[0] == 18 || temp[0] == 19){//this to check oif there is actually a treasure there to find
				System.out.println("Now Searching");
				
				//with which table
				String choice = GUI.whichSearchTable();//locate+loot
				
				if(choice.compareTo("Locate") == 0){//using locate table
					PlayerActions.locatingAction(this, true, map, currentTile);
					
				}else if(choice.compareTo("Looting") == 0){//using loot table
					PlayerActions.lootingAction(this, true, map, currentTile, currentClearing, map.getMapTile(currentTile));
					
				}else{
					System.out.println("ERROR");
				}
			}else{
				JOptionPane.showMessageDialog(GUI.MainWindow,
					    "There is no treasure or passages to find.",
					    "Search results",
					    JOptionPane.PLAIN_MESSAGE);
				System.out.println("There is no treasure or passages to find");
				
			}
			

		}else if(action.compareTo("Rest")==0){// if rest action
			//if Dwarf do twice
			if(this.getProfile().getType().compareTo("Dwarf") == 0){
				PlayerActions.restingAction(this);
				PlayerActions.restingAction(this);
			}else	//not a Dwarf
				PlayerActions.restingAction(this);
		}	

	}


	public void choiceOfFightChits(String response) {
		//user picked an action chit based on current active ones
		//interpret result and set the attack

		if(profile.getType().compareTo("Amazon") == 0){
			//interpret response	Fight:  Time 4,  Effort 1,	Strength 1
			switch(response){
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			}

		}else if(profile.getType().compareTo("Black Knight") == 0){
			switch(response){
			case "Fight:  Time 4, Effort 2, Strength 2":	//42H					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}

		}else if(profile.getType().compareTo("Captain") == 0){
			switch(response){
			case "Fight:  Time 6, Effort 0, Strength 2":	//60H					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}

		}else if(profile.getType().compareTo("Dwarf") == 0){
			switch(response){
			case "Fight:  Time 5, Effort 2, Strength 3":	//52T					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 5, Effort 1, Strength 3":	//51T
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}

		}else if(profile.getType().compareTo("Elf") == 0){
			switch(response){
			case "Fight:  Time 3, Effort 1, Strength 1":	//31M					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 4, Effort 0, Strength 1":	//40M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(profile.getType().compareTo("Swordsman") == 0){
			switch(response){
			case "Fight:  Time 4, Effort 0, Strength 0":	//40L					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 5, Effort 0, Strength 1":	//50M
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 2, Effort 2, Strength 0":	//22L
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				break;	
			}
			
		}else{
			System.out.println("CANNOT IDENTIFRY WHO IS DOING ACTION");
		}
		
	}
	
	public void choiceOfFatigueWoundChits(String response, boolean fatigue) {//if fatigue is true then only fatigue the chosen chit, if false wound it
		System.out.println("FATIGUE/WOUND this chit: " + response);
		//wound and fatigue change						
		if(profile.getType().compareTo("Amazon") == 0){
			//interpret response	Fight:  Time 4,  Effort 1,	Strength 1
			switch(response){
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Move:  Time 3, Effort 1, Strength 1":	//31M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue); 
				break;
			}
			
		}else if(profile.getType().compareTo("Black Knight") == 0){
			switch(response){
			case "Move:  Time 4, Effort 2, Strength 2":		//42H					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 4, Effort 2, Strength 2":	//42H					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue); 
				break;
			}
			
		}else if(profile.getType().compareTo("Captain") == 0){
			switch(response){
			case "Move:  Time 4, Effort 1, Strength 1":	//41M					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 6, Effort 0, Strength 2":	//60H					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue); 
				break;
			}
			
		}else if(profile.getType().compareTo("Dwarf") == 0){
			switch(response){
			case "Move:  Time 5, Effort 2, Strength 3":	//42T					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 5, Effort 2, Strength 3":	//52T					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Fight:  Time 5, Effort 1, Strength 3":	//51T
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue); 
				break;
			}
			
		}else if(profile.getType().compareTo("Elf") == 0){
			switch(response){
			case "Move:  Time 4, Effort 0, Strength 1":	//40M					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 3, Effort 1, Strength 1":	//31M					
				//set the values for your attack
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Fight:  Time 4, Effort 0, Strength 1":	//40M
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue); 
				break;
			}
			
		}else if(profile.getType().compareTo("Swordsman") == 0){
			switch(response){
			case "Fight:  Time 4, Effort 0, Strength 0":	//40L					
				//set the values for your attack
				this.setAttack(profile.action1);
				profile.action1Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action1, fatigue); 
				break;
			case "Fight:  Time 5, Effort 0, Strength 1":	//50M
				this.setAttack(profile.action2);
				profile.action2Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action2, fatigue); 
				break;
			case "Fight:  Time 2, Effort 2, Strength 0":	//22L
				this.setAttack(profile.action3);
				profile.action3Num--;	//lower the num of action# since you can only use it once a day
				increaseFatigueWound(profile.action3, fatigue);
				break;	
			}
			
		}else{
			System.out.println("CANNOT IDENTIFRY WHO IS DOING ACTION");
		}
		
		System.out.println("There are " + (profile.action1Num+profile.action2Num+profile.action3Num) + " possible moves available");
	}
	
	private void increaseFatigueWound(CombatChit action, boolean fatigue) {
		if(fatigue){//fatigue the action
			action.fatigued++;
		}else{		//wound the action
			action.wounded++;
		}	
	}
	
	//reset at end of round to not confuse next round
	public void resetFightGear() {
		//put the directions and values to normal
		this.setCombatAttackDirection(null);
		this.setAttack(null);
		
		this.setShieldDirection(null);
		
		this.setEvadeDirection(null);
		this.setEvade(null);
	}

}
