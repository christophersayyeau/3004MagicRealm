package View;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

//import com.sun.xml.internal.ws.util.StringUtils;
import Control.Client;
import Control.Game;
import Control.Player;
import Model.ArrayUtils;
import Model.Clearing;
import Model.CombatChit;
import Model.MapChits;
import Model.Denizen.*;
import Model.Map;
import Model.MapChits.*;
import Model.MapTiles;

public class GUI implements MouseListener{
	
	Game game;
	private Map map;
	public Map getMap(){
		return map;
	}
	
	static String serverIP = "172.17.129.239";
	static Client client;
	
	public static String[] possibilities = new String[6];
	
	Boolean move = false;
	boolean pause = false;
	int clickedLocation;
	
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public JLabel[] mapTiles;
	public JLabel[][] clearingTiles;
	int tileCount;
	
	public static JPanel Players = new JPanel();
	public static JPanel Date = new JPanel();
	public static JPanel Instruction = new JPanel();
	
	@SuppressWarnings("rawtypes")
	public static JList jlPlayers = new JList();
	private static JScrollPane spPlayers = new JScrollPane();
	private static JButton startButton = new JButton();
	
	JLabel dLabel = new JLabel("Label for date");
	public static JLabel moveLabel = new JLabel("Click on a clearing to move the character");
	//set tile values
	final int x = 125;
	final int y = 215;
	final int tileX = 300;
	final int tileY = 305;
	
	public JLabel amazon = new JLabel();
	public static JLabel[] player = new JLabel[6];
	
	public void initLabels(){
		for(int i = 0; i<6; i++){
			player[i] = new JLabel();
		}
	}
	int playerX;
	int playerY;
	
	public void setMap(Map m){
		map = m;
	}
	
