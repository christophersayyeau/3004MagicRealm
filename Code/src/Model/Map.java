package Model;

import Control.Player;
import Model.MapChits.*;
import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];
	YellowChit [] warnings = new YellowChit[20];

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
		
		
		
		buildWarningChits();
		//loop to add a single warning chit to each tile
		for(int a=0; a<20; a++){
			mapTiles[a].addWarningChit(warnings[a]);
		}
		
//!!!!!!!!!!!!!Don't add anymore tiles until we have a decent game going	
		//get this working first then handle the rest of the tiles
		System.out.println("MapTiles: " + mapTiles[0] + mapTiles[1]);
	}

	
	//add values to the chits
	private void buildWarningChits() {
		MapChits temp = new MapChits();
		
		//assign the values to the warnings
	//these are the V type	
		warnings[0] = temp.new YellowChit();
		warnings[1] = temp.new YellowChit();
		warnings[2] = temp.new YellowChit();
		warnings[3] = temp.new YellowChit();
		warnings[4] = temp.new YellowChit();
	//these are the W type	
		warnings[5] = temp.new YellowChit();
		warnings[6] = temp.new YellowChit();
		warnings[7] = temp.new YellowChit();
		warnings[8] = temp.new YellowChit();
		warnings[9] = temp.new YellowChit();
	//these are the C type	
		warnings[10] = temp.new YellowChit();
		warnings[11] = temp.new YellowChit();
		warnings[12] = temp.new YellowChit();
		warnings[13] = temp.new YellowChit();
		warnings[14] = temp.new YellowChit();
	//these are the M type		
		warnings[15] = temp.new YellowChit();
		warnings[16] = temp.new YellowChit();
		warnings[17] = temp.new YellowChit();
		warnings[18] = temp.new YellowChit();
		warnings[19] = temp.new YellowChit();
		
	}

	public void moveCharacters(Player player1, int newLocation) {
		//remove from old tile
		mapTiles[player1.getCurrentLocation()].removePlayer(player1);
		
		//change the profile value
		player1.setCurrentLocation(newLocation);		
		
		//add player to new tile
		mapTiles[player1.getCurrentLocation()].putPlayer(player1);
		
		//testing
		System.out.println("Current Tile" + player1.getCurrentLocation() + " Players " + mapTiles[player1.getCurrentLocation()].getPlayers());
		//System.out.println("Current Tile 0 Players " + mapTiles[0].getPlayers());//this works puts Null errors since it works
				
	}


	
	//NOTE: if there is time create a way of building the board yourself
}
