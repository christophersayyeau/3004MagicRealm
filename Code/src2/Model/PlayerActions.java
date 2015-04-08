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
		GUI.combatMessage("You have " + fatigueness + " fatigued chits and " + wounds + " wounded chits.");


		//get all options for both possibilities
		String[] options = null;
		options = CombatChit.getFatigueWoundChits(person);

		if(options == null)//no null errors
			return;
		
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

	public static void lootingAction(Player player, boolean cheating, Map map, int currentTile, int currentClearing) {
		
		if(map.getMapTile(currentTile).clearing[currentClearing].isDrop){//if there is a player drop
			if (map.getMapTile(currentTile).treasure.found){
				//ask user which one to loot
				if(GUI.lootChoices()){	
					//loot treasure
					loot(player, map, cheating, map.getMapTile(currentTile).treasure, map.getMapTile(currentTile).treasure.shinies);
				}else{	
					//loot belongings
					loot(player, map, cheating, map.getMapTile(currentTile).clearing[currentClearing].getPlayerDrop(), map.getMapTile(currentTile).clearing[currentClearing].getPlayerDrop().belongings);
				}
			}else{
				//only loot belongings
				loot(player, map, cheating, map.getMapTile(currentTile).clearing[currentClearing].getPlayerDrop(), map.getMapTile(currentTile).clearing[currentClearing].getPlayerDrop().belongings);
			}
		}else{	//only treasure
			if (map.getMapTile(currentTile).treasure.found){
				loot(player, map, cheating, map.getMapTile(currentTile).treasure, map.getMapTile(currentTile).treasure.shinies);
			}else{
				System.out.println("Havent Found the treasure yet");
			}
		}
	}

	//handles both treasure and playerDrops
	private static void loot(Player player, Map map, boolean cheating, MapChits treasure, Items[] shinyStuff) {
		
		int result;
			//cheat mode or not
			if(cheating)
				result = Die.dieRollCheat();
			else
				result = Die.dieRoll();

			//if you roll over the number of treasures there you get nothing
			if(result > shinyStuff.length){
				System.out.println("Too GReedy, you will be Punished by finding nothing");
				return;
			}
			
			//if you found all the treasure
			if(result ==  shinyStuff.length){
				map.giveWholeTreasure(player, shinyStuff);//this gives all the contents then removes the chit
				return;
			}

			//at this point result<number of treasures
			switch (result){
			//the value of result is the number of treasure items you get
			
			case 1:  	map.giveOneTreasure(player, shinyStuff);//gives a treasure then removes it from goldChits array
			break;		
			//give 2 treasures
			case 2:  	for(int a=0; a<2; a++)	map.giveOneTreasure(player, shinyStuff);
			break;
			//etc
			case 3:  	for(int a=0; a<3; a++)	map.giveOneTreasure(player, shinyStuff);
			break;
			case 4:  	for(int a=0; a<4; a++)	map.giveOneTreasure(player, shinyStuff);
			break;
			case 5:  	for(int a=0; a<5; a++)	map.giveOneTreasure(player, shinyStuff);
			break;
			case 6:  	for(int a=0; a<6; a++)	map.giveOneTreasure(player, shinyStuff);
			break;
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
		case 1:  	
			//now choose whether you found passages or the treasure
			if(GUI.whatFound(currentTile)){//wants treasure
				GUI.revealTreasure();
				map.getMapTile(currentTile).treasure.found = true;
				
			}else{//wants hidden paths
				//display all passages
				map.getMapTile(currentTile).secretRoads(currentTile);
			}	
		break;
		case 2:  	//display all passages
			map.getMapTile(currentTile).secretRoads(currentTile);
			break;
		case 3:  	//display all passages
			map.getMapTile(currentTile).secretRoads(currentTile);
			break;
		case 4:  	GUI.revealTreasure();
					map.getMapTile(currentTile).treasure.found = true;
		break;
		case 5:break;
		case 6:break;
		}
		//When he discovers a roadway or treasure site, he is the only one who discovers it; it remains concealed from others, who must discover it on their own if they wish to use it.  He does not have to admit whether he actually discovers a treasure site. He must reveal what he rolled, but he does not have to reveal whether there is a treasure site chit in his clearing.
		//Once an individual discovers a hidden path, secret passage or treasure site, he never has to discover it again. He keeps a record of each discovery by crossing it off the Discoveries list on his Personal History sheet.
		//Once he has discovered a treasure site, he can search it for treasure whenever he is in its clearing.


	}

	@SuppressWarnings("static-access")
	public static void moveAction(Player player, boolean cheating, Map map, GUI view) {

		//THere are rules to handle moving through mountains+caves
		view.moveLabel.setText("Click on a clearing to move the character");
		//game.view.Instruction.setVisible(true);
		//int newLocation = Integer.parseInt(action.substring(5));
		view.setMove(true);
		
		int newLocation;
		if(cheating)
			newLocation = view.getNewLocation();
		else	
			newLocation = view.getNewLocation();


		System.out.println("New location = "+newLocation);
		System.out.println("Current Location = "+ player.getProfile().getCurrentLocation());

		if( map.canHeMove(player.getProfile().getCurrentLocation(), newLocation, player) ){
			//there are rules about how much weight
			map.moveCharacters(player, newLocation);//if yes then move

			//view.amazon.setLocation(view.getPlayerX(),view.getPlayerY());
			view.player[player.getPlayerNum()].setLocation(view.getPlayerX(),view.getPlayerY());
			
			System.out.println("You can move here, moving character now");
		}else{
			System.out.println("Can't Move There, phase wasted");
		}
		/*	//check to see if they can
			if( map.canHeMove(profile.getCurrentLocation(), newLocation, this) ){
				//there are rules about how much weight
				map.moveCharacters(this, newLocation);//if yes then move
			}else{
				System.out.println("Can't Move There, phase wasted");
			}
		 */
		
		//game.view.Instruction.setVisible(false);
		
	}

}
