package ocdSim;

import java.util.ArrayList;

public class FoodTypeManager extends PComponent {
	
	private static ArrayList<FoodType> foodTypes=new ArrayList<FoodType>();
	
	
	public FoodTypeManager(){
		FoodType apple=new FoodType(pSimulator.loadImage("C:/Users/root960/OCDSim/ocdSim/data/images/produce/Apple.png"), "Apple", "Eat the apple?");
		apple.addConcern(pSimulator.getConcernManager().getConcern(ConcernManager.ORGANIC));
		foodTypes.add(apple);
	}
	
	public FoodType getRandomFoodType(){
		return foodTypes.get((int)pSimulator.random(foodTypes.size()));	

	}
	
	
	
	
}
