package Model;

public class Armor 
{
	public enum Protect {ALL_DIRECTIONS, THRUST_AND_SWING, SMASH, ANY_ONE_DIRECTION, NOTHING}//nothing used when destroyed

	private boolean destroyed = false;	//starts of not broken
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	private boolean damaged = false;	//starts of not broken
	public boolean isDamaged() {
		return damaged;
	}
	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}
	
	//how much punishment can it take
	private int toughness;
	public int getToughness() {
		return toughness;
	}
	public void setToughness(int tough) {
		this.toughness = tough;
	}

	int intact_price;
	int damaged_price;
	int destroyed_price;
	Protect protect;

	public String getDirection(){
		return protect.name();
	}

	public class Suit_of_Armor extends Armor
	{
		public Suit_of_Armor()
		{
			protect = Protect.ALL_DIRECTIONS;
			intact_price = 17;
			damaged_price = 12;
			destroyed_price = 0;
			toughness = 3;	//heavy
		}
	}
	
	public class Breastplate extends Armor
	{
		public Breastplate()
		{
			protect = Protect.THRUST_AND_SWING;
			intact_price = 9;
			damaged_price = 6;
			destroyed_price = 0;
			toughness = 2; //med
		}
	}
	
	public class Helmet extends Armor
	{
		public Helmet()
		{
			protect = Protect.SMASH;
			intact_price = 5;
			damaged_price = 3;
			destroyed_price = 0;
			toughness = 2; //med
		}
	}
	
	public class Shield extends Armor
	{
		public Shield()
		{
			protect = Protect.ANY_ONE_DIRECTION;
			intact_price = 7;
			damaged_price = 5;
			destroyed_price = 0;
			toughness = 2; //med
		}
	}
	
	public class Tremendous_Armor extends Armor
	{
		public Tremendous_Armor()
		{
			protect = Protect.ALL_DIRECTIONS;
			intact_price = 25;
			damaged_price = 18;
			destroyed_price = 5;
			toughness = 4; //treme
		}
	}
	
	public class Silver_Breastplate extends Armor
	{
		public Silver_Breastplate()
		{
			protect = Protect.THRUST_AND_SWING;
			intact_price = 25;
			damaged_price = 21;
			destroyed_price = 15;
			toughness = 3; //heavy
		}
	}
	
	public class Gold_Helmet extends Armor
	{
		public Gold_Helmet()
		{
			protect = Protect.SMASH;
			intact_price = 30;
			damaged_price = 27;
			destroyed_price = 20;
			toughness = 3; //heavy
		}
	}
	
	public class Jade_Shield extends Armor
	{
		public Jade_Shield()
		{
			protect = Protect.ANY_ONE_DIRECTION;
			intact_price = 20;
			damaged_price = 16;
			destroyed_price = 10;
			toughness = 3; //heavy
		}
	}

}
