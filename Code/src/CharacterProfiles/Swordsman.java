/*

Swordsman: The Swordsman is a wily and nimble rascal, quick to react to an opportunity or threat. In combat he is extremely fast with his sword and with his feet: against individual Light, Medium and Heavy opponents his speed makes him a deadly antagonist, and he can run away when he faces Tremendous monsters, armored Heavy monsters and enemies who outnumber him.

*/
package CharacterProfiles;

import Model.Weapon.ThrustingSword;

public class Swordsman extends Character{

	//constructor
	public Swordsman(){
		type = "Swordsman";
		
		startSpot[0] = inn;		//the Swordsman starts in the inn
		weight = 1;				//Swordsman is Light
		
		//trading groups
		friendlyTrading[0] = "Rogue";
		friendlyTrading[1] = "Company";
		friendlyTrading[2] = "Warlock";
		enemyTrading[0] = "Patrol";
		
		//Combat chits
		ThrustingSword weapon = new ThrustingSword();

		
		//Action types
		action1.setType("Fight");	//set the type
		action1.setTime(4);			//the time needed to use
		action1.setEffort(0);		//extra effort needed to do action, 0-1-2
		action1.setStrength("L");	//strength of action
		
		action2.setType("Fight");	//set the type
		action2.setTime(5);			//the time needed to use
		action2.setEffort(0);		//extra effort needed to do action, 0-1-2
		action2.setStrength("M");	//strength of action
			
		action3.setType("Fight");	//set the type
		action3.setTime(2);			//the time needed to use
		action3.setEffort(2);		//extra effort needed to do action, 0-1-2
		action3.setStrength("L");	//strength of action
	}
	
	//Special Advantages
	Barter		//The Swordsman rolls one die instead of two whenever he uses the Meeting Table during a Trade activity. Note: He gets this advantage only during the Trade activity. He does not get it during the Hire activity or when he rolls for battling natives.
	
	Clever 		//Instead of taking his turn when his Attention chit is picked, the Swordsman chooses when he will take his turn.
					//2.1  At Sunrise he keeps his Attention chit instead of mixing it in with the others, and each time a new Attention chit is about to be picked during Daylight he can preempt and take his turn at that point. He can preempt only once per day (he gets only one turn per day), he cannot interrupt another character's turn once that other character's chit has been picked, and if he has not taken his turn when all of the Attention chits have been picked he must take his turn at that point.
					//2.2 The ability to preempt applies only during Daylight. It does not work when chits are picked during other periods of the day.
					//2.3  If several characters have the ability to preempt (due to spells or duplicate Swordsmen in the game), they can preempt or pass in turn, starting with the last character to take a turn and going to the left, skipping any characters who do not have the ability to preempt. When no chits remain to be picked, any characters who have not yet taken their turns cannot pass.

	
}
