package Model;

import Control.Player;

public class MapTiles {

	//overall class, will create subclasses based on tiles
	Player playersInTile;//for know it is a single value, this will need to c\be changed

	//the intertile connections, set to garbage values
	int topLeft = -1;
	int top = -1;
	int topRight = -1;
	
	int bottomRight = -1;
	int bottom = -1;
	int bottomLeft = -1;
	


	

	//now for the individual classes
	public class HighPass extends MapTiles {
		//constructor
		public HighPass(){
			System.out.println("Building HighPass");
		}
	}
	
	public class Cliff extends MapTiles {
		//constructor
		public Cliff(){
			System.out.println("Building Cliff");
		}
	}
	
	public class EvilValley extends MapTiles {
		//constructor
		public EvilValley(){
			System.out.println("Building EvilValley");
		}
	}
	
	public class DarkValley extends MapTiles {
		//constructor
		public DarkValley(){
			System.out.println("Building DarkValley");
		}
	}
	
	public class Ledges extends MapTiles {
		//constructor
		public Ledges(){
			System.out.println("Building Ledges");
		}
	}
	
	public class Crag extends MapTiles {
		//constructor
		public Crag(){
			System.out.println("Building Crag");
		}
	}

	public void putPlayer(Player player1) {
		//this only works because we have 1 character
		playersInTile = player1;
	}
}
