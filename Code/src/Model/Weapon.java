package Model;


//this will be the generic class
public class Weapon {

	int weaponLength;
	boolean missile = false;	//will set to false as basis
	
	alertedHarm
	unAlertedHarm
	
	int hands;		//how many needed for the weapon
	int goldCost;	//what's it worth
	

	
	
//now to handle all of the subClasses
	
	public class GreatAxe extends Weapon{
		//constructor
		public GreatAxe() {
			weaponLength = 5;
			alertedHarm = H*4
			unAlertedHarm = H*
			hands = 2;
			goldCost = 8;
		}
	}	
	
	public class LightBow extends Weapon{
		//constructor
		public LightBow() {
			weaponLength = 14;
			alertedHarm = L**1
			unAlertedHarm = L
			hands = 2;
			goldCost = 6;
		}
	}	
	
	public class Mace extends Weapon{
		//constructor
		public Mace() {
			weaponLength = 1;
			alertedHarm = M3
			unAlertedHarm = M
			hands = 1;
			goldCost = 6;
		}
	}	
	
	public class ShortSword extends Weapon{
		//constructor
		public ShortSword() {
			weaponLength = 3;
			alertedHarm = L*
			unAlertedHarm = L*
			hands = 1;
			goldCost = 4;
		}
	}
	
	public class Spear extends Weapon{
		//constructor
		public Spear() {
			weaponLength = 10;
			alertedHarm = M*
			unAlertedHarm = n6
			hands = 2;
			goldCost = 6;
		}
	}

	public class ThrustingSword extends Weapon{
		//constructor
		public ThrustingSword() {
			weaponLength = 4;
			alertedHarm = L*
			unAlertedHarm = L*4
			hands = 1;
			goldCost = 6;
		}
	}




}