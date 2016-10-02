package ocdSim;

import processing.core.*;

public class OCDSimComponent {
    
	protected static OCDSimulator ocdSimulator;
	
	
	public static void setPApplet(OCDSimulator pSimulator){
		OCDSimComponent.ocdSimulator=pSimulator;
	}
	
	
}
