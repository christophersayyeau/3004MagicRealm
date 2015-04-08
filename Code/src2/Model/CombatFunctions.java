package Model;

import javax.swing.JOptionPane;

import Model.Armor.Protect;
import Model.MapChits.PlayerDrop;
import View.GUI;
import Control.Player;

public class CombatFunctions{	//combat resolution, see page 28 and page 5 of flowchart

	//will set up players and then figure out their attack order
	public static void resolveCombat(GUI view, Player player, Player opponent, boolean cheating) {	//TODO Testing, this will need to be extensively tested
		System.out.println("Fight Starting between " + player.getProfile().getClass() + player.getProfile().getClass());

		//First round
		//first get the combatents ready for combat
		view.selectFightGear(player);
		view.selectFightGear(opponent);

		//Now handle COmbat
		//first check if they are doing anything
		if(player.getAttack() != null && opponent.getAttack() != null){
			//who goes first?
			//1st round hit order is weapon length,then faster attack time
			//	attack time=time on Fight chit

			//longest weapon goes first in first round of combat
			if(player.getProfile().getWeapon().weaponLength > opponent.getProfile().getWeapon().weaponLength){//player goes first	
				System.out.println(player.getProfile().getType() + "goes first since length "+ player.getProfile().getWeapon().weaponLength + ">"+ opponent.getProfile().getWeapon().weaponLength);

				//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
				finishCombat(player, opponent, cheating, view);

				if(opponent.alive){
					finishCombat(opponent, player, cheating, view);
				}

				//if the opponent has longer reach
			}else if(player.getProfile().getWeapon().weaponLength < opponent.getProfile().getWeapon().weaponLength){	
				System.out.println(player.getProfile().getType() + "goes second since length"+ player.getProfile().getWeapon().weaponLength + "<"+ opponent.getProfile().getWeapon().weaponLength);

				//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
				finishCombat(opponent, player, cheating, view);

				if(player.alive){
					finishCombat(player, opponent, cheating, view);
				}

				//both have same length	
			}else{
				System.out.println("Same Weapon Length, basing it on Attack TIme");
				//related to attack time	
				if(player.getAttack().getTime() <= opponent.getAttack().getTime()) {//player goes first
					System.out.println(player.getProfile().getType() + "goes first since time"+ player.getAttack().getTime() + "<"+ opponent.getAttack().getTime());

					//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
					finishCombat(player, opponent, cheating, view);

					if(opponent.alive){
						finishCombat(opponent, player, cheating, view);
					}

				}else{//oponnent goes first	
					System.out.println(player.getProfile().getType() + "goes second since time"+ player.getAttack().getTime() + ">"+ opponent.getAttack().getTime());

					//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
					finishCombat(opponent, player, cheating, view);

					if(player.alive){
						finishCombat(player, opponent, cheating, view);
					}
				}
			}

		}else if(opponent.getAttack() != null){//only opponent is attacking
			finishCombat(opponent, player, cheating, view);
		}else if(player.getAttack() != null){//only player is attacking
			finishCombat(player, opponent, cheating, view);
		}else{//no combat
			player.getProfile().setFoughtToday(true);
			opponent.getProfile().setFoughtToday(true);
			System.out.println("Cancelled Combat");
			return;
		}

		endOfRound(player, opponent);
		//end OF ROUND1

		//Start next round of combat
		//Multiple rounds, ends when 1 dead or both choose do nothing
		while(player != null && opponent != null) {
			//choose gear
			view.selectFightGear(player);
			view.selectFightGear(opponent);

			if(player.getAttack() == null && opponent.getAttack() == null){	//both want combat to stop
				player.getProfile().setFoughtToday(true);
				opponent.getProfile().setFoughtToday(true);
				System.out.println("Cancelled Combat");
				return;
			}else{
				//fastest fellow hits first in subsequent rounds(if equal it is weapon length)
				if(player.getAttack() != null && opponent.getAttack() != null){
					//who goes first?
					//faster attack time
					//	attack time=time on Fight chit

					if(player.getAttack().getTime() < opponent.getAttack().getTime()){//player goes first	
						System.out.println(player.getProfile().getType() + "goes first since time"+ player.getAttack().getTime() + "<"+ opponent.getAttack().getTime());

						//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
						finishCombat(player, opponent, cheating, view);

						if(opponent.alive){
							finishCombat(opponent, player, cheating, view);
						}

						//if the opponent has faster time
					}else if(player.getAttack().getTime() > opponent.getAttack().getTime()){	
						System.out.println(player.getProfile().getType() + "goes second since time"+ player.getAttack().getTime() + ">"+ opponent.getAttack().getTime());

						//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
						finishCombat(opponent, player, cheating, view);

						if(player.alive){
							finishCombat(player, opponent, cheating, view);
						}

						//both have same time	
					}else{
						System.out.println("Same Attack TIme, based on Weapon Length");
						//related to weapon length
						if(player.getProfile().getWeapon().weaponLength >= opponent.getProfile().getWeapon().weaponLength) {//player goes first
							System.out.println(player.getProfile().getType() + "goes first since length "+ player.getProfile().getWeapon().weaponLength + ">"+ opponent.getProfile().getWeapon().weaponLength);

							//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
							finishCombat(player, opponent, cheating, view);

							if(opponent.alive){
								finishCombat(opponent, player, cheating, view);
							}

						}else{//oponnent goes first	
							System.out.println(player.getProfile().getType() + "goes second since length"+ player.getProfile().getWeapon().weaponLength + "<"+ opponent.getProfile().getWeapon().weaponLength);

							//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
							finishCombat(opponent, player, cheating, view);

							if(player.alive){
								finishCombat(player, opponent, cheating, view);
							}
						}
					}

				}else if(opponent.getAttack() != null){//only opponent is attacking
					finishCombat(opponent, player, cheating, view);
				}else if(player.getAttack() != null){//only player is attacking
					finishCombat(player, opponent, cheating, view);
				}else{//no combat, should never be reached since it would have been caught earlier
					System.out.println("ERRORCancelled CombatERROR");
					return;
				} 
			}
			endOfRound(player, opponent);
		}

		//set true so they don't fight again today, will be reset in CHaracter.resetFIght() at the beginning of each day
		player.getProfile().setFoughtToday(true);
		opponent.getProfile().setFoughtToday(true);
		
		System.out.println("Fight FInished");
	}


