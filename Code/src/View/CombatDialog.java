package View;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import Model.ArrayUtils;
import Control.Player;

public class CombatDialog extends JDialog{
	  
							/*public CombatDialog(Player player) { i used the other version at bottom
								    
								    final Container cp = getContentPane();
								    cp.setLayout(new FlowLayout());
								    final JLabel title = new JLabel("Choose Attack Options");
								    cp.add(title);
								    
						
									// TODOprep player for combat
									//1)select fight counter and attack direction
								    //attack has direction and time(before it hits)
									//need a Fight chit to do attack
									//attack time=time on weapon, if none then time on Fight chit;
									//attack directions: thrust(straight)	Swing(sides)	Smash(down)
								    
								    
								    //Radio Buttons for choices
								    JRadioButton thrustButton = new JRadioButton("Thrust");
								    thrustButton.setActionCommand("Thrust");
								   // thrustButton.setSelected(true);		    
								    JRadioButton swingButton = new JRadioButton("Swing");
								    swingButton.setActionCommand("Swing");		    
								    JRadioButton smashButton = new JRadioButton("Smash");
								    smashButton.setActionCommand("Smash");	    
								    JRadioButton nothingButton = new JRadioButton("Do Nothing");
								    nothingButton.setActionCommand("Nothing");
								    	    
								    //Group them
								    final ButtonGroup directionGroup = new ButtonGroup();
								    directionGroup.add(thrustButton);
								    directionGroup.add(swingButton);
								    directionGroup.add(smashButton);
								    directionGroup.add(nothingButton);
								    
								    //ActionListener
								    ActionListener radList = new ActionListener() {
							            public void actionPerformed(ActionEvent e) {
							                JRadioButton button = (JRadioButton) e.getSource();
							                System.out.println("You select button: " + button.getText());
							        }};
							        //add to radio buttons
								    thrustButton.addActionListener(radList);
								    swingButton.addActionListener(radList);
								    smashButton.addActionListener(radList);
								    nothingButton.addActionListener(radList);
								    
								    //add to the frame
								    cp.add(thrustButton);
								    cp.add(swingButton);
								    cp.add(smashButton);
								    cp.add(nothingButton);
								   	    
								    //if weapon is unalert
								    if(!player.getProfile().getWeapon().getAlert()){
								    	JRadioButton alertButton = new JRadioButton("Alert Your Weapon");
								    	alertButton.setActionCommand("Alert");
								    	directionGroup.add(alertButton);
								    	alertButton.addActionListener(radList);
								    	cp.add(alertButton);
								    	
								    }
								    
								    
								    //TODOnow handle picking the Fight CHit used
								    
								    
																
								    
								    JButton ok = new JButton("OK");	    
								    ok.addActionListener(new ActionListener() {
								      public void actionPerformed(ActionEvent e) {
								    	  //when user clicks okay
								    	  
								    	  //if nothing selected
								    	  if(directionGroup.getSelection() == null){
								    		  System.out.println("SELECT SOMETHING");
								    		  title.setText("Havent't Selected Everything Yet");
								    		  
								    	  }else{
								    		  
								    		  System.out.println( directionGroup.getSelection().getActionCommand());
								    		  //TODOmake their be changes to player
								    		  dispose(); // Closes the dialog
								    	  }
								    	  
								      }
								    });
								    
								    cp.add(ok);
								    setSize(500, 500);  
							}*/
	
																	/*					//taken from internet	
																							class RadioListener implements ActionListener, //only one event type needed
																								    						ChangeListener, //for curiosity only
																								    						ItemListener {  //for curiosity only
																								public void actionPerformed(ActionEvent e) {
																								String factoryName = null;
																								
																								System.out.print("ActionEvent received: ");
																								if (e.getActionCommand() == "Thrust") {
																									System.out.println("Thrust" + " pressed.");			
																								} else {
																									System.out.println("Something Else" + " pressed.");
																								}
																								}
																								
																								public void itemStateChanged(ItemEvent e) {
																								System.out.println("ItemEvent received: " 
																								        + e.getItem()
																								        + " is now "
																								        + ((e.getStateChange() == ItemEvent.SELECTED)?
																									      "selected.":"unselected"));
																								}
																								
																								public void stateChanged(ChangeEvent e) {
																								System.out.println("ChangeEvent received from: "
																								        + e.getSource());
																								}
																							}*/




