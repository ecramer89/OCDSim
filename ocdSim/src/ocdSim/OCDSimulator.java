package ocdSim;


import processing.core.*;

import java.util.*;

public class OCDSimulator extends PApplet {

	private ConcernManager concernManager;
    private ConcernEventManager concernEventManager;
    private FoodTypeManager foodTypeManager;
    private GameParameters gameParameters;
    private AssetManager assetManager;
	private static ModeTransitioner transitioner;
	private static GameMode currentMode;
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ocdSim.OCDSimulator");

	}

	

	public void settings(){
		size(800, 800,P3D);
		OCDSimComponent.setPApplet(this);

	}

	public void setup(){
		concernManager=new ConcernManager();
		transitioner=new ModeTransitioner();
		concernEventManager=new ConcernEventManager();
		setAssetManager(new AssetManager());
		setFoodTypeManager(new FoodTypeManager());
		setGameParameters(new GameParameters());
		transitioner.beginGame();
		
	}

	public void draw(){
		currentMode.run();
	}


	public static ModeTransitioner getModeTransitioner(){
		return transitioner;
	}
	
	public ConcernManager getConcernManager() {
		return concernManager;
	}


	public static void setCurrentGameMode(GameMode newMode){
		currentMode=newMode;
	}



	public void keyPressed() {
		currentMode.handleKeyPressed();
	}

	public void mousePressed() {
		currentMode.handleMousePressed();
	}

	public void mouseReleased(){
		currentMode.handleMouseReleased();

	}


	public void keyReleased() {
		currentMode.handleKeyReleased();
	}


	public void translate(PVector translation) {
		translate(translation.x, translation.y, translation.z);
	}

	public void rotate(PVector quaternion) {
		rotate(quaternion.z);
		rotateX(quaternion.x);
		rotateY(quaternion.y);
	}



	public AssetManager getAssetManager() {
		return assetManager;
	}



	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}



	public GameParameters getGameParameters() {
		return gameParameters;
	}



	public void setGameParameters(GameParameters gameParameters) {
		this.gameParameters = gameParameters;
	}



	public FoodTypeManager getFoodTypeManager() {
		return foodTypeManager;
	}



	public void setFoodTypeManager(FoodTypeManager foodTypeManager) {
		this.foodTypeManager = foodTypeManager;
	}



	



	

	




}
