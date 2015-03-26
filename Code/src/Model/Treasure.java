package Model;

/*Treasures for iteration 1 should simply be random amounts of golds (10, 20, 30, 40 or 50). 
 *The two latter should be considered GREAT TREASURES IF (and only if) you have the time to tackle treasure looting. 
 */
public class Treasure extends Items
{
	private boolean Gt_Treasure;
	boolean Lg_Treasure;
	
	//filled with garbage values standard
	public int fame_value = -1;
	public int notoriety_value = -1;
	public int gold_price = -1;
	int intact_price = -1;
	int damaged_price = -1;
	int destroyed_price = -1;
	
	public boolean isGt_Treasure() {
		return Gt_Treasure;
	}
	public void setGt_Treasure(boolean gt_Treasure) {
		Gt_Treasure = gt_Treasure;
	}
	
	
	//Armor
	public class Suit_of_Armor extends Treasure
	{
		public Suit_of_Armor(){
			fame_value = 6;
			notoriety_value = 3;
			intact_price = 25;
			damaged_price = 18;
			destroyed_price = 5;
		}
	}
	
	public class Gold_Helmet extends Treasure
	{
		public Gold_Helmet(){
			fame_value = 4;
			notoriety_value = 4;
			intact_price = 30;
			damaged_price = 27;
			destroyed_price = 20;
		}
	}
	
	public class Silver_Breastplate extends Treasure
	{
		public Silver_Breastplate(){
			fame_value = 4;
			notoriety_value = 4;
			intact_price = 25;
			damaged_price = 21;
			destroyed_price = 15;
		}
	}

	public class Jade_Shield extends Treasure{
		public Jade_Shield(){
			fame_value = 4;
			notoriety_value = 4;
			intact_price = 20;
			damaged_price = 16;
			destroyed_price = 10;
		}
	}
	
	//Weapons
	public class Bane_GreatSword extends Treasure{
		public Bane_GreatSword(){
			fame_value = 6;
			notoriety_value = 3;
			gold_price = 20;
		}
	}
	
	public class Truesteel_Broadsword extends Treasure{
		public Truesteel_Broadsword(){
			fame_value = 6;
			notoriety_value = 3;
			gold_price = 25;
		}
	}
	
	public class Devil_Broadsword extends Treasure{
		public Devil_Broadsword(){
			fame_value = 0;
			notoriety_value = 15;
			gold_price = 20;
		}
	}
	
	public class Living_ThrustingSword extends Treasure{
		public Living_ThrustingSword(){
			fame_value = 3;
			notoriety_value = 9;
			gold_price = 25;
		}
	}
	
	//Treasure Table values
	public class League_Boots extends Treasure
	{
		public League_Boots(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 5;
		}
	}
	
	public class Alchemist_Mixture extends Treasure
	{
		public Alchemist_Mixture(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 4;
		}
	}
	
	public class Amulet extends Treasure{
		public Amulet(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 5;
		}
	}
	
	public class Ancient_Telescope extends Treasure{
		public Ancient_Telescope(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 5;
		}
	}
	
	public class Battle_Bracelets extends Treasure{
		public Battle_Bracelets(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 2;
			notoriety_value = 4;
			gold_price = 12;
		}
	}
	
	public class Beast_Pipes extends Treasure{
		public Beast_Pipes(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = -5;
			notoriety_value = 5;
			gold_price = 8;
		}
	}
	
	public class B_Dwarf_Vest extends Treasure{
		public B_Dwarf_Vest(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 5;
			gold_price = 27;
		}
	}
	
	public class Belt_of_Strength extends Treasure{
		public Belt_of_Strength(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 3;
			notoriety_value = 6;
			gold_price = 16;
		}
	}
	
	public class Black_Book extends Treasure{
		public Black_Book(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = -15;
			notoriety_value = 15;
			gold_price = 10;
		}
	}
	
	public class Blasted_Jewel extends Treasure{
		public Blasted_Jewel(){
			setGt_Treasure(true);
			Lg_Treasure = false;
			fame_value = -15;
			notoriety_value = 15;
			gold_price = 30;
		}
	}
	
	public class Book_of_Lore extends Treasure{
		public Book_of_Lore(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = -5;
			notoriety_value = 10;
			gold_price = 10;
		}
	}
	
