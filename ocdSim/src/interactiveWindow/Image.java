package interactiveWindow;
import processing.core.*;

public class Image extends PanelComponent {
	
	private PImage rawImg;
	

	@Override
	public void drawComponent(PApplet pApplet) {
	   pApplet.pushMatrix();
	   pApplet.translate(x, y);
	   if(hasPImage()){
		   pApplet.image(rawImg, -width/2, -height/2);
	   }
		
	}

	public boolean hasPImage() {
		// TODO Auto-generated method stub
		return rawImg!=null;
	}

	public void setPImage(PImage img) {
		this.rawImg=img;
		rawImg.resize(width, height);	
	}

	public PImage getPImage() {
		// TODO Auto-generated method stub
		return rawImg;
	}



}