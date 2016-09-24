package ocdSim;


import processing.core.*;

import java.util.*;

public class OCDSimulator extends PApplet {

	/*should function like an enum class. Consider all of these objects "readonly". */
	private static Concern ORGANIC;
	private static Concern BPA;
	private static Concern HOT;
	private static Concern UNFILTERED;
	private static Concern MOULDY; 

	private static int jewelsCollected;
	private static ModeTransitioner transitioner;
	private static GameMode currentMode;
	private static int sky;
	private static Texture horizon;
	private static ColorTexture ground;
	private static PImage EMPTY_IMAGE;

	private static ArrayList<ConcernEvent> events=new ArrayList<ConcernEvent>();
	private static int currConcernEvent;
	private static ArrayList<FoodType> foodTypes=new ArrayList<FoodType>();
	private Overworld overWorld;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ocdSim.OCDSimulator");

	}

	

	public void settings(){
		size(displayWidth, displayHeight,P3D);
		PComponent.setPApplet(this);

	}

	public void setup(){
		initialize();
		
	}

	public void draw(){
		currentMode.run();

	}

	void initialize() {
		loadAssets(); //loading the assets has to happen first
		transitioner=new ModeTransitioner();
		currConcernEvent=-1;
		jewelsCollected=0;
		overWorld=new Overworld();
		currentMode=overWorld;


	}

	


	//at some point, factor this out into a separate class.
	public static void updateJewelsCollected(int change){
		jewelsCollected+=change;
	}
	
	public static int getJewelsCollected(){
		return jewelsCollected;
	}

	public static ModeTransitioner getModeTransitioner(){
		return transitioner;
	}

	public static void setCurrentGameMode(GameMode newMode){
		currentMode=newMode;
	}

	public FoodType getRandomFoodType(){
		return foodTypes.get((int)random(foodTypes.size()));	

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


	private void loadAssets(){
		sky=color(255);
		horizon=new ImageTexture(loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/bg.tif"));
		ground=new ColorTexture(color(96, 165, 7));
		EMPTY_IMAGE=loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/empty.png");
		initializeFoodTypes();
		initializeConcerns();
		initializeConcernEvents();
	}
	
	public static Texture getGround(){
		return ground;
	}

	public static ConcernEvent getNextConcernEvent() {

		if (currConcernEvent<events.size()-1) currConcernEvent++;
		return events.get(currConcernEvent);
	}


	public FoodType getFoodType() {

		return foodTypes.get((int)random(foodTypes.size()));
	}




	private void initializeFoodTypes() {
		FoodType apple=new FoodType(loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/produce/Apple.png"), "Apple", "Eat the apple?");
		apple.addConcern(ORGANIC);
		foodTypes.add(apple);
	}
	

	
	private void initializeConcerns(){
		/*should function like an enum class. Consider all of these objects "readonly". */
		ORGANIC=new Concern("Organic", .5f);
		BPA=new Concern("BPA", .5f);
		HOT=new Concern("Hot", .5f);
		UNFILTERED=new Concern("Unfiltered", .5f);
		MOULDY=new Concern("Mouldy", .5f); //starts for peanuts; generalizes to all nuts; then to all squashes; etc.

	}


	private void initializeConcernEvents() {
	}

}
