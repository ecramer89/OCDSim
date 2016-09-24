package ocdSim;

import processing.core.*;

public class OverworldFoodFactory extends PComponent implements OverworldObjectFactory {

	@Override
	public OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ,
			float checkRadius, PVector quaternion) {
		dim.y=dim.x;
	    return new OverworldFood(pos, dim, parentTranslationX, parentTranslationZ, checkRadius, quaternion, new FoodObjectData(pSimulator.getRandomFoodType()));
	  
	}

}
