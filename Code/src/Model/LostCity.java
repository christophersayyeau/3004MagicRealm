package Model;

import Model.MapChits.GoldChit;
import Model.MapChits.RedChit;

public class LostCity {

	//has 5 counters taken randomly from the tresure and sound piles
	//we decided to hardcode them in normal mode
	
	private RedChit [] sound;// = new RedChit[3];
	private GoldChit [] treasure;// = new GoldChit[2];
	
	public boolean found = false;//used in cheat mode
	
	//to give them values
	public void setSound(int num, RedChit s) {
		//this.sound[num] = s;
		this.sound = ArrayUtils.add(sound, s);
	}
	public void setTreasure(int num, GoldChit s) {
		//this.treasure[num] = s;
		this.treasure = ArrayUtils.add(treasure, s);
	}
}
