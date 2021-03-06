package ocdSim;
import processing.core.*;
import java.util.*;

public class ConcernEvent extends OCDSimComponent {
	private ArrayList<PImage> screens;
	private ArrayList<String> messages;
	private int currMessage=-1;
	private int currScreen=-1;
	
	
	private ArrayList<FoodType> foodsAffected; //some concernevents can generalize a concern to food types that previously weren't affected
	  //by this concern (i.e., they can add it to the list; all subsequent food objects will inherit it)
	private Concern concern;

	  public ConcernEvent(Concern concern, ArrayList<PImage> screens, ArrayList<String> messages) {
	    this.screens=screens;
	    this.messages=messages;
	    this.concern=concern;
	  }
	  
	  public String nextMessage(){
	    if(currMessage<messages.size()-1) currMessage++;
	    return messages.get(currMessage);
	    
	  }
	  
	  public PImage nextImage(){
	    if(currScreen<screens.size()-1) currScreen++;
	    return screens.get(currScreen);
	    
	    
	    
	  }

	//!!to do, not all events will activate a concern... some might, some might deactivate a concern, some might add the concern to foods,
	//some might remove them. need to re design to support this variety
	  public  void trigger() {
	    concern.activate();
	    for(FoodType type : foodsAffected){
	     type.addConcern(concern); 
	    }
	  }
}
