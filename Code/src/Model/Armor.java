package Model;

public class Armor 
{
	public enum Protect {ALL_DIRECTIONS, THRUST_AND_SWING, SMASH, ANY_ONE_DIRECTION};
	
	int intact_price;
	int damaged_price;
	int destroyed_price;
	Protect protect;

	public class Suit_of_Armor extends Armor
	{
		public Suit_of_Armor()
		{
			//protect = ALL_DIRECTIONS;
			intact_price = 17;
			damaged_price = 12;
			destroyed_price = 0;
		}
	}
	
	public class Breastplate extends Armor
	{
		public Breastplate()
		{
			//protect = THRUST_AND_SWING;
			intact_price = 9;
			damaged_price = 6;
			destroyed_price = 0;
		}
	}
	
	public class Helmet extends Armor
	{
		public Helmet()
		{
			//protect = SMASH;
			intact_price = 5;
			damaged_price = 3;
			destroyed_price = 0;
		}
	}
	
	public class Shield extends Armor
	{
		public Shield()
		{
			//protect = ANY_ONE_DIRECTION;
			intact_price = 7;
			damaged_price = 5;
			destroyed_price = 0;
		}
	}
	
	public class Tremendous_Armor extends Armor
	{
		public Tremendous_Armor()
		{
			//protect = ALL_DIRECTIONS;
			intact_price = 25;
			damaged_price = 18;
			destroyed_price = 5;
		}
	}
	
	public class Silver_Breastplate extends Armor
	{
		public Silver_Breastplate()
		{
			//protect = THRUST_AND_SWING;
			intact_price = 25;
			damaged_price = 21;
			destroyed_price = 15;
		}
	}
	
	public class Gold_Helmet extends Armor
	{
		public Gold_Helmet()
		{
			//protect = SMASH;
			intact_price = 30;
			damaged_price = 27;
			destroyed_price = 20;
		}
	}
	
	public class Jade_Shield extends Armor
	{
		public Jade_Shield()
		{
			//protect = ANY_ONE_DIRECTION;
			intact_price = 20;
			damaged_price = 16;
			destroyed_price = 10;
		}
	}

}
