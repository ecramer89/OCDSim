package ocdSim;

import interactiveWindow.*;
import processing.core.*;

/*a class that represents the game mode that runs (any kind of) screen*/
public class WindowMode extends OCDSimComponent implements GameMode {
	
	private Window active; //the Window we are currently displaying.
	private FoodChoiceWindow foodChoiceWindow;
	private Window concernEventWindow;
	
	
	public WindowMode(){
		//inialize the concern event window *maybe consider making concerneventWindow and foodchoiceWindow sep. subclasses of window.
		foodChoiceWindow=new FoodChoiceWindow(ocdSimulator);	
		active=foodChoiceWindow;
	}

	@Override
	public void run() {
		ocdSimulator.camera();
		active.draw(ocdSimulator);
		
	}

	@Override
	public void handleKeyReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMousePressed() {
		active.mousePressed(ocdSimulator);
		
	}

	@Override
	public void handleMouseReleased() {
		active.mouseReleased(ocdSimulator);
		
	}

	@Override
	public void handleKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	public void encounteredFood(FoodObjectData foodData) {
		foodChoiceWindow.updatePanels(foodData);
		setActiveWindow(foodChoiceWindow);
		
	}

	private void setActiveWindow(Window window) {
		active=window;
		
	}

}
