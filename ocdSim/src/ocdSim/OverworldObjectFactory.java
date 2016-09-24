package ocdSim;
import processing.core.*;
import java.util.*;
public interface OverworldObjectFactory {

	
	public OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float checkRadius, PVector quaternion); 
	  
}
