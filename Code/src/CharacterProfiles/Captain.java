/*

Captain: The Captain is a renowned hero of many wars. His strength, weapon and armor make him dangerous when facing Medium or Heavy opponents, but he needs heavier equipment to deal with heavily armored foes. He is not really strong enough to face Tremendous foes.

*/
package CharacterProfiles;

//import CharacterProfiles.Character.Special;
import Model.*;
import Model.Weapon.ShortSword;
import Model.Armor.Helmet;
import Model.Armor.Breastplate;
import Model.Armor.Shield;

public class Captain extends Character{

	//constructor
	public Captain(){
		setType("Captain");
		
		//startSpot[0] = inn;		//the Captain starts in the inn
		currentLocation = 123;		//currently in tile 12 clearing 3, Bad Valley 3
	//TODO	add more start location in second iteration
//		startSpot[1] = house;
//		startSpot[2] = guardHouse;
		System.out.println("!!!!Captain has more options for start spots!!!!");
		
		weight = 2;				//Captain is Medium
		
		//trading groups
		friendlyTrading[0] = "Soldier";
		friendlyTrading[1] = "Patrol";
		friendlyTrading[2] = "Guard";
		friendlyTrading[3] = "Scholar";
		unfriendlyTrading[0] = "Woodfolk";
		enemyTrading[0] = "Bashkar";
		
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
		action1.setType("Move");	//set the type
		action1.setTime(4);			//the time needed to use
		action1.setEffort(1);		//extra effort needed to do action, 0-1-2
		action1.setStrength("M");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(6);			//the time needed to use
		action2.setEffort(0);		//extra effort needed to do action, 0-1-2
		action2.setStrength("H");	//strength of action
			
		action3.setType("Fight");	//set the type
		action3.setTime(4);			//the time needed to use
		action3.setEffort(1);		//extra effort needed to do action, 0-1-2
		action3.setStrength("M");	//strength of action
		
		//set special abilities
		specials[0] = Special.AIM;
		specials[1] = Special.REPUTATION;
	}
	
	//Special Advantages
	//Aim			//The Captain subtracts one from each die roll whenever he rolls on the Missile Table.
	/*
	 * if(weapon.missle == true){
	 *	//subtract one from the attack die roll
	 *	}
	 */
	//Reputation 	//The Captain can record and do an extra phase each day he is at a Dwelling (including a campfire). He must be at the Dwelling when he starts to do the phase, not when he records it. He can use the extra phase to do any normal activity.

}
