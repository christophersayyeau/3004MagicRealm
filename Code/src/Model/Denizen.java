package Model;

public class Denizen {
	String name;
	
	int size = -1;	//can be M=1, H=2, T=3 
	boolean armored = false;	//only Dragons,Trolls,Serpents,Vipers have it
	
	int fameBounty = 0;		//what it's worth to kill them
	int notorietyBounty = 0;
	
	//the combat values
	Harm regCombat;
	//alertedHarm = new Harm(new Harm(2, 1, 4); sending this H*4
	Harm aggresiveCombat;//darker side of card
	
	int currentClearing = -1;
	int startClearing = -1;
	
	//number without a letter on card
	int regMove = -1;
	int aggressiveMove = -1;//darker side
	
	public boolean prowling = false;
	
	//some functions
	public int getCurrentLocation() {
		return currentClearing;
	}
	public void setCurrentClearing(int newLocation) {
		currentClearing = newLocation;
	}
	public int getStartLocation() {
		return startClearing;
	}
	public void setStartClearing(int newLocation) {
		startClearing = newLocation;
	}
	
		
	//now for the monsters and natives
	public class Ghost extends Denizen {
		public Ghost() {
			name = "GHOST";
			
			size = 1;//medium
			//fame = 0
			notorietyBounty = 2;
			
			regCombat = new Harm(3, 0, 4);//H4
			regMove = 4;
			
			aggresiveCombat = new Harm(1, 0, 2);//L2
			aggressiveMove = 2;
			
			//always prowling
			prowling = true;
		}
	}
	
	public class Wolf extends Denizen {//note there are 2 profiles for the wolf we are using the second one
		public Wolf() {
			name = "WOLF";
			
			size = 1;//medium
			//fame = 0
			notorietyBounty = 1;
			
			regCombat = new Harm(2, 0, 5);//M5
			regMove = 3;
			
			aggresiveCombat = new Harm(1, 0, 3);//L3
			aggressiveMove = 4;
		}
	}


	//native garrisons are never prowling
	//TODO add more when we have time, for now we can just use this as a base for everything
}
