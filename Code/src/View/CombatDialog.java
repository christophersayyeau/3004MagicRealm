package View;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		//	need a Fight chit to do attack
		//	attack time=time on weapon, if none then time on Fight chit;
		//TODO ask about Fight chit to use	
		



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
			break;
		case "Make Weapon Alert":	
			player.setCombatAttackDirection("Make Weapon Alert");
			break;
		}

	}
}
		    