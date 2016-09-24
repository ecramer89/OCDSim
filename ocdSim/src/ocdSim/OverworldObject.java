package ocdSim;
import processing.core.*;
import java.util.*;


/*a class that represents an object that can appear in the overworld.*/
public abstract class OverworldObject extends PComponent {

	protected PVector pos, dim;
	protected  float checkRadius;
	protected  PVector absoluteTranslation; //sum of the translations from upper left corner of all the object's enclosing objects (i.e., the gameworld and the moveable npc map). for use in the collision checks.

	/* variables controlling the rotation parameters of the gameobjects. gameobjects have the ability to turn towards a target*/
	protected PVector quaternion;
	protected float targetAngleY;
	protected float dAngleY;
	protected float yAngleGive;

	public OverworldObject(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float checkRadius, PVector quaternion) {
		this.pos=pos;
		this.dim=dim;
		this.checkRadius=checkRadius;
		absoluteTranslation=pos.get();
		this.quaternion=quaternion;
		updatePerceivedTranslation(parentTranslationX, parentTranslationZ);
		dAngleY=pSimulator.radians(1);
		yAngleGive=pSimulator.radians(1);
	}

	public PVector absoluteTranslation() {
		return absoluteTranslation;
	}

	void turnTowards(float angle) {
		targetAngleY=angle;
	}

	/* GameObjects only exist within the game world*/
	public abstract void handleCollisionWithPlayer();

	public void updatePerceivedTranslation(float parentTranslationX, float parentTranslationZ) {
		absoluteTranslation.x=pos.x+parentTranslationX;
		absoluteTranslation.z=pos.z+parentTranslationZ;
	}

	public float checkRadius() {
		return checkRadius;
	}


	protected abstract void render();

	public void update() {
		turnTowardsTarget();
		draw();
	}


	protected void turnTowardsTarget() {
		if (pSimulator.abs(targetAngleY-quaternion.y)>yAngleGive) {
			if (targetAngleY>quaternion.y) {
				dAngleY=pSimulator.abs(dAngleY);
			} else {
				dAngleY=-pSimulator.abs(dAngleY);
			}
			quaternion.y+=dAngleY;
		}
	}

	private void draw() {
		pSimulator.pushMatrix();
		pSimulator.pushStyle();
		pSimulator.translate(pos);
		pSimulator.rotate(quaternion);
		render();
		pSimulator.popStyle();
		pSimulator.popMatrix();
	}
}