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
			
		}
	}
	
	
}
