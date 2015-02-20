package View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Control.Game;
import Control.Player;
import Model.Map;

public class GUI implements MouseListener{
	
	Game game;
	
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public JLabel[] mapTiles;
	int tileCount;
	
	public static JPanel Players = new JPanel();
	public static JPanel Buttons = new JPanel();
	
	//set tile values
	final int x = 125;
	final int y = 215;
	final int tileX = 300;
	final int tileY = 305;
	
	//constructor, called in player.java
	public GUI(Game g)
	{
		//allows to position tiles
		Map.setLayout(null);
		Map.setBackground(Color.white);
				
		tileCount = 0;
		mapTiles = new JLabel[20];
		//1st row
		tileBuilder("res/tiles/cliff.png", 2, 0);
		//second row
		tileBuilder("res/tiles/evil_valley.png", 1, 1);
		tileBuilder("res/tiles/ledges.png", 3, 1);
		tileBuilder("res/tiles/crag.png", 5, 1);
		tileBuilder("res/tiles/dark_valley.png", 7, 1);
		//third row
		tileBuilder("res/tiles/high_pass.png", 0, 2);
		tileBuilder("res/tiles/borderland.png", 2, 2);
		tileBuilder("res/tiles/oak_woods.png", 4, 2);
		tileBuilder("res/tiles/deep_woods.png", 6, 2);
		tileBuilder("res/tiles/curst_valley.png", 8, 2);
		//forth row
		tileBuilder("res/tiles/cavern.png", 1, 3);
		tileBuilder("res/tiles/bad_valley.png", 3, 3);
		tileBuilder("res/tiles/maple_woods.png", 5, 3);
		tileBuilder("res/tiles/nut_woods.png", 7, 3);
		//fifth row
		tileBuilder("res/tiles/mountain.png", 2, 4);
		tileBuilder("res/tiles/caves.png", 4, 4);
		tileBuilder("res/tiles/ruins.png", 6, 4);
		tileBuilder("res/tiles/awful_valley.png", 8, 4);
		
		//sixth row
		tileBuilder("res/tiles/pine_woods.png", 3, 5);
		tileBuilder("res/tiles/linden_woods.png", 7, 5);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		MainWindow.setBackground(Color.LIGHT_GRAY);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setVisible(true);
		MainWindow.setExtendedState(MainWindow.MAXIMIZED_BOTH);
		
		Map.setPreferredSize(new Dimension(1400,1400));
		scrollPane.setBackground(Color.white);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(Map);
		scrollPane.setPreferredSize(new Dimension((int)screenSize.getWidth()/2 - 20, (int)screenSize.getHeight()- 100));
		Map.setAutoscrolls(true);
		Map.addMouseListener(this);
		
		MainWindow.getContentPane().add(scrollPane);
		scrollPane.setLocation((int)screenSize.getWidth()/2, 0);
		scrollPane.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()-60);

		Players.setLayout(new BorderLayout()); 

		Players.setPreferredSize(new Dimension((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3));
		Players.setBackground(Color.white);
		MainWindow.getContentPane().add(Players);
		Players.setLocation(0,0);
		Players.setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3);
		
