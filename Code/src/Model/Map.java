package Model;

import Control.Player;
import Model.Denizen.Ghost;
import Model.MapChits.GoldChit;
import Model.MapTiles.DarkValley;
import Model.MapTiles.EvilValley;
import Model.MapChits.*;
import Model.MapTiles.*;
import View.GUI;

public class Map {
	GUI view;

	//an array of tiles, the values will be hardcoded in
	private MapTiles [] mapTiles = new MapTiles[20];
	YellowChit [] warningsV = new YellowChit[5];
	YellowChit [] warningsW = new YellowChit[5];
	YellowChit [] warningsC = new YellowChit[5];
	YellowChit [] warningsM = new YellowChit[5];
	
	GoldChit [] sites = new GoldChit[8];
	RedChit [] sounds = new RedChit[10];
	
	LostCastle lostCastle = new LostCastle();		//put in ledges
	LostCity lostCity = new LostCity();				//put in ruins
	
	//Ghost [] ghosts = new Ghost[2];
	
	public Map(GUI v){
		view = v;
	}

	public MapTiles [] getMapTiles() {
		return mapTiles;
	}
	public MapTiles getMapTile(int a) {
		return mapTiles[a];
	}
	public void setMapTiles(MapTiles [] mapTiles) {
		this.mapTiles = mapTiles;
	}


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
		AwfulValley awfulValley = temp.new AwfulValley(14, 7, -1, -1, -1, 5);
		getMapTiles()[0] = awfulValley;
		getMapTiles()[0].setWarning(warningsV[4]);
		getMapTiles()[0].setChapel(3, (AwfulValley) getMapTiles()[17]);//CHRIS NEEDS TO FIGURE THIS OUT THIS OUT SHOULD BE ON LARGEST CLEARING
		view.buildBuildings("Chapel", 1, 4);
		
		BadValley badValley = temp.new BadValley(11, 10, 8, 6, 12, 19);
		getMapTiles()[1] = badValley;
		getMapTiles()[1].setWarning(warningsV[3]);
		getMapTiles()[1].setInn(3, (BadValley) getMapTiles()[11]);//CHRIS NEEDS TO FIGURE THIS OUT THIS OUT SHOULD BE ON LARGEST CLEARING
		view.buildBuildings("Inn", 2, 4);
		
		CurstValley curstValley = temp.new CurstValley(17, 3, -1, -1, -1, 7);
		getMapTiles()[2] = curstValley;
		getMapTiles()[2].setWarning(warningsV[2]);
		getMapTiles()[2].setHouse(3, (CurstValley) getMapTiles()[9]);//CHRIS NEEDS TO FIGURE THIS OUT THIS OUT SHOULD BE ON LARGEST CLEARING
		view.buildBuildings("House", 3, 4);
		
		DarkValley darkValley = temp.new DarkValley(16, -1, -1, -1, 2, 17);
		getMapTiles()[3] = darkValley;
		getMapTiles()[3].setWarning(warningsV[1]);
		getMapTiles()[3].setGuardHouse(3, (DarkValley) getMapTiles()[4]);//CHRIS NEEDS TO FIGURE THIS OUT THIS OUT SHOULD BE ON LARGEST CLEARING
		view.buildBuildings("GuardHouse", 4, 4);
		
		EvilValley evilValley = temp.new EvilValley(-1, -1, 15, 18, 10, 13);
		getMapTiles()[4] = evilValley;
		getMapTiles()[4].setWarning(warningsV[0]);
		//all garrison natives start the game at their dwellings and dont move unless hired
		//buildGhosts();//there are 2 ghosts
		//putGhostsAtStartPositions();
		//resetGhosts();//put in start positions
		//mapTiles[1].putGhosts(3);
		getMapTiles()[4].setGhosts(3, (EvilValley) getMapTiles()[4]);
		
		LindenWoods lindenWoods = temp.new LindenWoods(-1, 14, 0, -1, -1, -1);
		getMapTiles()[5] = lindenWoods;
		getMapTiles()[5].setWarning(warningsW[4]);
		
		MapleWoods mapleWoods = temp.new MapleWoods(1, 8, 17, 7, 14, 12);
		getMapTiles()[6] = mapleWoods;
		getMapTiles()[6].setWarning(warningsW[1]);
		
