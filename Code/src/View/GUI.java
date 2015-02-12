package View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import Model.*;

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SOME OF THE TILES ARE FACING THE WWRONG WAY, please compare to the picture and fix, ex darkValley in corner


public class GUI implements MouseListener{
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public static JLabel L_cliff = new JLabel();
	public static JLabel L_ledge = new JLabel();
	public static JLabel L_crag = new JLabel();
	public static JLabel L_evil_valley = new JLabel();
	public static JLabel L_high_pass = new JLabel();
	public static JLabel L_cavern = new JLabel();
	public static JLabel L_mountain = new JLabel();
	public static JLabel L_pine_woods = new JLabel();
	public static JLabel L_caves = new JLabel();
	public static JLabel L_ruins = new JLabel();
	public static JLabel L_linden_woods = new JLabel();
	public static JLabel L_awful_valley = new JLabel();
	public static JLabel L_nut_woods = new JLabel();
	public static JLabel L_curst_valley = new JLabel();
	public static JLabel L_dark_valley = new JLabel();
	public static JLabel L_deep_woods = new JLabel();
	public static JLabel L_maple_woods = new JLabel();
	public static JLabel L_oak_woods = new JLabel();
	public static JLabel L_bad_valley = new JLabel();
	public static JLabel L_borderland = new JLabel();
	
	final int x = 125;
	final int y = 215;
	final int tileX = 300;
	final int tileY = 305;
	
	/*Just temporary to test the map building
	 * Work in  progress
	 * resource files also temporary
	 * pictures cropped 50x877x200x200
	 */ 
	//public static void main(String args[])
	
	//constructor, called in player.java
	public GUI()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon cliff = new ImageIcon("res/tiles/cliff.png");
		ImageIcon ledge = new ImageIcon("res/tiles/ledges.png");
		ImageIcon crag = new ImageIcon("res/tiles/crag.png");
		ImageIcon evil_valley = new ImageIcon("res/tiles/evil_valley.png");
		ImageIcon high_pass = new ImageIcon("res/tiles/high_pass.png");
		ImageIcon cavern = new ImageIcon("res/tiles/cavern.png");
		ImageIcon mountain = new ImageIcon("res/tiles/mountain.png");
		ImageIcon pine_woods = new ImageIcon("res/tiles/pine_woods.png");
		ImageIcon caves = new ImageIcon("res/tiles/caves.png");
		ImageIcon ruins = new ImageIcon("res/tiles/ruins.png");
		ImageIcon linden_woods = new ImageIcon("res/tiles/linden_woods.png");
		ImageIcon awful_valley = new ImageIcon("res/tiles/awful_valley.png");
		ImageIcon nut_woods = new ImageIcon("res/tiles/nut_woods.png");
		ImageIcon curst_valley = new ImageIcon("res/tiles/curst_valley.png");
		ImageIcon dark_valley = new ImageIcon("res/tiles/dark_valley.png");
		ImageIcon deep_woods = new ImageIcon("res/tiles/deep_woods.png");
		ImageIcon maple_woods = new ImageIcon("res/tiles/maple_woods.png");
		ImageIcon oak_woods = new ImageIcon("res/tiles/oak_woods.png");
		ImageIcon bad_valley = new ImageIcon("res/tiles/oak_woods.png");
		ImageIcon borderland = new ImageIcon("res/tiles/borderland.png");
		
		
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
		
		
		//allows to position tiles
		Map.setLayout(null);
		
		Map.setBackground(Color.white);
		
		L_cliff.setIcon(cliff);
		L_cliff.setLocation(x*2,0);
		L_cliff.setSize(tileX, tileY);
		L_cliff.setVisible(true);
		L_cliff.addMouseListener(this);
		Map.add(L_cliff);
		
		L_ledge.setIcon(ledge);
		L_ledge.setLocation(x*3,y);
		L_ledge.setSize(tileX, tileY);
		L_ledge.addMouseListener(this);
		L_ledge.setVisible(true);
		Map.add(L_ledge);
		
		L_crag.setIcon(crag);
		L_crag.setLocation(x*5,y);
		L_crag.setSize(tileX, tileY);
		L_crag.addMouseListener(this);
		L_crag.setVisible(true);
		Map.add(L_crag);
		
		L_evil_valley.setIcon(evil_valley);
		L_evil_valley.setLocation(x,y);
		L_evil_valley.setSize(tileX, tileY);
		L_evil_valley.addMouseListener(this);
		L_evil_valley.setVisible(true);
		Map.add(L_evil_valley);
		
