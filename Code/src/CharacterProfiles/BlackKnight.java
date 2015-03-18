/*

BLACK KNIGHT: The Black Knight is a dead¬ly and feared veteran of many battlefields. He is at his best against humans. He is too weak to dispatch Tremendous monsters until he gets a heavier weapon.

*/
package CharacterProfiles;

//import CharacterProfiles.Character.Special;
import Model.*;
import Model.Weapon.Mace;
import Model.Armor.Shield;
import Model.Armor.Suit_of_Armor;

public class BlackKnight extends Character{

	//constructor
	public BlackKnight(){
		setType("BlackKnight");
		
		startSpots[0] = "Inn";		//the BlKn starts in the inn
		currentLocation = 25;		//currently in tile 2 clearing 3, Bad Valley 3
		
		weight = 2;				//BlKn is Medium
		
		//trading groups
		allyTrading[0] = "Company";
		friendlyTrading[0] = "Soldier";
		friendlyTrading[1] = "Crone";
		unfriendlyTrading[0] = "Lancer";
		enemyTrading[0] = "Guard";
		
		//Combat chits
		Mace weapon1 = weapon.new Mace();
		setWeapon(weapon1);
		
		//built a temporary armor object so I have access to its subclass
		Armor temp = new Armor();
		
		Suit_of_Armor armor = temp.new Suit_of_Armor();
		getDefense()[0] = armor;
		
		Shield shield = temp.new Shield();
		getDefense()[1] = shield;
		shieldActive = true;
		
		//Action types
		action1.setType("Move");	//set the type
		action1.setTime(4);			//the time needed to use
		action1.setEffort(2);		//extra effort needed to do action, 0-1-2
		action1.setStrength("H");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(4);			//the time needed to use
		action2.setEffort(2);		//extra effort needed to do action, 0-1-2
		action2.setStrength("H");	//strength of action
			
		action3.setType("Fight");	//set the type
		action3.setTime(3);			//the time needed to use
		action3.setEffort(2);		//extra effort needed to do action, 0-1-2
		action3.setStrength("M");	//strength of action
		
		//set special abilities
		specials[0] = Special.AIM;
		specials[1] = Special.FEAR;
	}
	
	//Special Advantages
	//Aim			//The Black Knight subtracts one from each die roll whenever he rolls on the Missile Table.
	
	//Fear		//Whenever the Black Knight rolls on the Meeting Table he rolls one die instead of two. His deadly reputation makes it easier for him to trade and hire natives, and it makes his enemies think twice before blocking or battling him.
}
