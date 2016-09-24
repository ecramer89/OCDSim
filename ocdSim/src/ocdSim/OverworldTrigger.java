package ocdSim;
import processing.core.*;
import java.util.*;
public class OverworldTrigger extends OverworldObject {
	
	public OverworldTrigger(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float triggerRadius, PVector quaternion) {
	    super(pos, dim, parentTranslationX, parentTranslationZ, triggerRadius, quaternion);
	  }

	   @Override
	  public void handleCollisionWithPlayer() {
	    //overWorld.activateConcernEvent();
	    //it should also tell the gameworld to create a quest that is somehow linked to this concern and put it in the same place where the concern was
	  }
	   
	   
	   @Override
	 public void render() {
	    pSimulator.fill(255);
	    pSimulator.rect(-dim.x/2, -dim.y*2, dim.x, dim.y);
	  }

}