		ImageIcon p1 = new ImageIcon("res/characters/amazon.png");
		JLabel qwe = new JLabel();
		qwe.setIcon(p1);
		qwe.setVisible(true);
		qwe.setLocation(565,520);
		qwe.setSize(50,50);
		Map.add(qwe);
		Map.setComponentZOrder(qwe, 0);
		
		
		//TODO add in function calls
		Buttons.setLayout(new FlowLayout());
		JButton move = new JButton("Move");
		move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the move function");
			}
		});
		
		JButton hide = new JButton("Hide");
		hide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the hide function");
			}
		});
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the search function");
			}
		});
		
		JButton rest = new JButton("Rest");
		rest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the rest function");
			}
		});
		
		JButton trade = new JButton("Trade");
		trade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the trade function");
				doYouWantToTrade();
			}
		});
		
		
		Buttons.add(move);
		Buttons.add(hide);
		Buttons.add(search);
		Buttons.add(rest);
		Buttons.add(trade);
		
		Buttons.setBackground(Color.gray);
		
		MainWindow.getContentPane().add(Buttons);
		
		Buttons.setLocation(0,(int)screenSize.getHeight()/3);
		Buttons.setSize((int)screenSize.getWidth()/2,40);
	}
	
	/* Function to create mapTiles
	 * takes in image location along with the offset of the specific tile
	 * creates a JLabel and adds it to the map array while adding a click listener
	 */
	public void tileBuilder(String path, int xOffset, int yOffset){
		ImageIcon icon = new ImageIcon(path);
		JLabel label = new JLabel();
		label.setIcon(icon);
		label.setLocation(x*xOffset,y*yOffset);
		label.setSize(tileX, tileY);
		label.setVisible(true);
		//label.addMouseListener(this);
		mapTiles[tileCount++] = label;
		Map.add(label);
	}
	
	/* Allows player selection
	 * 
	 */
	public String createPlayer(){
		String[] possibilities = {"Amazon","Black Knight", "Captain", "Dwarf", "Elf", "Swordsman"};
		Object s = JOptionPane.showInputDialog(
				Players,
				"Which character would you like to be?\n",
				"Choose your character",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				"Amazon");
		System.out.println("You have chosen to be a " + s);
		//game.gotCharacter = true;
		return (String)s;
		
		//TODO, only suppose to be 1 of each type max, but that isnt important right now, work on other stuff
	}
	
	public int numOfPlayers(){
		String[] i = {"1","2","3","4","5","6"};
		
		Object s = JOptionPane.showInputDialog(
				Players,
				"How many players?\n",
				"New Players",
				JOptionPane.PLAIN_MESSAGE,
				null,
				i,
				"Amazon");
		String x = (String)s;
		return Integer.valueOf(x);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println(e.getSource());
		System.out.println("x = " + e.getX());
		System.out.println("y = " + e.getY());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures, maps and related visuals
		
	}
	
	public void recordTurn(Player player, int phasesAvailable, Model.Map gameMap) {//returns the number of phases
		
				
		//TODO 
		/*
		all of the characters secretly and simultaneously
		record what they will do during their turns. When each character does his
		turn, he must do it exactly as he recorded it.
		He can use his turn to	move, hide, search, trade and rest.
		When each character does his turn, he must do it exactly as he recorded it.
		He can leave phases blank.
		He can record only one activity per phase, but he can record any activity in any phase, repeating or switching activities as he wishes
		 */
		
		
		
		System.out.println("User now builds his turn");	
		 
	}

	public void hideMapChits() {
		//end of day, all map chits that can be seen are unseen
		// TODO Auto-generated method stub
		
	}

	public void revealMapChits(int i) {
		//caled at end of player turn
		// TODO Auto-generated method stub
		//System.out.println("mapchits in tile ->face up, substitue chits exchanged, other map chits summon new monsters from apperance chart");
	}

	public void trading(Map gameMap, Player player1) {
		// For now we assume he trades meaningless baubles and get some gold
		
		int currentTile = player1.getProfile().getCurrentLocation()/10-1;
		int currentClearing = player1.getProfile().getCurrentLocation()%10-1;
		
		//first determine if there is anyone else in clearing
		if(player1.isThereOthersInCLearing(gameMap, currentTile, currentClearing)){
			//ask user if he wants to trade
			if(doYouWantToTrade()){
				System.out.println("User traded some stuff and got 10 gold");
				player1.getProfile().setGold(player1.getProfile().getGold()+10);
			}
		}
	}
	public boolean doYouWantToTrade() {
		//just ask user if they want to trade with others in clearing, see declaration
		int n = JOptionPane.showConfirmDialog(Players,
				"Would you like to trade?",
				"Trade",
				JOptionPane.YES_NO_OPTION);
		if(n == 0){
			System.out.println("Should return true for trade");
			return true;
		}
		else{
			System.out.println("Should return false for trade");
			return false;
		}
	}
}
