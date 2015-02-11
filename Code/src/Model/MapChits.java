package Model;

public class MapChits {

	int clearing = -1;//number value on chit tells location
	String type;
	
	
	//site(treaseure)
	public class GoldChit extends MapChits {
		//constructor
		public GoldChit(){
			clearing = ;
			type = ;
		}
	}

	//Sounds
	public class RedChit extends MapChits {
		//constructor
		public RedChit(){
			clearing = ;
			type = ;//monster that will appear
		}
	}
	
	//Warnings
	public class YellowChit extends MapChits {
		//constructor
		public YellowChit(){
			clearing = ;
			type = ;
		}
	}
}
