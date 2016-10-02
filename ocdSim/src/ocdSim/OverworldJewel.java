package ocdSim;
import processing.core.*;
import java.util.*;
public class OverworldJewel extends OverworldObject {

	int fill;
	int faces;
	float faceTheta=ocdSimulator.PI/4;

	public OverworldJewel(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float triggerRadius, PVector quaternion) {
		super(pos, dim, parentTranslationX, parentTranslationZ, triggerRadius, quaternion);
		float rand=ocdSimulator.random(1);
		fill=(rand<.15? ocdSimulator.color(255, 0, 0): rand<.3? ocdSimulator.color(0, 255, 0) : rand<.6?ocdSimulator.color(0, 0, 255): rand<.9? ocdSimulator.color(255, 255, 0) : ocdSimulator.color(225, 0, 255));
		faces=(int)(ocdSimulator.TWO_PI/faceTheta);
	}


	public OverworldJewel() {
		super();
	}


	@Override
	public void update() {
		super.update();
		//rotate clockwise slowly
		quaternion.y+=.02;
	}

	@Override
	public void handleCollisionWithPlayer() {
		ocdSimulator.getGameParameters().updateJewelsCollected(1);

	}

	@Override
	protected void render() {
		ocdSimulator.noStroke();
		ocdSimulator.rotateZ(ocdSimulator.PI/4);

		for (int i=0; i<faces; i++) {
			ocdSimulator.fill(ocdSimulator.red(fill)/(i+1), ocdSimulator.green(fill)/(i+1), ocdSimulator.blue(fill)/(i+1));
			ocdSimulator.rotateY(faceTheta);
			ocdSimulator.rect(-dim.x/2, -dim.y*2, dim.x, dim.y);
		}

	}


	@Override
	public OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ,
			float checkRadius, PVector quaternion) {
			dim.y=dim.x;
		    return new OverworldJewel(pos, dim, parentTranslationX, parentTranslationZ, checkRadius, quaternion);

	}


}
