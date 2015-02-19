/*

AMAZON:  The Amazon is a skilled warrior and soldier, with excellent speed and fair strength. She is deadliest against Medium and Heavy opponents. She should avoid or run from Tremen-dous and armored Heavy monsters, who are too dangerous for her to handle even if she ob¬tains heavier equipment.

*/
package CharacterProfiles;

import Model.*;
import Model.Weapon.ShortSword;
import Model.Armor.Helmet;
import Model.Armor.Breastplate;
import Model.Armor.Shield;

public class Amazon extends Character{
	
	//constructor
	public Amazon(){
		setType("Amazon");
		
		//startSpot[0] = inn;		//the Amazon starts in the inn
		currentLocation = 123;		//currently in tile 12 clearing 3, Bad Valley 3
			
		weight = 2;				//Amazon is Medium
		
		//trading groups
		friendlyTrading[0] = "Lancer";
		friendlyTrading[1] = "Patrol";
		friendlyTrading[2] = "Shaman";
		unfriendlyTrading[0] = "Company";
		unfriendlyTrading[1] = "Bashkars";
		
		//Combat chits
		ShortSword weapon1 = weapon.new ShortSword();
		setWeapon(weapon1);
		
		//built a temporary armor object so I have access to its subclass
		Armor temp = new Armor();
		
		Helmet helmet = temp.new Helmet();
		getDefense()[0] = helmet;
		
		Breastplate breastplate = temp.new Breastplate();		
		getDefense()[1] = breastplate;
		
		Shield shield = temp.new Shield();
		getDefense()[2] = shield;
		
		
		//Action types
		action1.setType("Fight");	//set the type
		action1.setTime(4);			//the time needed to use
		action1.setEffort(1);		//extra effort needed to do action, 0-1-2
		action1.setStrength("M");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(3);			//the time needed to use
		action2.setEffort(2);		//extra effort needed to do action, 0-1-2
		action2.setStrength("M");	//strength of action
			
		action3.setType("Move");	//set the type
		action3.setTime(3);			//the time needed to use
		action3.setEffort(1);		//extra effort needed to do action, 0-1-2
		action3.setStrength("M");	//strength of action
		
		//set special abilities
		specials[0] = Special.AIM;
		specials[1] = Special.STAMINA;
				
	}
	//Special Advantages
	//Aim				//The Amazon subtracts one from each die roll whenever she rolls on the Missile Table to attack with a missile weapon.
	/*
	 * if(weapon.missle == true){
	 *	//subtract one from the attack die roll
	 *	}
	 */
	//Stamina			//The Amazon can record and do an extra Move phase each turn. She gets this bonus even when she is riding a horse - her stamina includes being an excellent horsewoman. 
	
}
