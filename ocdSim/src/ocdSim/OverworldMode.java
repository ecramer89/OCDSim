package ocdSim;
import processing.core.*;
import java.util.*;
public class OverworldMode extends OCDSimComponent implements GameMode{

		public static final int RESET_QUIT_DELAY=90;
		private GameWorld gameWorld;
		private CameraController cameraController;
		private NavigationInputHandler inputHandler;
		private CollisionChecker collisionChecker;
		private LayoutReader layoutReader;

		//stuff that goes on the UI
		final float DEPLETION_RATE=.05f;
		private EnergyBar energyBar;

		boolean left, up, down, right, mouseDown;

		private OverworldFood pendingFood; 
		private Concern pendingConcern;


		public OverworldMode() {

			layoutReader=new LayoutReader();
			gameWorld=new GameWorld(new PVector(ocdSimulator.width/2, ocdSimulator.height/2), ocdSimulator.width, ocdSimulator.height, ocdSimulator.height*2);
			gameWorld.mapTextures(null, null, null, null, ocdSimulator.getAssetManager().getGround());
			cameraController=new CameraController();
			inputHandler=new NavigationInputHandler();
			collisionChecker=new CollisionChecker(cameraController);
			ocdSimulator.textAlign(ocdSimulator.CENTER);
			PVector energyBarDimensions=new PVector(ocdSimulator.width/3, 50);
			energyBar=new EnergyBar(new PVector(energyBarDimensions.x/2, energyBarDimensions.y/2, 0), energyBarDimensions);
		}



		public OverworldFood getPendingFood() {
			return pendingFood;
		}

		private void repleteEnergy(float amount) {
			energyBar.replete(amount);
		}


		public void run() {
			drawBackGround();
			inputHandler.update();
			collisionChecker.update();
			gameWorld.draw();
			cameraController.viewScene();
			updateEnergyBar();
			showjewelsCollectedCollected();
		}


		private void showjewelsCollectedCollected() {
			ocdSimulator.pushMatrix();
			ocdSimulator.pushStyle();
			ocdSimulator.translate(ocdSimulator.width-200, 80);
			ocdSimulator.fill(255);
			ocdSimulator.textSize(30);
			ocdSimulator.text("Jewels:  "+ocdSimulator.getGameParameters().getJewelsCollected(), 0, 0);
			ocdSimulator.popMatrix();
			ocdSimulator.popStyle();
		}


		public void prepareToRun() {
			pendingFood=null;
			left=false;
			right=false;
			up=false;
			down=false;
		}

		public void handleKeyPressed() {
			if (ocdSimulator.key==ocdSimulator.CODED) {
				if (ocdSimulator.keyCode==ocdSimulator.LEFT) {
					left=true;
				}

				if (ocdSimulator.keyCode==ocdSimulator.UP) {
					up=true;
				}

				if (ocdSimulator.keyCode==ocdSimulator.DOWN) {
					down=true;
				}

				if (ocdSimulator.keyCode==ocdSimulator.RIGHT) {
					right=true;
				}
			}
		}
		
		@Override
		public void handleKeyReleased() {
			if (ocdSimulator.key==ocdSimulator.CODED) {
				if (ocdSimulator.keyCode==ocdSimulator.LEFT) {
					left=false;
				}

				if (ocdSimulator.keyCode==ocdSimulator.UP) {
					up=false;
				}

				if (ocdSimulator.keyCode==ocdSimulator.DOWN) {
					down=false;
				}

				if (ocdSimulator.keyCode==ocdSimulator.RIGHT) {
					right=false;
				}
			}
			
		}

		@Override
		public void handleMousePressed() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void handleMouseReleased() {
			// TODO Auto-generated method stub
			
		}

	


		public void drawBackGround() {
			ocdSimulator.background(0);
		}


		public void updateEnergyBar() {
			if (ocdSimulator.frameCount%60==0) {
				energyBar.deplete(DEPLETION_RATE);
			}
			if (energyBar.empty()) {
				OCDSimulator.getModeTransitioner().gameOver();
			}
			energyBar.draw();
		}