	public static void Connect()
	{
		try
		{
			final int PORT = 65001;
			String HOST = serverIP;
			Socket SOCK = new Socket(HOST, PORT);
			System.out.println("You connected to: " + HOST);
			avaiableChars(SOCK);
			String s = createPlayer();
			Player player = new Player(s);
			
			PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
			OUT.println("ADDPLAYER:" + player.getProfile().getType());
			OUT.flush();
			
			client = new Client(SOCK, player);
			
			Thread X = new Thread(client);
			X.start();			
		}
		catch(Exception X)
		{
			System.out.print(X);
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	public static void main(String args[])
	{
		@SuppressWarnings("unused")
		String ip = JOptionPane.showInputDialog(null, "What is the server's IP? ");
		//serverIP = JOptionPane.showInputDialog(null, "What is the server's IP? ");
		//System.out.print(serverIP);
		Connect();
	}
	
	//constructor, called in player.java
	public GUI(Game g)
	{
		initLabels();
		buildMap();
		initilizeWindow();		
		initPlayers();
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
	public static String createPlayer(){
		
		//TODO STEFAN, possibilities is empty, find the other variable to use
		//Use possibilities to test the game (for now)
		String[] possibilities = {"Amazon","Black Knight", "Captain", "Dwarf", "Elf", "Swordsman"};
		
		Object s = JOptionPane.showInputDialog(
				Players,
				"Which character would you like to be?\n",
				"Choose your character",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				possibilities[0]);
		
		//TODO STEFAN, erase if fixed in Server, only suppose to be 1 of each type max, but that isnt important, work on other stuff
		
		
		System.out.println("You have chosen to be a " + s);
		//game.gotCharacter = true;
		return (String)s;
		
		
	}
	
	public static int chooseStart(Player currPlayer){
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
		
		int startHere = Game.determineStart((String) s, currPlayer);
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
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		JLabel j = (JLabel)e.getSource();
		/*
		System.out.println(j.getName());
		System.out.println("x = " + j.getX());
		System.out.println("y = " + j.getY());
		*/
		playerX = j.getX();
		playerY = j.getY();
		displayClearing(j.getName());
		
		move = false;
		pause = false;
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	
	public void Refresh(String labelText) {
		//Called thourhgout the day to refresh the screen
		// TODO update all pictures and related visuals to new locations
		//not sure if already taken care of elsewhere in code
		//maybee just link to those
		changeDate(labelText);
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
		Instruction.setVisible(true);
		moveLabel.setText(player.getProfile().getType() + "'s turn, choose your " + phasesAvailable + " actions");
		for(int a=0; a<phasesAvailable; a++){//repeat for every phase possible
			String[] options = new String[] {"Move", "Hide", "Search", "Rest","Trade", "Quit", "View"};
			
			int response = JOptionPane.showOptionDialog(null, "Build Your Turn, Here are your options: ", "Record Turn",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, options[0]);
			
			//based on answer
			switch(response){
			case 0:			//Move
				System.out.println("Call the move function");
				player.setPhaseActions("Move");	//choose location during your turn
				
				if(player.getProfile().getType().compareTo("Amazon") == 0 && !player.getProfile().amazonUsed){
					a--;
					phasesAvailable++;
					player.getProfile().amazonUsed = true;	//so we can't use them again this time
					System.out.println("Amazon gets extra move phase");
				}
				break;
			case 1:			//Hide
				System.out.println("Call the hide function");
				player.setPhaseActions("Hide");	
				
				if(player.getProfile().haveCloak() && !player.getProfile().cloakUsed){
					a--;				//make loop again
					phasesAvailable++;	//increase the value
					player.getProfile().cloakUsed = true;	//so we can't use them again this time
					System.out.println("Cloak gets extra hide phase");
				}
				if(player.getProfile().getType().compareTo("Elf") == 0 && !player.getProfile().elfUsed){
					a--;
					phasesAvailable++;
					player.getProfile().elfUsed = true;	//so we can't use them again this time
					System.out.println("Elf gets extra hide phase");
				}
				break;
			case 2:			//Search
				System.out.println("Call the search function");
				player.setPhaseActions("Search");	
				
				if(player.getProfile().haveGlasses() && !player.getProfile().glassesUsed){
					a--;
					phasesAvailable++;
					player.getProfile().glassesUsed = true;	//so we can't use them again this time
					System.out.println("Glasses gets extra search phase");
				}
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
			
			case 6:			//View clearing/map
				//System.out.println("Click on a clearing to continue game");
				moveLabel.setText("Click on a clearing to continue game");
				//Instruction.setVisible(true);
				pause = true;
				
				//Pause the game until mouse is clicked
				while(pause == true){
					try {
					       Thread.sleep(200);
					    } catch(InterruptedException e) {
					    }
				}
				//Instruction.setVisible(false);
				moveLabel.setText(player.getProfile().getType() + "'s turn, choose your " + phasesAvailable + " actions");
				a--;
				break;
			}
		}	
		
		System.out.println("User has now built his turn");	
		 
	}

	public void hideMapChits() {
		
		// TODO Ignore, third step, end of day, all map chits that can be seen are unseen
		
	}

	public void revealMapChits(int i) {
		//caled at end of player turn
		// TODO Ignore, third step, end of player turn, reveal monster, warning chits
		//System.out.println("mapchits in tile ->face up, substitue chits exchanged, other map chits summon new monsters from apperance chart");
	}

	public void trading(Map gameMap, Player player1) {
		// For now we assume he trades meaningless baubles and get some gold
		
		String[] pos = new String[2];
		pos[0] = Integer.toString(player1.getProfile().getCurrentLocation()/10);
		pos[1] = Integer.toString(player1.getProfile().getCurrentLocation()%10);
		int[] temp = new int[2];
		temp = convertNameToPosition(pos);
		
		int currentTile = temp[0];
		int currentClearing = temp[1];
		
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

	public static String whichSearchTable() {
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

	public static void revealTreasure(int tile) {
		// TODO RYAN HAS THIS BEEN HANDLED BELOW?second step, reveal that the treasure in this tile has been found, change the descriptive word in clearing view
		//Dont need to display contents, just show that it is considered found and can now be looted
		
	}
	
	public void displayScore(Player[] gamers) {		
		//display the final scores of all users
		String[] scores = new String[gamers.length];
		for(int a =0; a< gamers.length; a++){	//go to all users		
			scores[a] = "Player " + a + " Total Score: " + gamers[a].calculateScore();
			combatMessage(scores[a]);//send message to user
		}
		//TODO change label to display array of everybodys scores
	}
	

	/* Function to output what is in a specific clearing in
	 * 
	 */
	public void displayClearing(String s){
		String q[] = s.split(" ");
		System.out.print("Display the name ");
		for(int i=0; i<q.length;i++){
			System.out.print(q[i]+" ");
		}
		System.out.println("");
		clickedLocation = Integer.parseInt(s.replaceAll("\\s+",""));
		System.out.println("Location number = "+clickedLocation);
		
		int pos[] = new int[2];
		pos = convertNameToPosition(q);
		int x=0, y=0;
		x = pos[0];
		y= pos[1];
		System.out.println("Array Location = " + String.valueOf(x) + String.valueOf(y));
		
		Clearing c = map.getMapTile(x).clearing[y];
		ArrayList<String> list = new ArrayList<String>();
		if(c.chapel)
			list.add("Chapel");
		else if(c.guardHouse)
			list.add("GuardHouse");
		else if(c.house)
			list.add("House");
		else if(c.inn)
			list.add("Inn");
		else if(c.playersInClearing != null){//check for players in clearing
			System.out.println("Display num players in clearing " + c.numPLayersInClearing);
			for(int n=0; n<c.numPLayersInClearing; n++){
				//TODO does not start the list at location [0], will continue to grow, but no duplication anymore
				//if(c.playersInClearing[n]!=null)
				list.add(c.playersInClearing[n].getProfile().getType());	//add all of the ones in the clearing
			}
		}
		for(int i=0; i < c.monstersInClearing.length; ++i){
			if(c.monstersInClearing[i] != null)
				list.add(c.monstersInClearing[i].getName());
		}
		
		if(map.getMapTile(x).getWarning().found)
			list.add(map.getMapTile(x).getWarning().type);
		else
			list.add("Unkown Warning");
		
		if(list.size() == 0)
			list.add("Empty");

		if(map.getMapTile(x).getTreasure() != null){
			if(map.getMapTile(x).getTreasure().found == true)
				list.add("Treasure has been found already");
			//TODO RYAN HAS THIS BEEN HANDLED HERE?remove 'else' when done testing, used to check if treasure exists
			else
				list.add("Unknown Treasure");
		}
		
		String [] list2 = new String[list.size()];
		list2 = list.toArray(list2);
		
		@SuppressWarnings("unused")
		Object z = JOptionPane.showInputDialog(
				Players,
				"Inside of clearing:\n",
				"Tile " + q[0] + " Clearing " + q[1],
				JOptionPane.INFORMATION_MESSAGE,
				null,
				list2,
				list2[0]);
				
	}

	public int getNewLocation() {
		while(move == true){
			try {
			       Thread.sleep(200);
			    } catch(InterruptedException e) {
			    }
		}
		//System.out.println("Move is false");
		return clickedLocation;
		
		
	}

	public void setMove(boolean moving){
		move = moving;
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

	public MapChits getSoundTreasureCheat(String tileType, int tileNum) {
		//need to pick from available sounds or treasure or lostcastlecity if in right type of tile, remove the picked one from the future choices 

		String[] choices = null;
		MapChits temp = new MapChits();
		
		if(tileType.compareTo("V")==0){//if a valley
			System.out.println("Valley don't have sounds or treasures");
			return null;
			
		}else if(tileType.compareTo("C")==0){//cave		
			//create the array to ask
			
			//first add the lost
			if(!map.getLostCity().found)
				choices = ArrayUtils.add(choices, "Lost City");
	
		}else if(tileType.compareTo("W")==0){//woods
			System.out.println("Woods have nothing in them");
			return null;
			
		}else if(tileType.compareTo("M")==0){//mountain
			//create the array to ask
			
			//first add the lost
			if(!map.getLostCastle().found)
				choices = ArrayUtils.add(choices, "Lost Castle");
		}else{
			System.out.println("ERROR IN TYPE IDENTIFICATION FOR SOUNDS AND TREASURES");
			choices = ArrayUtils.add(choices, "ERROR HAS OCCURED");
		}
		
		//now create the rest of the array
		for(int a=0; a<10; a++){		//go through all sounds
					if(!map.getSound(a).found)	//has it already been claimed
						choices = ArrayUtils.add(choices, map.getSound(a).type + map.getSound(a).clearing);	//add the title to the array
		}
		for(int a=0; a<8; a++){		//go through all treasures
				if(!map.getTreasure(a).found)	//has it already been claimed
					choices = ArrayUtils.add(choices, map.getTreasure(a).type);	//add the title to the array
		}
		
		//now ask user to pick
		Object response = JOptionPane.showInputDialog(null, "What Sound, Treasure of Other would you like in tile "+ tileNum + "?",	"Map Pieces",
				JOptionPane.PLAIN_MESSAGE,
				null,	choices, choices[0]);
		//interpret response
		switch((String)response){
			//first handle the sounds
			case "HOWL4":		map.getSound(0).found = true;	//remove from future choices
				return map.getSound(0);
			case "FLUTTER1":	map.getSound(1).found = true;
				return map.getSound(1);	
			case "ROAR6":		map.getSound(2).found = true;
				return map.getSound(2);
			case "PATTER2":		map.getSound(3).found = true;
				return map.getSound(3);
			case "SLITHER3":	map.getSound(4).found = true;
				return map.getSound(4);
			case "HOWL5":		map.getSound(5).found = true;
				return map.getSound(5);
			case "FLUTTER2":	map.getSound(6).found = true;
				return map.getSound(6);
			case "PATTER5":		map.getSound(7).found = true;
				return map.getSound(7);
			case "ROAR4":		map.getSound(8).found = true;
				return map.getSound(8);
			case "SLITHER6":	map.getSound(9).found = true;
				return map.getSound(9);
			
			//next handle the treasure
			case "STATUE":		map.getTreasure(0).found = true;
				return map.getTreasure(0);
			case "HOARD":		map.getTreasure(1).found = true;
				return map.getTreasure(1);
			case "ALTAR":		map.getTreasure(2).found = true;
				return map.getTreasure(2);
			case "LAIR":		map.getTreasure(3).found = true;
				return map.getTreasure(3);
			case "VAULT":		map.getTreasure(4).found = true;
				return map.getTreasure(4);
			case "CAIRNS":		map.getTreasure(5).found = true;
				return map.getTreasure(5);
			case "POOL":		map.getTreasure(6).found = true;
				return map.getTreasure(6);
			case "SHRINE":		map.getTreasure(7).found = true;
				return map.getTreasure(7);
			
			//finally handle losts	
			case "Lost Castle": map.getLostCastle().found = true;
				//build it here
				for(int c=0; c<5; c++) buildCastleCheat();//handle it 5 times
				return temp.new YellowChit("LOSTCASTLE");//create a temporary warning value since no other warning uses this function
			case "Lost City":	map.getLostCity().found = true;
				//build it here
				for(int c=0; c<5; c++) buildCityCheat();//handle it 5 times
				return temp.new YellowChit("LOSTCITY");	
		}
		//return the picked choice, if it was woods or valley null was returned
		return temp.new YellowChit("Major Error");
		
	}

	private void buildCityCheat() {
		//add sounds and treasures just like Castle
		String[] choices = null;
		//now create the array
		for(int a=0; a<10; a++){		//go through all sounds
					if(!map.getSound(a).found)	//has it already been claimed
						choices = ArrayUtils.add(choices, map.getSound(a).type + map.getSound(a).clearing);	//add the title to the array
		}
		for(int a=0; a<8; a++){		//go through all treasures
				if(!map.getTreasure(a).found)	//has it already been claimed
					choices = ArrayUtils.add(choices, map.getTreasure(a).type);	//add the title to the array
		}
		
		//now ask user to pick
		Object response = JOptionPane.showInputDialog(null, "What Sound, Treasure would you like in the Lost City?",	"Lost city",
				JOptionPane.PLAIN_MESSAGE,
				null,	choices, choices[0]);
		
		//interpret response		
	switch((String)response){
			//first handle the sounds
			case "HOWL4":		map.getSound(0).found = true;	//remove from future choices
				map.getLostCity().setSound(map.getSound(0));
				break;
			case "FLUTTER1":	map.getSound(1).found = true;
				map.getLostCity().setSound(map.getSound(1));
				break;
			case "ROAR6":		map.getSound(2).found = true;
				map.getLostCity().setSound(map.getSound(2));
				break;
			case "PATTER2":		map.getSound(3).found = true;
				map.getLostCity().setSound(map.getSound(3));
				break;
			case "SLITHER3":	map.getSound(4).found = true;
				map.getLostCity().setSound(map.getSound(4));
				break;
			case "HOWL5":		map.getSound(5).found = true;
				map.getLostCity().setSound(map.getSound(5));
				break;
			case "FLUTTER2":	map.getSound(6).found = true;
				map.getLostCity().setSound(map.getSound(6));
				break;
			case "PATTER5":		map.getSound(7).found = true;
				map.getLostCity().setSound(map.getSound(7));
				break;
			case "ROAR4":		map.getSound(8).found = true;
				map.getLostCity().setSound(map.getSound(8));
				break;
			case "SLITHER6":	map.getSound(9).found = true;
				map.getLostCity().setSound(map.getSound(9));
				break;
			
			//next handle the treasure
			case "STATUE":		map.getTreasure(0).found = true;
				map.getLostCity().setTreasure(map.getTreasure(0));
				break;
			case "HOARD":		map.getTreasure(1).found = true;
				map.getLostCity().setTreasure(map.getTreasure(1));
				break;
			case "ALTAR":		map.getTreasure(2).found = true;
				map.getLostCity().setTreasure(map.getTreasure(2));
				break;
			case "LAIR":		map.getTreasure(3).found = true;
				map.getLostCity().setTreasure(map.getTreasure(3));
				break;
			case "VAULT":		map.getTreasure(4).found = true;
				map.getLostCity().setTreasure(map.getTreasure(4));
				break;
			case "CAIRNS":		map.getTreasure(5).found = true;
				map.getLostCity().setTreasure(map.getTreasure(5));
				break;
			case "POOL":		map.getTreasure(6).found = true;
				map.getLostCity().setTreasure(map.getTreasure(6));
				break;
			case "SHRINE":		map.getTreasure(7).found = true;
				map.getLostCity().setTreasure(map.getTreasure(7));
				break;
		}
	}
	private void buildCastleCheat() {
		// add sounds and treasures
		String[] choices = null;
		//now create the array
				for(int a=0; a<10; a++){		//go through all sounds
							if(!map.getSound(a).found)	//has it already been claimed
								choices = ArrayUtils.add(choices, map.getSound(a).type + map.getSound(a).clearing);	//add the title to the array
				}
				for(int a=0; a<8; a++){		//go through all treasures
						if(!map.getTreasure(a).found)	//has it already been claimed
							choices = ArrayUtils.add(choices, map.getTreasure(a).type);	//add the title to the array
				}
				
				//now ask user to pick
				Object response = JOptionPane.showInputDialog(null, "What Sound, Treasure would you like in the Lost Castle?",	"Lost Castle",
						JOptionPane.PLAIN_MESSAGE,
						null,	choices, choices[0]);
				
				//interpret response		
			switch((String)response){
					//first handle the sounds
					case "HOWL4":		map.getSound(0).found = true;	//remove from future choices
						map.getLostCastle().setSound(map.getSound(0));
						break;
					case "FLUTTER1":	map.getSound(1).found = true;
						map.getLostCastle().setSound(map.getSound(1));
						break;
					case "ROAR6":		map.getSound(2).found = true;
						map.getLostCastle().setSound(map.getSound(2));
						break;
					case "PATTER2":		map.getSound(3).found = true;
						map.getLostCastle().setSound(map.getSound(3));
						break;
					case "SLITHER3":	map.getSound(4).found = true;
						map.getLostCastle().setSound(map.getSound(4));
						break;
					case "HOWL5":		map.getSound(5).found = true;
						map.getLostCastle().setSound(map.getSound(5));
						break;
					case "FLUTTER2":	map.getSound(6).found = true;
						map.getLostCastle().setSound(map.getSound(6));
						break;
					case "PATTER5":		map.getSound(7).found = true;
						map.getLostCastle().setSound(map.getSound(7));
						break;
					case "ROAR4":		map.getSound(8).found = true;
						map.getLostCastle().setSound(map.getSound(8));
						break;
					case "SLITHER6":	map.getSound(9).found = true;
						map.getLostCastle().setSound(map.getSound(9));
						break;
					
					//next handle the treasure
					case "STATUE":		map.getTreasure(0).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(0));
						break;
					case "HOARD":		map.getTreasure(1).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(1));
						break;
					case "ALTAR":		map.getTreasure(2).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(2));
						break;
					case "LAIR":		map.getTreasure(3).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(3));
						break;
					case "VAULT":		map.getTreasure(4).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(4));
						break;
					case "CAIRNS":		map.getTreasure(5).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(5));
						break;
					case "POOL":		map.getTreasure(6).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(6));
						break;
					case "SHRINE":		map.getTreasure(7).found = true;
						map.getLostCastle().setTreasure(map.getTreasure(7));
						break;
				}
	}

