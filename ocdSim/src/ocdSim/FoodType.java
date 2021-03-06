package ocdSim;
import processing.core.*;
import java.util.*;

public class FoodType {
	
	//the concerns that are active/attached to a particular FoodType (i.e., all Apples) can change.
	//therefore, this is not a proper enumeration.
	
	private ArrayList<Concern> concerns=new ArrayList<Concern>(); //the concerns that apply to this type of food
	private String label; //the label for this type of food
	private String requestToEat; //each food can remember a special message requesting the player to eat it
	private PImage img; //this food's image
	private float energyProvided; //the amount of energy this food provides if it's eaten


	  public FoodType(PImage img, String label, String requestToEat) {
	    this.img=img;
	    this.label=label;
	    this.requestToEat=requestToEat;
	  }

	  public String getLabel() {
	    return label;
	  }

	  public PImage getImage() {
	    return img;
	  }

	  public void addConcern(Concern concern) {
	    concerns.add(concern);
	  }

	  public ArrayList<Concern> getConcerns() {
	    return concerns;
	  }

	  public void setEnergy(float energy) {
	    this.energyProvided=energy;
	  }
	  public float getEnergy() {
	    return energyProvided;
	  }

	  public String getRequestToEat() {
	    return requestToEat;
	  }

}
