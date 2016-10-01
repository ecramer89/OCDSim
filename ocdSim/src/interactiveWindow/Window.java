package interactiveWindow;
import java.util.*;

import processing.core.*;

/* a class that represents the top level container of an interactive 2d window*/
public class Window {
	
	private ArrayList<Panel> panels;
	private Panel activePanel;
	
	
	public Window(){
		panels=new ArrayList<Panel>();
	}
	
	
	/*a window can only have one active Panel at a time. the active panel is drawn and it responds to input events */
	public void addPanel(Panel panel){
		panels.add(panel);
		if(activePanel==null){
			activePanel=panel;
		}
	}
	
	
	public void draw(PApplet pApplet){
		activePanel.draw(pApplet);
	}
	
	public void mousePressed(PApplet pApplet){
		activePanel.handleMousePressed(pApplet.mouseX, pApplet.mouseY);
	}
	
	public void mouseReleased(PApplet pApplet){
		activePanel.handleMouseReleased(pApplet.mouseX, pApplet.mouseY);
	}
	

	
}