		NutWoods nutWoods = temp.new NutWoods(6, 17, 2, -1, 0, 14);
		getMapTiles()[7] = nutWoods;
		getMapTiles()[7].setWarning(warningsW[2]);

		OakWoods oakWoods = temp.new OakWoods(10, 18, 16, 17, 6, 1);
		getMapTiles()[8] = oakWoods;
		getMapTiles()[8].setWarning(warningsW[0]);
		
		PineWoods pineWoods = temp.new PineWoods(-1, 19, 12, -1, -1, -1);
		getMapTiles()[9] = pineWoods;
		getMapTiles()[9].setWarning(warningsW[3]);				
		
		BorderLand borderLand = temp.new BorderLand(13, 4, 18, 8, 1, 11);
		getMapTiles()[10] = borderLand;
		getMapTiles()[10].setWarning(warningsC[1]);
		getMapTiles()[10].setSound(sounds[8]);
		
		Cavern cavern = temp.new Cavern(-1, 13, 10, 1, 19, -1);
		getMapTiles()[11] = cavern;
		getMapTiles()[11].setWarning(warningsC[2]);
		getMapTiles()[11].setTreasure(sites[6]);
		
		Caves caves = temp.new Caves(19, 1, 6, 14, -1, 9);
		getMapTiles()[12] = caves;
		getMapTiles()[12].setWarning(warningsC[3]);
		getMapTiles()[12].setSound(sounds[9]);
		
		HighPass highPass = temp.new HighPass(-1, -1, 4, 10, 11, -1);
		getMapTiles()[13] = highPass;
		getMapTiles()[13].setWarning(warningsC[0]);
		getMapTiles()[13].setSound(sounds[7]);
		
		Ruins ruins = temp.new Ruins(12, 6, 7, 0, 5, -1);
		getMapTiles()[14] = ruins;
		getMapTiles()[14].setWarning(warningsC[4]);
		getMapTiles()[14].setLostCity();//instead of treasure or sound
	
		Cliff cliff = temp.new Cliff(-1, -1, -1, -1, 18, 4);	//only the last 2 values have tiles there
		getMapTiles()[15] = cliff;
		getMapTiles()[15].setWarning(warningsM[0]);
		getMapTiles()[15].setTreasure(sites[4]);
		
		Crag crag = temp.new Crag(18, -1, -1, 3, 17, 8);
		getMapTiles()[16] = crag;
		getMapTiles()[16].setWarning(warningsM[2]);
		getMapTiles()[16].setSound(sounds[6]);
		
		DeepWoods deepWoods = temp.new DeepWoods(8, 16, 3, 2, 7, 6);
		getMapTiles()[17] = deepWoods;
		getMapTiles()[17].setWarning(warningsM[3]);
		getMapTiles()[17].setTreasure(sites[5]);
		
		Ledges ledges = temp.new Ledges(4, 15, -1, 16, 8, 10);
		getMapTiles()[18] = ledges;
		getMapTiles()[18].setWarning(warningsM[1]);
		getMapTiles()[18].setLostCastle();//instead of a sound or treasure
		
		Mountain mountain = temp.new Mountain(-1, 11, 1, 12, 9, -1);
		getMapTiles()[19] = mountain;
		getMapTiles()[19].setWarning(warningsM[4]);
		getMapTiles()[19].setTreasure(sites[7]);
		System.out.println("FINISH ADDING TILES");
	}
	
/*	private void buildGhosts() {
		Denizen temp = new Denizen();
		ghosts[0] = temp.new Ghost();
		ghosts[1] = temp.new Ghost();
		
		//set to their start clearing
		ghosts[0].setStartClearing(3);
		ghosts[1].setStartClearing(3);
	}*/