	private static void endOfRound(Player player, Player opponent) {
		//if effortThisRound > 1 he must fatigue one effort worth of action chits
		if(player.effortThisRound > 1){
			String tired = GUI.selectFatigue(player);
			player.choiceOfFatigueWoundChits(tired, true);
		}
		if(opponent.effortThisRound > 1){
			String tired = GUI.selectFatigue(opponent);
			opponent.choiceOfFatigueWoundChits(tired, true);
		}
		
		//reset at end of round			
		player.effortThisRound = 0;
		opponent.effortThisRound = 0;
		player.resetFightGear();
		opponent.resetFightGear();
	}


	//will determine if attack hits and remove the effort needed
	private static void finishCombat(Player attacker, Player defender, boolean cheating, GUI view) {
		//max of 2 effort per round, if higher it is cancelled
		if(attacker.effortThisRound+attacker.getAttack().getEffort() <= 2){
			//does the defender try and evade and does he have the juice for it
			
			if(defender.getEvade() != null && defender.effortThisRound + defender.getEvade().getEffort() <= 2){
				//compare the time to attack and evade to see if it undercuts
				if(attacker.getAttack().getTime() < defender.getEvade().getTime()){//if it undercuts
					
					//if attack time lower then maneuver time it undercuts and autohits
					attackHits(attacker, defender, cheating, view);				
		
				}else{
					//attacktime equal or larger
					//compare maneuvers, doeshe intercept
					//thrust hits charge,	swing hits dodge,	smash hits duck
					if( attacker.getCombatAttackDirection().compareTo("Thrust")==0 		&& defender.getEvadeDirection().compareTo("Charge")==0	|| 
							attacker.getCombatAttackDirection().compareTo("Swing")==0 	&& defender.getEvadeDirection().compareTo("Dodge")==0	||
							attacker.getCombatAttackDirection().compareTo("Smash")==0 	&& defender.getEvadeDirection().compareTo("Duck")==0	){
											
						//manage to hit the defener while he tries to dodge
						attackHits(attacker, defender, cheating, view);
		
					}else{
						//Dosn't intercept
						System.out.println("Attack Missed");
						GUI.combatMessage("Attack Missed");
						
						//alert the weapon
						attacker.getProfile().getWeapon().alerted = true;
					}
				}
				
				//tire out the defender, attacker effort dealt with later
				defender.effortThisRound += defender.getEvade().getEffort();
		
			}else{
				//defender isn't menauvering or is too tired
				attackHits(attacker, defender, cheating, view);	
			}
			//increase this value so you only use to this day
			attacker.effortThisRound += attacker.getAttack().getEffort();
			
		}else{
			System.out.println("Not Enough Effort");
			GUI.combatMessage("Not Enough Effort");
		}
	}

