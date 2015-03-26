package Model;

import CharacterProfiles.Character;

public class MapChits {

	public int clearing = -1;//number value on chit tells location
	public String type;
	
	public boolean found = false;//treasures not found until search table

	//site(treaseure)	8 of these
	public class GoldChit extends MapChits {
		public Treasure [] shinies;
		int smallTreasures = 0;
		int lgTreasures = 0;
		
		//constructor
		public GoldChit(String word, int spot){
			clearing = spot;
			type = word;

			//how many treasures there are	
		/*	loc		sm	lg			
			HOARD	4	5*
			LAIR	4	3*
			ALTAR	—	4*
			SHRINE	2	2*
			POOL	6	3*
			VAULT	—	5*
			CAIRNS	6	1*
			STATUE	2	1*				*/
			switch(type){
			case "HOARD":	smallTreasures = 4;	lgTreasures = 5;
				break;
			case "LAIR":	smallTreasures = 4;	lgTreasures = 3;
				break;
			case "ALTAR":	smallTreasures = 0;	lgTreasures = 4;
				break;
			case "SHRINE":	smallTreasures = 2;	lgTreasures = 2;
				break;
			case "POOL":	smallTreasures = 6;	lgTreasures = 3;
				break;
			case "VAULT":	smallTreasures = 0;	lgTreasures = 5;
				break;
			case "CAIRNS":	smallTreasures = 6;	lgTreasures = 1;
				break;
			case "STATUE":	smallTreasures = 2;	lgTreasures = 1;
				break;
			}
			
			//this will hold all treasures contained
			//better stuff will be at end of array
			shinies = new Treasure[0];
			for(int a = 0; a < smallTreasures; a++){
				//shinies = ArrayUtils.add(shinies, );TODO add treasure to array
			}
			for(int a = 0; a < lgTreasures; a++){
				//shinies = ArrayUtils.add(shinies, );
			}

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