		L_high_pass.setIcon(high_pass);
		L_high_pass.setLocation(0,y*2);
		L_high_pass.setSize(tileX, tileY);
		L_high_pass.setVisible(true);
		L_high_pass.addMouseListener(this);
		Map.add(L_high_pass);
		
		L_cavern.setIcon(cavern);
		L_cavern.setLocation(x,y*3);
		L_cavern.setSize(tileX, tileY);
		L_cavern.setVisible(true);
		L_cavern.addMouseListener(this);
		Map.add(L_cavern);
		
		L_mountain.setIcon(mountain);
		L_mountain.setLocation(x*2,y*4);
		L_mountain.setSize(tileX, tileY);
		L_mountain.setVisible(true);
		L_mountain.addMouseListener(this);
		Map.add(L_mountain);
		
		L_pine_woods.setIcon(pine_woods);
		L_pine_woods.setLocation(x*3,y*5);
		L_pine_woods.setSize(tileX, tileY);
		L_pine_woods.setVisible(true);
		L_pine_woods.addMouseListener(this);
		Map.add(L_pine_woods);
		
		L_caves.setIcon(caves);
		L_caves.setLocation(x*4,y*4);
		L_caves.setSize(tileX, tileY);
		L_caves.setVisible(true);
		L_caves.addMouseListener(this);
		Map.add(L_caves);
		
		L_ruins.setIcon(ruins);
		L_ruins.setLocation(x*6,y*4);
		L_ruins.setSize(tileX, tileY);
		L_ruins.setVisible(true);
		L_ruins.addMouseListener(this);
		Map.add(L_ruins);
		
		L_linden_woods.setIcon(linden_woods);
		L_linden_woods.setLocation(x*7,y*5);
		L_linden_woods.setSize(tileX, tileY);
		L_linden_woods.setVisible(true);
		L_linden_woods.addMouseListener(this);
		Map.add(L_linden_woods);
		
		L_awful_valley.setIcon(awful_valley);
		L_awful_valley.setLocation(x*8,y*4);
		L_awful_valley.setSize(tileX, tileY);
		L_awful_valley.setVisible(true);
		L_awful_valley.addMouseListener(this);
		Map.add(L_awful_valley);
		
		L_nut_woods.setIcon(nut_woods);
		L_nut_woods.setLocation(x*7,y*3);
		L_nut_woods.setSize(tileX, tileY);
		L_nut_woods.setVisible(true);
		L_nut_woods.addMouseListener(this);
		Map.add(L_nut_woods);
		
		L_curst_valley.setIcon(curst_valley);
		L_curst_valley.setLocation(x*8,y*2);
		L_curst_valley.setSize(tileX, tileY);
		L_curst_valley.setVisible(true);
		L_curst_valley.addMouseListener(this);
		Map.add(L_curst_valley);
		
		L_dark_valley.setIcon(dark_valley);
		L_dark_valley.setLocation(x*7,y);
		L_dark_valley.setSize(tileX, tileY);
		L_dark_valley.setVisible(true);
		L_dark_valley.addMouseListener(this);
		Map.add(L_dark_valley);
		
		L_deep_woods.setIcon(deep_woods);
		L_deep_woods.setLocation(x*6,y*2);
		L_deep_woods.setSize(tileX, tileY);
		L_deep_woods.setVisible(true);
		L_deep_woods.addMouseListener(this);
		Map.add(L_deep_woods);
		
		L_oak_woods.setIcon(oak_woods);
		L_oak_woods.setLocation(x*4,y*2);
		L_oak_woods.setSize(tileX, tileY);
		L_oak_woods.setVisible(true);
		L_oak_woods.addMouseListener(this);
		Map.add(L_oak_woods);
		
		L_borderland.setIcon(borderland);
		L_borderland.setLocation(x*2,y*2);
		L_borderland.setSize(tileX, tileY);
		L_borderland.setVisible(true);
		L_borderland.addMouseListener(this);
		Map.add(L_borderland);
		
		L_maple_woods.setIcon(maple_woods);
		L_maple_woods.setLocation(x*5,y*3);
		L_maple_woods.setSize(tileX, tileY);
		L_maple_woods.setVisible(true);
		L_maple_woods.addMouseListener(this);
		Map.add(L_maple_woods);
		
		L_bad_valley.setIcon(bad_valley);
		L_bad_valley.setLocation(x*3,y*3);
		L_bad_valley.setSize(tileX, tileY);
		L_bad_valley.setVisible(true);
		L_bad_valley.addMouseListener(this);
		Map.add(L_bad_valley);
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
		//see declaration for details
		// TODO Auto-generated method stub
		
	}

	public void hideMapChits() {
		//end of day, all map chits that can be seen are unseen
		// TODO Auto-generated method stub
		
	}
}
