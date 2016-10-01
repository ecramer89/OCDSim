package ocdSim;
import processing.core.*;
import java.util.*;

/* a container for the information that is specific to different instances of Food that appear in the overworld. */
public class FoodObjectData extends OCDSimComponent {
	private FoodType type; //the type of food that this food object is
	private  Map<Concern, Boolean> concerns=new HashMap<Concern, Boolean>(); //the results of each check on each concern

	  public FoodObjectData(FoodType type) {
	    this.type=type;
	    for (Concern concern : type.getConcerns()) {
	      concerns.put(concern, null);
	    }
	  }
	  
	  public  float getEnergy(){
	    return type.getEnergy();
	    
	  }
	  
	  public  String getLabel(){
	   return type.getLabel();
	  }
	  
	  public String getRequestToEat(){
	    return type.getRequestToEat();
	  }
	  
	  public ArrayList<Concern> getConcerns(){
	    return type.getConcerns();
	  }
	  
	  public PImage getImage(){
	    return type.getImage();
	  }

	  public void storeResultOfCheck(Concern concern, boolean result) {
	    concerns.put(concern, new Boolean(result));
	  }

	  boolean getResultFor(Concern concern) {
	    Boolean result=concerns.get(concern);
	    return result.booleanValue();
	  }

	  boolean hasResultFor(Concern concern) {
	    return concerns.get(concern)!=null;
	  }
	
}
