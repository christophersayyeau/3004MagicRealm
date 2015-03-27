package Model;

import Control.Player;
import Model.Clearing;
import Model.MapChits.*;
import Model.Denizen.*;

public class MapTiles {
	
	public Clearing [] clearing;
	
	//overall class, will create subclasses based on tiles
	Player[] playersInTile = new Player[0];//for know it is a single value, this will need to c\be changed
	private PlayerDrop[] playerDrop = new PlayerDrop[0];
	public boolean isDrop = false;
	
	public Denizen [] monstersInTile = new Denizen[5];//put 5 for now, we will have to change when if it crashes
	int numMonstersInTile = 0;
	
	//each tile has 1 warning chit
	private YellowChit warning = null;
	
	//some tiles have sounds or treasure
	@SuppressWarnings("unused")
	private RedChit sound = null;
	public GoldChit treasure = null;
	
	//lost stuff will be boolean since their values are stored in map object
	boolean lostCastle = false;
	boolean lostCity = false;
	
	//the inter-tile connections, set to garbage values
	int topLeft = -1;		int topRight = -1;						
	int left = -1;			int right = -1;							
	int bottomLeft = -1;	int bottomRight = -1;
	
	String type = "";//use this to determine whether mountain, woods, etc

	//the natives
	public GreatSwordsman[] guard;
	public GreatSwordsman[] order;
	public GreatSwordsman[] rogues;
	public GreatSwordsman[] soldiers;
	public Ghost[] ghosts;
	
	private boolean ghostTile = false;//use for cheat mode regeneration
	
