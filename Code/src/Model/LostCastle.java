package Model;

import Model.MapChits.GoldChit;
import Model.MapChits.RedChit;

public class LostCastle {

	//has 5 counters taken randomly from the tresure and sound piles
	//we decided to hardcode them in
	
	private RedChit [] sound = new RedChit[3];

	
	private GoldChit [] treasure = new GoldChit[2];
	
	
	//to give them values
	public void setSound(int num, RedChit s) {
		this.sound[num] = s;
	}
	public void setTreasure(int num, GoldChit s) {
		this.treasure[num] = s;
	}
}
