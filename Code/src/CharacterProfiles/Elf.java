/*

Elf: The Elf is an elusive and graceful warrior and magician. With his Light Bow he is a deadly match for anything less than an ar�mored Heavy foe, and with a Medium Bow he can face any opponent. He has the speed to escape numerous opponents.

*/
package CharacterProfiles;

import Model.Weapon.LightBow;

public class Elf extends Character{

	//constructor
	public Elf(){
		setType("Elf");
		
		//startSpot[0] = inn;		//the Elf starts in the inn
		currentLocation = 0;//change this later!!!
		
		weight = 1;				//Elf is Light
		
		//trading groups
		allyTrading[0] = "Woodfolk";
		friendlyTrading[0] = "Bashkar";
		unfriendlyTrading[0] = "Order";
		unfriendlyTrading[1] = "Scholar";
		enemyTrading[0] = "Lancer";
		
		//Combat chits
		LightBow weapon1 = weapon.new LightBow();				
		setWeapon(weapon1);
		
		//Magic magic = new Magic();//2 Spells (III or VII)//removed since we don't support magic in first iteration
		
		//Action types
		action1.setType("Move");	//set the type
		action1.setTime(4);			//the time needed to use
		action1.setEffort(0);		//extra effort needed to do action, 0-1-2
		action1.setStrength("M");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(3);			//the time needed to use
		action2.setEffort(1);		//extra effort needed to do action, 0-1-2
		action2.setStrength("M");	//strength of action
			
		action3.setType("Fight");	//set the type
		action3.setTime(4);			//the time needed to use
		action3.setEffort(0);		//extra effort needed to do action, 0-1-2
		action3.setStrength("M");	//strength of action
	}
	
	//Special Advantages
	//Elusiveness		//The Elf can record and do an extra Hide phase each day.

	//Archer 			//The Elf rolls one die Instead of two whenever he rolls on the Missile Table to make an attack with a bow or crossbow.
	
}
