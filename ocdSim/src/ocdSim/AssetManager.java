package ocdSim;

import processing.core.PImage;

public class AssetManager extends PComponent {

	private static int sky;
	private static ColorTexture ground;
	private static PImage EMPTY_IMAGE;
	
	public AssetManager(){
		sky=pSimulator.color(255);
		ground=new ColorTexture(pSimulator.color(96, 165, 7));
		EMPTY_IMAGE=pSimulator.loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/empty.png");
		
	}
	
	
	public static Texture getGround(){
		return ground;
	}

}
