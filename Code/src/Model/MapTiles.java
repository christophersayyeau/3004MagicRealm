package Model;

import Control.Player;
import Model.Clearing;
import Model.MapChits.RedChit;
import Model.MapChits.*;

public class MapTiles {
	
	Clearing [] clearing;
	
	//overall class, will create subclasses based on tiles
	Player playersInTile;//for know it is a single value, this will need to c\be changed

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
	
	

	public void putPlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = player1;
	}
	public void removePlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = null;	
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
	
	public void setGuardHouse(int i) {
		this.clearing[i-1].guardHouse = true;
		//all garrison natives start the game at their dwellings and dont move unless hired(not implemented)
		//TODO putNativeGuards();
	}


	
//lost city in one of 5 cave tiles(borderland,cavern,caves,highpass,ruins)	
//lost castle in one of 5 mountain tiles(cliff,crag,deepWoods,ledges,mountain)	
	//each one represents 5 chits determined in private class

	
	//now for the individual classes
	public class HighPass extends MapTiles {
		//constructor
		public HighPass(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building HighPass");
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
			left = l;
			topLeft = tl;
			topRight = tr;
			
			right = r;
			bottomRight = br;
			bottomLeft = bl;
			
			
			System.out.println("There are 2 ghosts in the 3rd clearing");
			//all garrison natives start the game at their dwellings and dont move unless hired
			putGhosts();
			

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
		//constructor
		public DarkValley(int l, int tl, int tr, int r, int br, int bl){
			System.out.println("Building DarkValley");
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



//TODO!!!!!!!!!!!!!Don't add anymore tiles until we have a decent game going
//for dwellings in valley
		//chapel has order in awfulvalley
		//house has soldiers in curstvalley
		//inn has rogues in badvalley
	//none of natives have any differences in first iteration
	



}
