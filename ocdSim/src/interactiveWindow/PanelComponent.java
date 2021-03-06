package interactiveWindow;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class PanelComponent {
	protected int width, height, x, y;
	protected int backgroundColor;

	public void setSize(float width, float height){
		this.width=(int)width;
		this.height=(int)height;	
	}
	
	/*a method that restores any mutable fields to the values they would have when
	 * running this component for the first time.
	 */
	public void reset(){
		
	}


	public int getCenterX(){
		return x;
	}

	public int getLeftX(){
		return getCenterX()-getWidth()/2;
	}

	public int getUpperY(){
		return getCenterY()-getHeight()/2;
	}

	public int getRightX(){
		return getCenterX()+getWidth()/2;
	}

	public int getLowerY(){
		return getCenterY()+getHeight()/2;
	}
	public int getCenterY(){
		return y;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public final void draw(PApplet pApplet){
		updateFields(pApplet);
		drawRect(pApplet);
		drawComponent(pApplet);

	}

	protected void updateFields(PApplet pApplet){

	}

	protected void drawRect(PApplet pApplet){
		pApplet.pushMatrix();
		pApplet.translate(x, y);
		pApplet.fill(backgroundColor);
		pApplet.rect(-width/2,  -height/2, width, height);
		pApplet.popMatrix();
	}

	protected abstract void drawComponent(PApplet pApplet);

	public void setCenter(float x, float y){
		this.x=(int)x;
		this.y=(int)y;
	}

	public void setBackground(int backgroundColor){
		this.backgroundColor=backgroundColor;
	}


	public boolean pointIn(int x, int y){
		return Math.abs(x-this.x)<width/2&&Math.abs(y-this.y)<height/2;
	}

}
