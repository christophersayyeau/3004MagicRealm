package Model;

import Control.Player;
import Model.Clearing;
import Model.Denizen.*;
import Model.MapChits.*;

public class MapTiles {
	
	public Clearing [] clearing;
	
	//overall class, will create subclasses based on tiles
	Player playersInTile;//for know it is a single value, this will need to c\be changed

	public Denizen [] monstersInTile = new Denizen[5];//put 5 for now, we will have to change when if it crashes
	int numMonstersInTile = 0;
	
	//each tile has 1 warning chit
	private YellowChit warning;
	
	//some tiles have sounds or treasure
	@SuppressWarnings("unused")
	private RedChit sound = null;
	@SuppressWarnings("unused")
	private GoldChit treasure = null;
	
	//lost stuff will be boolean since their values are stored in map object
	boolean lostCastle = false;
	boolean lostCity = false;
	
	//the inter-tile connections, set to garbage values
	int topLeft = -1;		int topRight = -1;						
	int left = -1;			int right = -1;							
	int bottomLeft = -1;	int bottomRight = -1;
	
	String type = "";//use this to determine whether mountain, woods, etc
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public void putPlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = player1;
	}
	public void removePlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = null;	
	}
	
	public void putDenizen(Denizen monster) {
		monstersInTile[numMonstersInTile] = monster;
		numMonstersInTile++;		
	}
	public void removeDenizen(Denizen monster) {
		//first find the monster in the array
		int a;
		for(a=0; a<numMonstersInTile; a++){//num... will always be bigger than 0
			//compare
			System.out.println("Comparing " + monstersInTile[a] + " and " + monster);
			if(monstersInTile[a].equals(monster)){
				System.out.println("Match FOund");
				break;//no need to go through rest of array
			}		
		}
		monstersInTile =  ArrayUtils.remove(monstersInTile, a);
		numMonstersInTile--;
	}
		
	public String getPlayers() {
		//can only handle 1 at moment
		return playersInTile.getProfile().getType(); 
	}
	
	//autogenerated
	public YellowChit getWarning() {
		return warning;
	}
	public void setWarning(YellowChit warning) {
		this.warning = warning;
	}

	public void setSound(RedChit s) {
		this.sound = s;
	}
	public void setTreasure(GoldChit s) {
		this.treasure = s;
	}
	
	//value of lost stuff in map
	public void setLostCastle() {
		lostCastle = true;	
	}
	public void setLostCity() {
		lostCity = true;		
	}
	
	public void setGuardHouse(int i, DarkValley tile) {
		this.clearing[i-1].guardHouse = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeGuards(tile);
	}
	private void putNativeGuards(DarkValley tile) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		tile.guard[0] = temp.new GreatSwordsman("Guard");
		tile.guard[1] = temp.new GreatSwordsman("Guard");
		tile.guard[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		tile.guard[0].setStartClearing(3);
		tile.guard[1].setStartClearing(3);	
		tile.guard[2].setStartClearing(3);
		
		tile.guard[0].setCurrentClearing(3);
		tile.guard[1].setCurrentClearing(3);
		tile.guard[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(tile.guard[0]);
		clearing[3].putDenizen(tile.guard[1]);
		clearing[3].putDenizen(tile.guard[2]);	
	}

	public void setHouse(int i, CurstValley curstValley) {
		this.clearing[i-1].house = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeSoldiers(curstValley);
	}
	private void putNativeSoldiers(CurstValley tile) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		tile.soldiers[0] = temp.new GreatSwordsman("Guard");
		tile.soldiers[1] = temp.new GreatSwordsman("Guard");
		//tile.soldiers[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		tile.soldiers[0].setStartClearing(3);
		tile.soldiers[1].setStartClearing(3);	
		//tile.soldiers[2].setStartClearing(3);
		
		tile.soldiers[0].setCurrentClearing(3);
		tile.soldiers[1].setCurrentClearing(3);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(tile.soldiers[0]);
		clearing[3].putDenizen(tile.soldiers[1]);
		//clearing[3].putDenizen(tile.soldiers[2]);	
	}
	
	public void setInn(int i, BadValley badValley) {
		this.clearing[i-1].inn = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeRogues(badValley);
	}
	private void putNativeRogues(BadValley badValley) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		badValley.rogues[0] = temp.new GreatSwordsman("Guard");
		badValley.rogues[1] = temp.new GreatSwordsman("Guard");
		//tile.soldiers[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		badValley.rogues[0].setStartClearing(3);
		badValley.rogues[1].setStartClearing(3);	
		//tile.soldiers[2].setStartClearing(3);
		
		badValley.rogues[0].setCurrentClearing(3);
		badValley.rogues[1].setCurrentClearing(3);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(badValley.rogues[0]);
		clearing[3].putDenizen(badValley.rogues[1]);
		//clearing[3].putDenizen(tile.soldiers[2]);	
	}
	
	public void setChapel(int i, AwfulValley awfulValley) {
		this.clearing[i-1].chapel = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeOrder(awfulValley);
	}
	private void putNativeOrder(AwfulValley awfulValley) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		awfulValley.order[0] = temp.new GreatSwordsman("Guard");
		awfulValley.order[1] = temp.new GreatSwordsman("Guard");
		//tile.soldiers[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		awfulValley.order[0].setStartClearing(3);
		awfulValley.order[1].setStartClearing(3);	
		//tile.soldiers[2].setStartClearing(3);
		
		awfulValley.order[0].setCurrentClearing(3);
		awfulValley.order[1].setCurrentClearing(3);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(awfulValley.order[0]);
		clearing[3].putDenizen(awfulValley.order[1]);
		//clearing[3].putDenizen(tile.soldiers[2]);	
	}

