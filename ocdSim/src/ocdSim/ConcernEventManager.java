package ocdSim;

import java.util.ArrayList;


/*a concern event is a list of screen that tell the story of how a given concern was triggered.
 * a concern event might result in a new concern being activated, deactivated or a concern being added or removed to the template foodtypes*/
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