/*	private void putGhostsAtStartPositions() {
		//since they can't leave the tile we will add them here
		getMapTiles()[1].putDenizen(ghosts[0]);
		getMapTiles()[1].putDenizen(ghosts[1]);
		//mapTiles[1].clearing[0].putDenizen(ghosts[0]);
		//mapTiles[1].clearing[0].putDenizen(ghosts[1]);
		ghosts[0].setCurrentClearing(0);
		ghosts[1].setCurrentClearing(0);
		
		moveDenizen(ghosts[0], 3, 2);//second tile, 3rd clearing
		moveDenizen(ghosts[1], 3, 2);//second tile, 3rd clearing	
	}*/
	private void resetGhosts(){

		//if(ghosts[0] != null){
			getMapTiles()[1].removeDenizen(((EvilValley) getMapTiles()[1]).ghosts[0]);
			getMapTiles()[1].clearing[3].removeDenizen(((EvilValley) getMapTiles()[1]).ghosts[0]);		
			//getMapTiles()[1].clearing[3]
		//}
	//	if(ghosts[1] != null){
			getMapTiles()[1].removeDenizen(((EvilValley) getMapTiles()[1]).ghosts[1]);
			getMapTiles()[1].clearing[3].removeDenizen(((EvilValley) getMapTiles()[1]).ghosts[1]);
	//	}
		
		//now put them back in
		getMapTiles()[1].setGhosts(3, (EvilValley) getMapTiles()[1]);//handled in MapTiles

		/*		
		if(ghosts[0] != null) getMapTiles()[1].removeDenizen(ghosts[0]);
		if(ghosts[1] != null) getMapTiles()[1].removeDenizen(ghosts[1]);
		//if(ghosts[0].equals(null)) getMapTiles()[1].removeDenizen(ghosts[0]);
		//if(ghosts[1].equals(null)) getMapTiles()[1].removeDenizen(ghosts[1]);
		
		//ghosts[0] = null;
		//ghosts[1] = null;
		buildGhosts();		
		
		putGhostsAtStartPositions();*/
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
		sounds[8] = temp.new RedChit("ROAR", 4);	//borderland
		sounds[9] = temp.new RedChit("SLITHER", 6);	//caves
	}

	private void buildTreasureChits() {
		MapChits temp = new MapChits();
		
		//assign values to treasure
		sites[0] = temp.new GoldChit("STATUE", 2);	//lost castle
		sites[1] = temp.new GoldChit("HOARD", 6);	//lost castle
		sites[2] = temp.new GoldChit("ALTAR", 1);	//lost city
		sites[3] = temp.new GoldChit("LAIR", 3);	//lost city
		sites[4] = temp.new GoldChit("VAULT", 3);	//cliff
		sites[5] = temp.new GoldChit("CAIRNS", 5);	//deepWoods
		sites[6] = temp.new GoldChit("POOL", 6);	//cavern
		sites[7] = temp.new GoldChit("SHRINE", 4);	//mountain
		
	}

	//add values to the chits
	private void buildWarningChits() {
		MapChits temp = new MapChits();
		
		//assign the values to the warnings
	//these are the V type	
		warningsV[0] = temp.new YellowChit("BONES");	//evilvalley
		warningsV[1] = temp.new YellowChit("DANK");		//darkvalley
		warningsV[2] = temp.new YellowChit("RUINS");	//curstValley
		warningsV[3] = temp.new YellowChit("SMOKE");	//badvalley
		warningsV[4] = temp.new YellowChit("STINK");	//awfulvalley
	//these are the W type	
		warningsW[0] = temp.new YellowChit("BONES");				//oakWoods
		warningsW[1] = temp.new YellowChit("DANK");					//maplewoods
		warningsW[2] = temp.new YellowChit("RUINS");				//nutWoods
		warningsW[3] = temp.new YellowChit("SMOKE");//smallCampfire	//pinewoods
		warningsW[4] = temp.new YellowChit("STINK");//LargeCampfire	//lindenwoods
	//these are the C type	
		warningsC[0] = temp.new YellowChit("BONES");	//highpass
		warningsC[1] = temp.new YellowChit("DANK");		//borderland
		warningsC[2] = temp.new YellowChit("RUINS");	//cavern
		warningsC[3] = temp.new YellowChit("SMOKE");	//caves
		warningsC[4] = temp.new YellowChit("STINK");	//Ruins
	//these are the M type		
		warningsM[0] = temp.new YellowChit("BONES");	//used for cliff
		warningsM[1] = temp.new YellowChit("DANK");		//ledges
		warningsM[2] = temp.new YellowChit("RUINS");	//crag
		warningsM[3] = temp.new YellowChit("SMOKE");	//deepWoods
		warningsM[4] = temp.new YellowChit("STINK");	//mountain
		
	}

	public void moveCharacters(Player player1, int newLocation) {
		//location and new location will be ex: 32, tile 3 clearing 2
		
		int currentTile = player1.getCurrentLocation()/10-1;
		int currentClearing = player1.getCurrentLocation()%10-1;
		
		//remove from old tile
		getMapTiles()[currentTile].removePlayer(player1);
		getMapTiles()[currentTile].clearing[currentClearing].removePlayer(player1);
		
	
		//change the profile value
		player1.setCurrentLocation(newLocation);		
		
		
		int newTile = player1.getCurrentLocation()/10-1;
		int newClearing = player1.getCurrentLocation()%10-1;
		
		//add player to new tile
		getMapTiles()[newTile].putPlayer(player1);
		getMapTiles()[newTile].clearing[newClearing].putPlayer(player1);
		
	}
	
	public void moveDenizen(Denizen monster, int newClearing, int tile) {
		tile--;//so that it is at the correct location
		//can only move from clearing to clearing		
		int currentClearing = monster.getCurrentLocation();
	//System.out.println("CurrentClearing "+tile+currentClearing+newClearing);
		//remove from old clearing
		getMapTiles()[tile].clearing[currentClearing].removeDenizen(monster);
		
	
		//change the profile value
		monster.setCurrentClearing(newClearing);		
		
			
		//add player to new clearing
		getMapTiles()[tile].clearing[newClearing-1].putDenizen(monster);
		
	}
		
	public void returnDenizensToStart() {
		//return monsters and ghosts to starting clearing, regenerating those that died
		resetGhosts();
		resetMonsters();
	}
	
	private void resetMonsters() {

		//circle through all tiles
		for(int a=0; a<20; a++){
			//cycle thourhg all monsters in tile
			for(int b=0; b<mapTiles[a].numMonstersInTile; b++){
				//just move the denizen to his start location
				moveDenizen(mapTiles[a].monstersInTile[b], mapTiles[a].monstersInTile[b].getStartLocation(), a);			
				
			}
		}
	}
	
	public void denizensProwling() {//ghosts are always prowling and can be ignored
		//technically it is a row thing on a chart we don't have, but we will give it a 1/6 chance
		
		//circle through all tiles
		for(int a=0; a<20; a++){
			//cycle thourhg all monsters
			for(int b=0; b<mapTiles[a].numMonstersInTile; b++){
				//if it isn't a ghost
				if(mapTiles[a].monstersInTile[b].name.compareTo("GHOST") != 0){
					
					//determine if it will be prowling
					if(Die.dieRoll() == 6)
						mapTiles[a].monstersInTile[b].prowling = true;//turn on prowling
				}
				
			}
		}
		
	}
	
	public void denizensProwlingStop() {//ghosts are always prowling

		//circle through all tiles
		for(int a=0; a<20; a++){
			//cycle thourhg all monsters
			for(int b=0; b<mapTiles[a].numMonstersInTile; b++){
				//is it prowling
				if(mapTiles[a].monstersInTile[b].prowling){
					//if it isn't a ghost
					if(mapTiles[a].monstersInTile[b].name.compareTo("GHOST") != 0){
						//turn off prowling
						mapTiles[a].monstersInTile[b].prowling = false;
					}
				}
			}
		}
	}
	
	
	public void giveTreasure(Player player, GoldChit treasure) {
		//give the treasure values to the player
		// TODO fix this in second iteration
		System.out.println("He got treasure");
		player.getProfile().setGold(100 + player.getProfile().getGold());//for now give him 100 gold
	}
	
	//checks if the player can go to the newLocation(TileClearing combined cordinate)
	public boolean canHeMove(int oldLocation, int newLocation, Player player) {
		
		int currentTile = oldLocation/10-1;
		int currentClearing = oldLocation%10-1;
		//int newTile = newLocation/10-1;
		int newClearing = newLocation%10-1;
	
		for(int a = 0; a<4; a++){
			//if(this.getMapTile(currentTile).clearing[currentClearing].getConnectedTo()[a] != null)//handle null
				if(this.getMapTile(currentTile).clearing[currentClearing].getConnectedTo()[a] == newClearing)
					return true;//if they are connected
		}	
		//if it isn't in the array
		return false;
	}
	
	//checks whats in a clearing
	public Clearing getClearing(int tile, int clearing){
		return mapTiles[tile-1].clearing[clearing-1];
	}


	
	//NOTE: if there is time create a way of building the board yourself
}
