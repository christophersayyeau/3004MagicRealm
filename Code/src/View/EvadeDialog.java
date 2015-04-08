package View;

import javax.swing.JOptionPane;

import Control.Player;
import Model.ArrayUtils;

public class EvadeDialog {

	public static void getEvasion(Player player) {	//most of this code was taken from CombatDialog		
		
			//	evasion has direction and time(before it works)		
			//evade directions: charge(straight)	Dodge(sides)	Duck(down)
			String[] choices = {"Straight", "Dodge", "Duck", "Do Nothing"};		//can choose not to evade

			//now ask user to pick
			Object response = JOptionPane.showInputDialog(null, "Which Evade Direction Do You Want To Use?",	player.getProfile().getType() + "'s Evading Direction",
					JOptionPane.PLAIN_MESSAGE,
					null,	choices, choices[0]);

			//interpret response		
			switch((String)response){
			//first handle the sounds
			case "Straight":		
				player.setEvadeDirection("Straight");
				break;
			case "Dodge":	
				player.setEvadeDirection("Dodge");
				break;
			case "Duck":		
				player.setEvadeDirection("Duck");
				break;
			case "Do Nothing":		
				player.setEvadeDirection("Do Nothing");
				player.setEvade(null);
				return;//dont do anything
			}
			
			
			//	need a Move chit to do move		
			String[] options = null;
			for(int a=0; a< player.getProfile().action1Num; a++){	//add all of the first action available
				if(player.getProfile().action1.getType().compareTo("Move") == 0)	//only fight type allowed
					options = ArrayUtils.add(options, player.getProfile().action1.toString());
			}
			for(int a=0; a< player.getProfile().action2Num; a++){	//add all of the second action available
				if(player.getProfile().action2.getType().compareTo("Move") == 0)
					options = ArrayUtils.add(options, player.getProfile().action2.toString());
			}
			for(int a=0; a< player.getProfile().action3Num; a++){	//add all of the third action available
				if(player.getProfile().action3.getType().compareTo("Move") == 0)
					options = ArrayUtils.add(options, player.getProfile().action3.toString());
			}

			if(options == null){
				return;
			}
			//now ask user to pick
			response = JOptionPane.showInputDialog(null, "Which Move Chit do You Wish To Use?",	"Evading",
					JOptionPane.PLAIN_MESSAGE,
					null,	options, options[0]);

			//going to seperate based on user type
			if(player.getProfile().getType().compareTo("Amazon") == 0){
				//only 1 type of move for amazon
				player.setEvade(player.getProfile().action3);
				player.getProfile().action3Num--;
				
			}else if(player.getProfile().getType().compareTo("Black Knight") == 0){
				//only 1 type of move for blkknight
				player.setEvade(player.getProfile().action1);
				player.getProfile().action1Num--;
				
			}else if(player.getProfile().getType().compareTo("Captain") == 0){
				player.setEvade(player.getProfile().action1);
				player.getProfile().action1Num--;
				
			}else if(player.getProfile().getType().compareTo("Dwarf") == 0){
				player.setEvade(player.getProfile().action1);
				player.getProfile().action1Num--;
				
			}else if(player.getProfile().getType().compareTo("Elf") == 0){
				player.setEvade(player.getProfile().action1);
				player.getProfile().action1Num--;
				
			}else if(player.getProfile().getType().compareTo("Swordsman") == 0){
				System.out.println("If we reach here there is serious issues");
				
			}else{
				System.out.println("CANNOT IDENTIFRY WHO IS DOING ACTION "+player.getProfile().getType());
			}
		
	}

}