	public class Chest extends Treasure{
		public Chest(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Cloak_of_Mist extends Treasure{//TODO Cloak, maybee just give these standard to the amazon, so we can show that they work without having to fix treasure randomization
		public Cloak_of_Mist(){
			setGt_Treasure(true);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 4;
		}
	}
	
	public class Cloven_Hoof extends Treasure{
		public Cloven_Hoof(){
			setGt_Treasure(true);
			Lg_Treasure = false;
			fame_value = -20;
			notoriety_value = 40;
			gold_price = 4;
		}
	}
	
	public class Crypt_of_the_Knight extends Treasure{
		public Crypt_of_the_Knight(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Crystal_Ball extends Treasure{
		public Crystal_Ball(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 5;
			gold_price = 20;
		}
	}
	
	public class Deft_Gloves extends Treasure{
		public Deft_Gloves(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 6;
			gold_price = 10;
		}
	}
	
	public class Dragon_Essence extends Treasure{
		public Dragon_Essence(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = -10;
			notoriety_value = 20;
			gold_price = 3;
		}
	}
	
	public class Dragonfang_Necklace extends Treasure{
		public Dragonfang_Necklace(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 12;
			gold_price = 8;
		}
	}
	
	public class Draught_of_Speed extends Treasure{
		public Draught_of_Speed(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 6;
		}
	}
	
	public class Elusive_Cloak extends Treasure{
		public Elusive_Cloak(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 10;
		}
	}
	
	public class Elven_Slippers extends Treasure{
		public Elven_Slippers(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 5;
		}
	}
	
	public class Enchanted_Meadow extends Treasure{
		public Enchanted_Meadow(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Enchanter_Skull extends Treasure{
		public Enchanter_Skull(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = -10;
			notoriety_value = 10;
			gold_price = 17;
		}
	}
	
	public class Eye_of_the_Idol extends Treasure{
		public Eye_of_the_Idol(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = -5;
			notoriety_value = 10;
			gold_price = 34;
		}
	}
	
	public class Eye_of_the_Moon extends Treasure{
		public Eye_of_the_Moon(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 13;
			gold_price = 13;
		}
	}
	
	public class Flowers_of_Rest extends Treasure{
		public Flowers_of_Rest(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 2;
		}
	}
	
	public class Flying_Carpet extends Treasure{
		public Flying_Carpet(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 12;
			gold_price = 17;
		}
	}
	
	public class Garb_of_Speed extends Treasure{
		public Garb_of_Speed(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 2;
			notoriety_value = 6;
			gold_price = 16;
		}
	}
	
	public class Girtle_of_Energy extends Treasure{
		public Girtle_of_Energy(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 2;
			notoriety_value = 4;
			gold_price = 13;
		}
	}
	
	public class Glimmering_Ring extends Treasure{
		public Glimmering_Ring(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 10;
			gold_price = 15;
		}
	}
	
	public class Gloves_of_Strength extends Treasure{
		public Gloves_of_Strength(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 6;
			gold_price = 8;
		}
	}
	
	public class Glowing_Gem extends Treasure{
		public Glowing_Gem(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 5;
			gold_price = 17;
		}
	}
	
	public class Golden_Arm_Band extends Treasure{
		public Golden_Arm_Band(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 15;
		}
	}
	
	public class Golden_Crown extends Treasure{
		public Golden_Crown(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = -15;
			gold_price = 50;
		}
	}
	
	public class Golden_Icon extends Treasure{
		public Golden_Icon(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = -10;
			notoriety_value = 20;
			gold_price = 100;
		}
	}
	
	public class Good_Book extends Treasure{
		public Good_Book(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 5;
			notoriety_value = 5;
			gold_price = 10;
		}
	}
	
	public class Gripping_Dust extends Treasure{
		public Gripping_Dust(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Handy_Gloves extends Treasure{
		public Handy_Gloves(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 1;
			notoriety_value = 2;
			gold_price = 6;
		}
	}
	
	public class Hidden_Ring extends Treasure{
		public Hidden_Ring(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = -10;
			notoriety_value = 10;
			gold_price = 20;
		}
	}
	
	public class Imperial_Tabard extends Treasure{
		public Imperial_Tabard(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = -10;
			gold_price = 17;
		}
	}
	
	public class Lost_Keys extends Treasure{
		public Lost_Keys(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 5;
		}
	}
	
	public class Lucky_Charm extends Treasure{
		public Lucky_Charm(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 14;
		}
	}
	
	public class Magic_Spectacles extends Treasure{//TODO Spectacles, see cloack comment
		public Magic_Spectacles(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 6;
		}
	}
	
	public class Magic_Wand extends Treasure{
		public Magic_Wand(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = -10;
			notoriety_value = 10;
			gold_price = 17;
		}
	}
	
	public class Map_of_Lost_Castle extends Treasure{
		public Map_of_Lost_Castle(){
			setGt_Treasure(true);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Map_of_Lost_City extends Treasure{
		public Map_of_Lost_City(){
			setGt_Treasure(true);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Map_of_Ruins extends Treasure{
		public Map_of_Ruins(){
			setGt_Treasure(true);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Mouldy_Skeleton extends Treasure{
		public Mouldy_Skeleton(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Oil_of_Poison extends Treasure{
		public Oil_of_Poison(){
			setGt_Treasure(false);
			Lg_Treasure = true;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Ointment_of_Bite extends Treasure{
		public Ointment_of_Bite(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 5;
		}
	}
	
	public class Ointment_of_Steel extends Treasure{
		public Ointment_of_Steel(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 4;
		}
	}
	
	public class Penetrating_Grease extends Treasure{
		public Penetrating_Grease(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 4;
		}
	}
	
	public class Phantom_Glass extends Treasure{
		public Phantom_Glass(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 8;
		}
	}
	
	public class Potion_of_Energy extends Treasure{
		public Potion_of_Energy(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 5;
		}
	}
	
	public class Poultice_of_Health extends Treasure{
		public Poultice_of_Health(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 2;
		}
	}
	
	public class Power_Boots extends Treasure{
		public Power_Boots(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 3;
			gold_price = 8;
		}
	}
	
	public class Power_Gauntlets extends Treasure{
		public Power_Gauntlets(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 3;
			notoriety_value = 4;
			gold_price = 7;
		}
	}
	
	public class Quick_Boots extends Treasure{
		public Quick_Boots(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 1;
			notoriety_value = 2;
			gold_price = 8;
		}
	}
	
	public class Reflecting_Grease extends Treasure{
		public Reflecting_Grease(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
	
	public class Regent_of_Jewels extends Treasure{
		public Regent_of_Jewels(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 10;
			gold_price = 67;
		}
	}
	
	public class Remain_of_Thief extends Treasure{
		public Remain_of_Thief(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Royal_Sceptre extends Treasure{
		public Royal_Sceptre(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = -15;
			gold_price = 8;
		}
	}
	
	
	
	public class Sacred_Grail extends Treasure{
		public Sacred_Grail(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = -25;
			gold_price = 12;
		}
	}
	
	public class Sacred_Statue extends Treasure{
		public Sacred_Statue(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = -5;
			gold_price = 10;
		}
	}
	
	public class Scroll_of_Alchemy extends Treasure{
		public Scroll_of_Alchemy(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = -10;
			notoriety_value = 15;
			gold_price = 10;
		}
	}
	
	public class Scroll_of_Nature extends Treasure{
		public Scroll_of_Nature(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 5;
			gold_price = 10;
		}
	}
	
	public class Shielded_Lantern extends Treasure{
		public Shielded_Lantern(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 8;
		}
	}
	
	public class Shoes_of_Stealth extends Treasure{
		public Shoes_of_Stealth(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 2;
			gold_price = 7;
		}
	}
	
	public class Timeless_Jewel extends Treasure{
		public Timeless_Jewel(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 7;
			gold_price = 34;
		}
	}
	
	public class Toadstool_Circle extends Treasure{
		public Toadstool_Circle(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 0;
		}
	}
	
	public class Toadstool_Ring extends Treasure{
		public Toadstool_Ring(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 3;
			gold_price = 9;
		}
	}
	
	public class Vial_of_Healing extends Treasure{
		public Vial_of_Healing(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 2;
		}
	}
	
	public class Withered_Claw extends Treasure{
		public Withered_Claw(){
			setGt_Treasure(false);
			Lg_Treasure = false;
			fame_value = 0;
			notoriety_value = 0;
			gold_price = 3;
		}
	}
}