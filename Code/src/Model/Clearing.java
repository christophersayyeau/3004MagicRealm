package Model;

public class Clearing {

	private int value = -1;
	
	private int [] connectedTo = new int[3];//the highest I noticed was 3

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int [] getConnectedTo() {
		return connectedTo;
	}

}
