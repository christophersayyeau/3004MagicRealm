package View;

import java.awt.Image;
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
	public static void main(String args[])
	{
		ImageIcon image1 = new ImageIcon("res/tiles/cliff.jpg");
		ImageIcon image2 = new ImageIcon("res/tiles/ledges.jpg");
		ImageIcon image3 = new ImageIcon("res/tiles/crag.jpg");
		
		MainWindow.setBackground(new java.awt.Color(255,255,255));
		MainWindow.setSize(1000, 1000);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setLocation(220, 180);
		MainWindow.setResizable(false);
		MainWindow.setVisible(true);
		
		l1.setIcon(image1);
		MainWindow.getContentPane().add(l1);
		l1.setBounds(0, 0, 336, 359);
		l1.setVisible(true);
		
		l2.setIcon(image2);
		MainWindow.getContentPane().add(l2);
		l2.setBounds(105, 365, 336, 359);
		l2.setVisible(true);
		
		l3.setIcon(image3);
		//MainWindow.getContentPane().add(l3);
		l3.setBounds(500, 365, 500, 500);
		l3.setVisible(true);
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures, maps and related visuals
		
	}
}