	public YellowChit getWarningCheat(String tileType, int tileNum) {
		//cheat mode, need to pick from available warnings, remove the picked one from the future choices and return the picked choice
		String[] choices = null;
		YellowChit[] temp = null;
		
		//determine what type of tile based on string
		if(tileType.compareTo("V")==0){//if a valley			
			temp = map.warningsV;
		}else if(tileType.compareTo("C")==0){//cave
			temp = map.warningsC;
		}else if(tileType.compareTo("W")==0){//woods
			temp = map.warningsW;
		}else if(tileType.compareTo("M")==0){//mountain
			temp = map.warningsM;
		}else{
			System.out.println("ERORO CANT IDENTIFY TYPE IN getWarningCheat");
		}
		
		//create the array to ask
		for(int a=0; a<5; a++){		//go through all warning values
				if(!temp[a].found)	//if not already used
					choices = ArrayUtils.add(choices, temp[a].type);	//add the title to the array
		}
		
		//now ask which one to send
		Object response = JOptionPane.showInputDialog(null, "What Warning Would you like in Tile "+ tileNum + "?",	"Warnings",
				JOptionPane.PLAIN_MESSAGE,
				null,	choices, choices[0]);
		//interpret response
		switch((String)response){
			case "BONES":	temp[0].found = true;		
				return temp[0];
			case "DANK":	temp[1].found = true;	
				return temp[1];
			case "RUINS":	temp[2].found = true;	
				return temp[2];
			case "SMOKE":	temp[3].found = true;	
				return temp[3];
			case "STINK":	temp[4].found = true;
				return temp[4];
		}
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
	
	/*Updates map after it is created in game
	 * 
	 */
	public void updateMap(Map m){
		map = m;
	}
	
	/*function to convert name tile and clearing
	 * into position in arrays
	 * ie: tile 8 clearing 5 becomes 7 2
	 */
	public static int[] convertNameToPosition(String q []){
		int pos[] = new int[2];
		pos[0] = Integer.parseInt(q[0]) - 1;//decrease the tile umber
		
		//for valley
		if(pos[0] < 5){
			if(Integer.parseInt(q[1]) > 2)
				pos[1] = Integer.parseInt(q[1]) - 2;
			else
				pos[1] = Integer.parseInt(q[1]) - 1;
		}
		//for woods
		else if(pos[0] < 10){
			if(Integer.parseInt(q[1]) > 3)
				pos[1] = Integer.parseInt(q[1]) - 3;
			else
				pos[1] = Integer.parseInt(q[1]) - 2;
		}
		//other tiles
		else{
			pos[1] = Integer.parseInt(q[1]) - 1;
		}
		return pos;
	}
	
	public void changeDate(String s){
		dLabel.setText(s);
	}

	public Player fightWho(Clearing clearing) {	//ask user from available choices who he whishes to fight in combat
		//choose player to fight based on players[a].getProfile().foughtToday must be false
		
		//create the array to ask
		String[] choices = null;
		Player[] values = null;
		
		for(int a=0; a<clearing.numPLayersInClearing; a++){		//go through all players
				if(!clearing.playersInClearing[a].getProfile().getFoughtToday()){	//if not already fought
					choices = ArrayUtils.add(choices, clearing.playersInClearing[a].getProfile().getType());//add the title to the array
					values = ArrayUtils.add(values, clearing.playersInClearing[a]);
				}
		}
		
		if(choices == null){
			return null;//should return null if there are no choices, return player you will fight
		
		}else{	//there is in fact an opponent
			//now ask which one to send
			int response = JOptionPane.showOptionDialog(null, "Who would you like to fight?",	"Oponents",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null,	choices, choices[0]);
		
			//return the player value
			return values[response];
		}
		//Unreachable
	}

	public void selectFightGear(Player player) {
		System.out.println("Creating Gear Setup Stuff");
		
		//1)select fight counter and attack direction
			//CombatDialog combat = new CombatDialog( player);
			//combat.show();
		//CombatDialog combat = new CombatDialog(player);	//go get direction and attack chit type
		CombatDialog.getAttack(player);//action chits can only be used once per round
		
		//2)select armors 	always have armor active		
		//some armor needs to choose which direction to protect
		//ArmorDialog protect = new ArmorDialog(player);
		if(player.getProfile().shieldActive)	//only need to make a choice if you have a shield
			ArmorDialog.getArmor(player);
		
		//3)select move counter and defense direction
		if(player.getProfile().getType().compareTo("Swordsman") != 0)	//swordsman can't do anything anyway
			EvadeDialog.getEvasion(player);//action chits can only be used once per round
	}
	
	public int getPlayerX(){ return playerX;}
	public int getPlayerY(){ return playerY;}
	public void setPlayerX(int x){ playerX = x; }
	public void setPlayerY(int y){ playerY = y; }
	
	public void buildMap(){
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
	}
	
	@SuppressWarnings("static-access")
	public void initilizeWindow(){
		//allows to position tiles
		//map = m;
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
				
		MainWindow.getContentPane().add(Instruction);
		Instruction.setLocation(0,(int)screenSize.getHeight()/3);
		Instruction.setSize((int)screenSize.getWidth()/2,25);
				
		Instruction.add(moveLabel);
		Instruction.setVisible(false);
		Date.add(dLabel);
		Date.setBackground(Color.white);
		MainWindow.getContentPane().add(Date);
		Date.setLocation(0,(int)screenSize.getHeight()/3+25);
		Date.setSize((int)screenSize.getWidth()/2,25);
				
				
		//String test[] = {"QWE", "ERT", "RTYYSDFG","ASDFXZVDFG","ASFWEFAS"};
		//jlPlayers.setListData(test);
		jlPlayers.setForeground(Color.black);
		jlPlayers.setBackground(Color.LIGHT_GRAY);
		spPlayers.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPlayers.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		spPlayers.setViewportView(jlPlayers);
		Players.add(spPlayers);
		spPlayers.setSize(new Dimension((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/5));
				
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setText("Start Game");
		MainWindow.getContentPane().add(startButton);
		startButton.setLocation(0,(int)screenSize.getHeight()/5);
		startButton.setSize(new Dimension(200,100));
		startButton.setEnabled(true);
		startButton.addActionListener
			(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent evt)
						{ ACTION_START(); }
				}
		);
				
		Players.setLayout(new BorderLayout()); 
		Players.setPreferredSize(new Dimension((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3));
		Players.setBackground(Color.white);
		MainWindow.getContentPane().add(Players);
		Players.setLocation(0,0);
		Players.setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3);	
	}
	
