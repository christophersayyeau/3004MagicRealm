package View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Control.Game;


public class GUI implements MouseListener{
	
	Game game;
	
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public JLabel[] mapTiles;
	int tileCount;
	
	public static JPanel Players = new JPanel();
	
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
		
		MainWindow.getContentPane().add(scrollPane);
		scrollPane.setLocation((int)screenSize.getWidth()/2, 0);
		scrollPane.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight()-60);
		
		//Use a different layout later if needed, using this for testing
		Players.setLayout(new BorderLayout()); 
		//JButton b1 = new JButton("Create");
		//Players.add(b1,BorderLayout.SOUTH);
		/*b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Call the create character function");
				createPlayer();
			}
		});*/
		Players.setPreferredSize(new Dimension((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3));
		Players.setBackground(Color.white);
		MainWindow.getContentPane().add(Players);
		Players.setLocation(0,0);
		Players.setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/3);
		
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
		label.addMouseListener(this);
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
		System.out.println("You have chosen to be a: " + s);
		//game.gotCharacter = true;
		return (String)s;
		
		
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
	
	public void recordTurn() {
		//see declaration for details and the return value stuff
		// TODO Auto-generated method stub
		
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

	public boolean trading() {
		//just ask user if they want to trade with others in clearing, see declaration
		// TODO return true if the user wants to trade
		return false;
	}
}
