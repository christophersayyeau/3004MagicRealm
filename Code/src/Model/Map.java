package Model;

import Control.Player;
import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];

	public void build() {
		//starting on top of picture 4842
		
		//built a temporary mapTile object so I have access to its subclass
		MapTiles temp = new MapTiles();
		
		//first build the tiles then add to array
		Cliff cliff = temp.new Cliff();
		mapTiles[0] = cliff;
		
		EvilValley evilValley = temp.new EvilValley();
		mapTiles[1] = evilValley;
		
		Ledges ledges = temp.new Ledges();
		mapTiles[2] = ledges;
		
		Crag crag = temp.new Crag();
		mapTiles[3] = crag;
		
		DarkValley darkValley = temp.new DarkValley();
		mapTiles[4] = darkValley;
		
		HighPass highPass = temp.new HighPass();
		mapTiles[5] = highPass;
		
		//get this working first then handle the rest of the tiles
		System.out.println("MapTiles: " + mapTiles);
	}

	public void moveCharacters(Player player1) {
		mapTiles[player1.getCurrentLocation()].putPlayer(player1);
		//at the moment it put the character into his spot
		//this won't work when he changes tile
	}
	
	
	
	//NOTE: if there is time create a way of building the board yourself
}
