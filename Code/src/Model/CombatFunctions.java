package Model;

import View.GUI;
import Control.Player;

public class CombatFunctions{

	public static void resolveCombat(GUI view, Player player, Player opponent) {
		System.out.println("Fight Starting between " + player.getProfile().getClass() + player.getProfile().getClass());
		
/*		one round of combat between 2 players
		no running away
		2* limit
		combat resolved into 1 death, 2 death or combat stop
		ignore fatigued and wounded counters*/
		
		view.selectFightGear(player);
		view.selectFightGear(opponent);
		
		
		// TODO combat resolution
		//Does fastest fellow hit
		if(player.getAttack().getTime() < opponent.getAttack().getTime()) {//player goes first
		}else{//oponnent goes first
		}
		//longest weapon goes first
		if(player.getProfile().getWeapon().weaponLength > opponent.getProfile().getWeapon().weaponLength){//player goes first			
		}else{		
		}
		
		
		//max of 2 effort per round, if higher it is cancelled
		/*each character makes an attack against each other, if one dies before he attacks it is discounted if slower
		1st round hit order is weapon length,then faster attack time
		//	attack time=time on weapon, if none then time on Fight chit;
		
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
		restore all action chits for next round*/
		
		
		//set true so they don't fight again
		player.getProfile().setFoughtToday(true);
		opponent.getProfile().setFoughtToday(true);
		System.out.println("Fight FInished");
	}
	
}
