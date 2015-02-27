package View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Control.Game;
import Control.Player;
import Model.Map;

public class GUI implements MouseListener{
	
	Game game;
	Map map;
	
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public JLabel[] mapTiles;
	public JLabel[][] clearingTiles;
	int tileCount;
	
	public static JPanel Players = new JPanel();
	
	//TODO revert if needed (original buttons location)
	//public static JPanel Buttons = new JPanel();
	
	//set tile values
	final int x = 125;
	final int y = 215;
	final int tileX = 300;
	final int tileY = 305;
	
	//constructor, called in player.java
	public GUI(Game g, Map m)
	{
		//allows to position tiles
		map = m;
		Map.setLayout(null);
		Map.setBackground(Color.white);
				
		tileCount = 0;
		mapTiles = new JLabel[20];
		clearingTiles = new JLabel[20][6];
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
		
		generateClearings();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		MainWindow.setBackground(Color.LIGHT_GRAY);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setVisible(true);
		MainWindow.setExtendedState(MainWindow.MAXIMIZED_BOTH);
		MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Map.setPreferredSize(new Dimension(1400,1400));
		scrollPane.setBackground(Color.white);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(Map);
		scrollPane.setPreferredSize(new Dimension((int)screenSize.getWidth()/2 - 20, (int)screenSize.getHeight()- 100));
		Map.setAutoscrolls(true);
		//Map.addMouseListener(this);
		
		MainWindow.getContentPane().add(scrollPane);
		scrollPane.setLocation((int)screenSize.getWidth()/2, 0);
		scrollPane.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()-60);

		Players.setLayout(new BorderLayout()); 

		Players.setPreferredSize(new Dimension((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3));
		Players.setBackground(Color.white);
		MainWindow.getContentPane().add(Players);
		Players.setLocation(0,0);
		Players.setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3);
		
		/*ImageIcon p1 = new ImageIcon("res/characters/amazon.png");
		JLabel qwe = new JLabel();
		qwe.setIcon(p1);
		qwe.setVisible(true);
		qwe.setLocation(560,1175);
		qwe.setSize(50,50);
		Map.add(qwe);
		Map.setComponentZOrder(qwe, 0);*/
		
	//TODO can these functions be erased? Since they are handled at the bottom	
		//TODO add in function calls
		/*
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
		*/
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
	
	//determines # of players for game
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
	
	public void generateClearings(){
		//generate valleys 4 clearings
		genValley(1, 1050, 985, 1085,1055, 1165, 990, 1085,920);
		genValley(2, 535,705,530,835,430,810,455,705);
		genValley(3,1090,495,1055,560,1145,595,1165,495);
		genValley(4,1040,410,1080,340,980,310,965,410);
		genValley(5,215,280,285,280,315,380,215,400);
		//generate woods 3 clearings
		genWoods(6,980,1235,960,1140,1050,1170);
		genWoods(7,805,810,715,845,750,715);
		genWoods(8,1060,810,960,845,980,745);
		genWoods(9,565,520,700,555,625,625);
		genWoods(10,450,1235,465,1135,560,1175);
		//generate 6 clearing tiles
		genOther(11,300,605,380,475,300,520,460,605,400,590,350,570);
		genOther(12,335,775,290,700,260,755,205,845,205,705,260,815);
		genOther(13,585,1060,585,920,630,975,550,1010,665,920,700,1030);
		genOther(14,90,625,200,560,170,625,125,560,55,560,165,500);
		genOther(15,915,920,950,1030,835,1055,875,990,835,920,890,1040);
		genOther(16,330,200,410,200,375,125,335,60,415,60,300,125);
		genOther(17,710,275,790,415,800,355,690,335,730,395,770,295);
		genOther(18,810,520,950,520,950,600,815,600,880,635,880,560);
		genOther(19,520,285,425,345,460,275,500,345,535,410,580,300);
		genOther(20,350,1020,420,1065,420,985,285,990,420,910,340,935);	
	}
	
