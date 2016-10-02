package ocdSim;

import processing.core.PImage;

public class AssetManager extends OCDSimComponent {

	private static int sky;
	private static ColorTexture ground;
	private static PImage EMPTY_IMAGE;
	
	public AssetManager(){
		sky=ocdSimulator.color(255);
		ground=new ColorTexture(ocdSimulator.color(96, 165, 7));
		EMPTY_IMAGE=ocdSimulator.loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/empty.png");
		
	}
	
	
	public static Texture getGround(){
		return ground;
	}

}
