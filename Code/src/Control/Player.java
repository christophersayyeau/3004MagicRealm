
package Control;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.CombatChit;
import Model.Die;
import Model.Map;
import View.GUI;
//import View.GUI;
import CharacterProfiles.*;
import CharacterProfiles.Character;

public class Player {

	//commented out view for hotseat game
	//GUI view;
	
	//May have players have their own icons
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
	private String [] phaseActions = new String[5];//we can increase it later
	
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
	//constructor
	/*public Player(){
		
		//for simplicity we will set the first character to 
		Dwarf profile1 = new Dwarf();
		profile = profile1;
		
		System.out.println("Built a " + profile1.getType() + " Player");
		System.out.println("Stats: " + profile1.getWeapon() + "  "+ profile1.getDefense(0));
		
		//pick you character
		//Character.pickCharacter();
		//build later when game works better
		
		//create window for user, then display it
		//view = new GUI();	
	}*/
	
	//constructor for choosing type
	public Player(String s){
		if(s.equals("Amazon")){
			profile = new Amazon();
		}
		else if(s.equals("Black Knight")){
			profile = new BlackKnight();
		}
		else if(s.equals("Captain")){
			profile = new Captain();
		}
		else if(s.equals("Dwarf")){
			profile = new Dwarf();
		}
		else if(s.equals("Elf")){
			profile = new Elf();
		}
		else if(s.equals("Swordsman")){
			profile = new Swordsman();
		}
		
		System.out.println("Built a " + profile.getType() + " Player");
		//System.out.println("Stats: " + profile.getWeapon() + "  "+ profile.getDefense(0));
	}

		
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
			//THere are rules to handle moving through mountains+caves
			game.view.moveLabel.setText("Click on a clearing to move the character");
			//game.view.Instruction.setVisible(true);
			//int newLocation = Integer.parseInt(action.substring(5));
			game.view.setMove(true);
			int newLocation = game.view.getNewLocation();
			
			
			System.out.println("New location = "+newLocation);
			System.out.println("Current Location = "+profile.getCurrentLocation());
			
