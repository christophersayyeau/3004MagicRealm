package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Control.Game;

public class ServerGUI {
	Game game;
	JFrame dialog;
	
	public ServerGUI(Game g){
		game = g;
		
		String[] i = {"1","2","3","4","5","6"};
		
		dialog = new JFrame();
		dialog.setTitle("Game Setup");
		dialog.setSize(200, 100);
		dialog.setLocation(450, 200);
		dialog.setResizable(false);
		
		JPanel setupPanel = new JPanel();
		
		JLabel label = new JLabel("Enter number of Players: ");
		
		final JComboBox listPlayers = new JComboBox(i);
		listPlayers.setSelectedIndex(0);
		
		JButton button = new JButton();
		button.setText("OK");
		
		setupPanel.add(label);
		setupPanel.add(listPlayers);
		setupPanel.add(button);
		
		dialog.add(setupPanel);
		dialog.setVisible(true);

		button.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{ OK(listPlayers.getSelectedIndex() + 1); }
				}
		);
	}
	
	public void OK(int i) {
		game.numOfPlayers = i;
		game.gotNumPlayers = true;
		dialog.dispose();
		System.out.println(i);
	}

}
