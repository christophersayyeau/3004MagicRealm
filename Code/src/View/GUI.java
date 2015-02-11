package View;

import java.awt.*;

import javax.swing.*;
import Model.*;

public class GUI {
	public static JFrame MainWindow = new JFrame();
	public static JPanel Map = new JPanel();
	public static JScrollPane scrollPane = new JScrollPane(Map);
	public static JLabel l1 = new JLabel();
	public static JLabel l2 = new JLabel();
	public static JLabel l3 = new JLabel();
	public static JLabel l4 = new JLabel();
	public static JLabel l5 = new JLabel();
	public static JLabel l6 = new JLabel();
	
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
		ImageIcon image1 = new ImageIcon("res/tiles/cliff.png");
		ImageIcon image2 = new ImageIcon("res/tiles/ledges.png");
		ImageIcon image3 = new ImageIcon("res/tiles/crag.png");
		
		MainWindow.setBackground(Color.LIGHT_GRAY);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setVisible(true);
		MainWindow.setExtendedState(MainWindow.MAXIMIZED_BOTH);
		
		Map.setPreferredSize(new Dimension(2000,2000));
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
		
		l1.setIcon(image1);
		l1.setBounds(0, 0, 300, 305);
		l1.setVisible(true);
		Map.add(l1);
		
		l2.setIcon(image2);
		l2.setBounds(125, 215, 300, 305);
		l2.setVisible(true);
		Map.add(l2);
		
		l3.setIcon(image3);
		l3.setBounds(370, 214, 300, 305);
		l3.setVisible(true);
		Map.add(l3);
		
		l4.setIcon(image3);
		l4.setBounds(370, 714, 300, 305);
		l4.setVisible(true);
		Map.add(l4);
		
		l5.setIcon(image3);
		l5.setBounds(370, 1214, 300, 305);
		l5.setVisible(true);
		Map.add(l5);
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures, maps and related visuals
		
	}
}
