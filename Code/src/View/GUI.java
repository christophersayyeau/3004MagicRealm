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
	 * 
	 * 
	 */
	public static void main(String args[])
	{
		ImageIcon image1 = new ImageIcon("res/cliff1.gif");
		ImageIcon image2 = new ImageIcon("res/ledges1.gif");
		
		MainWindow.setBackground(new java.awt.Color(255,255,255));
		MainWindow.setSize(1000, 1000);
		MainWindow.getContentPane().setLayout(null);
		MainWindow.setTitle("Magic Realm");
		MainWindow.setLocation(220, 180);
		MainWindow.setResizable(false);
		MainWindow.setVisible(true);
		
		l1.setIcon(image1);
		MainWindow.getContentPane().add(l1);
		l1.setBounds(0, 0, 500, 500);
		l1.setVisible(true);
		
		l2.setIcon(image2);
		MainWindow.getContentPane().add(l2);
		l2.setBounds(000, 500, 500, 500);
		l2.setVisible(true);
		
		while(true)
		{
			
		}
	}
	
	public void Refresh() {
		// TODO Auto-generated method stub
		//update all pictures, maps and related visuals
		
	}
}