		/*private inner class to handle navigating through the overworld*/
		class NavigationInputHandler {
			final float D_ROTATE=ocdSimulator.radians(2);
			final float SCROLL_SPEED=5;
			float angleEyeGameWorld;
			float currViewAngle;
			PVector forward;
			PVector backward;
			PVector leftWard=new PVector(0, -D_ROTATE, 0);
			PVector rightWard=new PVector(0, D_ROTATE, 0);


			NavigationInputHandler() {
				initializeVectors();
				updateVectors();
			}

			void initializeVectors() {
				angleEyeGameWorld=ocdSimulator.atan2(cameraController.eyePosition.z-gameWorld.origin.z, cameraController.eyePosition.x-gameWorld.origin.x);
				forward=new PVector();
				backward=new PVector();
			}

			/*update the forward/backward vectors so that the objects move towards the player regardless of the rotation of the gameworld.
     because we never actually move the origins of either the gameworld or the eye, we move the objects towards the point
     where the eye would be if -it- had moved such that its angle with the gameworld origin would be equal to that of the gameworld origin's current
     rotation*/

			void updateVectors() {
				currViewAngle=angleEyeGameWorld+gameWorld.getAngle();
				forward.x=PApplet.cos(currViewAngle)*SCROLL_SPEED;
				forward.z=PApplet.sin(currViewAngle)*SCROLL_SPEED;
				currViewAngle+=PConstants.PI; //backwards
				backward.x=PApplet.cos(currViewAngle)*SCROLL_SPEED; 
				backward.z=PApplet.sin(currViewAngle)*SCROLL_SPEED;
			}


			void update() {
				updateVectors();
				rotateByKey();
				scrollByKey();
				panByMouse();
			}



			void panByMouse() {
				if (mouseDown) {
					cameraController.eyePosition.y+=ocdSimulator.mouseY-ocdSimulator.pmouseY;
				}
			}


			void rotateByKey() {
				if (left) {
					gameWorld.turn(leftWard);
				}
				if (right) {
					gameWorld.turn(rightWard);
				}
			}


			void scrollByKey() {
				if (up) {
					gameWorld.move(forward);
				}
				if (down) {
					gameWorld.move(backward);
				}
			}
		}



		/* a class whose job is to check whether a given scrollable object in in the "collision view" of the camera.
   Because this class must know the details of the gameWorld's current angle, its origin, as well as the fixed position of the camera,
   I have placed the logic in its own class */

		class CollisionChecker {
			//a window around the camera in which objects are "within view"
			float distanceOriginViewOrigin; //cache the absolute distance between the game world origin and the origin of the camera view zone
			float angleOriginViewOrigin; //the initial angle between the origin of the gameworld and the position of the camera view zone
			CameraController camController;
			float currPerceivedViewZoneX, currPerceivedViewZoneZ; //taking the gameworld rotation into account, the current 
			//apparent position of the camera view zone

			CollisionChecker(CameraController camController) {
				this.camController=camController;
				distanceOriginViewOrigin=ocdSimulator.dist(camController.viewOrigin.x, camController.viewOrigin.z, gameWorld.origin.x, gameWorld.origin.z);
				angleOriginViewOrigin=ocdSimulator.atan2(camController.viewOrigin.z-gameWorld.origin.z, camController.viewOrigin.x-gameWorld.origin.x); //angle between origin and the testing point
			}


			void update() {
				//update the current perceived camera view zone location, given the change in the gameworld y rotation
				currPerceivedViewZoneX=gameWorld.origin.x+ocdSimulator.cos(angleOriginViewOrigin+gameWorld.quaternion.y)*distanceOriginViewOrigin;
				currPerceivedViewZoneZ=gameWorld.origin.z+ocdSimulator.sin(angleOriginViewOrigin+gameWorld.quaternion.y)*distanceOriginViewOrigin;
			}

