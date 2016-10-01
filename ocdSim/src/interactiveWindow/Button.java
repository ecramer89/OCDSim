package interactiveWindow;
import java.util.*;

import processing.core.*;

public class Button extends PanelComponent implements MouseListener, TimerListener{


	//a two dimensional array. Specify Messages (function) to be invoked in paralell or sequentially for
	//each successive button press.
	private Message[][] messages;
	private int currMessageIndex;

	private ButtonState[] buttonStates;
	private ButtonState currentState;
	private int currStateIndex;
	
	private Timer pressedTimer;
	private int pressedColor;
	

	 public Button(Message[][] messages, ButtonState[] buttonStates){
		 setMessages(messages);
		 setButtonStates(buttonStates);
		 pressedTimer=new Timer(this, 30);
		 
	 }
	 
	 public Button(){
		 this(new Message[][]{{}}, new ButtonState[]{});
	 }
	
	 
	 public void setMessages(Message[][] messages){
		 this.messages=messages;
	 }
	 
	 public void setButtonStates(ButtonState[] buttonStates){
		 this.buttonStates=buttonStates;
	 }
	 

	@Override
	public void handleMousePressed(int mouseX, int mouseY) {
		if(isUnderMouse(mouseX, mouseY)&&pressedTimer.isDone()){
			changeState();
			sendMessages();
			pressedTimer.reset();
			setBackground(pressedColor);
			
		}

	}

	private void changeState(){
		currStateIndex=updateIndex(currStateIndex,buttonStates.length-1);
		this.currentState=buttonStates[currStateIndex];
		backgroundColor=currentState.getBgColor();

	}

	private void sendMessages(){
		currMessageIndex=updateIndex(currMessageIndex,messages.length-1);

		Message[] messageSet=messages[currMessageIndex];
		for(int i=0; i< messages.length; i++){
			Message m = messageSet[i];
			m.send();
		}

	}

	private int updateIndex(int index, int max){
		if(index<max){
			return index+1;
		}
		return 0;
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		return super.pointIn(mouseX, mouseY);
	}

	@Override
	public void handleMouseReleased(int mouseX, int mouseY) {

	}

	@Override
	protected void drawComponent(PApplet pApplet) {
		pApplet.pushMatrix();
		pApplet.translate(x, y);
        currentState.getImg().drawComponent(pApplet);
        currentState.getText().drawComponent(pApplet);
		pApplet.popMatrix();	
	}
	
	@Override
	protected void updateFields(PApplet pApplet){
		pressedTimer.update(pApplet);	
	}
	
	public void onTick(){	
	}
	
	public void timerDone(){
		setBackground(currentState.getBgColor());
	}





}