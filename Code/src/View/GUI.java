package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Control.Game;
import Control.Player;
import Model.ArrayUtils;
import Model.Denizen.*;
import Model.Map;
import Model.MapChits.RedChit;
import Model.MapChits.YellowChit;
import Model.MapTiles;

public class GUI implements MouseListener{
	
	Game game;
	Map map;
	
	Boolean move = false;
	int clickedLocation;
	
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public JLabel[] mapTiles;
	public JLabel[][] clearingTiles;
	int tileCount;
	
	public static JPanel Players = new JPanel();
	public static JPanel Date = new JPanel();
	JLabel dLabel = new JLabel("Adjust dLabel for date");
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
		
		Date.add(dLabel);
		Date.setBackground(Color.white);
		MainWindow.getContentPane().add(Date);
		Date.setLocation((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3);
		
		/*ImageIcon p1 = new ImageIcon("res/characters/amazon.png");
		JLabel qwe = new JLabel();
		qwe.setIcon(p1);
		qwe.setVisible(true);
		qwe.setLocation(560,1175);
		qwe.setSize(50,50);
		Map.add(qwe);
		Map.setComponentZOrder(qwe, 0);*/
													//These were replaced in recordTurn
														//TO DO can these functions be erased? Since they are handled at the bottom	
															//TO DO add in function calls
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
				possibilities[0]);
		System.out.println("You have chosen to be a " + s);
		//game.gotCharacter = true;
		return (String)s;
		
