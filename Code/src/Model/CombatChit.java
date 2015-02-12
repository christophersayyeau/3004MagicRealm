package Model;

//this handles all of the actions available to characters
public class CombatChit {
	//use type to specify action
	int type = -1;//set to garbage value
	
	//time to do action
	int time = -1;
	
	//number of asterisks
	int effort = -1;
	
	//strength of action L,M,H
	int strength = -1;
	
	public void setType(String string) {
		//compare the strings to determine what it is
		if(string.compareTo("Move") == 0){
			//System.out.println("Set to Move");
			type = 0;		
		}else if(string.compareTo("Fight") == 0){
			//System.out.println("Set to Fight");
			type = 1;
		}else if(string.compareTo("Duck") == 0){
			//System.out.println("Set to Duck");
			type = 2;
		}else if(string.compareTo("Berserk") == 0){
			//System.out.println("Set to Berserk");
			type = 3;
		}else if(string.compareTo("Magic") == 0){
			//System.out.println("Set to Magic");
			type = 4;
			
		//nothing recognized
		}else{
			System.out.println("Something is Wrong, no Type Selected for Action");
		}
		
	}
	
	public void setTime(int i) {
		time = i;	
	}

	public void setEffort(int i) {
		effort = i;	
	}

	public void setStrength(String string) {
		//compare the strings to determine what it is
		if(string.compareTo("L") == 0){
			//System.out.println("Set to Light");
			type = 0;		
		}else if(string.compareTo("M") == 0){
			//System.out.println("Set to Medium");
			type = 1;
		}else if(string.compareTo("H") == 0){
			//System.out.println("Set to Heavy");
			type = 2;
		}else if(string.compareTo("T") == 0){
			//System.out.println("Set to Titan");
			type = 3;
			
		//nothing recognized
		}else{
			System.out.println("Something is Wrong, no Strength for Action");
			System.out.println("Type: " + type + " Strength: " + string);
		}
	}
	
}
