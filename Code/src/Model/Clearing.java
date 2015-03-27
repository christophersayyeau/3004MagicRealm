package Model;

import Control.Player;
import Model.ArrayUtils;

public class Clearing {

	public Player[] playersInClearing;//for know it is a single value, this will need to c\be changed
	public int numPLayersInClearing = 0;
	
	public Denizen [] monstersInClearing = new Denizen[5];//put 5 for now, we will have to change when if it crashes
	int numMonstersInClearing = 0;
	
	private int value = -1;
	
	private int [] connectedTo = new int[4];//the highest I noticed was 3

	public boolean guardHouse = false;//only one clearing has guardhouse
	public boolean chapel = false;
	public boolean house = false;
	public boolean inn = false;

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
		//playersInClearing = player1;
		playersInClearing = ArrayUtils.add(playersInClearing, player1);
		this.numPLayersInClearing++;
	}
	
	
	public void removePlayer(Player player1) {
		System.out.println("The remove players in clearing function has been called");
		//playersInClearing = null;	
//TODO removePlayer from clearing still doesn't work, 
//System.out.println("Removing " + player1 + " from array " + playersInClearing + ", which is currently in position " + ArrayUtils.indexOf(player1, playersInClearing));
		playersInClearing = (Player[]) ArrayUtils.remove(playersInClearing, ArrayUtils.indexOf(player1, playersInClearing));
		this.numPLayersInClearing--;
	}

	public void putDenizen(Denizen monster) {
		monstersInClearing[numMonstersInClearing] = monster;
		numMonstersInClearing++;
	}
	public void removeDenizen(Denizen monster) {
		//first find the monster in the array
		int a;
		//System.out.println("!!!!! "+ numMonstersInClearing);
		for(a=0; a<numMonstersInClearing; a++){//num... will always be bigger than 0
			//compare
		//System.out.println("Match: "+monster+monstersInClearing[a]);
			if(monstersInClearing[a].equals(monster)){
				System.out.println("Match Found");
				monstersInClearing =  (Denizen[]) ArrayUtils.remove(monstersInClearing, a);
				numMonstersInClearing--;
				return;//no need to go through rest of array
			}		
		}
		System.out.println("Not Found");
	}
}
