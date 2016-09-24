package ocdSim;
import processing.core.*;
import java.util.*;
public class OverworldJewel extends OverworldObject {

	int fill;
	int faces;
	float faceTheta=pSimulator.PI/4;

	public OverworldJewel(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float triggerRadius, PVector quaternion) {
		super(pos, dim, parentTranslationX, parentTranslationZ, triggerRadius, quaternion);
		float rand=pSimulator.random(1);
		fill=(rand<.15? pSimulator.color(255, 0, 0): rand<.3? pSimulator.color(0, 255, 0) : rand<.6?pSimulator.color(0, 0, 255): rand<.9? pSimulator.color(255, 255, 0) : pSimulator.color(225, 0, 255));
		faces=(int)(pSimulator.TWO_PI/faceTheta);
	}


	@Override
	public void update() {
		super.update();
		//rotate clockwise slowly
		quaternion.y+=.02;
	}

	@Override
	public void handleCollisionWithPlayer() {
		OCDSimulator.updateJewelsCollected(1);

	}

	@Override
	protected void render() {
		pSimulator.noStroke();
		pSimulator.rotateZ(pSimulator.PI/4);

		for (int i=0; i<faces; i++) {
			pSimulator.fill(pSimulator.red(fill)/(i+1), pSimulator.green(fill)/(i+1), pSimulator.blue(fill)/(i+1));
			pSimulator.rotateY(faceTheta);
			pSimulator.rect(-dim.x/2, -dim.y*2, dim.x, dim.y);
		}

	}

}
