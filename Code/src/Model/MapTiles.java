package Model;

public class MapTiles {

	//overall class, will create subclasses based on tiles


	//the intertile connections
	MapTiles topLeft = new MapTiles();
	MapTiles top = new MapTiles();
	MapTiles topRight = new MapTiles();
	
	MapTiles bottomRight = new MapTiles();
	MapTiles bottom = new MapTiles();
	MapTiles bottomLeft = new MapTiles();
	

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
}
