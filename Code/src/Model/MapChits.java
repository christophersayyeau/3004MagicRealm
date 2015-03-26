package Model;

import CharacterProfiles.Character;

public class MapChits {

	public int clearing = -1;//number value on chit tells location
	public String type;
	
	public boolean found = false;//treasures not found until search table

	//site(treaseure)	8 of these
	public class GoldChit extends MapChits {
		public Treasure [] shinies;
		
		//constructor
		public GoldChit(String word, int spot){
			clearing = spot;
			type = word;

			//this will hold all treasures contained
			shinies = new Treasure[0];
			//TODO secondStep, fill with treasure, see playerDrop below
		}
	}

	//Sounds	10 of these
	public class RedChit extends MapChits {
		//found value only used in cheat mode
		//constructor
		public RedChit(String word, int spot){
			clearing = spot;
			type = word;//monster that will appear
		}
	}
	
	//Warnings or Map	20 of these, split into 4 groups of 5(V,W,C,M)
	public class YellowChit extends MapChits {
		//found value only used in cheat mode
		//constructor
		public YellowChit(String word){
			type = word;
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
	//This will represent character drop when he dies
	public class PlayerDrop extends MapChits {
		public Items [] belongings;
		//constructor
		public PlayerDrop(int location, Character profile){
			clearing = location;
			found = true;		//visible
			
			int numOfThings = 1 + profile.getDefense().length + profile.belongings.length;	//weapon + armor + belongings
			System.out.println("Creating an array of size: " + numOfThings);
			
			//create array
			belongings = new Items[0];
			
			//weapon
			belongings = ArrayUtils.add(belongings, profile.getWeapon());
			//armor
			for(int a = 0; a<profile.getDefense().length; a++){
				belongings = ArrayUtils.add(belongings, profile.getDefense(a));
			}
			//treasurestuff
			for(int b = 0; b<profile.belongings.length; b++){
				belongings = ArrayUtils.add(belongings, profile.belongings[b]);
			}
		}
	}
}
