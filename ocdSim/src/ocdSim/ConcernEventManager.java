package ocdSim;

import java.util.ArrayList;

public class ConcernEventManager {


	private static ArrayList<ConcernEvent> concernEvents=new ArrayList<ConcernEvent>();
	private static int currConcernEvent;
	
	
	public ConcernEventManager(){
		currConcernEvent=-1;
	}
	
	public static ConcernEvent getNextConcernEvent() {

		if (currConcernEvent<concernEvents.size()-1) currConcernEvent++;
		return concernEvents.get(currConcernEvent);
	}

}
