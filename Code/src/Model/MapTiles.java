package Model;

import Control.Player;

public class MapTiles {

	//overall class, will create subclasses based on tiles
	Player playersInTile;//for know it is a single value, this will need to c\be changed

	//the intertile connections, set to garbage values
	int topLeft = -1;		int topRight = -1;						
	int left = -1;			int right = -1;							
	int bottomLeft = -1;	int bottomRight = -1;
	
	public void putPlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = player1;
	}

	

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

	
	
	

}