		//TODO, only suppose to be 1 of each type max, but that isnt important, work on other stuff
	}
	
	public int chooseStart(Player currPlayer){
		String[] choices = currPlayer.getProfile().getStartLocations();
		
		//used to choose from start positions available
		Object s = JOptionPane.showInputDialog(
				Players,
				"Choose Where You Would Like To Start?\n",
				"Choose your StartSpot",
				JOptionPane.PLAIN_MESSAGE,
				null,
				choices,
				choices[0]);
		
		//System.out.println("You have chosen to start at " + s);
		
		int startHere = Game.determineStart((String) s);
		return startHere;
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
		if(s == null){
			System.out.println("No characters have been selected");
			return 0;
		}
		else
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
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//TODO adjust the mouse pressed to do more than just output co-ordinates
		//System.out.println(e.getSource());
		JLabel j = (JLabel)e.getSource();
		System.out.println(j.getName());
		System.out.println("x = " + e.getX());
		System.out.println("y = " + e.getY());
		
		displayClearing(j.getName());
		
		move = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures and related visuals to new locations
		
	}
	
	public void recordTurn(final Player player, int phasesAvailable, Model.Map gameMap) {//returns the number of phases
			
		/*
		all of the characters secretly and simultaneously
		record what they will do during their turns. When each character does his
		turn, he must do it exactly as he recorded it.
		He can use his turn to	move, hide, search, trade and rest.
		When each character does his turn, he must do it exactly as he recorded it.
		He can leave phases blank.
		He can record only one activity per phase, but he can record any activity in any phase, repeating or switching activities as he wishes
		 */
									
														/*//commented this out to be replaced with other stuff
														JPanel Buttons = new JPanel();
														Buttons.setLayout(new FlowLayout());
														
														JButton move = new JButton("Move");
														move.addActionListener(new ActionListener(){
															public void actionPerformed(ActionEvent e){
																System.out.println("Call the move function");
																player.setPhaseActions("Move23");	//TO DO need to include coordinate of location clearing, here is temp value
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
														*/

		for(int a=0; a<phasesAvailable; a++){//repeat for every phase possible
			String[] options = new String[] {"Move", "Hide", "Search", "Rest","Trade", "Quit"};
			
			int response = JOptionPane.showOptionDialog(null, "Build Your Turn, Here are your options: ", "Record Turn",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			
			//System.out.println("Response "+response);
			//based on answer
			switch(response){
			case 0:			//Move
				System.out.println("Call the move function");
				player.setPhaseActions("Move");	//choose location during your turn
				break;
			case 1:			//Hide
				System.out.println("Call the hide function");
				player.setPhaseActions("Hide");	
				break;
			case 2:			//Search
				System.out.println("Call the search function");
				player.setPhaseActions("Search");	
				break;
			case 3:			//Rest
				System.out.println("Call the rest function");
				player.setPhaseActions("Rest");	
				break;
			case 4:			//Trade
				System.out.println("Call the trade function");
				player.setPhaseActions("Trade");
				break;
			case 5:			//Quit
				System.out.println("You have chosen to close the program");
				System.exit(0);
				break;
			}
		}	
		
		System.out.println("User has now built his turn");	
		 
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
		
		int n = JOptionPane.showOptionDialog(Players,
				"Which Search Table Would You Like TO Use?" + "\n" + "Can only loot after you have located a treasure",
				"Search",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
				//JOptionPane.YES_NO_OPTION);old version
		
		//their answer is..
		if(n == 0){//clicked locate
			System.out.println("Should return Locate");
			return "Locate";
		}
		else{//clicked loot
			System.out.println("Should return Looting");
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
		System.out.print("Display the name ");
		for(int i=0; i<q.length;i++){
			System.out.print(q[i]+" ");
		}
		System.out.println("");
		clickedLocation = Integer.parseInt(s.replaceAll("\\s+",""));
		System.out.println("Location number = "+clickedLocation);
		
		//TODO somehow get clickedLocation to getNewLocation, possibly store the value in global?
	}

	public int getNewLocation() {
		// TODO Auto-generated method stub
		//get the location of the clearing the user wants to move to based on hsi click
		while(move == true){
			//System.out.println("Move is true");
		}
		move = true;
		//System.out.println("Move is false");
		return clickedLocation;
	}

	
	
	//CHEAT MODE----------------------------------------------------------------------------------
	public static boolean cheatMode() {
		//return true if user wants cheat mode
		int n = JOptionPane.showConfirmDialog(null,  "Engage Cheat Mode?", "CheatMode", 
				JOptionPane.YES_NO_OPTION);
		
		//System.out.println(n);
		if(n == 0){//yes
			return true;
		}else{//no
			return false;
		}
	}

	public RedChit getSoundTreasureCheat() {
		// TODO cheat mode, need to pick from available sounds or treasure or lostcastlecity if in right type of tile, remove the picked one from the future choices and return the picked choice
		//if putting lost castlecity then return null
		return null;
	}

	public YellowChit getWarningCheat() {
		// TODO cheat mode, need to pick from available warnings, remove the picked one from the future choices and return the picked choice
		return null;
	}

	public static int diceAnswer() {
		//return value of dice
				
		String[] options = new String[] {"1", "2", "3", "4","5", "6"};
		
		int response = JOptionPane.showOptionDialog(null, "What Does The Die Roll?", "Dice",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);

		//based on answer
		switch(response){
		case 0:			
			return 1;
		case 1:			
			return 2;
		case 2:			
			return 3;
		case 3:			
			return 4;
		case 4:			
			return 5;
		case 5:			
			return 6;
		}
		
		System.out.println("ERROR IN DICE SELECTION");
		return -1;//nonsense value
	}

	public void pickLocationsDwellingsCheat(MapTiles awfulValley, MapTiles badValley, 
											MapTiles curstValley, MapTiles darkValley,
											MapTiles evilValley) {
		//ask user to choose where to add dwellings and ghosts, then add dwelling pictures
		
		String[] options = new String[] {"Chapel", "House", "Inn", "GuardHouse", "Ghosts"};
		
		//First ask what to put in the awfulValley
		Object response = JOptionPane.showOptionDialog(null, "What Dwelling to You Want in the Awful Valley", "Dwellings",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);

		switch((int)response){
		case 0:			awfulValley.order = new GreatSwordsman[2];			//create the array of natives
						awfulValley.setChapel(4, awfulValley);				//put the dwelling and then the natives
						buildBuildings("Chapel", 1, 4);						//put picture
						options = (String[]) ArrayUtils.remove(options, 0);	//remove dwelling from options list
			break;
		case 1:			awfulValley.soldiers = new GreatSwordsman[2];
						awfulValley.setHouse(4, awfulValley);
						buildBuildings("House", 1, 4);	
						options = (String[]) ArrayUtils.remove(options, 1);
			break;
		case 2:			awfulValley.rogues = new GreatSwordsman[2];
						awfulValley.setInn(4, awfulValley);
						buildBuildings("Inn", 1, 4);
						options = (String[]) ArrayUtils.remove(options, 2);
			break;
		case 3:			awfulValley.guard = new GreatSwordsman[3];
						awfulValley.setGuardHouse(4, awfulValley);
						buildBuildings("GuardHouse", 1, 4);
						options = (String[]) ArrayUtils.remove(options, 3);
			break;
		case 4:			awfulValley.ghosts = new Ghost[2];
						awfulValley.setGhosts(4, awfulValley);
						options = (String[]) ArrayUtils.remove(options, 4);
						awfulValley.setGhostTile(true);
			break;
		}
				
	//Second ask what to put in badValley
		response = JOptionPane.showInputDialog(null, "What Dwelling to You Want in the Bad Valley",	"Dwellings2",
				JOptionPane.PLAIN_MESSAGE,
				null,	options, options[0]);
		
		while(response == null){//handle the wise ass who choses null
			response = JOptionPane.showInputDialog(null, "Can't Be Null",	"Dwellings2",
					JOptionPane.PLAIN_MESSAGE,
					null,	options, options[0]);
		}
			
		switch((String)response){
		case "Chapel":			badValley.order = new GreatSwordsman[2];			//create the array of natives
						badValley.setChapel(4, badValley);				//put the dwelling and then the natives
						buildBuildings("Chapel", 2, 4);						//put picture
						options = (String[]) ArrayUtils.remove(options, 0);	//remove dwelling from options list
			break;
		case "House":			badValley.soldiers = new GreatSwordsman[2];
						badValley.setHouse(4, badValley);
						buildBuildings("House", 2, 4);	
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("House", options));
			break;
		case "Inn":			badValley.rogues = new GreatSwordsman[2];
						badValley.setInn(4, badValley);
						buildBuildings("Inn", 2, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Inn", options));
			break;
		case "GuardHouse":		badValley.guard = new GreatSwordsman[3];
						badValley.setGuardHouse(4, badValley);
						buildBuildings("GuardHouse", 2, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("GuardHouse", options));
			break;
		case "Ghosts":			badValley.ghosts = new Ghost[2];
						badValley.setGhosts(4, badValley);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Ghosts", options));
						badValley.setGhostTile(true);
			break;
		}
		
	//ask what to put in curst vallye
		response = JOptionPane.showInputDialog(null, "What Dwelling to You Want in the Curst Valley",	"Dwellings3",
				JOptionPane.PLAIN_MESSAGE,
				null,	options, options[0]);
		
		while(response == null){//handle the wiseass who choses null
			response = JOptionPane.showInputDialog(null, "Can't Be Null",	"Dwellings3",
					JOptionPane.PLAIN_MESSAGE,
					null,	options, options[0]);
		}
			
		switch((String)response){
		case "Chapel":			curstValley.order = new GreatSwordsman[2];			//create the array of natives
						curstValley.setChapel(4, curstValley);				//put the dwelling and then the natives
						buildBuildings("Chapel", 3, 4);						//put picture
						options = (String[]) ArrayUtils.remove(options, 0);	//remove dwelling from options list
			break;
		case "House":			curstValley.soldiers = new GreatSwordsman[2];
						curstValley.setHouse(4, curstValley);
						buildBuildings("House", 3, 4);	
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("House", options));
			break;
		case "Inn":			curstValley.rogues = new GreatSwordsman[2];
						curstValley.setInn(4, curstValley);
						buildBuildings("Inn", 3, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Inn", options));
			break;
		case "GuardHouse":		curstValley.guard = new GreatSwordsman[3];
						curstValley.setGuardHouse(4, curstValley);
						buildBuildings("GuardHouse", 3, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("GuardHouse", options));
			break;
		case "Ghosts":			curstValley.ghosts = new Ghost[2];
						curstValley.setGhosts(4, curstValley);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Ghosts", options));
						curstValley.setGhostTile(true);
			break;
		}
	
		//ask what to put in darkvalley
		response = JOptionPane.showInputDialog(null, "What Dwelling to You Want in the Dark Valley",	"Dwellings4",
				JOptionPane.PLAIN_MESSAGE,
				null,	options, options[0]);
		
		while(response == null){//handle the wiseass who choses null
			response = JOptionPane.showInputDialog(null, "Can't Be Null",	"Dwellings4",
					JOptionPane.PLAIN_MESSAGE,
					null,	options, options[0]);
		}
			
		switch((String)response){
		case "Chapel":			darkValley.order = new GreatSwordsman[2];			//create the array of natives
						darkValley.setChapel(4, darkValley);				//put the dwelling and then the natives
						buildBuildings("Chapel", 4, 4);						//put picture
						options = (String[]) ArrayUtils.remove(options, 0);	//remove dwelling from options list
			break;
		case "House":			darkValley.soldiers = new GreatSwordsman[2];
						darkValley.setHouse(4, darkValley);
						buildBuildings("House", 4, 4);	
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("House", options));
			break;
		case "Inn":			darkValley.rogues = new GreatSwordsman[2];
						darkValley.setInn(4, darkValley);
						buildBuildings("Inn", 4, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Inn", options));
			break;
		case "GuardHouse":		darkValley.guard = new GreatSwordsman[3];
						darkValley.setGuardHouse(4, darkValley);
						buildBuildings("GuardHouse", 4, 4);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("GuardHouse", options));
			break;
		case "Ghosts":			darkValley.ghosts = new Ghost[2];
						darkValley.setGhosts(4, darkValley);
						options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Ghosts", options));
						darkValley.setGhostTile(true);
			break;
		}
		
		//put what it is left in evilvalley
		System.out.println("Putting "+ options[0] + " in EvilValley");
		switch(options[0]){
			case "Chapel":			evilValley.order = new GreatSwordsman[2];			//create the array of natives
							evilValley.setChapel(4, evilValley);				//put the dwelling and then the natives
							buildBuildings("Chapel", 5, 4);						//put picture
							options = (String[]) ArrayUtils.remove(options, 0);	//remove dwelling from options list
				break;
			case "House":			evilValley.soldiers = new GreatSwordsman[2];
							evilValley.setHouse(4, evilValley);
							buildBuildings("House", 5, 4);	
							options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("House", options));
				break;
			case "Inn":			evilValley.rogues = new GreatSwordsman[2];
							evilValley.setInn(4, evilValley);
							buildBuildings("Inn", 5, 4);
							options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Inn", options));
				break;
			case "GuardHouse":		evilValley.guard = new GreatSwordsman[2];
							evilValley.setGuardHouse(4, evilValley);
							buildBuildings("GuardHouse", 5, 4);
							options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("GuardHouse", options));
				break;
			case "Ghosts":			evilValley.ghosts = new Ghost[2];
							evilValley.setGhosts(4, evilValley);
							options = (String[]) ArrayUtils.remove(options, ArrayUtils.indexOf("Ghosts", options));
							evilValley.setGhostTile(true);
				break;
		}
		
	}
}
