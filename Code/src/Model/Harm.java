package Model;

public class Harm {

	//also strength if monster value
	int weight;		//n,L,M,H,T
					//0,1,2,3,4
	
	int sharpness;	// ,*,**,***
					//0,1,2 ,3
	
	int time;		//attack time
	
	//constructor
	public Harm(int weig, int shar, int ti){
		weight = weig;
		sharpness = shar;
		time = ti;
	}
}
