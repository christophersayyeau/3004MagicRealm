package Model;

import View.GUI;
import Control.Player;

public class CombatFunctions{

	public static void resolveCombat(GUI view, Player player, Player opponent) {	//TODO this will need to be extensively tested once finished
		System.out.println("Fight Starting between " + player.getProfile().getClass() + player.getProfile().getClass());
		
		/*	one round of combat between 2 players
		no running away
		2* limit
		combat resolved into 1 death, 2 death or combat stop
		ignore fatigued and wounded counters*/
		
		//first get the combatenets ready for combat
		view.selectFightGear(player);
		view.selectFightGear(opponent);
		
		//Now handle COmbat
		//who goes first?
		//1st round hit order is weapon length,then faster attack time
		//	attack time=time on Fight chit
		
		//longest weapon goes first in first round of combat
		if(player.getProfile().getWeapon().weaponLength > opponent.getProfile().getWeapon().weaponLength){//player goes first	
			System.out.println(player.getProfile().getType() + "goes first since "+ player.getProfile().getWeapon().weaponLength + ">"+ opponent.getProfile().getWeapon().weaponLength);
		
			//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
			finishCombat(player, opponent);
			
			if(opponent.alive){
				finishCombat(opponent, player);
			}
		
		//if the opponent has longer reach
		}else if(player.getProfile().getWeapon().weaponLength < opponent.getProfile().getWeapon().weaponLength){	
			System.out.println(player.getProfile().getType() + "goes second since "+ player.getProfile().getWeapon().weaponLength + "<"+ opponent.getProfile().getWeapon().weaponLength);
			
			//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
			finishCombat(opponent, player);
		
			if(player.alive){
				finishCombat(player, opponent);
			}
			
		//both have same length	
		}else{
			//related to attack time
			if(player.getAttack().getTime() < opponent.getAttack().getTime()) {//player goes first
				System.out.println(player.getProfile().getType() + "goes first since "+ player.getAttack().getTime() + "<"+ opponent.getAttack().getTime());
				
				//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
				finishCombat(player, opponent);
				
				if(opponent.alive){
					finishCombat(opponent, player);
				}
				
			}else{//oponnent goes first	
				System.out.println(player.getProfile().getType() + "goes second since "+ player.getAttack().getTime() + ">"+ opponent.getAttack().getTime());
				
				//each character makes an attack against each other, if one dies before he attacks it is discounted if slower
				finishCombat(opponent, player);
				
				if(player.alive){
					finishCombat(player, opponent);
				}
			}
		}
		
		//fastest fellow hits first in subsequent rounds
		//COPY IT FORMM WHEN WEPONLENGTH IS SAME
		

		
		
		
		//restore all action chits for next round done in Character.resetFight()
		
		//set true so they don't fight again today, will be reset in CHaracter.resetFIght() at the beginning of each day
		player.getProfile().setFoughtToday(true);
		opponent.getProfile().setFoughtToday(true);
		System.out.println("Fight FInished");
	}

	private static void finishCombat(Player attacker, Player defender) {
		// TODO combat resolution	
		/*max of 2 effort per round, if higher it is cancelled

		if hit inflicts harm (THML)+levels of sharpness
		if don't hit armor sharp stars increase harm a level
		if hit armor 1 sharp star dosn't count, rest increase harm
		
		if hit with missile weapon, roll on missile table and adjust harm
		if strength of Fight chit bigger that weight of weapon increase 1 level only
		
		if weapon hits it becomes unalerted
		if misses it becomes alerted
		
		denizen harm compared to vulnerability
		
		when armor hit by attack inflicting less harm it ignores
		when armor hit by harm equal to toughness becomes damaged
		when armor hit by greater it is destroyed
		if damaged armor damaged again it is destroyed;
		2* limit
		combat resolved into 1 death, 2 death or combat stop
		ignore fatigued and wounded counters*/
	}
	
}