			if( map.canHeMove(profile.getCurrentLocation(), newLocation, this) ){
				//there are rules about how much weight
				map.moveCharacters(this, newLocation);//if yes then move
				
				//TODO add in code to move icon around
				//Make the code more generalized, figure out how/where to store icons
				game.view.amazon.setLocation(game.view.getPlayerX(),game.view.getPlayerY());
				
				System.out.println("You can move here, moving character now");
			}else{
				System.out.println("Can't Move There, phase wasted");
			}
			//game.view.Instruction.setVisible(false);
			
		}else if(action.compareTo("Hide")==0){//if hide action
			//roll on hide table, only a 6 does nothing
			if(Die.dieRoll() != 6)	this.hidden = true;
			
			
		}else if(action.compareTo("Trade")==0){//if Trade action
			//call of trade function
			game.view.trading(map, this);
			
		}else if(action.compareTo("Search")==0){//if search action//TODO second part, all of related treasure stuff should be fixed
			//where are you searching//can only search his own clearing using locate
			int currentTile = profile.getCurrentLocation()/10-1;
			
			if(map.getMapTile(currentTile).treasure != null){//this to check oif there is actually a treasure there to find
				
				//with which table
				String choice = game.view.whichSearchTable();//locate+loot
				
				
				if(choice.compareTo("Locate") == 0){//using locate table
					int result = Die.dieRoll();
					switch (result){
						case 1:  	game.view.revealTreasure(currentTile);//technically you can choose but that is dumb
									map.getMapTile(currentTile).treasure.found = true;
						break;
						case 2:  	//display all passages and mentally note that treasure
						break;
						case 3:  	//display all passages
						break;
						case 4:  	game.view.revealTreasure(currentTile);
									map.getMapTile(currentTile).treasure.found = true;
						break;
						//5 and 6 do nothing
					}
					//When he discovers a roadway or treasure site, he is the only one who discovers it; it remains concealed from others, who must discover it on their own if they wish to use it.  He does not have to admit whether he actually discovers a treasure site. He must reveal what he rolled, but he does not have to reveal whether there is a treasure site chit in his clearing.
					//Once an individual discovers a hidden path, secret passage or treasure site, he never has to discover it again. He keeps a record of each discovery by crossing it off the Discoveries list on his Personal History sheet.
					//Once he has discovered a treasure site, he can search it for treasure whenever he is in its clearing.
					
				}else if(choice.compareTo("Looting") == 0){//using loot table
	
					//need to have located it first before trying to loot
					if (map.getMapTile(currentTile).treasure.found){
						//if you roll over the number of treasures there you get nothing
						int result = Die.dieRoll();
						switch (result){															//TODO also could be a playerdrop here
							case 1:  	map.giveWholeTreasure(this, map.getMapTile(currentTile).treasure);//TODO second step, treasure stuff this function declariation nneeds work
							break;
	//						case 2:  	2nd//since there is only going to be one treasure
	//						break;
	//						case 3:  	3rd
	//						break;
	//						case 4:  	4th
	//						break;
	//						case 5:  	5th
	//						break;
	//						case 6:  	6th
	//						break;
						}
					}
				}
			}else{
				System.out.println("There is no treasure to find");
			}
			
		}else if(action.compareTo("Rest")==0){//if rest action
			System.out.println("Resting Now");
			//resting (activity to get rid of wounds fatigue see page 21)
			//can either turn 1 wound into 1 fatigue chit
				//or return 1 fatigue to play
			Character person = this.getProfile();
			
			
			//first tell user their status so they don't waste a turn
			int wounds = 0;
			int fatigueness = 0;
			fatigueness = person.action1.fatigued + person.action2.fatigued + person.action3.fatigued;
			wounds = person.action1.wounded + person.action2.wounded + person.action3.wounded;
			GUI.combatMessage("You have " + fatigueness + " fatigued chits and/n" + wounds + "wounded chits.");
			
			
			//get all options for both possibilities
			String[] options = null;
			options = CombatChit.getFatigueWoundChits(person);
			
			//ask user what they want to do
			Object response = JOptionPane.showInputDialog(null, "Which Chit do You Wish To Fix?",	"Resting",
					JOptionPane.PLAIN_MESSAGE,
					null,	options, options[0]);
			
			//now interpret result (String)response, increase actionNum and lower faitgue/wound
			System.out.println("This is the substring" + ((String) response).substring(0, 4));
			switch(((String) response).substring(0, 4)){
			case "Fati":	//if user chose to unfatigue a chit
				//if user chose action1
				if(((String) response).substring(((String) response).length()).compareTo("1") == 0){
					person.action1Num++;
					person.action1.fatigued--;
				}
				//if user chose action2
				else if(((String) response).substring(((String) response).length()).compareTo("2") == 0){
					person.action2Num++;
					person.action2.fatigued--;
				}
				//if user chose action3
				else if(((String) response).substring(((String) response).length()).compareTo("3") == 0){
					person.action3Num++;
					person.action3.fatigued--;
				} else {
					System.out.println("ERROR, didn't recog the action");
				}
				break;
			case "Woun":	//if user chose to heal
				//if user chose action1
				if(((String) response).substring(((String) response).length()).compareTo("1") == 0){
					person.action1.wounded--;
					person.action1.fatigued++;
				}
				//if user chose action2
				else if(((String) response).substring(((String) response).length()).compareTo("2") == 0){
					person.action2.wounded--;
					person.action2.fatigued++;
				}
				//if user chose action3
				else if(((String) response).substring(((String) response).length()).compareTo("3") == 0){
					person.action3.wounded--;
					person.action3.fatigued++;
				} else {
					System.out.println("ERROR, didn't recog the action");
				}
				break;
			}
											/*//will just reset completely for now				
											person.action1Num = 4;
											person.action2Num = 4;
											person.action3Num = 4;	*/
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

	public int calculateScore() {
		/*
	For the sake of specifying how the game ends in this first Iteration, 
	we will NOT have players specify victory points but instead I am making up the following rule: 
		the game ends after one month (28 days) and the winner is the player with the highest number of victory points 
		(where we score victory points as per the rules):

    1 point per great treasure, 1 point per 2 learnt spells (but I repeat I think you should forget about learning spells)

    1 point for each 10 points of fame, 1 point for each 20 points of notoriety, and 1 point for each 30 gold
		*/
		System.out.println("Now Calculating Score");
		int score = 0;
		
		score += this.getProfile().getGreatTreasure();//1 point for each	
		score += this.getProfile().getFame()/10;//1 point for every 10 points of fame
		score += this.getProfile().getNotoriety()/20;//1 point for every 20 points of notoriety
		score += this.getProfile().getGold()/30;//1 point for every 30 points of gold
		return score;
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
				//THere are rules to handle moving through mountains+caves
					
				//int newLocation = Integer.parseInt(action.substring(5));
				int newLocation = cheatGame.view.getNewLocation();
				
				//check to see if they can
				if( map.canHeMove(profile.getCurrentLocation(), newLocation, this) ){
					//there are rules about how much weight
					map.moveCharacters(this, newLocation);//if yes then move
				}else{
					System.out.println("Can't Move There, phase wasted");
				}
					
		}else if(action.compareTo("Hide")==0){//if hide action
					//roll on hide table, only a 6 does nothing
			if(Die.dieRollCheat() != 6)	this.hidden = true;
					
					
			}else if(action.compareTo("Trade")==0){//if Trade action
					//call of trade function
				cheatGame.view.trading(map, this);
					
				}else if(action.compareTo("Search")==0){//if search action//TODO second part, all of related treasure stuff should be fixed as above with added cheating stuff as well
					//where are you searching//can only search his own clearing using locate
					int currentTile = profile.getCurrentLocation()/10-1;
					
					if(map.getMapTile(currentTile).treasure != null){//this to check oif there is actually a treasure there to find
						
						//with which table
						String choice = cheatGame.view.whichSearchTable();//locate+loot
						
						
						if(choice.compareTo("Locate") == 0){//using locate table
							int result = Die.dieRollCheat();
							switch (result){
								case 1:  	cheatGame.view.revealTreasure(currentTile);//technically you can choose but that is dumb
											map.getMapTile(currentTile).treasure.found = true;
								break;
								case 2:  	//display all passages and mentally note that treasure
								break;
								case 3:  	//display all passages
								break;
								case 4:  	cheatGame.view.revealTreasure(currentTile);
											map.getMapTile(currentTile).treasure.found = true;
								break;
								//5 and 6 do nothing
							}
							//When he discovers a roadway or treasure site, he is the only one who discovers it; it remains concealed from others, who must discover it on their own if they wish to use it.  He does not have to admit whether he actually discovers a treasure site. He must reveal what he rolled, but he does not have to reveal whether there is a treasure site chit in his clearing.
							//Once an individual discovers a hidden path, secret passage or treasure site, he never has to discover it again. He keeps a record of each discovery by crossing it off the Discoveries list on his Personal History sheet.
							//Once he has discovered a treasure site, he can search it for treasure whenever he is in its clearing.
							
						}else if(choice.compareTo("Looting") == 0){//using loot table
			
							//need to have located it first before trying to loot
							if (map.getMapTile(currentTile).treasure.found){
								//if you roll over the number of treasures there you get nothing
								int result = Die.dieRollCheat();
								switch (result){
									case 1:  	map.giveWholeTreasure(this, map.getMapTile(currentTile).treasure);
									break;
			//						case 2:  	2nd//TODO second step, need to differentiate between contents of treasure
			//						break;
			//						case 3:  	3rd
			//						break;
			//						case 4:  	4th
			//						break;
			//						case 5:  	5th
			//						break;
			//						case 6:  	6th
			//						break;
								}
							}
						}
					}else{
						System.out.println("There is no treasure to find");
					}
					
				}else if(action.compareTo("Rest")==0){//if rest action
					System.out.println("Nothing is Done here in this iteration");
					
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
			
		}else if(profile.getType().compareTo("BlackKnight") == 0){
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
			
		}else if(profile.getType().compareTo("BlackKnight") == 0){
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