	//private void initPlayers(String name){
	@SuppressWarnings("unused")
	private void initPlayers(){
		//TODO RYAN HAS THIS BEEN HANDLED edit code to be able to use icons
		ImageIcon amazonIcon = new ImageIcon("res/characters/amazon.png");
		ImageIcon bknightIcon = new ImageIcon("res/characters/black_knight.png");
		ImageIcon captainIcon = new ImageIcon("res/characters/captain.png");
		ImageIcon dwarfIcon = new ImageIcon("res/characters/dwarf.png");
		ImageIcon elfIcon = new ImageIcon("res/characters/elf.png");
		ImageIcon swordsmanIcon = new ImageIcon("res/characters/swordsman.png");
				
		/*
		amazon.setIcon(amazonIcon);
		amazon.setVisible(true);
		//amazon.setLocation(560,1175);
		amazon.setSize(50,50);
		Map.add(amazon);
		Map.setComponentZOrder(amazon, 0);
		*/
		for(int i=0; i<6; i++){
			//player[i].setVisible(true);
			player[i].setSize(50,50);
			Map.add(player[i]);
			Map.setComponentZOrder(player[i],0);
		}
		/*
		ImageIcon p1 = new ImageIcon("res/characters/amazon.png");
		JLabel qwe = new JLabel();
		qwe.setIcon(p1);
		qwe.setVisible(true);
		qwe.setLocation(560,1175);
		qwe.setSize(50,50);
		Map.add(qwe);
		Map.setComponentZOrder(qwe, 0);
		*/
	}
	
