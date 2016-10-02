package ocdSim;

import java.util.ArrayList;

public class FoodTypeManager extends OCDSimComponent {
	
	private static ArrayList<FoodType> foodTypes=new ArrayList<FoodType>();
	
	
	public FoodTypeManager(){
		FoodType apple=new FoodType(ocdSimulator.loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/produce/Apple.png"), "Apple", "Eat the apple?");
		apple.addConcern(ocdSimulator.getConcernManager().getConcern(ConcernManager.ORGANIC));
		foodTypes.add(apple);
	}
	
	public FoodType getRandomFoodType(){
		return foodTypes.get((int)ocdSimulator.random(foodTypes.size()));	

	}
	
	
	
	
}