			/*perceivedTranslationX, Y: the displacement of the object from the gameWorld origin.
     triggerRadius: radius of the trigger circle drawn on the plane around each object, which we use to verify collision with
     the camera view radius */
			boolean collidedWithCamera(float perceivedTranslationX, float perceivedTranslationZ, float triggerRadius) {
				//1. calculate the distance between the perceived translation and the gameworld origin
				float distPointOrigin=ocdSimulator.dist(perceivedTranslationX, perceivedTranslationZ, gameWorld.origin.x, gameWorld.origin.z);
				if (ocdSimulator.abs(distanceOriginViewOrigin-distPointOrigin)>cameraController.viewRadius+triggerRadius) return false; //the two circles must have same diameter (*taking the sizes of the points on the circles into account)
				//2. calculate angle between gameworld and the perceived translation of the point
				float anglePointOrigin=ocdSimulator.atan2(perceivedTranslationZ-gameWorld.origin.z, perceivedTranslationX-gameWorld.origin.x);
				//3. calculate the perceived point of the object, taking the angle between the gameworld and perceived translation into account
				float perceivedX=gameWorld.origin.x+ocdSimulator.cos(anglePointOrigin)*distPointOrigin;
				float perceivedZ=gameWorld.origin.z+ocdSimulator.sin(anglePointOrigin)*distPointOrigin;
				return ocdSimulator.dist(currPerceivedViewZoneX, currPerceivedViewZoneZ, perceivedX, perceivedZ)<cameraController.viewRadius+triggerRadius;
			}


			boolean collidedWithCamera(OverworldObject gameObject) {
				return collidedWithCamera(gameObject.absoluteTranslation().x, gameObject.absoluteTranslation().z, gameObject.checkRadius());
			}
		}





		/* from now on, you will only be able to "move" forward or backwards. you will move "left" and "right" by rotating the gameworld first.
   I dont want to actually change the position of the camera. it should stay fixed. Likewie,
   the box won't move. only the scroll able objects will move (NPCS and other objects)*/

		class CameraController {

			//camera variables
			float ANGLE=ocdSimulator.PI/2;
			PVector sceneCenter=new PVector();
			PVector eyePosition=new PVector();
			PVector upwardAxis=new PVector(0, 1, 0);
			PVector nextPosition=new PVector();

			//a window around the camera in which objects are "within view"
			float viewRadius=50;
			PVector viewOrigin;


			CameraController() {
				restoreDefaults();
			}

			void pan(PVector towards) {
				sceneCenter.add(towards);
				eyePosition.add(towards);
			}



			void viewScene() {
				ocdSimulator.camera(eyePosition.x, eyePosition.y, eyePosition.z, sceneCenter.x, sceneCenter.y, sceneCenter.z, upwardAxis.x, upwardAxis.y, upwardAxis.z);
			}

			void restoreDefaults() {
				sceneCenter.x=ocdSimulator.width/2;
				sceneCenter.y=ocdSimulator.height/2;
				sceneCenter.z=0;
				eyePosition.z=gameWorld.southernBoundary();
				eyePosition.x=sceneCenter.x;
				eyePosition.y=sceneCenter.y-50;
				viewOrigin=new PVector(eyePosition.x, gameWorld.origin.y, eyePosition.z-viewRadius);
			}
		}



		/* a class that represents a GameWorld. if we decide to have extra stages we should create additional game world objects (they function like levels)*/
		class GameWorld {
			Box box;
			PVector origin, quaternion=new PVector();
			GameObjectMap gameObjectMap;




			GameWorld(PVector origin, int width, int height, int length) {
				this.origin=origin;
				box=new Box(origin.get(), width, height, length);
				gameObjectMap=new GameObjectMap(new PVector(), new PVector(width, length), origin.x, origin.z);
				turn(new PVector(0, ocdSimulator.TWO_PI, 0));
				layoutReader.populateMap(gameObjectMap, layoutReader.layout1, this);
			}

			float getAngle() {
				return quaternion.y;
			}


			/* returns true if the gameObject is within the boundaries of the ground plane*/
			boolean inView(OverworldObject gameObject) {
				return box.isInside(gameObject);
			}

			float northernBoundary() {
				return box.northernBoundary;
			}

			PVector dimensions() {
				return box.dimensions;
			}

			float easternBoundary() {
				return box.easternBoundary;
			}


			float westernBoundary() {
				return box.westernBoundary;
			}

			float southernBoundary() {
				return box.southernBoundary;
			}

			void mapTextures(Texture north, Texture south, Texture east, Texture west, Texture bottom) {
				box.mapTextures(north, south, east, west, bottom);
			}