	@SuppressWarnings("resource")
	private static void avaiableChars(Socket X) throws IOException{
		Scanner INPUT = new Scanner(X.getInputStream());
		List<String> n = new ArrayList<String>();
		
		String s = INPUT.nextLine();
		s = s.replace("[", "");
		s = s.replace("]", "");
		s = s.replace(" ", "");

		n = Arrays.asList(s.split(","));
		
		for(int x=0; x < n.size(); ++x){
			possibilities[x] = n.get(x);
		}
		System.out.println(s);
		System.out.println(possibilities);
	}

	public static void combatMessage(String message) {
		//DIsplay Message to user
		JOptionPane.showMessageDialog(null, message);		
	}

	public static String selectFatigue(Player person) {
		//Since effort is 2 then need to fatigue 1 effort worth of action Chits
		//FIrst get the active ones
		String[] options = null;
		CombatChit.getActiveEffortChits(person, options);
				
		//next ask which one to fatigue
		//now ask defender to pick
		@SuppressWarnings("null")
		Object response = JOptionPane.showInputDialog(null, "Which Action Chit do You Wish To Fatigue?",	"Fatiguing",
				JOptionPane.PLAIN_MESSAGE,	null,	options, options[0]);
		
		return (String)response;
	}
	
	public static String displayMessage(String message){
		JOptionPane.showMessageDialog(null, message);
		return "";
	}