	public void genValley(int tile,int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		
		l1.setSize(50,50);
		l2.setSize(50,50);
		l3.setSize(50,50);
		l4.setSize(50,50);
		
		l1.setName(tile + " 1");
		l2.setName(tile + " 2");
		l3.setName(tile + " 4");
		l4.setName(tile + " 5");
		
		l1.setLocation(x1, y1);
		l2.setLocation(x2, y2);
		l3.setLocation(x3, y3);
		l4.setLocation(x4, y4);
		
		/*ImageIcon i = new ImageIcon("res/characters/test.png");
		l1.setIcon(i);
		l2.setIcon(i);
		l3.setIcon(i);
		l4.setIcon(i);*/
		
		Map.setComponentZOrder(l1, 0);
		Map.setComponentZOrder(l2, 0);
		Map.setComponentZOrder(l3, 0);
		Map.setComponentZOrder(l4, 0);
		
		l1.addMouseListener(this);
		l2.addMouseListener(this);
		l3.addMouseListener(this);
		l4.addMouseListener(this);
		
		clearingTiles[tile-1][0] = l1;
		clearingTiles[tile-1][1] = l2;
		clearingTiles[tile-1][2] = l3;
		clearingTiles[tile-1][3] = l4;
		clearingTiles[tile-1][4] = null;
		clearingTiles[tile-1][5] = null;
	}
	
	public void genWoods(int tile,int x1, int y1, int x2, int y2, int x3, int y3){
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		
		l1.setSize(50,50);
		l2.setSize(50,50);
		l3.setSize(50,50);
		
		l1.setName(tile + " 2");
		l2.setName(tile + " 4");
		l3.setName(tile + " 5");
		
		l1.setLocation(x1, y1);
		l2.setLocation(x2, y2);
		l3.setLocation(x3, y3);
		
		/*ImageIcon i = new ImageIcon("res/characters/test.png");
		l1.setIcon(i);
		l2.setIcon(i);
		l3.setIcon(i);*/
		
		Map.setComponentZOrder(l1, 0);
		Map.setComponentZOrder(l2, 0);
		Map.setComponentZOrder(l3, 0);
		
		l1.addMouseListener(this);
		l2.addMouseListener(this);
		l3.addMouseListener(this);
		
		clearingTiles[tile-1][0] = l1;
		clearingTiles[tile-1][1] = l2;
		clearingTiles[tile-1][2] = l3;
		clearingTiles[tile-1][3] = null;
		clearingTiles[tile-1][4] = null;
		clearingTiles[tile-1][5] = null;	
	}
	
	public void genOther(int tile,int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6){
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		JLabel l5 = new JLabel();
		JLabel l6 = new JLabel();
		
		l1.setSize(50,50);
		l2.setSize(50,50);
		l3.setSize(50,50);
		l4.setSize(50,50);
		l5.setSize(50,50);
		l6.setSize(50,50);
		
		l1.setName(tile + " 1");
		l2.setName(tile + " 2");
		l3.setName(tile + " 3");
		l4.setName(tile + " 4");
		l5.setName(tile + " 5");
		l6.setName(tile + " 6");
		
		l1.setLocation(x1, y1);
		l2.setLocation(x2, y2);
		l3.setLocation(x3, y3);
		l4.setLocation(x4, y4);
		l5.setLocation(x5, y5);
		l6.setLocation(x6, y6);
		
		/*ImageIcon i = new ImageIcon("res/characters/test.png");
		l1.setIcon(i);
		l2.setIcon(i);
		l3.setIcon(i);
		l4.setIcon(i);
		l5.setIcon(i);
		l6.setIcon(i);*/
		
		Map.setComponentZOrder(l1, 0);
		Map.setComponentZOrder(l2, 0);
		Map.setComponentZOrder(l3, 0);
		Map.setComponentZOrder(l4, 0);
		Map.setComponentZOrder(l5, 0);
		Map.setComponentZOrder(l6, 0);
		
		l1.addMouseListener(this);
		l2.addMouseListener(this);
		l3.addMouseListener(this);
		l4.addMouseListener(this);
		l5.addMouseListener(this);
		l6.addMouseListener(this);
		
		clearingTiles[tile-1][0] = l1;
		clearingTiles[tile-1][1] = l2;
		clearingTiles[tile-1][2] = l3;
		clearingTiles[tile-1][3] = l4;
		clearingTiles[tile-1][4] = l5;
		clearingTiles[tile-1][5] = l6;	
	}
	
