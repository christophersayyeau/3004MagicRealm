package Model;

public class MapChits {

	int clearing = -1;//number value on chit tells location
	String type;
	
	
	//site(treaseure)	8 of these
	public class GoldChit extends MapChits {
		//constructor
		public GoldChit(){
			clearing = ;
			type = ;
		}
	}

	//Sounds	10 of these
	public class RedChit extends MapChits {
		//constructor
		public RedChit(){
			clearing = ;
			type = ;//monster that will appear
		}
	}
	
	//Warnings or Map	20 of these, split into 4 groups of 5(V,W,C,M)
	public class YellowChit extends MapChits {
		//constructor
		public YellowChit(){
			clearing = ;
			type = ;
		}
	}
	
	//how they are sorted
	/*
	 Gold and red mixed=18
	 random 5 put into lost City, random 5 put into lost castle
	 =8left
	 
	 warning/map chit=20
	 5"C" put on the 5 caveType tiles(brderLand,Caves,Cavern,HighPass,Ruins)
	 half(4) of gold/red leftovers and lost CIty randomly assigned to the 5 tiles
	 
	 5"M" put on the 5 mountainType tiles(Cliff, Crag, Deep Woods, Ledges, Mountain)
	 last 4 of gold/red leftovers and lost Castle randomly assigned to the 5 tiles
	 
	 5"V" put on 5 valley tiles(Awful Valley, Bad Valley, Cursed Valley, Dark Valley, Evil Valley)
	 
	 5"W" put on 5 woods tiles(Linden Woods, Maple Woods, Nut Woods, Oak Woods, Pine Woods)
	 
	 
	 */
}
