package ocdSim;
import processing.core.*;
import java.util.*;
public class OverworldFood extends OverworldObject {


	private FoodObjectData data;

	public OverworldFood(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float triggerRadius, PVector quaternion, FoodObjectData data) {
		super(pos, dim, parentTranslationX, parentTranslationZ, triggerRadius, quaternion);
		this.data=data;
	}


	@Override
	public void handleCollisionWithPlayer() {
		OCDSimulator.getModeTransitioner().encounteredFood(this);

	}

	public FoodObjectData getFoodData() {
		return data;
	}

	@Override
	protected void render() {
		pSimulator.image(data.getImage(), -dim.x/2, -dim.y*2);

	}

}
