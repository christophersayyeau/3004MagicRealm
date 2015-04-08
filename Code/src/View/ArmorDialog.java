package View;

import javax.swing.JOptionPane;

import Control.Player;

public class ArmorDialog {

	public static void getArmor(Player player) {
		//ask which direction the chield protects you {from ALL_DIRECTIONS, THRUST_AND_SWING, SMASH, ANY_ONE_DIRECTION}
		String question = "You are currently protected in these directions: ";
		
		//now add the covered directions
		for(int i=0; i< player.getProfile().getDefense().length; i++){
			question += player.getProfile().getDefense(i).getDirection();
			question += " ";
		}		
		question += "\nNow you will choose which direction ANY_ONE_DIRECTION will protect you from.";
		
		//create responses
		String[] choices = {"Thrust", "Swing", "Smash"};
		
		//now offer the question to user and get his result	
		Object response = JOptionPane.showInputDialog(null, question,	player.getProfile().getType() + "'s Shield Direction",
						JOptionPane.PLAIN_MESSAGE,
						null,	choices, choices[0]);
		//interpret response		
		switch((String)response){
		//first handle the sounds
		case "Thrust":		
			player.setShieldDirection("THRUST");
			break;
		case "Swing":	
			player.setShieldDirection("SWING");
			break;
		case "Smash":		
			player.setShieldDirection("SMASH");
			break;
		}
	}

}