			void draw() {
				ocdSimulator.pushMatrix();
				ocdSimulator.translate(origin);
				ocdSimulator.rotate(quaternion);
				box.draw();
				gameObjectMap.draw();    
				ocdSimulator.popMatrix();
			}

			void turn(PVector heading) {
				quaternion.add(heading);
			}

			void move(PVector translation) {
				gameObjectMap.move(translation);
			}
		}


		class GameObjectMap {
			ArrayList<OverworldObject> gameObjects=new ArrayList<OverworldObject>();
			PVector origin, dim;
			PVector absoluteTranslation; //sum of the translations from upper left corner of all the object's enclosing objects (i.e., the gameworld and the moveable npc map). for use in the collision checks.



			GameObjectMap(PVector origin, PVector dim, float parentTranslationX, float parentTranslationZ) {
				this.origin=origin;
				this.dim=dim;
				absoluteTranslation=origin.get();
				absoluteTranslation.x+=parentTranslationX;
				absoluteTranslation.z+=parentTranslationZ;
			}

			void addGameObject(OverworldObject gameObject) {
				gameObjects.add(gameObject);
			}



			void move(PVector translation) {
				origin.add(translation);
				absoluteTranslation.add(translation);
			}


			void update() {
				draw();
			}

			void draw() {
				ocdSimulator.pushMatrix();
				ocdSimulator.translate(origin);
				updateOverworldObjects();
				ocdSimulator.popMatrix();
			}


			void updateOverworldObjects() {
				OverworldObject gameObject;
				for (int i=0; i<gameObjects.size(); i++) {
					gameObject = gameObjects.get(i);
					if (gameWorld.inView(gameObject)) {
						gameObject.turnTowards(-gameWorld.quaternion.y);
						gameObject.update();
						gameObject.updatePerceivedTranslation(absoluteTranslation.x, absoluteTranslation.z);
						if (collisionChecker.collidedWithCamera(gameObject)) {
							gameObject.handleCollisionWithPlayer();
							gameObjects.remove(gameObject);
						}
					}
				}
			}
		}




		class EnergyBar {
			float MAX_ENERGY=1.0f;
			float currEnergy=MAX_ENERGY;
			float currWidth;
			PVector pos, dim;

			EnergyBar(PVector pos, PVector dim) {
				this.pos=pos;
				this.dim=dim;
				currWidth=dim.x;
			}

			boolean empty() {
				return currEnergy==0;
			}

			void deplete(float amount) {
				currEnergy=ocdSimulator.max(0, currEnergy-amount);
				currWidth=dim.x*currEnergy;
			}

			void replete(float amount) {
				currEnergy=ocdSimulator.min(MAX_ENERGY, currEnergy+amount);
				currWidth=dim.x*currEnergy;
			}


			void draw() {
				ocdSimulator.pushMatrix();
				ocdSimulator.pushStyle();
				ocdSimulator.translate(pos);
				ocdSimulator.noStroke();

				ocdSimulator.fill(96, 245, 124);
				ocdSimulator.rect(-dim.x/2, -dim.y/2, currWidth, dim.y);
				ocdSimulator.fill(255);
				ocdSimulator.rect(-dim.x/2, -dim.y/2, currWidth, 10);
				ocdSimulator.textSize(20);
				ocdSimulator.textAlign(ocdSimulator.LEFT);
				ocdSimulator.text("Energy", -dim.x/2, dim.y+5);
				ocdSimulator.popStyle();
				ocdSimulator.popMatrix();
			}
		}



		class LayoutReader {
			final int MAX_OBJECT_TRIGGER_RADIUS=80;

			private OverworldObject EMPTY=null;
			private OverworldTrigger CONCERN=new OverworldTrigger();
			private OverworldJewel JEWEL=new OverworldJewel();
			private OverworldFood FOOD=new OverworldFood();


			OverworldObject[][] layout1=new OverworldObject[][]{
				{EMPTY, EMPTY, EMPTY, EMPTY, JEWEL}, 
				{EMPTY, EMPTY, JEWEL, JEWEL, EMPTY}, 
				{EMPTY, EMPTY, JEWEL, FOOD, EMPTY}, 
				{EMPTY, JEWEL, EMPTY, EMPTY, EMPTY}, 
				{JEWEL, CONCERN, EMPTY, EMPTY, EMPTY}, 
				{EMPTY, JEWEL, CONCERN, EMPTY, EMPTY}, 
				{EMPTY, FOOD, JEWEL, CONCERN, EMPTY}, 
			};

