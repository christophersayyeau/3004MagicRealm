package Model;

public class Items {

	
	//this will encompass armor, weapon, treasure
	//will be used to hold users belongings, goldCHits to hold treasures, and playerDrops to hold their stuff
	
	//theses should only be used by belongings with none of these values
	//called in looting
	public int notoriety_value = 0;
	public int gold_price = 0;
	public int fame_value = 0;
	public String name = "unknown";//used by cloak and spect
}