	public void ACTION_START(){
		client.SEND("STARTGAME");
	}

	public static boolean lootChoices() {
		//Ask user if he wants to loot the treasure or the belongings
		
		String[] options = {"Treasure","Corpse"};
		//return true if user wants cheat mode
		int n = JOptionPane.showOptionDialog(null,  "Do You Wish To Loot the Treasure or the Corpse?", "Loot", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		//System.out.println(n);
		//return true if want to loot treasure, false for belongings
		if(n == 0){//yes
			return true;
		}else{//no
			return false;
		}
	}

	public static boolean whatFound(int currentTile) {
		//Ask user if he wants to locate the treasure or the secret paths
		String[] options = {"Treasure","Passages"};
		//return true if user wants cheat mode
		int n = JOptionPane.showOptionDialog(null,  "Do You Wish To Locate the Treasure or the Passages?", "Locate", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		//System.out.println(n);
		//return true if want to locate treasure, false for passages
		if(n == 0){//yes
			return true;
		}else{//no
			return false;
		}
	}

	public static int victoryPoints(String string, int[] values) {
		
		//convert to String
		String[] options = new String[values.length];
		for(int a=0; a<values.length; a++)	options[a] = "" + values[a];//create array
		
		// Ask user what value it will be	
		Object response = JOptionPane.showInputDialog(null, string,	"Victory",
				JOptionPane.PLAIN_MESSAGE,	null,	options, options[0]);//test before adding suppress
		
		
		return Integer.parseInt(response.toString());
	}
	
}