//lost city in one of 5 cave tiles(borderland,cavern,caves,highpass,ruins)	
//lost castle in one of 5 mountain tiles(cliff,crag,deepWoods,ledges,mountain)	
	//each one represents 5 chits determined in private class

	
	//now for the individual classes
	public class HighPass extends MapTiles {	
		//constructor
		public HighPass(int l, int tl, int tr, int r, int br, int bl){		
			System.out.println("Building HighPass");
			setType("C");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			clearing[0].getConnectedTo()[0] = 4;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[1] = 5;	//clearing 1 connected to 5
		clearing[1].getConnectedTo()[0] = 71;//clearing 2 connected to 1 in tile 7
			clearing[1].getConnectedTo()[1] = 4;	//clearing 2 connected to 4			
		clearing[2].getConnectedTo()[0] = 115;//clearing 3 connected to 5 in tile 11
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 1;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[1] = 2;//clearing 4 connected to 2
			clearing[4].getConnectedTo()[0] = 1;//clearing 5 connected to 1
			clearing[5].getConnectedTo()[0] = 3;//clearing 6 connected to 3
		clearing[5].getConnectedTo()[1] = 23;//clearing 6 connected 3 in tile 2
		}
	}
	
	public class Cliff extends MapTiles {
		//constructor
		public Cliff(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Cliff");
			setType("M");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			clearing[0].getConnectedTo()[0] = 6;//clearing 1 connected to 6
		clearing[0].getConnectedTo()[1] = 22;	//clearing 1 connected to clearing 2 in tile 2
			clearing[1].getConnectedTo()[0] = 3;//clearing 2 connected to 3
		clearing[1].getConnectedTo()[1] = 33;	//clearing 2 connected to 3 in tile 3			
			clearing[2].getConnectedTo()[0] = 2;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 5;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 6;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 3;//clearing 5 connected to 3
			clearing[5].getConnectedTo()[0] = 1;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 4;//clearing 6 connected to 4		
		}
		
	}
	
	public class EvilValley extends MapTiles {
		//constructor
		public EvilValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building EvilValley");
			setType("V");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
		

			//handle the clearings
			clearing = new Clearing[4];
			for(int a=0; a<4; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			
			clearing[0].getConnectedTo()[0] = 4;//clearing 1 connected to 4
		clearing[1].getConnectedTo()[0] = 11;//clearing 2 connected to 1 in tile 1
			clearing[1].getConnectedTo()[1] = 3;	//clearing 2 connected to 3			
		clearing[2].getConnectedTo()[0] = 56;//clearing 3 connected to 6 in tile 5
			clearing[2].getConnectedTo()[1] = 2;//clearing 3 connected to 2
		clearing[3].getConnectedTo()[0] = 32;//clearing 4 connected to 2 in tile 3
		clearing[3].getConnectedTo()[1] = 72;//clearing 4 connected to 2 in tile 7
				
		}
	}
	
	public class DarkValley extends MapTiles {
		GreatSwordsman [] guard = new GreatSwordsman[3];//because it has a dwelling it also has natives

		//constructor
		public DarkValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building DarkValley");
			setType("V");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;

			//System.out.println("There is a Guard House in the 3rd clearing");//handled in Map.java
			

			//handle the clearings
			clearing = new Clearing[4];
			for(int a=0; a<4; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			
			clearing[0].getConnectedTo()[0] = 4;//clearing 1 connected to 4
		clearing[0].getConnectedTo()[1] = 101;	//clearing 1 connected to 1 in tile 10
			clearing[1].getConnectedTo()[0] = 3;//clearing 2 connected to 3
		clearing[2].getConnectedTo()[0] = 92;//clearing 3 connected to 2 in tile 9
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 1;//clearing 4 connected to 1		
		}
	}
	
	public class Ledges extends MapTiles {
		//constructor
		public Ledges(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Ledges");
			setType("M");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			

			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			clearing[0].getConnectedTo()[0] = 4;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[1] = 6;	//clearing 1 connected to 6
		clearing[1].getConnectedTo()[0] = 24;//clearing 2 connected to 4 in tile 2
			clearing[1].getConnectedTo()[1] = 5;	//clearing 2 connected to 5
		clearing[2].getConnectedTo()[0] = 12;//clearing 3 connected to 2 in tile 1
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 1;//clearing 4 connected to 1
		clearing[3].getConnectedTo()[1] = 74;//clearing 4 connected to 4 in tile 7
			clearing[4].getConnectedTo()[0] = 2;//clearing 5 connected to 2
		clearing[4].getConnectedTo()[1] = 82;//clearing 5 connected to 2 in tile 8
			clearing[5].getConnectedTo()[0] = 1;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 3;//clearing 6 connected to 3
		}
	}
	
	public class Crag extends MapTiles {
		//constructor
		public Crag(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Crag");
			setType("M");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;

			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			clearing[0].getConnectedTo()[0] = 4;//clearing 1 connected to 4
		clearing[1].getConnectedTo()[0] = 91;//clearing 2 connected to 9 in tile 1
			clearing[1].getConnectedTo()[1] = 5;	//clearing 2 connected to 5
			clearing[2].getConnectedTo()[0] = 5;//clearing 3 connected to 5
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 1;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[1] = 6;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 2;//clearing 5 connected to 2
			clearing[4].getConnectedTo()[1] = 3;//clearing 5 connected to 3
			clearing[5].getConnectedTo()[0] = 3;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 4;//clearing 6 connected to 4
		}
	}

	public class BorderLand extends MapTiles {
		//constructor
		public BorderLand(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building BorderLand");
			setType("C");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
		clearing[0].getConnectedTo()[0] = 65;//clearing 1 connected to 5 in tile 6
		clearing[0].getConnectedTo()[1] = 125;//clearing 1 connected to 5 in tile 12
		clearing[1].getConnectedTo()[0] = 24;//clearing 2 connected to 4 in tile 2
		clearing[1].getConnectedTo()[1] = 72;	//clearing 2 connected to 2 in tile 7
			clearing[1].getConnectedTo()[2] = 3;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 2;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 5;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 5;//clearing 4 connected to 5
			clearing[3].getConnectedTo()[1] = 6;//clearing 4 connected to 6
		clearing[3].getConnectedTo()[2] = 34;//clearing 4 connected to 4 in tile 3
		clearing[4].getConnectedTo()[0] = 112;//clearing 5 connected to 2 in tile 11
			clearing[4].getConnectedTo()[1] = 3;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[2] = 4;//clearing 5 connected to 4
			clearing[5].getConnectedTo()[0] = 3;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 4;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 1;//clearing 6 connected to 1
		}	
	}

	public class OakWoods extends MapTiles {
		//constructor
		public OakWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Oakwoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[3];
			for(int a=0; a<3; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(2);
			clearing[1].setValue(4);
			clearing[2].setValue(5);

		clearing[0].getConnectedTo()[0] = 35;//clearing 2 connected to 5 in tile 3
		clearing[0].getConnectedTo()[1] = 72;//clearing 2 connected to 2 in tile 7
			clearing[0].getConnectedTo()[2] = 4;//clearing 1 connected to 4
		clearing[1].getConnectedTo()[0] = 91;//clearing 4 connected to 9 in tile 1
			clearing[1].getConnectedTo()[1] = 2;//clearing 4 connected to 5
		clearing[1].getConnectedTo()[2] = 91;//clearing 4 connected to 1 in tile 9
		clearing[2].getConnectedTo()[0] = 121;//clearing 5 connected to 1 in tile 12
		clearing[2].getConnectedTo()[1] = 135;//clearing 5 connected to 5 in tile 13
				
		}	
	}
	
	public class DeepWoods extends MapTiles {
		//constructor
		public DeepWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Deepwoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
		clearing[0].getConnectedTo()[0] = 42;//clearing 1 connected to 2 in tile 8
		clearing[0].getConnectedTo()[1] = 84;//clearing 1 connected to 4 in tile 8
			clearing[0].getConnectedTo()[2] = 4; //clearing 1 connected to 4
			clearing[0].getConnectedTo()[3] = 6;//clearing 1 connected to 6
		clearing[1].getConnectedTo()[0] = 55;//clearing 2 connected to 5 in tile 5
		clearing[1].getConnectedTo()[1] = 102;	//clearing 2 connected to 2 in tile 10
			clearing[1].getConnectedTo()[2] = 3;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 2;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 6;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 5;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 5;//clearing 4 connected to 5
			clearing[3].getConnectedTo()[1] = 6;//clearing 4 connected to 6
			clearing[3].getConnectedTo()[2] = 1;//clearing 4 connected to 1
		clearing[4].getConnectedTo()[0] = 135;//clearing 5 connected to 5 in tile 13
			clearing[4].getConnectedTo()[1] = 3;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[2] = 4;//clearing 5 connected to 4
			clearing[5].getConnectedTo()[0] = 3;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 4;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 1;//clearing 6 connected to 1
			
		}	
	}
	
	public class CurstValley extends MapTiles {
		//greatswordsman,pikeman,pikeman,crossbowman
		GreatSwordsman [] soldiers = new GreatSwordsman[2];//because it has a dwelling it also has natives

		//constructor
		public CurstValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building CurstValley");
			setType("V");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[4];
			for(int a=0; a<4; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);

		clearing[0].getConnectedTo()[0] = 51;//clearing 1 connected to 1 in tile 5
			clearing[0].getConnectedTo()[1] = 4;//clearing 1 connected to 4
			clearing[1].getConnectedTo()[0] = 5;//clearing 2 connected to 5
		clearing[1].getConnectedTo()[1] = 92;	//clearing 2 connected to 2 in tile 9
		clearing[2].getConnectedTo()[0] = 145;//clearing 3 connected to 5 in tile 14
			clearing[2].getConnectedTo()[1] = 1;//clearing 3 connected to 1
			clearing[3].getConnectedTo()[0] = 2;//clearing 4 connected to 2

		}	
	}
	
	public class Cavern extends MapTiles {
		//constructor
		public Cavern(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Cavern");
			setType("C");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			//TODO	
		}	
	}
	
	public class BadValley extends MapTiles {
		//assassin,greatAxeman,greataxeman,s.swordsman,archer,assasin,swordsman,swordsman
		GreatSwordsman [] rogues = new GreatSwordsman[2];//because it has a dwelling it also has natives

		//constructor
		public BadValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building BadValley");
			setType("V");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[4];
			for(int a=0; a<4; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			
			//TODO	
		}	
	}
	
	public class MapleWoods extends MapTiles {
		//constructor
		public MapleWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building MapleWoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[3];
			for(int a=0; a<3; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(2);
			clearing[1].setValue(4);
			clearing[2].setValue(5);

			//TODO	
		}	
	}
	
	public class NutWoods extends MapTiles {
		//constructor
		public NutWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building NutWoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[3];
			for(int a=0; a<3; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(2);
			clearing[1].setValue(4);
			clearing[2].setValue(5);

			//TODO	
		}	
	}
	
	public class Mountain extends MapTiles {
		//constructor
		public Mountain(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Mountain");
			setType("M");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			//TODO	
		}	
	}
	
	public class Caves extends MapTiles {
		//constructor
		public Caves(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Caves");
			setType("C");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			//TODO	
		}	
	}
	
	public class Ruins extends MapTiles {
		//constructor
		public Ruins(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building Ruins");
			setType("C");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[5];
			for(int a=0; a<5; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			
			//TODO	
		}	
	}
	
	public class AwfulValley extends MapTiles {
		//knight,knight,knight,knight
		GreatSwordsman [] order = new GreatSwordsman[2];//because it has a dwelling it also has natives

		//constructor
		public AwfulValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building AwfulValley");
			setType("V");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[4];
			for(int a=0; a<4; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);

			//TODO	
		}	
	}
	
	public class PineWoods extends MapTiles {
		//constructor
		public PineWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building PineWoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[3];
			for(int a=0; a<3; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(2);
			clearing[1].setValue(4);
			clearing[2].setValue(5);
			
			//TODO	
		}	
	}
	
	public class LindenWoods extends MapTiles {
		//constructor
		public LindenWoods(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building LindenWoods");
			setType("W");
			
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			//handle the clearings
			clearing = new Clearing[3];
			for(int a=0; a<3; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(2);
			clearing[1].setValue(4);
			clearing[2].setValue(5);
			
			//TODO	
		}	
	}
}
