package Model;

import javax.swing.JOptionPane;

import CharacterProfiles.Character;
import Control.Player;
import View.GUI;

public class PlayerActions {

	public static void restingAction(Player player) {
		//The rest action
		System.out.println("Resting Now");
		//resting (activity to get rid of wounds fatigue see page 21)
		//can either turn 1 wound into 1 fatigue chit
		//or return 1 fatigue to play
		Character person = player.getProfile();


		//first tell user their status so they don't waste a turn
		int wounds = 0;
		int fatigueness = 0;
		fatigueness = person.action1.fatigued + person.action2.fatigued + person.action3.fatigued;
		wounds = person.action1.wounded + person.action2.wounded + person.action3.wounded;
		GUI.combatMessage("You have " + fatigueness + " fatigued chits and/n" + wounds + "wounded chits.");


		//get all options for both possibilities
		String[] options = null;
		options = CombatChit.getFatigueWoundChits(person);

		//ask user what they want to do
		Object response = JOptionPane.showInputDialog(null, "Which Chit do You Wish To Fix?",	"Resting",
				JOptionPane.PLAIN_MESSAGE,
				null,	options, options[0]);

		//now interpret result (String)response, increase actionNum and lower faitgue/wound
		System.out.println("This is the substring" + ((String) response).substring(0, 4));
		switch(((String) response).substring(0, 4)){
		case "Fati":	//if user chose to unfatigue a chit
			//if user chose action1
			if(((String) response).substring(((String) response).length()).compareTo("1") == 0){
				person.action1Num++;
				person.action1.fatigued--;
			}
			//if user chose action2
			else if(((String) response).substring(((String) response).length()).compareTo("2") == 0){
				person.action2Num++;
				person.action2.fatigued--;
			}
			//if user chose action3
			else if(((String) response).substring(((String) response).length()).compareTo("3") == 0){
				person.action3Num++;
				person.action3.fatigued--;
			} else {
				System.out.println("ERROR, didn't recog the action");
			}
			break;
		case "Woun":	//if user chose to heal
			//if user chose action1
			if(((String) response).substring(((String) response).length()).compareTo("1") == 0){
				person.action1.wounded--;
				person.action1.fatigued++;
			}
			//if user chose action2
			else if(((String) response).substring(((String) response).length()).compareTo("2") == 0){
				person.action2.wounded--;
				person.action2.fatigued++;
			}
			//if user chose action3
			else if(((String) response).substring(((String) response).length()).compareTo("3") == 0){
				person.action3.wounded--;
				person.action3.fatigued++;
			} else {
				System.out.println("ERROR, didn't recog the action");
			}
			break;
		}
		/*//will just reset completely for now				
										person.action1Num = 4;
										person.action2Num = 4;
										person.action3Num = 4;	*/

	}

	public static void lootingAction(Player player, boolean cheating, Map map, int currentTile) {
		//need to have located it first before trying to loot
		if (map.getMapTile(currentTile).treasure.found){

			int result;
			//cheat mode or not
			if(cheating)
				result = Die.dieRollCheat();
			else
				result = Die.dieRoll();

			//if you roll over the number of treasures there you get nothing
			if(result > map.getMapTile(currentTile).treasure.shinies.length){
				System.out.println("Too GReedy");
				return;
			}

			switch (result){
			case 1:  	map.giveWholeTreasure(player, map.getMapTile(currentTile).treasure);
			break;
//			case 2:  	2nd//TODO second step, need to differentiate between contents of treasure
//			break;
//			case 3:  	3rd
//			break;
//			case 4:  	4th
//			break;
//			case 5:  	5th
//			break;
//			case 6:  	6th
//			break;
			}
		}

	}

	public static void locatingAction(Player player, boolean cheating, Map map, int currentTile) {
		int result;
		//cheat mode or not
		if(cheating)
			result = Die.dieRollCheat();
		else
			result = Die.dieRoll();


		switch (result){
		case 1:  	GUI.revealTreasure(currentTile);//technically you can choose but that is dumb
					map.getMapTile(currentTile).treasure.found = true;
		break;
		case 2:  	//TODO display all passages and mentally note that treasure
			break;
		case 3:  	//display all passages
			break;
		case 4:  	GUI.revealTreasure(currentTile);
					map.getMapTile(currentTile).treasure.found = true;
		break;
		//5 and 6 do nothing
		}
		//When he discovers a roadway or treasure site, he is the only one who discovers it; it remains concealed from others, who must discover it on their own if they wish to use it.  He does not have to admit whether he actually discovers a treasure site. He must reveal what he rolled, but he does not have to reveal whether there is a treasure site chit in his clearing.
		//Once an individual discovers a hidden path, secret passage or treasure site, he never has to discover it again. He keeps a record of each discovery by crossing it off the Discoveries list on his Personal History sheet.
		//Once he has discovered a treasure site, he can search it for treasure whenever he is in its clearing.


	}

}
