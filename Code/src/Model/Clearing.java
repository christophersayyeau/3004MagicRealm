package Model;

import Control.Player;

public class Clearing {

	Player playersInTile;//for know it is a single value, this will need to c\be changed
	
	private int value = -1;
	
	private int [] connectedTo = new int[4];//the highest I noticed was 3

	public boolean guardHouse = false;//only one clearing has guardhouse

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int [] getConnectedTo() {
		return connectedTo;
	}

	
	
	public void putPlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = player1;
	}
	public void removePlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = null;	
	}
}