			/* if you want to access the array indices for a given gameobject position, you will need to use the game object's absolute position
     (the one we use to check for collisions). we don't need to take the rotation into account. however, you will eed to take the rotation into account when
     ascertaining whether or not the object appears "offscreen")*/

			void populateMap(GameObjectMap map, OverworldObject[][] layout, GameWorld gameWorld) {
				float tileHeight=map.dim.y/layout.length;
				float tileWidth=map.dim.x/layout[0].length;
				float x;
				float z;
				for (int i=0; i<layout.length; i++) {
					for (int j=0; j<layout[0].length; j++) {
						x=tileWidth/2+(tileWidth*j);
						z=-map.dim.y/2+tileHeight/2+(tileHeight*i);
						OverworldObject type=layout[i][j];
						if (!(type==EMPTY)) {
							OverworldObject ob= type.generate(new PVector(x-map.absoluteTranslation.x, 0, z-map.absoluteTranslation.z), new PVector(ocdSimulator.min(50, tileWidth), ocdSimulator.min(100, tileHeight)), map.absoluteTranslation.x, map.absoluteTranslation.z, ocdSimulator.min(MAX_OBJECT_TRIGGER_RADIUS, ocdSimulator.min(tileWidth/2, tileHeight/2)), new PVector(0, -gameWorld.quaternion.y, 0));
							map.addGameObject(ob);
						}
					}
				}
			}
		}



		class Box {

			ArrayList<Wall> walls=new ArrayList<Wall>();
			Wall north, south, east, west, bottom;
			PVector origin, dimensions;
			float northernBoundary, southernBoundary, easternBoundary, westernBoundary;


			Box(PVector origin, int width, int height, int length) {
				this.origin=origin;
				dimensions=new PVector(width, height, length);
				northernBoundary=origin.z-length/2;
				southernBoundary=origin.z+length/2;
				easternBoundary=origin.x+width/2;
				westernBoundary=origin.x-width/2;


				bottom=new Wall(new PVector(), new PVector(width, length), new PVector(ocdSimulator.PI/2, 0, 0));
				north=new Wall(new PVector(0, -height/2, -length/2), new PVector(width, height), new PVector());
				south=new Wall(new PVector(0, -height/2, length/2), new PVector(width, height), new PVector());
				east=new Wall(new PVector(-width/2, -height/2, 0), new PVector(length, height), new PVector(0, ocdSimulator.PI/2, 0));
				west=new Wall(new PVector(width/2, -height/2, 0), new PVector(length, height), new PVector(0, ocdSimulator.PI/2, 0));
				walls.add(bottom);
				walls.add(north);
				walls.add(south);
				walls.add(east);
				walls.add(west);
			}

			void mapTextures(Texture north, Texture south, Texture east, Texture west, Texture bottom) {
				this.north.setTexture(north);
				this.south.setTexture(south);
				this.west.setTexture(west);
				this.east.setTexture(east);
				this.bottom.setTexture(bottom);
			}

			boolean isInside(OverworldObject gameObject) {

				return ocdSimulator.abs(gameObject.absoluteTranslation.x-origin.x)<dimensions.x&&ocdSimulator.abs(gameObject.absoluteTranslation.z-origin.z)<dimensions.z;
			}


			void draw() {
				for (Wall wall : walls)
					wall.draw();
			}
		}


		class Wall {
			PVector origin, dimension, quaternion;
			Texture texture;


			Wall(PVector origin, PVector dimension, PVector quaternion) {
				this.origin=origin;
				this.dimension=dimension;
				this.quaternion=quaternion;
			}



			void setTexture(Texture texture) {
				if (texture!=null) {
					texture.resize((int)dimension.x, (int)dimension.y);
					this.texture=texture;
				}
			}


			void draw() {
				ocdSimulator.pushMatrix();
				ocdSimulator.translate(origin);
				ocdSimulator.rotate(quaternion);
				if (texture!=null) texture.apply();
				ocdSimulator.popMatrix();
			}
		}
	}

	