	public CombatDialog(Player player) {	
		//	attack has direction and time(before it hits)		
		//attack directions: thrust(straight)	Swing(sides)	Smash(down)
		String[] choices = {"Thrust", "Swing", "Smash", "Do Nothing"};//can choose not to make attack

		//can play Fight chit during melee step to alert weapon instead
		if(!player.getProfile().getWeapon().getAlert())//if weapon is unalert
			choices = ArrayUtils.add(choices, "Make Weapon Alert");

		//now ask user to pick
		Object response = JOptionPane.showInputDialog(null, "Which Attack Direction Do You Want To Use?",	"Attack Direction",
				JOptionPane.PLAIN_MESSAGE,
				null,	choices, choices[0]);

		//interpret response		
		switch((String)response){
		//first handle the sounds
		case "Thrust":		
			player.setCombatAttackDirection("Thrust");
			break;
		case "Swing":	
			player.setCombatAttackDirection("Swing");
			break;
		case "Smash":		
			player.setCombatAttackDirection("Smash");
			break;
		case "Do Nothing":		
			player.setCombatAttackDirection("Do Nothing");
			return;//dont do anything
		case "Make Weapon Alert":	
			player.setCombatAttackDirection("Make Weapon Alert");
			break;
		}
		
		
		//	need a Fight chit to do attack		
		String[] options = null;
		for(int a=0; a< player.getProfile().action1Num; a++){	//add all of the first action available
			if(player.getProfile().action1.getType().compareTo("Fight") == 0)	//only fight type allowed
				options = ArrayUtils.add(options, player.getProfile().action1.toString());
		}
		for(int a=0; a< player.getProfile().action2Num; a++){	//add all of the second action available
			if(player.getProfile().action2.getType().compareTo("Fight") == 0)
				options = ArrayUtils.add(options, player.getProfile().action2.toString());
		}
		for(int a=0; a< player.getProfile().action3Num; a++){	//add all of the third action available
			if(player.getProfile().action3.getType().compareTo("Fight") == 0)
				options = ArrayUtils.add(options, player.getProfile().action3.toString());
		}

		//now ask user to pick
		response = JOptionPane.showInputDialog(null, "Which Fight Chit do You Wish To Use?",	"Attack",
				JOptionPane.PLAIN_MESSAGE,
				null,	options, options[0]);

		//going to seperate based on user type
		if(player.getProfile().getType().compareTo("Amazon") == 0){
			//interpret response	Fight:  Time 4,  Effort 1,	Strength 1
			switch((String)response){
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M					
				//set the values for your attack
				player.setAttack(player.getProfile().action1);
				player.getProfile().action1Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(player.getProfile().getType().compareTo("BlackKnight") == 0){
			switch((String)response){
			case "Fight:  Time 4, Effort 2, Strength 2":	//42H					
				//set the values for your attack
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 3, Effort 2, Strength 1":	//32M
				player.setAttack(player.getProfile().action3);
				player.getProfile().action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(player.getProfile().getType().compareTo("Captain") == 0){
			switch((String)response){
			case "Fight:  Time 6, Effort 0, Strength 2":	//60H					
				//set the values for your attack
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 4, Effort 1, Strength 1":	//41M
				player.setAttack(player.getProfile().action3);
				player.getProfile().action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(player.getProfile().getType().compareTo("Dwarf") == 0){
			switch((String)response){
			case "Fight:  Time 5, Effort 2, Strength 3":	//52T					
				//set the values for your attack
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 5, Effort 1, Strength 3":	//51T
				player.setAttack(player.getProfile().action3);
				player.getProfile().action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(player.getProfile().getType().compareTo("Elf") == 0){
			switch((String)response){
			case "Fight:  Time 3, Effort 1, Strength 1":	//31M					
				//set the values for your attack
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 4, Effort 0, Strength 1":	//40M
				player.setAttack(player.getProfile().action3);
				player.getProfile().action3Num--;	//lower the num of action# since you can only use it once a day
				break;
			}
			
		}else if(player.getProfile().getType().compareTo("Swordsman") == 0){
			switch((String)response){
			case "Fight:  Time 4, Effort 0, Strength 0":	//40L					
				//set the values for your attack
				player.setAttack(player.getProfile().action1);
				player.getProfile().action1Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 5, Effort 0, Strength 1":	//50M
				player.setAttack(player.getProfile().action2);
				player.getProfile().action2Num--;	//lower the num of action# since you can only use it once a day
				break;
			case "Fight:  Time 2, Effort 2, Strength 0":	//22L
				player.setAttack(player.getProfile().action3);
				player.getProfile().action3Num--;	//lower the num of action# since you can only use it once a day
				break;	
			}
			
		}else{
			System.out.println("CANNOT IDENTIRY WHO IS DOING ACTION");
		}
	}
}
		    