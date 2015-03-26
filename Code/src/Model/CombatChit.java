package Model;

import Control.Player;

//this handles all of the actions available to characters
public class CombatChit {
	//use type to specify action
	private String type = null;//set to garbage value
	
	//time to do action
	private int time = -1;
	
	//number of asterisks
	private int effort = -1;
	
	//strength of action L,M,H
	private int strength = -1;
	
	public void setType(String string) {
		//compare the strings to determine what it is
/*		if(string.compareTo("Move") == 0){
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
		}*/
		type = string;
	}
	
	public String getType(){
		return type;
	}
	public int getTime(){
		return time;
	}
	public int getEffort(){
		return effort;
	}
	public int getStrength(){
		return strength;
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
			strength = 0;		
		}else if(string.compareTo("M") == 0){
			//System.out.println("Set to Medium");
			strength = 1;
		}else if(string.compareTo("H") == 0){
			//System.out.println("Set to Heavy");
			strength = 2;
		}else if(string.compareTo("T") == 0){
			//System.out.println("Set to Titan");
			strength = 3;
			
		//nothing recognized
		}else{
			System.out.println("Something is Wrong, no Strength for Action");
			System.out.println("Type: " + type + " Strength: " + string);
		}
	}
	
	public String toString(){
		return(type + ":  Time "+ time + ", Effort "+ effort+ ", Strength " + strength);
	}

	public static String[] getActiveChits(Player player, String[] options) {	//moved from CombatDialog
		
		for(int a=0; a< player.getProfile().action1Num; a++){	//add all of the first action available
			if(player.getProfile().action1.getType().compareTo("Fight") == 0)	//only fight type allowed
				options = ArrayUtils.add(options, player.getProfile().action1.toString());
		}
		for(int a=0; a< player.getProfile().action2Num; a++){	//add all of the second action available
			if(player.getProfile().action2.getType().compareTo("Fight") == 0)
				options = ArrayUtils.add(options, player.getProfile().action2.toString());
		}
		for(int a=0; a< player.getProfile().action3Num; a++){	//add all of the third action available
			if(player.getProfile().action3.getType().compareTo("Fight") == 0)
				options = ArrayUtils.add(options, player.getProfile().action3.toString());
		}
		return options;
	}
	
	public static String[] getActiveEffortChits(Player player, String[] options) {	//only used when fatiguing a chit
		//only add actionchits that cost effort
		
		for(int a=0; a< player.getProfile().action1Num; a++){	//add all of the first action available
			if(player.getProfile().action1.effort > 0)	//only those costing effort
				options = ArrayUtils.add(options, player.getProfile().action1.toString());
		}
		for(int a=0; a< player.getProfile().action2Num; a++){	//add all of the second action available
			if(player.getProfile().action2.effort > 0)
				options = ArrayUtils.add(options, player.getProfile().action2.toString());
		}
		for(int a=0; a< player.getProfile().action3Num; a++){	//add all of the third action available
			if(player.getProfile().action3.effort > 0)
				options = ArrayUtils.add(options, player.getProfile().action3.toString());
		}
		return options;
	}

	public static String[] getActionChits(Player player, String[] options) {	//only used when wounding a chit
		//add all that are avialbale		
		for(int a=0; a< player.getProfile().action1Num; a++){	//add all of the first action available
			
				options = ArrayUtils.add(options, player.getProfile().action1.toString());
		}
		for(int a=0; a< player.getProfile().action2Num; a++){	//add all of the second action available
			
				options = ArrayUtils.add(options, player.getProfile().action2.toString());
		}
		for(int a=0; a< player.getProfile().action3Num; a++){	//add all of the third action available
			
				options = ArrayUtils.add(options, player.getProfile().action3.toString());
		}
		return options;
	}
}