	public void buildBuildings(String s, int tile, int clearing){
		ImageIcon icon = null;
		if(s=="GuardHouse"){
			icon = new ImageIcon("res/buildings/guard.png");
		}
		else if(s=="House"){
			icon = new ImageIcon("res/buildings/house.png");
		}
		else if(s=="Chapel"){
			icon = new ImageIcon("res/buildings/chapel.png");
		}
		else if(s=="Inn"){
			icon = new ImageIcon("res/buildings/inn.png");
		}
		else
			icon = new ImageIcon("res/characters/test.png");
		clearingTiles[tile-1][clearing-1].setIcon(icon);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println(e.getSource());
		JLabel j = (JLabel)e.getSource();
		System.out.println(j.getName());
		System.out.println("x = " + e.getX());
		System.out.println("y = " + e.getY());
		
		displayClearing(j.getName());
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
		//update all pictures and related visuals to new locations
		
	}
	
	public void recordTurn(final Player player, int phasesAvailable, Model.Map gameMap) {//returns the number of phases
		
//TODO the panel is being pushed(to back) behind the window		
				/*
				all of the characters secretly and simultaneously
				record what they will do during their turns. When each character does his
				turn, he must do it exactly as he recorded it.
				He can use his turn to	move, hide, search, trade and rest.
				When each character does his turn, he must do it exactly as he recorded it.
				He can leave phases blank.
				He can record only one activity per phase, but he can record any activity in any phase, repeating or switching activities as he wishes
				 */
		
		
		JPanel Buttons = new JPanel();
		Buttons.setLayout(new FlowLayout());
		
		JButton move = new JButton("Move");
		move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the move function");
				player.setPhaseActions("Move");	//TODO need to include coordinate of location clearing
			}
		});
		
		JButton hide = new JButton("Hide");
		hide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the hide function");
				player.setPhaseActions("Hide");	
			}
		});
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the search function");
				player.setPhaseActions("Search");	
			}
		});
		
		JButton rest = new JButton("Rest");
		rest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the rest function");
				player.setPhaseActions("Rest");	
			}
		});
		
		JButton trade = new JButton("Trade");
		trade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the trade function");
				//doYouWantToTrade();
				//trading(map, player);
				player.setPhaseActions("Trade");
			}
		});
		
		JButton quit = new JButton("Quit");
		trade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("You have chosen to close the program");
				System.exit(0);
			}
		});
		
		Buttons.add(move);
		Buttons.add(hide);
		Buttons.add(search);
		Buttons.add(rest);
		Buttons.add(trade);
		Buttons.add(quit);
		JDialog frame = new JDialog();
		
		Buttons.setBackground(Color.gray);
		frame.add(Buttons);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(Buttons);
		frame.pack();
		frame.setVisible(true);
		frame.setModal(true);
		
		/*
		String[] options = new String[] {"Move", "Hide", "Search", "Rest","Trade"};
		int response = JOptionPane.showOptionDialog(null, "Message", "Title",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
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

	public String whichSearchTable() {
		//ask user if they want to "Locate" or "Looting" 
		Object[] choices = {"Locate", "Loot"};
		
		int n = (int) JOptionPane.showInputDialog(Players,
				"Which Search Table Would You Like TO Use?" + "\n" + "/n" + "Can only loot after you have located a treasure",
				"Search",
				JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
				//JOptionPane.YES_NO_OPTION);
		//their answer is..
System.out.println("!!TEST!!Should be 0 if locate, 1 if loot: " + n);
		if(n == 0){//clicked locate
			System.out.println("Should return true for trade");
			return "Locate";
		}
		else{//clicked loot
			System.out.println("Should return false for trade");
			return "Looting";
		}
	}

	public void displayTreasure(int tile) {
		// TODO Auto-generated method stub
		//display the treasure chit in this tile, technically only the player can see it but who cares
	}
	
	public void displayScore(Player[] gamers) {
		// TODO Auto-generated method stub
		//display the final scores of all users
	}
	
	public void displayClearing(String s){
		String q[] = s.split(" ");
		//map.
	}
}
