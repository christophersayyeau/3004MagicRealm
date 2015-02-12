package Model;


//this will be the generic class
public class Weapon {

	//whether it is alert or not
	boolean alerted = false;
	
	int weaponLength;
	boolean missile = false;	//will set to false as basis
	
	Harm alertedHarm;
	//alertedHarm = new Harm(new Harm(2, 1, 4); sending this H*4
	Harm unalertedHarm;
	
	int hands;		//how many needed for the weapon
	int goldCost;	//what's it worth
	
	
//now to handle all of the subClasses of weapons	
	public class GreatAxe extends Weapon{
		//constructor
		public GreatAxe() {
			weaponLength = 5;
			alertedHarm = new Harm(3, 1, 4);//H*4
			unalertedHarm = new Harm(3, 1, 0);//H*
			hands = 2;
			goldCost = 8;
		}
	}	
	
	public class LightBow extends Weapon{
		//constructor
		public LightBow() {
			missile = true;
			weaponLength = 14;
			alertedHarm = new Harm(1,2,1);//L**1
			unalertedHarm = new Harm(1,0,0);//L
			hands = 2;
			goldCost = 6;
		}
	}	
	
	public class Mace extends Weapon{
		//constructor
		public Mace() {
			weaponLength = 1;
			alertedHarm = new Harm(2,0,3);//M3
			unalertedHarm = new Harm(2,0,0);//M
			hands = 1;
			goldCost = 6;
		}
	}	
	
	public class ShortSword extends Weapon{
		//constructor
		public ShortSword() {
			weaponLength = 3;
			alertedHarm = new Harm(1,1,0);//L*
			unalertedHarm = new Harm(1,1,0);//L*
			hands = 1;
			goldCost = 4;
		}
	}
	
	public class Spear extends Weapon{
		//constructor
		public Spear() {
			weaponLength = 10;
			alertedHarm = new Harm(2,1,0);//M*
			unalertedHarm = new Harm(0,0,6);//n6
			hands = 2;
			goldCost = 6;
		}
	}

	public class ThrustingSword extends Weapon{
		//constructor
		public ThrustingSword() {
			weaponLength = 4;
			alertedHarm = new Harm(1,1,0);//L*
			unalertedHarm = new Harm(1,1,4);//L*4
			hands = 1;
			goldCost = 6;
		}
	}

//now to handle all of the subClasses of weapons used exclusively by Natives and denizens
	public class Axe extends Weapon{
		//constructor
		public Axe() {
			weaponLength = 2;
			alertedHarm = new Harm(2,1,0);//M*
			unalertedHarm = new Harm(2,1,5);//M*5
			hands = 1;
			goldCost = 4;
		}
	}

	public class BroadSword extends Weapon{
		//constructor
		public BroadSword() {
			weaponLength = 7;
			alertedHarm = new Harm(2,1,0);//M*
			unalertedHarm = new Harm(2,1,5);//M*5
			hands = 1;
			goldCost = 8;
		}
	}	
	
	public class CrossBow extends Weapon{
		//constructor
		public CrossBow() {
			missile = true;
			weaponLength = 12;
			alertedHarm = new Harm(3,1,1);//H*1
			unalertedHarm = new Harm(3,0,0);//H
			hands = 2;
			goldCost = 10;
		}
	}	
	
	public class GreatSword extends Weapon{
		//constructor
		public GreatSword() {
			weaponLength = 8;
			alertedHarm = new Harm(3,1,0);//H*
			unalertedHarm = new Harm(3,1,6);//H*6
			hands = 2;
			goldCost = 10;
		}
	}

	public class MediumBow extends Weapon{
		//constructor
		public MediumBow() {
			missile = true;
			weaponLength = 16;
			alertedHarm = new Harm(2,2,1);//M**1
			unalertedHarm = new Harm(2,0,0);//M
			hands = 2;
			goldCost = 8;
		}
	}

	public class Staff extends Weapon{
		//constructor
		public Staff() {
			weaponLength = 9;
			alertedHarm = new Harm(1,0,0);//L
			unalertedHarm = new Harm(1,0,0);//L
			hands = 2;
			goldCost = 1;
		}
	}

	public class PowerOfThePit extends Weapon{
		//Not sure what is here
		public PowerOfThePit(){
			System.out.println("THE POWER OF THE PIT COMPELS YOU");
			System.out.println("seriously thou there isn't anything here");
		}
	}

	public void setUnAlert() {
		alerted = false;
	}
	
}