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
	public JLabel[] mapTiles;
	int tileCount;
	
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
		
		
		//allows to position tiles
		Map.setLayout(null);
		
		Map.setBackground(Color.white);
	}
	
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