	public boolean isGhostTile() {
		return ghostTile;
	}
	public void setGhostTile(boolean ghostTile) {
		this.ghostTile = ghostTile;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void putPlayerDrop(PlayerDrop drop) {
		//playersInTile = player1;
		playerDrop = ArrayUtils.add(playerDrop, drop);
		this.isDrop = true;
	}
	
	public void putPlayer(Player player1) {
		//playersInTile = player1;
		playersInTile = ArrayUtils.add(playersInTile, player1);
	}
	public void removePlayer(Player player1) {
		//playersInTile = null;	
		playersInTile = (Player[]) ArrayUtils.remove(playersInTile, ArrayUtils.indexOf(player1, playersInTile));
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
	
	public void setGuardHouse(int i, MapTiles tile) {
		this.clearing[i-1].guardHouse = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeGuards(tile);
	}
	private void putNativeGuards(MapTiles tile) {
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

	public void setHouse(int i, MapTiles valley) {
		this.clearing[i-1].house = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeSoldiers(valley);
	}
	private void putNativeSoldiers(MapTiles tile) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		tile.soldiers[0] = temp.new GreatSwordsman("Soldier");
		tile.soldiers[1] = temp.new GreatSwordsman("Soldier");
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
	
	public void setInn(int i, MapTiles valley) {
		this.clearing[i-1].inn = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeRogues(valley);
	}
	private void putNativeRogues(MapTiles valley) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		valley.rogues[0] = temp.new GreatSwordsman("Rogue");
		valley.rogues[1] = temp.new GreatSwordsman("Rogue");
		//tile.soldiers[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		valley.rogues[0].setStartClearing(3);
		valley.rogues[1].setStartClearing(3);	
		//tile.soldiers[2].setStartClearing(3);
		
		valley.rogues[0].setCurrentClearing(3);
		valley.rogues[1].setCurrentClearing(3);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(valley.rogues[0]);
		clearing[3].putDenizen(valley.rogues[1]);
		//clearing[3].putDenizen(tile.soldiers[2]);	
	}
	
	public void setChapel(int i, MapTiles valley) {
		this.clearing[i-1].chapel = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putNativeOrder(valley);
	}
	private void putNativeOrder(MapTiles valley) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		valley.order[0] = temp.new GreatSwordsman("Order");
		valley.order[1] = temp.new GreatSwordsman("Order");
		//tile.soldiers[2] = temp.new GreatSwordsman("Guard");
		
		//set to their start clearing
		valley.order[0].setStartClearing(3);
		valley.order[1].setStartClearing(3);	
		//tile.soldiers[2].setStartClearing(3);
		
		valley.order[0].setCurrentClearing(3);
		valley.order[1].setCurrentClearing(3);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(valley.order[0]);
		clearing[3].putDenizen(valley.order[1]);
		//clearing[3].putDenizen(tile.soldiers[2]);	
	}

	
	public void setGhosts( int i, MapTiles valley) {
		//this.clearing[i-1].ghosts = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		putGhosts(valley, i-1);
	}
	private void putGhosts(MapTiles valley, int clearin) {
		//THere are 3 guards
		Denizen temp = new Denizen();
		valley.ghosts[0] = temp.new Ghost();
		valley.ghosts[1] = temp.new Ghost();
				
		//set to their start clearing
		valley.ghosts[0].setStartClearing(clearin);
		valley.ghosts[1].setStartClearing(clearin);	
				
		valley.ghosts[0].setCurrentClearing(clearin);
		valley.ghosts[1].setCurrentClearing(clearin);
		//tile.soldiers[2].setCurrentClearing(3);	
		
		clearing[3].putDenizen(valley.ghosts[0]);
		clearing[3].putDenizen(valley.ghosts[1]);
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
			
			clearing[0].getConnectedTo()[0] = 144;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[1] = 145;//clearing 1 connected to 5
		clearing[1].getConnectedTo()[0] = 111;//clearing 2 connected to 1 in tile 11
			clearing[1].getConnectedTo()[1] = 144;//clearing 2 connected to 4			
		clearing[2].getConnectedTo()[0] = 125;//clearing 3 connected to 5 in tile 12
			clearing[2].getConnectedTo()[1] = 146;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 141;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[1] = 142;//clearing 4 connected to 2
			clearing[4].getConnectedTo()[0] = 141;//clearing 5 connected to 1
			clearing[5].getConnectedTo()[0] = 143;//clearing 6 connected to 3
		clearing[5].getConnectedTo()[1] = 53;//clearing 6 connected 3 in tile 5
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
			
			clearing[0].getConnectedTo()[0] = 166;//clearing 1 connected to 6
		clearing[0].getConnectedTo()[1] = 52;//clearing 1 connected to clearing 2 in tile 5
			clearing[1].getConnectedTo()[0] = 163;//clearing 2 connected to 3
		clearing[1].getConnectedTo()[1] = 193;//clearing 2 connected to 3 in tile 19	
			clearing[1].getConnectedTo()[2] = 165;//clearing 2 connected to 5
			clearing[2].getConnectedTo()[0] = 162;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 165;//clearing 3 connected to 5
			clearing[2].getConnectedTo()[2] = 166;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 166;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 163;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[1] = 162;//clearing 5 connected to 2
			clearing[5].getConnectedTo()[0] = 161;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 164;//clearing 6 connected to 4		
			clearing[5].getConnectedTo()[2] = 163;//clearing 6 connected to 3
		}
		
	}
	
	public class EvilValley extends MapTiles {
		//public Ghost [] ghosts = new Ghost[2];
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
			clearing[2].setValue(4);
			clearing[3].setValue(5);
			
