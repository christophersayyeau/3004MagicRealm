package Model;

import Control.Player;
import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];

	public void build() {
		//starting on top of picture 4842, left to right
		
		//built a temporary mapTile object so I have access to its subclass
		MapTiles temp = new MapTiles();
		
		//first build the tiles then add to array
		Cliff cliff = temp.new Cliff(-1, -1, -1, -1, 1, 2);//only the last 2 values have tiles there
		mapTiles[0] = cliff;
		
		EvilValley evilValley = temp.new EvilValley(-1, -1, 0, 2, 6, 5);
		mapTiles[1] = evilValley;
		
		Ledges ledges = temp.new Ledges(1, 0, -1, 3, 7, 6);
		mapTiles[2] = ledges;
		
		Crag crag = temp.new Crag(2, -1, -1, 4, 8, 7);
		mapTiles[3] = crag;
		
		DarkValley darkValley = temp.new DarkValley(3, -1, -1, -1, 9, 8);
		mapTiles[4] = darkValley;
		
		HighPass highPass = temp.new HighPass(-1, -1, 1, 6, 10, -1);
		mapTiles[5] = highPass;
		
		//get this working first then handle the rest of the tiles
		System.out.println("MapTiles: " + mapTiles[0] + mapTiles[1]);
	}

	public void moveCharacters(Player player1) {
		mapTiles[player1.getCurrentLocation()].putPlayer(player1);
		//at the moment it put the character into his spot
		//this won't work when he changes tile
	}
	
	
	
	//NOTE: if there is time create a way of building the board yourself
}
