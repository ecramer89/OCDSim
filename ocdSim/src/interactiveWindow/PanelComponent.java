package interactiveWindow;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class PanelComponent {
	protected int width, height, x, y;
    protected int backgroundColor;
    
	public void setSize(PVector dimension){
		width=(int)dimension.x;
		height=(int)dimension.y;	
	}
	
	public void draw(PApplet pApplet){
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
	
	public void setPosition(PVector position){
		x=(int)position.x;
		y=(int)position.y;
	}
	
	public void setBackground(int backgroundColor){
		this.backgroundColor=backgroundColor;
	}
	
	
	public boolean pointIn(int x, int y){
		return Math.abs(x-this.x)<width/2&&Math.abs(y-this.y)<height/2;
	}

}