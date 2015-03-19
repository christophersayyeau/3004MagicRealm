package Model;

import View.GUI;
import Control.Player;

public class CombatFunctions{

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
		
		
		//TODO combat resolution see page 28 and page 5 of flowchart
		//figure out if the armor intercepts hit
		/*switch (attacker.getCombatAttackDirection()){
			case "Thrust":		
				
				break;
			case "Swing":	
				
				break;
			case "Smash":		
				
				break;
			case "Do Nothing":		//Dont do anything and let the next guy attack you				
				return;
			case "Make Weapon Alert":	
				
				break;
			
		}
		
		
		//hit gets bonus based on sharpness
		if armor dosnt intercept
		harmLevel += weaponHarm.sharpness;	//sharpness of weapon
		if armor hits
			if sharpness >= 1
			harmLevel += weaponHarm.sharpness-1;//only lose 1 star, can't give negative

		//if hit with missile weapon, roll on missile table and adjust harm
		if(attacker.getProfile().getWeapon().missile){
			harmLevel += Weapon.missileRoll();
		}else{
			//you are attacking up close
				//commented out rules since they only apply to striking weapons
				//if strength of Fight chit bigger that weight of weapon increase 1 level only
					//if(attacker.getAttack().getStrength() > attacker.getProfile().getWeapon().weight)
					//	harmLevel +=1;	
		}
		
		

		denizen harm compared to vulnerability

		when armor hit by attack inflicting less harm it ignores
		when armor hit by harm equal to toughness becomes damaged
		when armor hit by greater it is destroyed
		if damaged armor damaged again it is destroyed;

		combat resolved into 1 death, 2 death or combat stop*/	
		
		//unalert weapon
		attacker.getProfile().getWeapon().alerted = false;
	}
	
}
