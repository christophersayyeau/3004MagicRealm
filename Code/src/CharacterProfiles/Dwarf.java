/*

Dwarf: The Dwarf is a slow and powerful fighter who is at his best in the caves, where he is respected as a master of searching, hiding and fighting the monsters that live there. Outside of the caves he is slow and clumsy. In battle his ability to duck allows him to swiftly escape enemy blows and out-maneuver the largest and slowest denizens.
			He must be careful to avoid the fast opponents who live outside of the caves, however, and he is extremely vulnerable to attacks made by other characters, who can always Smash him as he ducks. Since he relies heavily on the ducking maneuver, his helmet is a critical part of his defenses.

*/
package CharacterProfiles;

//import CharacterProfiles.Character.Special;
import Model.Armor;
import Model.Armor.Helmet;
import Model.ArrayUtils;
import Model.Weapon.GreatAxe;

public class Dwarf extends Character{

	//constructor
	public Dwarf(){
		setType("Dwarf");
		
		startSpots[0] = "Inn";		//the Dwarf starts in the inn
		
		startSpots = ArrayUtils.add(startSpots, "GuardHouse");
		//startSpots[1] = "Guardhouse";
		
		currentLocation = 25;		//currently in tile 2 clearing 3, Bad Valley 3, the Inn

		
		weight = 3;				//Dwarf is Heavy
		setGold(23);
		this.setStartGold(23);
		
		//trading groups
		friendlyTrading[0] = "Company";
		friendlyTrading[1] = "Guard";
		friendlyTrading[2] = "Scholar";
		unfriendlyTrading[0] = "Woodfolk";
		unfriendlyTrading[1] = "Bashkars";
		
		//Combat chits
		GreatAxe weapon1 = weapon.new GreatAxe();
		setWeapon(weapon1);
		
		//built a temporary armor object so I have access to its subclass
		Armor temp = new Armor();
		
		Helmet helmet = temp.new Helmet();	
		//getDefense()[0] = helmet;
		this.defense = ArrayUtils.add(this.defense, helmet);
		setDefense(this.defense);
		
		//Action types
		action1.setType("Move");	//set the type
		action1.setTime(5);			//the time needed to use
		action1.setEffort(2);		//extra effort needed to do action, 0-1-2
		action1.setStrength("T");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(5);			//the time needed to use
		action2.setEffort(2);		//extra effort needed to do action, 0-1-2
		action2.setStrength("T");	//strength of action
			
		action3.setType("Fight");	//set the type
		action3.setTime(5);			//the time needed to use
		action3.setEffort(1);		//extra effort needed to do action, 0-1-2
		action3.setStrength("T");	//strength of action
		
		//set special abilities
		specials[0] = Special.SHORT_LEGS;
		specials[1] = Special.CAVE_KNOWLEDGE;
	}
	
	//Special Advantages
	//ShortLegs		//This "advantage" is a mixture of advantages and disadvantages:
						//1.1HANDLED The Dwarf can never use sunlight phases - he can only use basic phases (plus any extra phases due to belongings or spells).  He can Follow characters normally, even if they are using sunlight phases. Note: When using the optional Seasons/Weather rules, the Dwarf can also use Sheltered phases.
						//1.2 The doughty Dwarf can rest an extra effort asterisk each time he does a Rest activity.
						//1.3 The Dwarf can use his Duck chit as a special Move chit. He can play it only to do the "Duck" maneuver during the Melee Step. He cannot use it for any other purpose: he cannot use it to carry items, to charge or run away during the Encounter Step, and he cannot use it to do any maneu�ver except "Duck". For purposes of fatigue, it counts as a Move chit.  Note: In the Development Game, he can use the Duck chit even as a Youngster, before he receives the Short Legs special advantage.


	//CaveKnowledge 	//The Dwarf rolls one die instead of two whenever he uses the Hide table, the Meeting Table, or any Search table when he is in a cave clearing. This gives him some powerful advantages in the caves, somewhat offsetting his short legs. Obviously, the Dwarf prefers to spend as much time as possible in the caves.
	
}
