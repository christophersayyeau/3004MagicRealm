package Model;

import Model.Armor.Protect;
import View.GUI;
import Control.Player;

public class CombatFunctions{	//combat resolution, see page 28 and page 5 of flowchart

	//will set up players and then figure out their attack order
	public static void resolveCombat(GUI view, Player player, Player opponent) {	//TODO this will need to be extensively tested once finished
		System.out.println("Fight Starting between " + player.getProfile().getClass() + player.getProfile().getClass());
		
		/*	one round of combat between 2 players
		no running away
		2* limit
		combat resolved into 1 death, 2 death or combat stop
		ignore fatigued and wounded counters*/
		
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
				finishCombat(player, opponent);
				
				if(opponent.alive){
					finishCombat(opponent, player);
				}
			
			//if the opponent has longer reach
			}else if(player.getProfile().getWeapon().weaponLength < opponent.getProfile().getWeapon().weaponLength){	
				System.out.println(player.getProfile().getType() + "goes second since length"+ player.getProfile().getWeapon().weaponLength + "<"+ opponent.getProfile().getWeapon().weaponLength);
				
				//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
				finishCombat(opponent, player);
			
				if(player.alive){
					finishCombat(player, opponent);
				}
				
			//both have same length	
			}else{
				System.out.println("Same Weapon Length, basing it on Attack TIme");
				//related to attack time	
				if(player.getAttack().getTime() <= opponent.getAttack().getTime()) {//player goes first
					System.out.println(player.getProfile().getType() + "goes first since time"+ player.getAttack().getTime() + "<"+ opponent.getAttack().getTime());
					
					//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
					finishCombat(player, opponent);
					
					if(opponent.alive){
						finishCombat(opponent, player);
					}
					
				}else{//oponnent goes first	
					System.out.println(player.getProfile().getType() + "goes second since time"+ player.getAttack().getTime() + ">"+ opponent.getAttack().getTime());
					
					//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
					finishCombat(opponent, player);
					
					if(player.alive){
						finishCombat(player, opponent);
					}
				}
			}
	
		}else if(player.getAttack() == null){//only opponent is attacking
			finishCombat(opponent, player);
		}else{//only player is attacking
			finishCombat(player, opponent);
		}
		//fastest fellow hits first in subsequent rounds(if equal it is weapon length)
		//COPY IT FORMM WHEN WEPONLENGTH IS SAME
		
		//reset at end of round			CHANGE WHEN MULTI ROUND
		player.effortThisRound = 0;
		opponent.effortThisRound = 0;
	
		
		
		
		
		
		
		
		//set true so they don't fight again today, will be reset in CHaracter.resetFIght() at the beginning of each day
		player.getProfile().setFoughtToday(true);
		opponent.getProfile().setFoughtToday(true);
		//restore all action chits for next day done in Character.resetFight()
		
		System.out.println("Fight FInished");
	}

	
	//will determine if attack hits and remove the effort needed
	private static void finishCombat(Player attacker, Player defender) {
		//max of 2 effort per round, if higher it is cancelled
		if(attacker.effortThisRound+attacker.getAttack().getEffort() <= 2){
			//does the defender try and evade and does he have the juice for it
			
			if(defender.getEvade() != null && defender.effortThisRound + defender.getEvade().getEffort() <= 2){
				//compare the time to attack and evade to see if it undercuts
				if(attacker.getAttack().getTime() < defender.getEvade().getTime()){//if it undercuts
					
					//if attack time lower then maneuver time it undercuts and autohits
					attackHits(attacker, defender);				
		
				}else{
					//attacktime equal or larger
					//compare maneuvers, doeshe intercept
					//thrust hits charge,	swing hits dodge,	smash hits duck
					if( attacker.getCombatAttackDirection().compareTo("Thrust")==0 		&& defender.getEvadeDirection().compareTo("Charge")==0	|| 
							attacker.getCombatAttackDirection().compareTo("Swing")==0 	&& defender.getEvadeDirection().compareTo("Dodge")==0	||
							attacker.getCombatAttackDirection().compareTo("Smash")==0 	&& defender.getEvadeDirection().compareTo("Duck")==0	){
											
						//manage to hit the defener while he tries to dodge
						attackHits(attacker, defender);
		
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
				attackHits(attacker, defender);	
			}
			//increase this value so you only use to this day
			attacker.effortThisRound += attacker.getAttack().getEffort();
			
		}else{
			System.out.println("Not Enough Effort");
			GUI.combatMessage("Not Enough Effort");
		}
	}

	//attack has hit target, now resolve damage
	private static void attackHits(Player attacker, Player defender) {
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
			harmLevel += Weapon.missileRoll();//TODO cheat Mode difference, make call to missileRollCheat instead
			
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
				//TODO kill character, remove from game logic then dispose of window
				
			}else{
				//if harm less then vulnerability but more than negligable suffers a wound
				if(harmLevel > 0){
					System.out.println("That Didn't Even Hurt");
				
				}else{	//inflicted Light or higher damage
					System.out.println("Wounded Once");
					//TODO wound action chit
					//?roll 2 dice take higest and wound that many action chits
					
					//Once all action CHits wounded he is killed
				}
			}	
		}
		
		//unalert weapon
		attacker.getProfile().getWeapon().alerted = false;
	}
	
}
