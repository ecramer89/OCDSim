package ocdSim;
import processing.core.*;
import java.util.*;


/*a class that represents an object that can appear in the overworld.*/
public abstract class OverworldObject extends OCDSimComponent {

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
		dAngleY=ocdSimulator.radians(1);
		yAngleGive=ocdSimulator.radians(1);
	}
	
	
	public OverworldObject(){
		this(new PVector(), new PVector(), 0, 0, 0, new PVector());
	}
	
	public abstract OverworldObject generate(PVector pos, PVector dim, float parentTranslationX, float parentTranslationZ, float checkRadius, PVector quaternion);

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
		if (ocdSimulator.abs(targetAngleY-quaternion.y)>yAngleGive) {
			if (targetAngleY>quaternion.y) {
				dAngleY=ocdSimulator.abs(dAngleY);
			} else {
				dAngleY=-ocdSimulator.abs(dAngleY);
			}
			quaternion.y+=dAngleY;
		}
	}

	private void draw() {
		ocdSimulator.pushMatrix();
		ocdSimulator.pushStyle();
		ocdSimulator.translate(pos);
		ocdSimulator.rotate(quaternion);
		render();
		ocdSimulator.popStyle();
		ocdSimulator.popMatrix();
	}
}
