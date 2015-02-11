package Model;

import Control.Player;
import Model.Clearing;
import Model.MapChits.*;

public class MapTiles {
	
	Clearing [] clearing;
	
	//overall class, will create subclasses based on tiles
	Player playersInTile;//for know it is a single value, this will need to c\be changed

	//each tile has 1 warning chit
	YellowChit warning;
	
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
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
//for dwellings in valley
		//chapel has order in awfulvalley
		//house has soldiers in curstvalley
		//inn has rogues in badvalley
	
//lost city in one of 5 cave tiles(borderland,cavern,caves,highpass,ruins)	
//lost castle in one of 5 mountain tiles(cliff,crag,deepWoods,ledges,mountain)	
	//each one represents 5 chits determined elsewhere
	
	
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
			clearing[1].getConnectedTo()[0] = 3;//clearing 2 connected to 3
			clearing[2].getConnectedTo()[0] = 2;//clearing 3 connected to 2
			clearing[2].getConnectedTo()[1] = 5;//clearing 3 connected to 5
			clearing[3].getConnectedTo()[0] = 6;//clearing 4 connected to 6
			clearing[4].getConnectedTo()[0] = 3;//clearing 5 connected to 3
			clearing[5].getConnectedTo()[0] = 1;//clearing 6 connected to 1
			clearing[5].getConnectedTo()[1] = 4;//clearing 6 connected to 4
			
	//!!there should be connections to the clearings in other tiles
			
//			//show the clearing info
//			for(int a=0; a<6; a++){
//				System.out.println("Clearing " + clearing[a].getValue());
//			}
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
			//all garrison natives start the game at their dwellings and dont move unless hred
			putGhosts;
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

			System.out.println("There is a Guard House in the 3rd clearing");
			putGuardHouse;
			putNativeGuards;//all garrison natives start the game at their dwellings and dont move unless hred
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
		}
	}


//!!!!!!!!!!!!!Don't add anymore tiles until we have a decent game going




}