			clearing[0].getConnectedTo()[0] = 54;//clearing 1 connected to 4
		clearing[1].getConnectedTo()[0] = 161;//clearing 2 connected to 1 in tile 16
			clearing[1].getConnectedTo()[1] = 55;//clearing 2 connected to 5			
		clearing[2].getConnectedTo()[0] = 112;//clearing 4 connected to 2 in tile 11
			clearing[2].getConnectedTo()[1] = 51;//clearing 4 connected to 1
		clearing[2].getConnectedTo()[2] = 192;//clearing 4 connected to 2 in tile 19
		clearing[3].getConnectedTo()[0] = 146;//clearing 5 connected to 6 in tile 14
			clearing[3].getConnectedTo()[1] = 52;//clearing 5 connected to 2
				
		}
	}
	
	public class DarkValley extends MapTiles {
		//GreatSwordsman [] guard = new GreatSwordsman[2];//because it has a dwelling it also has natives

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
			clearing[2].setValue(4);
			clearing[3].setValue(5);
			
			clearing[0].getConnectedTo()[0] = 44;//clearing 1 connected to 4
		clearing[0].getConnectedTo()[1] = 31;//clearing 1 connected to 1 in tile 3
			clearing[1].getConnectedTo()[0] = 45;//clearing 2 connected to 5
			clearing[2].getConnectedTo()[0] = 41;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[0] = 42;//clearing 5 connected to 2	
			clearing[3].getConnectedTo()[1] = 182;//clearing 5 connected to 2 in tile 18
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
			
			clearing[0].getConnectedTo()[0] = 194;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[1] = 196;//clearing 1 connected to 6
			clearing[0].getConnectedTo()[2] = 193;//clearing 1 connected to 3
		clearing[1].getConnectedTo()[0] = 54;//clearing 2 connected to 4 in tile 5
			clearing[1].getConnectedTo()[1] = 195;//clearing 2 connected to 5
		clearing[2].getConnectedTo()[0] = 162;//clearing 3 connected to 2 in tile 16
			clearing[2].getConnectedTo()[1] = 196;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 191;//clearing 3 connected to 1
			clearing[3].getConnectedTo()[0] = 191;//clearing 4 connected to 1
		clearing[3].getConnectedTo()[1] = 114;//clearing 4 connected to 4 in tile 11
			clearing[3].getConnectedTo()[2] = 196;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 192;//clearing 5 connected to 2
		clearing[4].getConnectedTo()[1] = 92;//clearing 5 connected to 2 in tile 9
			clearing[5].getConnectedTo()[0] = 191;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 193;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[2] = 194;//clearing 6 connected to 4
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
			
			clearing[0].getConnectedTo()[0] = 174;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[1] = 176;//clearing 1 connected to 6
		clearing[1].getConnectedTo()[0] = 181;//clearing 2 connected to 1 in tile 18
			clearing[1].getConnectedTo()[1] = 175;//clearing 2 connected to 5
			clearing[1].getConnectedTo()[2] = 173;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 175;//clearing 3 connected to 5
			clearing[2].getConnectedTo()[1] = 176;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 172;//clearing 3 connected to 2
			clearing[3].getConnectedTo()[0] = 171;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[1] = 176;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 172;//clearing 5 connected to 2
			clearing[4].getConnectedTo()[1] = 173;//clearing 5 connected to 3
			clearing[5].getConnectedTo()[0] = 173;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 174;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 171;//clearing 6 connected to 1
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
			
		clearing[0].getConnectedTo()[0] = 142;//clearing 1 connected to 2 in tile 14
		clearing[0].getConnectedTo()[1] = 25;//clearing 1 connected to 5 in tile 2
			clearing[0].getConnectedTo()[2] = 116;//clearing 1 is connected to 6 
		clearing[1].getConnectedTo()[0] = 54;//clearing 2 connected to 4 in tile 5
		clearing[1].getConnectedTo()[1] = 92;//clearing 2 connected to 2 in tile 9
			clearing[1].getConnectedTo()[2] = 113;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 112;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 116;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 115;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 115;//clearing 4 connected to 5
			clearing[3].getConnectedTo()[1] = 116;//clearing 4 connected to 6
		clearing[3].getConnectedTo()[2] = 194;//clearing 4 connected to 4 in tile 19
		clearing[4].getConnectedTo()[0] = 122;//clearing 5 connected to 2 in tile 12
			clearing[4].getConnectedTo()[1] = 113;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[2] = 114;//clearing 5 connected to 4
			clearing[5].getConnectedTo()[0] = 113;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 114;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 111;//clearing 6 connected to 1
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

		clearing[0].getConnectedTo()[0] = 195;//clearing 2 connected to 5 in tile 19
		clearing[0].getConnectedTo()[1] = 112;//clearing 2 connected to 2 in tile 11
			clearing[0].getConnectedTo()[2] = 94;//clearing 2 connected to 4
		clearing[1].getConnectedTo()[0] = 181;//clearing 4 connected to 9 in tile 18
			clearing[1].getConnectedTo()[1] = 92;//clearing 4 connected to 5
		clearing[2].getConnectedTo()[0] = 21;//clearing 5 connected to 1 in tile 2
		clearing[2].getConnectedTo()[1] = 75;//clearing 5 connected to 5 in tile 7
				
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
			
		clearing[0].getConnectedTo()[0] = 172;//clearing 1 connected to 2 in tile 17
		clearing[0].getConnectedTo()[1] = 94;//clearing 1 connected to 4 in tile 9
			clearing[0].getConnectedTo()[2] = 184; //clearing 1 connected to 4
			clearing[0].getConnectedTo()[3] = 186;//clearing 1 connected to 6
		clearing[1].getConnectedTo()[0] = 45;//clearing 2 connected to 5 in tile 4
		clearing[1].getConnectedTo()[1] = 32;	//clearing 2 connected to 2 in tile 3
			clearing[1].getConnectedTo()[2] = 183;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 182;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 186;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 185;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 185;//clearing 4 connected to 5
			clearing[3].getConnectedTo()[1] = 186;//clearing 4 connected to 6
			clearing[3].getConnectedTo()[2] = 181;//clearing 4 connected to 1
		clearing[4].getConnectedTo()[0] = 75;//clearing 5 connected to 5 in tile 7
			clearing[4].getConnectedTo()[1] = 183;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[2] = 184;//clearing 5 connected to 4
			clearing[5].getConnectedTo()[0] = 183;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 184;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 181;//clearing 6 connected to 1
			
		}	
	}
	
	public class CurstValley extends MapTiles {
		//greatswordsman,pikeman,pikeman,crossbowman
		//GreatSwordsman [] soldiers = new GreatSwordsman[2];//because it has a dwelling it also has natives

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
			clearing[2].setValue(4);
			clearing[3].setValue(5);

		clearing[0].getConnectedTo()[0] = 41;//clearing 1 connected to 1 in tile 4
			clearing[0].getConnectedTo()[1] = 34;//clearing 1 connected to 4
			clearing[1].getConnectedTo()[0] = 35;//clearing 2 connected to 5
		clearing[1].getConnectedTo()[1] = 182;	//clearing 2 connected to 2 in tile 18
		clearing[2].getConnectedTo()[0] = 85;//clearing 4 connected to 5 in tile 8
			clearing[2].getConnectedTo()[1] = 31;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[0] = 32;//clearing 5 connected to 2

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
			
		clearing[0].getConnectedTo()[0] = 24;//clearing 1 connected to 4 in tile 2
			clearing[0].getConnectedTo()[1] = 123;//clearing 1 connected to 3
			clearing[0].getConnectedTo()[2] = 124; //clearing 1 connected to 4
		clearing[1].getConnectedTo()[0] = 115;//clearing 2 connected to 5 in tile 11
			clearing[1].getConnectedTo()[1] = 123;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 122;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 126;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[2] = 125;//clearing 3 connected to 5
			clearing[2].getConnectedTo()[3] = 121;//clearing 3 connected to 1
			clearing[3].getConnectedTo()[0] = 125;//clearing 4 connected to 5
			clearing[3].getConnectedTo()[1] = 126;//clearing 4 connected to 6
			clearing[3].getConnectedTo()[2] = 121;//clearing 4 connected to 1
		clearing[4].getConnectedTo()[0] = 143;//clearing 5 connected to 3 in tile 14
			clearing[4].getConnectedTo()[1] = 123;//clearing 5 connected to 3
			clearing[4].getConnectedTo()[2] = 124;//clearing 5 connected to 4
			clearing[5].getConnectedTo()[0] = 123;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 124;//clearing 6 connected to 4
				
		}	
	}
	
	public class BadValley extends MapTiles {
		//assassin,greatAxeman,greataxeman,s.swordsman,archer,assasin,swordsman,swordsman
		//GreatSwordsman [] rogues = new GreatSwordsman[2];//because it has a dwelling it also has natives

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
			clearing[2].setValue(4);
			clearing[3].setValue(5);
			
		clearing[0].getConnectedTo()[0] = 95;//clearing 1 connected to 5 in tile 9
			clearing[0].getConnectedTo()[1] = 24;//clearing 1 connected to 4
			clearing[1].getConnectedTo()[0] = 25;//clearing 2 connected to 5
		clearing[1].getConnectedTo()[1] = 132;//clearing 2 connected to 2 in tile 13
		clearing[2].getConnectedTo()[0] = 121;//clearing 4 connected to 1 in tile 12
		clearing[2].getConnectedTo()[1] = 205;//clearing 4 connected to 5 in tile 20
			clearing[3].getConnectedTo()[0] = 22;//clearing 5 connected to 2
		clearing[3].getConnectedTo()[1] = 111;//clearing 5 connected to 1 in tile 11
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

		clearing[0].getConnectedTo()[0] = 85;//clearing 2 connected to 5 in tile 8
			clearing[0].getConnectedTo()[1] = 74;//clearing 2 connected to 4
		clearing[0].getConnectedTo()[2] = 155;//clearing 2 connected to 5 in tile 15
			clearing[1].getConnectedTo()[0] = 72;//clearing 4 connected to 2
		clearing[1].getConnectedTo()[1] = 135;//clearing 4 connected to 5 in tile 13
		clearing[2].getConnectedTo()[0] = 85;//clearing 5 connected to 5 in tile 8
		clearing[2].getConnectedTo()[1] = 95;//clearing 5 connected to 5 in tile 9
			
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

		clearing[0].getConnectedTo()[0] = 15;//clearing 2 connected to 5 in tile 1
			clearing[0].getConnectedTo()[1] = 84;//clearing 2 connected to 4
			clearing[1].getConnectedTo()[0] = 82;//clearing 4 connected to 2
		clearing[1].getConnectedTo()[1] = 151;//clearing 4 connected to 1 in tile 15
		clearing[2].getConnectedTo()[0] = 34;//clearing 5 connected to 4 in tile 3
		clearing[2].getConnectedTo()[1] = 72;//clearing 5 connected to 2 in tile 7
		
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
			
			clearing[0].getConnectedTo()[0] = 203;//clearing 1 connected to 3
			clearing[1].getConnectedTo()[0] = 204;//clearing 2 connected to 4
			clearing[1].getConnectedTo()[1] = 205;//clearing 2 connected to 5
		clearing[1].getConnectedTo()[2] = 104;//clearing 2 connected to 4 in tile 10
			clearing[2].getConnectedTo()[0] = 201;//clearing 3 connected to 1
			clearing[2].getConnectedTo()[1] = 206;//clearing 3 connected to 6
			clearing[3].getConnectedTo()[0] = 202;//clearing 4 connected to 2
			clearing[3].getConnectedTo()[1] = 206;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 202;//clearing 5 connected to 2
			clearing[4].getConnectedTo()[1] = 206;//clearing 5 connected to 6
		clearing[4].getConnectedTo()[2] = 24;//clearing 5 connected to 4 in tile 2
			clearing[5].getConnectedTo()[0] = 203;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 204;//clearing 6 connected to 4
			clearing[5].getConnectedTo()[2] = 205;//clearing 6 connected to 5
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
			
			clearing[0].getConnectedTo()[0] = 136;//clearing 1 connected to 6
		clearing[0].getConnectedTo()[1] = 105;//clearing 1 connected to 5 in tile 10
			clearing[1].getConnectedTo()[0] = 134;//clearing 2 connected to 4
			clearing[1].getConnectedTo()[1] = 133;//clearing 2 connected to 3
		clearing[1].getConnectedTo()[2] = 22;//clearing 2 connected to 2 in tile 2
			clearing[2].getConnectedTo()[0] = 132;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 135;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 132;//clearing 4 connected to 2
			clearing[3].getConnectedTo()[1] = 136;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 133;//clearing 5 connected to 3
		clearing[4].getConnectedTo()[1] = 74;//clearing 5 connected to 4 in tile 7
			clearing[5].getConnectedTo()[0] = 131;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 134;//clearing 6 connected to 4
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
			clearing = new Clearing[6];
			for(int a=0; a<6; a++)			clearing[a] = new Clearing();
			
			//number valueof each clearing
			clearing[0].setValue(1);
			clearing[1].setValue(2);
			clearing[2].setValue(3);
			clearing[3].setValue(4);
			clearing[4].setValue(5);
			clearing[5].setValue(6);
			
			clearing[0].getConnectedTo()[0] = 152;//clearing 1 connected to 2
			clearing[0].getConnectedTo()[1] = 154;//clearing 1 connected to 4
			clearing[0].getConnectedTo()[2] = 155;//clearing 1 connected to 5
		clearing[0].getConnectedTo()[3] = 84;//clearing 1 connected to 4 in tile 8
			clearing[1].getConnectedTo()[0] = 151;//clearing 2 connected to 1
		clearing[1].getConnectedTo()[1] = 11;//clearing 2 connected to 1 in tile 1
		clearing[1].getConnectedTo()[2] = 64;//clearing 2 connected to 4 in tile 6
			clearing[2].getConnectedTo()[0] = 156;//clearing 3 connected to 6
			clearing[2].getConnectedTo()[1] = 155;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 151;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[1] = 156;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 153;//clearing 5 connected to 3
		clearing[4].getConnectedTo()[1] = 72;//clearing 5 connected to 2 in tile 7
			clearing[4].getConnectedTo()[2] = 151;//clearing 5 connected to 1 
			clearing[5].getConnectedTo()[0] = 153;//clearing 6 connected to 3
			clearing[5].getConnectedTo()[1] = 154;//clearing 6 connected to 4
		}	
	}
	
	public class AwfulValley extends MapTiles {
		//knight,knight,knight,knight
		//GreatSwordsman [] order = new GreatSwordsman[2];//because it has a dwelling it also has natives

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
			clearing[2].setValue(4);
			clearing[3].setValue(5);

		clearing[0].getConnectedTo()[0] = 152;//clearing 1 connected to 2 in tile 15
			clearing[0].getConnectedTo()[1] = 14;//clearing 1 connected to 4
			clearing[1].getConnectedTo()[0] = 15;//clearing 2 connected to 5
		clearing[1].getConnectedTo()[1] = 65;//clearing 2 connected to 5 in tile 6
			clearing[2].getConnectedTo()[0] = 11;//clearing 4 connected to 1
			clearing[3].getConnectedTo()[0] = 12;//clearing 5 connected to 2
		clearing[3].getConnectedTo()[1] = 82;//clearing 5 connected to 2 in tile 8
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
			
			clearing[0].getConnectedTo()[0] = 104;//clearing 2 connected to 4
			clearing[1].getConnectedTo()[0] = 102;//clearing 4 connected to 2
		clearing[1].getConnectedTo()[1] = 202;//clearing 4 connected to 2 in tile 20
		clearing[2].getConnectedTo()[0] = 131;//clearing 5 connected to 1 in tile 13
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
			
			clearing[0].getConnectedTo()[0] = 64;//clearing 2 connected to 4
			clearing[1].getConnectedTo()[0] = 62;//clearing 4 connected to 2
		clearing[1].getConnectedTo()[1] = 152;//clearing 4 connected to 2 in tile 15
		clearing[2].getConnectedTo()[0] = 12;//clearing 5 connected to 2 in tile 1	
		
		}	
	}

	public void setSoundTreasureCheat(MapChits mapChit) {
		
		//will receive null from valleys and woods
		if( mapChit == null){
			//nothing should happen since woods and valleys get nothing		
		
		//will receive goldChit for treasure
		}else if(mapChit.getClass() == GoldChit.class){
			//meaning this is a treasure
			this.setTreasure((GoldChit) mapChit);
		
		//will receive redCHit for sound	
		}else if(mapChit.getClass() == RedChit.class){
			//meaning that this is a sound
			this.setSound((RedChit) mapChit);
		
		//will receive yellow for losts or error	
		}else if(mapChit.getClass() == YellowChit.class){
			//meaning that this is losts or errors
		
			//add them to the tile
			if(mapChit.type.compareTo("LOSTCITY") == 0){
				this.setLostCity();
				
			}else if(mapChit.type.compareTo("LOSTCASTLE") == 0){
				this.setLostCastle();//this sets it to true
							
				//errors
			}else if(mapChit.type.compareTo("Major Error") == 0){
				System.out.println("Error Couldnt identify type in GUI");
			}else{
				System.out.println("CAN'T IDENTIFY THE GOLDEN CHIT");
			}
		
		}else{
			System.out.println("CANT IDENTIFY CLASS TYPE IN MAPTILES");
		}
	}
}
