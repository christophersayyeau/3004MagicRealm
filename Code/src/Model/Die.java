package Model;

public class Die {

	public static int dieRoll() {
		int roll = (int) (Math.random()*6+1);
		System.out.println("Rolled a " + roll);
		return roll;
	}

}
