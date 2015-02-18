package View;

import java.awt.event.ActionEvent;

import javax.swing.*;

import Control.*;

public class ServerGUI {
	Game game;
	JFrame dialog;
	
	public ServerGUI(Game g){
		game = g;
	}
	
	public void getPlayers(){
		String[] i = {"1","2","3","4","5","6"};
		
		dialog = new JFrame();
		dialog.setTitle("Game Setup");
		dialog.setSize(200, 100);
		dialog.setLocation(450, 200);
		dialog.setResizable(false);
		
		JPanel setupPanel = new JPanel();
		
		JLabel label = new JLabel("Enter number of Players: ");
		
		JComboBox listPlayers = new JComboBox(i);
		listPlayers.setSelectedIndex(0);
		
		JButton button = new JButton();
		button.setText("OK");
		
		setupPanel.add(label);
		setupPanel.add(listPlayers);
		setupPanel.add(button);
		
		dialog.add(setupPanel);
		dialog.setVisible(true);

		button.setActionCommand("" + listPlayers.getSelectedIndex());
	}
	
	public void actionPerformed(ActionEvent e) {
		game.numOfPlayers = Integer.valueOf(e.getActionCommand());
		System.out.println(e.getActionCommand());
		dialog.dispose();
	}

}
