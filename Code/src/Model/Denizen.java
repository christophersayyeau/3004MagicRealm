package Model;

public class Denizen {

	int size = -1;	//can be M=1, H=2, T=3 
	boolean armored = false;	//only Dragons,Trolls,Serpents,Vipers have it
	
	int fameBounty = 0;		//what it's worth to kill them
	int notorietyBounty = 0;
	
	Harm regCombat;
	//alertedHarm = new Harm(new Harm(2, 1, 4); sending this H*4
	Harm aggresiveCombat;//darker side of card
	
	int regMove = -1;
	int aggressiveMove = -1;//darker side
	
	public class Ghost extends Denizen {
		public Ghost() {
			size = 1;//medium
			//fame = 0
			notorietyBounty = 2;
			
			regCombat = new Harm(3, 0, 4);//H4
			regMove = 4;
			
			aggresiveCombat = new Harm(1, 0, 2);//L2
			aggressiveMove = 2;
		}
	}
	
	public class Wolf extends Denizen {//note there are 2 profiles for the wolf we are using the second one
		public Wolf() {
			size = 1;//medium
			//fame = 0
			notorietyBounty = 1;
			
			regCombat = new Harm(2, 0, 5);//M5
			regMove = 3;
			
			aggresiveCombat = new Harm(1, 0, 3);//L3
			aggressiveMove = 4;
		}
	}
	
	//TODO add more when we have time, for now we can just use this as a base for everything
}
