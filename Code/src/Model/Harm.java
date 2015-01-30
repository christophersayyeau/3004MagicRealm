package Model;

public class Harm {

	int weight;		//n,L,M,H,T
					//0,1,2,3,4
	
	int sharpness;	// ,*,**,***
					//0,1,2 ,3
	
	int time;		//attack time
	
	public Harm(int weig, int shar, int ti){
		weight = weig;
		sharpness = shar;
		time = ti;
	}
}
