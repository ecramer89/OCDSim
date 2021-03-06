package interactiveWindow;
import processing.core.*;
import java.util.*;

public class Panel extends PanelComponent implements MouseListener {

	private ArrayList<MouseListener> mouseListeners;
	private ArrayList<PanelComponent> children;


	public Panel(){
		children=new ArrayList<PanelComponent>();
		mouseListeners=new ArrayList<MouseListener>();
	}
	
	
	public void add(PanelComponent child){
		children.add(child);
	}
	
	public void reset(){
		for(PanelComponent child : children){
			child.reset();
		}
	}


	public void addMouseListener(MouseListener mouseListener){
		mouseListeners.add(mouseListener);
	}


	public void drawComponent(PApplet pApplet) {
		for(PanelComponent component : children){
			component.draw(pApplet);
		}

	}


	@Override
	public void handleMousePressed(int mouseX, int mouseY) {
		if(isUnderMouse(mouseX, mouseY)){
			for(int i=mouseListeners.size()-1; i>-1; i--){
				MouseListener ml = mouseListeners.get(i);
				ml.handleMousePressed(mouseX, mouseY);	
			}
		}
	}




	@Override
	public void handleMouseReleased(int mouseX, int mouseY) {
		if(isUnderMouse(mouseX, mouseY)){
			for(int i=mouseListeners.size()-1; i>-1; i--){
				MouseListener ml = mouseListeners.get(i);
				ml.handleMouseReleased(mouseX, mouseY);	
			}
		}

	}


	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		return super.pointIn(mouseX, mouseY);
	}








}
