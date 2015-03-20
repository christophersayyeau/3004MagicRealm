package Model;

import Control.Player;
import Model.Denizen.*;
import Model.MapChits.GoldChit;
import Model.MapChits.RedChit;
import Model.MapTiles.DarkValley;
import Model.MapTiles.EvilValley;
import Model.MapChits.*;
import Model.MapTiles.*;
import View.GUI;

public class Map {
	GUI view;

	//an array of tiles, the values will be hardcoded in
	private MapTiles [] mapTiles = new MapTiles[20];
	public YellowChit [] warningsV = new YellowChit[5];
	public YellowChit [] warningsW = new YellowChit[5];
	public YellowChit [] warningsC = new YellowChit[5];
	public YellowChit [] warningsM = new YellowChit[5];
	
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

	public RedChit getSound(int a) {
		return sounds[a];
	}
	public GoldChit getTreasure(int a) {
		return sites[a];
	}
	public LostCastle getLostCastle() {
		return lostCastle;
	}
	public LostCity getLostCity() {
		return lostCity;
	}

	public void build() {
		//starting on top of picture 4842, left to right
		
		//built a temporary mapTile object so I have access to its subclass
		MapTiles temp = new MapTiles();
		buildWarningChits();
		buildSoundChits();
		buildTreasureChits();
		
		//first construct the LOST CASTLE and CITY
		lostCastle.setTreasure(sites[0]);
		lostCastle.setTreasure(sites[1]);
		lostCastle.setSound(sounds[0]);
		lostCastle.setSound(sounds[1]);
		lostCastle.setSound(sounds[2]);
		
		lostCity.setTreasure(sites[2]);
		lostCity.setTreasure(sites[3]);
		lostCity.setSound(sounds[3]);
		lostCity.setSound(sounds[4]);
		lostCity.setSound(sounds[5]);
		
		
		//build the tiles then add to array	
		AwfulValley awfulValley = temp.new AwfulValley(14, 7, -1, -1, -1, 5);
		getMapTiles()[0] = awfulValley;
		getMapTiles()[0].setWarning(warningsV[4]);
		getMapTiles()[0].order = new GreatSwordsman[2];//build the natives here
		getMapTiles()[0].setChapel(4, (AwfulValley) getMapTiles()[0]);
		view.buildBuildings("Chapel", 1, 4);
		
		BadValley badValley = temp.new BadValley(11, 10, 8, 6, 12, 19);
		getMapTiles()[1] = badValley;
		getMapTiles()[1].setWarning(warningsV[3]);
		getMapTiles()[1].rogues = new GreatSwordsman[2];//build the natives here
		getMapTiles()[1].setInn(4, (BadValley) getMapTiles()[1]);
		view.buildBuildings("Inn", 2, 4);
		
		CurstValley curstValley = temp.new CurstValley(17, 3, -1, -1, -1, 7);
		getMapTiles()[2] = curstValley;
		getMapTiles()[2].setWarning(warningsV[2]);
		getMapTiles()[2].soldiers = new GreatSwordsman[2];//build the natives here
		getMapTiles()[2].setHouse(4, (CurstValley) getMapTiles()[2]);
		view.buildBuildings("House", 3, 4);
		
		DarkValley darkValley = temp.new DarkValley(16, -1, -1, -1, 2, 17);
		getMapTiles()[3] = darkValley;
		getMapTiles()[3].setWarning(warningsV[1]);
		getMapTiles()[3].guard = new GreatSwordsman[3];//build the natives here
		getMapTiles()[3].setGuardHouse(4, (DarkValley) getMapTiles()[3]);
		view.buildBuildings("GuardHouse", 4, 4);
		
		EvilValley evilValley = temp.new EvilValley(-1, -1, 15, 18, 10, 13);
		getMapTiles()[4] = evilValley;
		getMapTiles()[4].setWarning(warningsV[0]);
		//all garrison natives start the game at their dwellings and dont move unless hired
		//buildGhosts();//there are 2 ghosts
		//putGhostsAtStartPositions();
		//resetGhosts();//put in start positions
		//mapTiles[1].putGhosts(3);
		getMapTiles()[4].ghosts = new Ghost[2];//build the natives here
		getMapTiles()[4].setGhosts(4, (EvilValley) getMapTiles()[4]);
		
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
	
	/*replaced with better version in cheat mode
	 * private void resetGhosts(){

		//if(ghosts[0] != null){
			getMapTiles()[4].removeDenizen(((EvilValley) getMapTiles()[4]).ghosts[0]);
			getMapTiles()[4].clearing[((EvilValley) getMapTiles()[4]).ghosts[0].currentClearing].removeDenizen(((EvilValley) getMapTiles()[4]).ghosts[0]);		
			//getMapTiles()[1].clearing[3]
		//}
	//	if(ghosts[1] != null){
			getMapTiles()[4].removeDenizen(((EvilValley) getMapTiles()[4]).ghosts[1]);
			getMapTiles()[4].clearing[((EvilValley) getMapTiles()[4]).ghosts[1].currentClearing].removeDenizen(((EvilValley) getMapTiles()[4]).ghosts[1]);
	//	}
		
		//now put them back in
		getMapTiles()[4].setGhosts(4, (EvilValley) getMapTiles()[4]);//handled in MapTiles

				
		if(ghosts[0] != null) getMapTiles()[1].removeDenizen(ghosts[0]);
		if(ghosts[1] != null) getMapTiles()[1].removeDenizen(ghosts[1]);
		//if(ghosts[0].equals(null)) getMapTiles()[1].removeDenizen(ghosts[0]);
		//if(ghosts[1].equals(null)) getMapTiles()[1].removeDenizen(ghosts[1]);
		
		//ghosts[0] = null;
		//ghosts[1] = null;
		buildGhosts();		
		
		putGhostsAtStartPositions();
	}*/

	private void buildSoundChits() {
		MapChits temp = new MapChits();
		//assign values to sounds					//tile in normal mode
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
		
		//assign values to treasure					//tiles in normal mode
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
		
		//assign the values to the warnings				//location in normal mode
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
		
		int currentTile = player1.getCurrentLocation()/10;
		int currentClearing = player1.getCurrentLocation()%10;
		
		String[] pos = new String[2];
		pos[0] = Integer.toString(currentTile);
		pos[1] = Integer.toString(currentClearing);
		int[] temp = new int[2];
		temp = view.convertNameToPosition(pos);
		
		//make sure they exist in tile
		getMapTiles()[temp[0]].putPlayer(player1);
		getMapTiles()[temp[0]].clearing[temp[1]].putPlayer(player1);
		
		if(newLocation > 0){
			//remove from old tile
			getMapTiles()[temp[0]].removePlayer(player1);
			getMapTiles()[temp[0]].clearing[temp[1]].removePlayer(player1);
		
	
			//change the profile value
		
			player1.setCurrentLocation(newLocation);		
			
			System.out.println("New location number = "+newLocation);
			int newTile = player1.getCurrentLocation()/10;
			int newClearing = player1.getCurrentLocation()%10;
		
			pos[0] = Integer.toString(newTile);
			pos[1] = Integer.toString(newClearing);
			temp = view.convertNameToPosition(pos);
		
			//add player to new tile
			getMapTiles()[temp[0]].putPlayer(player1);
			getMapTiles()[temp[0]].clearing[temp[1]].putPlayer(player1);
		}
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
		//resetGhosts();
		resetGhostsCheat();
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
		// TODO second part fix this treasure stuff 
		System.out.println("He got treasure");
		player.getProfile().setGold(100 + player.getProfile().getGold());//for now give him 100 gold
	}
	
	//checks if the player can go to the newLocation(TileClearing combined cordinate)
	public boolean canHeMove(int oldLocation, int newLocation, Player player) {
		
		int currentTile = oldLocation/10;
		int currentClearing = oldLocation%10;
		//int newTile = newLocation/10;
		int newClearing = newLocation;
		
		
		String[] pos = new String[2];
		pos[0] = Integer.toString(currentTile);
		pos[1] = Integer.toString(currentClearing);
		int[] temp = new int[2];
		temp = view.convertNameToPosition(pos);
		
		view.updateMap(this);
		
		for(int a = 0; a<4; a++){
			//if(this.getMapTile(currentTile).clearing[currentClearing].getConnectedTo()[a] != null)//handle null
			if(this.getMapTile(temp[0]).clearing[temp[1]].getConnectedTo()[a] == newClearing)
				return true;//if they are connected
		}	
		//if it isn't in the array
		return false;
	}
	
	//checks whats in a clearing
	public Clearing getClearing(int tile, int clearing){
		return mapTiles[tile].clearing[clearing];
	}

	
	
	
	//CHEAT MODE----------------------------------------------------------------------
	
	public void buildCheat() {//same as build() only since it is cheat mode dwellings and ghosts get put by user, sonds and warnings are added after user
		//starting on top of picture 4842, left to right
		
				//built a temporary mapTile object so I have access to its subclass
				MapTiles temp = new MapTiles();
				buildWarningChits();
				buildSoundChits();
				buildTreasureChits();	
				
				//build the tiles then add to array	
				AwfulValley awfulValley = temp.new AwfulValley(14, 7, -1, -1, -1, 5);
				getMapTiles()[0] = awfulValley;
					//getMapTiles()[0].setWarning(warningsV[4]);
							//		getMapTiles()[0].setChapel(4, (AwfulValley) getMapTiles()[0]);
							//		view.buildBuildings("Chapel", 1, 4);
				
				BadValley badValley = temp.new BadValley(11, 10, 8, 6, 12, 19);
				getMapTiles()[1] = badValley;
					//getMapTiles()[1].setWarning(warningsV[3]);
							//		getMapTiles()[1].setInn(4, (BadValley) getMapTiles()[1]);
							//		view.buildBuildings("Inn", 2, 4);
				
				CurstValley curstValley = temp.new CurstValley(17, 3, -1, -1, -1, 7);
				getMapTiles()[2] = curstValley;
					//getMapTiles()[2].setWarning(warningsV[2]);
							//		getMapTiles()[2].setHouse(4, (CurstValley) getMapTiles()[2]);
							//		view.buildBuildings("House", 3, 4);
				
				DarkValley darkValley = temp.new DarkValley(16, -1, -1, -1, 2, 17);
				getMapTiles()[3] = darkValley;
					//getMapTiles()[3].setWarning(warningsV[1]);
							//		getMapTiles()[3].setGuardHouse(4, (DarkValley) getMapTiles()[3]);
							//		view.buildBuildings("GuardHouse", 4, 4);
				
				EvilValley evilValley = temp.new EvilValley(-1, -1, 15, 18, 10, 13);
				getMapTiles()[4] = evilValley;
					//getMapTiles()[4].setWarning(warningsV[0]);
							//		getMapTiles()[4].setGhosts(4, (EvilValley) getMapTiles()[4]);
				
				//now handle adding the dwellings and ghosts
				view.pickLocationsDwellingsCheat(getMapTiles()[0], getMapTiles()[1], getMapTiles()[2], getMapTiles()[3], getMapTiles()[4]);
				
				
				LindenWoods lindenWoods = temp.new LindenWoods(-1, 14, 0, -1, -1, -1);
				getMapTiles()[5] = lindenWoods;
					//getMapTiles()[5].setWarning(warningsW[4]);
				
				MapleWoods mapleWoods = temp.new MapleWoods(1, 8, 17, 7, 14, 12);
				getMapTiles()[6] = mapleWoods;
					//getMapTiles()[6].setWarning(warningsW[1]);
				
				NutWoods nutWoods = temp.new NutWoods(6, 17, 2, -1, 0, 14);
				getMapTiles()[7] = nutWoods;
					//getMapTiles()[7].setWarning(warningsW[2]);

				OakWoods oakWoods = temp.new OakWoods(10, 18, 16, 17, 6, 1);
				getMapTiles()[8] = oakWoods;
					//getMapTiles()[8].setWarning(warningsW[0]);
				
				PineWoods pineWoods = temp.new PineWoods(-1, 19, 12, -1, -1, -1);
				getMapTiles()[9] = pineWoods;
					//getMapTiles()[9].setWarning(warningsW[3]);				
				
				BorderLand borderLand = temp.new BorderLand(13, 4, 18, 8, 1, 11);
				getMapTiles()[10] = borderLand;
					//getMapTiles()[10].setWarning(warningsC[1]);
				//getMapTiles()[10].setSound(sounds[8]);
				
				Cavern cavern = temp.new Cavern(-1, 13, 10, 1, 19, -1);
				getMapTiles()[11] = cavern;
					//getMapTiles()[11].setWarning(warningsC[2]);
				//getMapTiles()[11].setTreasure(sites[6]);
				
				Caves caves = temp.new Caves(19, 1, 6, 14, -1, 9);
				getMapTiles()[12] = caves;
					//getMapTiles()[12].setWarning(warningsC[3]);
				//getMapTiles()[12].setSound(sounds[9]);
				
				HighPass highPass = temp.new HighPass(-1, -1, 4, 10, 11, -1);
				getMapTiles()[13] = highPass;
					//getMapTiles()[13].setWarning(warningsC[0]);
				//getMapTiles()[13].setSound(sounds[7]);
				
				Ruins ruins = temp.new Ruins(12, 6, 7, 0, 5, -1);
				getMapTiles()[14] = ruins;
					//getMapTiles()[14].setWarning(warningsC[4]);
				//getMapTiles()[14].setLostCity();//instead of treasure or sound
			
				Cliff cliff = temp.new Cliff(-1, -1, -1, -1, 18, 4);	//only the last 2 values have tiles there
				getMapTiles()[15] = cliff;
					//getMapTiles()[15].setWarning(warningsM[0]);
				//getMapTiles()[15].setTreasure(sites[4]);
				
				Crag crag = temp.new Crag(18, -1, -1, 3, 17, 8);
				getMapTiles()[16] = crag;
					//getMapTiles()[16].setWarning(warningsM[2]);
				//getMapTiles()[16].setSound(sounds[6]);
				
				DeepWoods deepWoods = temp.new DeepWoods(8, 16, 3, 2, 7, 6);
				getMapTiles()[17] = deepWoods;
					//getMapTiles()[17].setWarning(warningsM[3]);
				//getMapTiles()[17].setTreasure(sites[5]);
				
				Ledges ledges = temp.new Ledges(4, 15, -1, 16, 8, 10);
				getMapTiles()[18] = ledges;
					//getMapTiles()[18].setWarning(warningsM[1]);
				//getMapTiles()[18].setLostCastle();//instead of a sound or treasure
				
				Mountain mountain = temp.new Mountain(-1, 11, 1, 12, 9, -1);
				getMapTiles()[19] = mountain;
					//getMapTiles()[19].setWarning(warningsM[4]);
			    //setMapTiles()[19].setTreasure(sites[7]);
				System.out.println("FINISH ADDING TILES");			
	}
	
	public void buildMapChits(){
				//now add the warnings and such
				//if the user has not yet picked the value of sound and warning
				
					//set the new values
				for(int a=0; a<20; a++){	//loop through all tiles		
					getMapTile(a).setSoundTreasureCheat(   	view.getSoundTreasureCheat(  getMapTile(a).getType() , a)  );
					getMapTile(a).setWarning( 				view.getWarningCheat( 		 getMapTile(a).getType() , a)  );
					System.out.println("TESTING THE BUILDING OF TILECHITS IN CHEAT MODE: warning is "+ getMapTile(a).getWarning().type);
				}
	}

	public void denizensProwlingCheat() {//ghosts always prowling
		//technically it is a row thing on a chart we don't have, but we will give it a 1/6 chance
		
		//circle through all tiles
		for(int a=0; a<20; a++){
			//cycle thourhg all monsters
			for(int b=0; b<mapTiles[a].numMonstersInTile; b++){
				//if it isn't a ghost
				if(mapTiles[a].monstersInTile[b].name.compareTo("GHOST") != 0){
					
					//determine if it will be prowling
					if(Die.dieRollCheat() == 6)
						mapTiles[a].monstersInTile[b].prowling = true;//turn on prowling
				}
				
			}
		}
		
	}
	
//	public void returnDenizensToStartCheat() {
//		//return monsters and ghosts to starting clearing, regenerating those that died
//		resetGhostsCheat();
//		resetMonsters();
//	}

	private void resetGhostsCheat() {

		for(int a=0; a<5; a++){//serach through the array of valleys
			if(getMapTiles()[a].isGhostTile()){
				//remove ghosts
				getMapTiles()[a].removeDenizen(getMapTiles()[a].ghosts[0]);
				getMapTiles()[a].clearing[ getMapTiles()[a].ghosts[0].currentClearing].removeDenizen( getMapTiles()[a].ghosts[0] );		

				getMapTiles()[a].removeDenizen( getMapTiles()[a].ghosts[1]);
				getMapTiles()[a].clearing[ getMapTiles()[a].ghosts[1].currentClearing].removeDenizen( getMapTiles()[a].ghosts[1] );

				//put them back
				getMapTiles()[a].setGhosts(4, getMapTiles()[a]);
			}
		}
						/*removed since more streamline above
						//check each tile until you find the one with ghosts
						if(getMapTiles()[0].isGhostTile()){
							//remove ghosts
							getMapTiles()[0].removeDenizen(getMapTiles()[0].ghosts[0]);
							getMapTiles()[0].clearing[ getMapTiles()[0].ghosts[0].currentClearing].removeDenizen( getMapTiles()[0].ghosts[0] );		
				
							getMapTiles()[0].removeDenizen( getMapTiles()[0].ghosts[1]);
							getMapTiles()[0].clearing[ getMapTiles()[0].ghosts[1].currentClearing].removeDenizen( getMapTiles()[0].ghosts[1] );
				
							//put them back
							getMapTiles()[0].setGhosts(4, getMapTiles()[0]);
							
						}else if(getMapTiles()[1].isGhostTile()){
							//remove ghosts
							getMapTiles()[1].removeDenizen(getMapTiles()[1].ghosts[0]);
							getMapTiles()[1].clearing[ getMapTiles()[1].ghosts[0].currentClearing].removeDenizen( getMapTiles()[1].ghosts[0] );		
				
							getMapTiles()[1].removeDenizen( getMapTiles()[1].ghosts[1]);
							getMapTiles()[1].clearing[ getMapTiles()[1].ghosts[1].currentClearing].removeDenizen( getMapTiles()[1].ghosts[1] );
				
							//put them back
							getMapTiles()[1].setGhosts(4, getMapTiles()[1]);
							
						}else if(getMapTiles()[2].isGhostTile()){
							//remove ghosts
							getMapTiles()[2].removeDenizen(getMapTiles()[2].ghosts[0]);
							getMapTiles()[2].clearing[ getMapTiles()[2].ghosts[0].currentClearing].removeDenizen( getMapTiles()[2].ghosts[0] );		
				
							getMapTiles()[2].removeDenizen( getMapTiles()[2].ghosts[1]);
							getMapTiles()[2].clearing[ getMapTiles()[2].ghosts[1].currentClearing].removeDenizen( getMapTiles()[2].ghosts[1] );
				
							//put them back
							getMapTiles()[2].setGhosts(4, getMapTiles()[2]);
							
						}else if(getMapTiles()[3].isGhostTile()){
							//remove ghosts
							getMapTiles()[3].removeDenizen(getMapTiles()[3].ghosts[0]);
							getMapTiles()[3].clearing[ getMapTiles()[3].ghosts[0].currentClearing].removeDenizen( getMapTiles()[3].ghosts[0] );		
				
							getMapTiles()[3].removeDenizen( getMapTiles()[3].ghosts[1]);
							getMapTiles()[3].clearing[ getMapTiles()[3].ghosts[1].currentClearing].removeDenizen( getMapTiles()[3].ghosts[1] );
				
							//put them back
							getMapTiles()[3].setGhosts(4, getMapTiles()[3]);
							
						}else if(getMapTiles()[4].isGhostTile()){
							//remove ghosts
							getMapTiles()[4].removeDenizen(getMapTiles()[4].ghosts[0]);
							getMapTiles()[4].clearing[ getMapTiles()[4].ghosts[0].currentClearing].removeDenizen( getMapTiles()[4].ghosts[0] );		
				
							getMapTiles()[4].removeDenizen( getMapTiles()[4].ghosts[1]);
							getMapTiles()[4].clearing[ getMapTiles()[4].ghosts[1].currentClearing].removeDenizen( getMapTiles()[4].ghosts[1] );
				
							//put them back
							getMapTiles()[4].setGhosts(4, getMapTiles()[4]);
				
						}else{
							System.out.println("EROR in reseting the GHosts");
						}*/
	}

}
