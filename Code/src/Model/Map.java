package Model;

import Control.Player;
import Model.MapChits.*;
import Model.MapTiles.*;


public class Map {

	//an array of tiles, the values will be hardcoded in
	MapTiles [] mapTiles = new MapTiles[20];
	YellowChit [] warningsV = new YellowChit[5];
	YellowChit [] warningsW = new YellowChit[5];
	YellowChit [] warningsC = new YellowChit[5];
	YellowChit [] warningsM = new YellowChit[5];
	
	GoldChit [] sites = new GoldChit[8];
	RedChit [] sounds = new RedChit[10];
	
	LostCastle lostCastle = new LostCastle();		//put in ledges
	LostCity lostCity = new LostCity();		//put in ruins

	public void build() {
		//starting on top of picture 4842, left to right
		
		//built a temporary mapTile object so I have access to its subclass
		MapTiles temp = new MapTiles();
		buildWarningChits();
		buildSoundChits();
		buildTreasureChits();
		
		//first construct the LOST CASTLE and CITY
		lostCastle.setTreasure(0, sites[0]);
		lostCastle.setTreasure(1, sites[1]);
		lostCastle.setSound(0, sounds[0]);
		lostCastle.setSound(1, sounds[1]);
		lostCastle.setSound(2, sounds[2]);
		
		lostCity.setTreasure(0, sites[2]);
		lostCity.setTreasure(1, sites[3]);
		lostCity.setSound(0, sounds[3]);
		lostCity.setSound(1, sounds[4]);
		lostCity.setSound(2, sounds[5]);
		
		
		//build the tiles then add to array
		Cliff cliff = temp.new Cliff(-1, -1, -1, -1, 1, 2);	//only the last 2 values have tiles there
		mapTiles[0] = cliff;
		mapTiles[0].setWarning(warningsM[0]);
		mapTiles[0].setTreasure(sites[4]);
		
		EvilValley evilValley = temp.new EvilValley(-1, -1, 0, 2, 6, 5);
		mapTiles[1] = evilValley;
		mapTiles[1].setWarning(warningsV[0]);
		//System.out.println("There are 2 ghosts in the 3rd clearing");
		//all garrison natives start the game at their dwellings and dont move unless hired
		mapTiles[1].putGhosts(3);
		
		Ledges ledges = temp.new Ledges(1, 0, -1, 3, 7, 6);
		mapTiles[2] = ledges;
		mapTiles[2].setWarning(warningsM[1]);
		mapTiles[2].setLostCastle();//instead of a sound or treasure
		
		Crag crag = temp.new Crag(2, -1, -1, 4, 8, 7);
		mapTiles[3] = crag;
		mapTiles[3].setWarning(warningsM[2]);
		mapTiles[3].setSound(sounds[6]);
		
		DarkValley darkValley = temp.new DarkValley(3, -1, -1, -1, 9, 8);
		mapTiles[4] = darkValley;
		mapTiles[4].setWarning(warningsV[1]);
		mapTiles[4].setGuardHouse(3);
		
		HighPass highPass = temp.new HighPass(-1, -1, 1, 6, 10, -1);
		mapTiles[5] = highPass;
		mapTiles[5].setWarning(warningsC[0]);
		mapTiles[5].setSound(sounds[7]);
		
		
		

		
//TODO!!!!!!!!!!!!!Don't add anymore tiles until we have a decent game going			
		//get this working first then handle the rest of the tiles
		
		
		System.out.println("FINISH ADDING TILES");
	}

	
	private void buildSoundChits() {
		MapChits temp = new MapChits();
		//assign values to sounds
		sounds[0] = temp.new RedChit("HOWL", 4);	//lost castle
		sounds[1] = temp.new RedChit("FLUTTER", 1);	//lost castle
		sounds[2] = temp.new RedChit("ROAR", 6);	//lost castle
		sounds[3] = temp.new RedChit("PATTER", 2);	//lost city
		sounds[4] = temp.new RedChit("SLITHER", 3);	//lost city
		sounds[5] = temp.new RedChit("HOWL", 5);	//lost city
		sounds[6] = temp.new RedChit("FLUTTER", 2);	//crag
		sounds[7] = temp.new RedChit("PATTER", 5);	//highpass
		sounds[8] = temp.new RedChit("ROAR", 4);
		sounds[9] = temp.new RedChit("SLITHER", 6);
	}

	private void buildTreasureChits() {
		MapChits temp = new MapChits();
		
		//assign values to treasure
		sites[0] = temp.new GoldChit("STATUE", 2);	//lost castle
		sites[1] = temp.new GoldChit("HOARD", 6);	//lost castle
		sites[2] = temp.new GoldChit("ALTAR", 1);	//lost city
		sites[3] = temp.new GoldChit("LAIR", 3);	//lost city
		sites[4] = temp.new GoldChit("VAULT", 3);	//cliff
		sites[5] = temp.new GoldChit("CAIRNS", 5);
		sites[6] = temp.new GoldChit("POOL", 6);
		sites[7] = temp.new GoldChit("SHRINE", 4);
		
	}

	//add values to the chits
	private void buildWarningChits() {
		MapChits temp = new MapChits();
		
		//assign the values to the warnings
	//these are the V type	
		warningsV[0] = temp.new YellowChit("BONES");	//evilvalley
		warningsV[1] = temp.new YellowChit("DANK");		//darkvalley
		warningsV[2] = temp.new YellowChit("RUINS");
		warningsV[3] = temp.new YellowChit("SMOKE");
		warningsV[4] = temp.new YellowChit("STINK");
	//these are the W type	
		warningsW[0] = temp.new YellowChit("BONES");
		warningsW[1] = temp.new YellowChit("DANK");
		warningsW[2] = temp.new YellowChit("RUINS");
		warningsW[3] = temp.new YellowChit("SMOKE");
		warningsW[4] = temp.new YellowChit("STINK");
	//these are the C type	
		warningsC[0] = temp.new YellowChit("BONES");	//highpass
		warningsC[1] = temp.new YellowChit("DANK");
		warningsC[2] = temp.new YellowChit("RUINS");
		warningsC[3] = temp.new YellowChit("SMOKE");
		warningsC[4] = temp.new YellowChit("STINK");
	//these are the M type		
		warningsM[0] = temp.new YellowChit("BONES");	//used for cliff
		warningsM[1] = temp.new YellowChit("DANK");		//ledges
		warningsM[2] = temp.new YellowChit("RUINS");	//crag
		warningsM[3] = temp.new YellowChit("SMOKE");
		warningsM[4] = temp.new YellowChit("STINK");
		
	}

	public void moveCharacters(Player player1, int newLocation) {
		//remove from old tile
		mapTiles[player1.getCurrentLocation()].removePlayer(player1);
		
		//change the profile value
		player1.setCurrentLocation(newLocation);		
		
		//add player to new tile
		mapTiles[player1.getCurrentLocation()].putPlayer(player1);
		
		//testing
		//System.out.println("Current Tile" + player1.getCurrentLocation() + " Players " + mapTiles[player1.getCurrentLocation()].getPlayers());
		//System.out.println("Current Tile 0 Players " + mapTiles[0].getPlayers());//this works puts Null errors since it works
		//TODO only moves between tiles, need to move between clearings		
	}


	
	//NOTE: if there is time create a way of building the board yourself
}
