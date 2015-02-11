package View;

import javax.swing.*;
import Model.*;

public class GUI {

	public static JFrame MainWindow = new JFrame();
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
		ImageIcon image1 = new ImageIcon("res/tiles/cliff.png");
		ImageIcon image2 = new ImageIcon("res/tiles/ledges.png");
		ImageIcon image3 = new ImageIcon("res/tiles/crag.png");
		
		MainWindow.setBackground(new java.awt.Color(255,255,255));
		MainWindow.setSize(1000, 1000);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setLocation(220, 180);
		MainWindow.setVisible(true);
		
		l1.setIcon(image1);
		MainWindow.getContentPane().add(l1);
		l1.setBounds(0, 0, 300, 305);
		l1.setVisible(true);
		
		l2.setIcon(image2);
		MainWindow.getContentPane().add(l2);
		l2.setBounds(125, 215, 300, 305);
		l2.setVisible(true);
		
		l3.setIcon(image3);
		MainWindow.getContentPane().add(l3);
		l3.setBounds(370, 214, 300, 305);
		l3.setVisible(true);
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures, maps and related visuals
		
	}
}
