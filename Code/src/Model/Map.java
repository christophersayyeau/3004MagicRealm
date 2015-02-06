package Model;

import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];

	public void build() {
		//starting on top of picture 4842
		
		//first build the tiles then add to array
		Cliff cliff = mapTiles[0].new Cliff();
		mapTiles[0] = cliff;
		
		EvilValley evilValley = mapTiles[1].new EvilValley();
		mapTiles[1] = evilValley;
		
		Ledges ledges = mapTiles[2].new Ledges();
		mapTiles[2] = ledges;
		
		Crag crag = mapTiles[3].new Crag();
		mapTiles[3] = crag;
		
		DarkValley darkValley = mapTiles[4].new DarkValley();
		mapTiles[4] = darkValley;
		
		HighPass highPass = mapTiles[5].new HighPass();
		mapTiles[5] = highPass;
		
		//get this working first then handle the rest of the tiles
		System.out.println("MapTiles: " + mapTiles);
	}
	
	
	
	//NOTE: if there is time create a way of building the board yourself
}