	//attack has hit target, now resolve damage
	private static void attackHits(Player attacker, Player defender, boolean cheating, GUI view) {
		//simplify process by getting harm level now
		Harm weaponHarm;
		if(attacker.getProfile().getWeapon().alerted){
			weaponHarm = attacker.getProfile().getWeapon().alertedHarm;
		}else{
			//unalerted
			weaponHarm = attacker.getProfile().getWeapon().unalertedHarm;
		}
		
		
		//determine level of harm
		int harmLevel = 0;
		
		//hit inflicts harm (THML)
		harmLevel += weaponHarm.weight;		//strength of weapon
		
		//hit gets bonus based on sharpness
		
		//use this for damage to armor
		Armor armorDamage = null;
		int locationOfArmor = -1;
		
		//check to see if you should even bother checking
		//only decrease the value by 1 to minimum of 0
		if(weaponHarm.sharpness >=1){
			System.out.println("Get BOnus Damage for Sharpness");
			//figure out if the armor intercepts hit(ALL_DIRECTIONS, THRUST_AND_SWING, SMASH, ANY_ONE_DIRECTION)
			switch (attacker.getCombatAttackDirection()){
				case "Thrust":
					//search through armor to see if pprotected
					for(int i=0; i< defender.getProfile().getDefense().length; i++){
						if(defender.getProfile().getDefense(i).getDirection().compareTo("ALL_DIRECTIONS") == 0){//if hits armor	
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getProfile().getDefense(i).getDirection().compareTo("THRUST_AND_SWING") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getShieldDirection().compareTo("THRUST") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
					}
					
					//gone through all armor, if reach here it isn't intercepted
					harmLevel += weaponHarm.sharpness;	//sharpness of weapon
					break;
				case "Swing":	
					//search through armor to see if pprotected
					for(int i=0; i< defender.getProfile().getDefense().length; i++){
						if(defender.getProfile().getDefense(i).getDirection().compareTo("ALL_DIRECTIONS") == 0){//if hits armor	
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getProfile().getDefense(i).getDirection().compareTo("THRUST_AND_SWING") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getShieldDirection().compareTo("SWING") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
					}
					
					//gone through all armor, if reach here it isn't intercepted
					harmLevel += weaponHarm.sharpness;	//sharpness of weapon
					break;
				case "Smash":		
					//search through armor to see if pprotected
					for(int i=0; i< defender.getProfile().getDefense().length; i++){
						if(defender.getProfile().getDefense(i).getDirection().compareTo("ALL_DIRECTIONS") == 0){//if hits armor	
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getProfile().getDefense(i).getDirection().compareTo("SMASH") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
						if(defender.getShieldDirection().compareTo("SMASH") == 0){
							harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative
							armorDamage = defender.getProfile().getDefense(i);
							locationOfArmor = i;
							break;
						}
					}
					
					//gone through all armor, if reach here it isn't intercepted
					harmLevel += weaponHarm.sharpness;	//sharpness of weapon
					break;
					
				case "Do Nothing":		System.out.println("ERROR, this should have been stopped by the if statements in resolveCombat");				
					return;			
				case "Make Weapon Alert":	
					attacker.getProfile().getWeapon().alerted = true;
					return;				//no need to figure out anything else		
			}
		}
		
		//Handle missile weapon difference	
		//if hit with missile weapon, roll on missile table and adjust harm
		if(attacker.getProfile().getWeapon().missile){
			
			//if in cheatmode get to decide roll
			if(cheating)
				harmLevel += Weapon.missileRollCheat();
			else	
				harmLevel += Weapon.missileRoll();
			
			//make sure it isn't negative
			if(harmLevel < 0)
				harmLevel = 0;
		}else{
			//you are attacking up close
				//commented out rules since they only apply to striking weapons
				//if strength of Fight chit bigger that weight of weapon increase 1 level only
					//if(attacker.getAttack().getStrength() > attacker.getProfile().getWeapon().weight)
					//	harmLevel +=1;	
			System.out.println("Up Close And Personal");
		}
		
		
		//now handle damage to armor
		if(armorDamage != null){	//did it hit something
			//compare final harm inflicted by attack to toughness of armor
			if(harmLevel > armorDamage.getToughness()){			//greater than
				System.out.println("Armor destroyed");
				defender.getProfile().getDefense(locationOfArmor).protect = Protect.NOTHING;//set it to defend against against nothing
				defender.getProfile().getDefense(locationOfArmor).setDestroyed(true);
				
			}else if(harmLevel == armorDamage.getToughness()){		//equal to
				System.out.println("Armor damaged");
				//if hit twice then destroyed
				if(defender.getProfile().getDefense(locationOfArmor).isDamaged()){ //if already damaged
					System.out.println("Armor destroyed");
					defender.getProfile().getDefense(locationOfArmor).protect = Protect.NOTHING;//set it to defend against against nothing
					defender.getProfile().getDefense(locationOfArmor).setDestroyed(true);
					
					//might as well reset this here
					defender.getProfile().getDefense(locationOfArmor).setDamaged(false);
				}else{
					//first damage
					System.out.println("Armor damaged First TIme");
					defender.getProfile().getDefense(locationOfArmor).setDamaged(true);
				}
			}else{			//less than
				System.out.println("Armor not affected because " + armorDamage.getToughness() + " > " + harmLevel);
			}	
		}else{						//didn't hit armor
			//damages the defender, compare to defender's toughness
			if(harmLevel >= defender.getProfile().getVulnerability()){	//weight is vulnerability
				System.out.println("Player dead");
				killPlayer(defender, view);
								
			}else{
				//if harm less then vulnerability but more than negligable suffers a wound
				if(harmLevel > 0){
					System.out.println("That Didn't Even Hurt");
				
				}else{	//inflicted Light or higher damage
					System.out.println("Wounded Once");
					//wound action chit 					//?roll 2 dice take higest and wound that many action chits, done in example, no mention in rules		
					//FIrst get the active ones
					String[] options = null;
					CombatChit.getActionChits(defender, options);
					
					//next ask which one to wound
					//now ask defender to pick
					@SuppressWarnings("null")
					Object response = JOptionPane.showInputDialog(null, "Which Action Chit do You Wish To Wound?",	"Wounding",
							JOptionPane.PLAIN_MESSAGE,	null,	options, options[0]);//test before adding suppress

					//going to seperate based on user type then apply result
					//wound this to almost identical version with minor change player.choiceOfActiveChits((String)response);						
					defender.choiceOfFatigueWoundChits((String)response, false);
					
					//Once all action CHits wounded he is killed
					//if they are all less then 1
					if(defender.getProfile().action1Num < 1 && defender.getProfile().action2Num < 1 && defender.getProfile().action3Num < 1 ){
						System.out.println("Player dead");
						killPlayer(defender, view);			
					}
				}
			}	
		}	
		//unalert weapon
		attacker.getProfile().getWeapon().alerted = false;
	}


	private static void killPlayer(Player defender, GUI view) {
		//Current Location
		String[] pos = new String[2];
		int currentTile 	= defender.getCurrentLocation()/10;
		int currentClearing = defender.getCurrentLocation()%10;
		pos[0] = Integer.toString(currentTile);
		pos[1] = Integer.toString(currentClearing);
		int[] temp = new int[2];
		temp = GUI.convertNameToPosition(pos);
		
		//kill character, remove from all arrays he is a part of, then dispose of his window
		GUI.combatMessage("YOU ARE DEAD XO /n Now Removing You From Game");
		
		//Create treasure pile out of his stuff
		MapChits temporary = new MapChits();
		PlayerDrop drop = temporary.new PlayerDrop(temp[1], defender.getProfile());
		view.getMap().getMapTile(temp[0]).clearing[temp[1]].putPlayerDrop(drop);
		
		//give nothing to attacker
		
		//now get rid of player
		//Removing from model
		view.getMap().getClearing(temp[0], temp[1]).removePlayer(defender);	//clearing
		view.getMap().getMapTile(temp[0]).removePlayer(defender);			//tile
		
		//removing from Controller
		//TODO STEFAN, networking, remove player from client, server and serverController, then close his window
	}
	
}
