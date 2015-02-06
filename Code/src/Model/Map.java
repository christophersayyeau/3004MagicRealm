package Model;

import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];

	public void build() {
		//starting on left of picture
		
		//first build the tiles then add to array
		HighPass highPass = mapTiles[0].new HighPass();
		mapTiles[0] = highPass;
		
		
		
	}
	
	
	
	//NOTE: if there is time create a way of building the board yourself
}
