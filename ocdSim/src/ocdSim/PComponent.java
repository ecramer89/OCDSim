package ocdSim;

import processing.core.*;

public class PComponent {

	protected static OCDSimulator pSimulator;
	
	public static void setPApplet(OCDSimulator pSimulator){
		PComponent.pSimulator=pSimulator;
	}
}