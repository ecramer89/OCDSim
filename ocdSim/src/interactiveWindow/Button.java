package interactiveWindow;
import java.util.*;

import processing.core.*;

public class Button extends PanelComponent implements MouseListener, TimerListener{


	//a two dimensional array. Specify Messages (function) to be invoked in paralell or sequentially for
	//each successive button press.
	private ArrayList<Message[]> messages;
	private int currMessageIndex;

	private ArrayList<ButtonState> buttonStates;
	private int preferredTextSize;
	private ButtonState currentState;
	private int currStateIndex;

	private Timer pressedTimer;
	private int pressedColor;



	public Button(ArrayList<Message[]> messages, ArrayList<ButtonState> buttonStates){
		setMessages(messages);
		setButtonStates(buttonStates);
		pressedTimer=new Timer(this, 30);
		currStateIndex=-1;
		currMessageIndex=0;
        addState(new ButtonState(this, "Press", 120));
        changeState();
	}



	public Button(){
		this(new ArrayList<Message[]>(), new ArrayList<ButtonState>());
	}


	public void setMessages(ArrayList<Message[]> messages2){
		this.messages=messages2;
	}

	public void setButtonStates(ArrayList<ButtonState> buttonStates2){
		this.buttonStates=buttonStates2;
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
	
	
	@Override
	public void setSize(float width, float height){
		super.setSize(width, height);
		for(ButtonState bState : buttonStates){
			bState.setSize(this);
		}
	}

	private void changeState(){
		currStateIndex=updateIndex(currStateIndex,buttonStates.size()-1);
		this.currentState=buttonStates.get(currStateIndex);
		backgroundColor=currentState.getBgColor();
		pressedColor=(int)(backgroundColor/1.2f);

	}

	private void sendMessages(){
		if(hasMessages()){
			currMessageIndex=updateIndex(currMessageIndex,messages.size()-1);
			Message[] messageSet=messages.get(currMessageIndex);
			for(int i=0; i< messages.size(); i++){
				Message m = messageSet[i];
				m.send();
			}

		}
	}

	private boolean hasMessages() {
		return messages.size()>0;
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

	public void setDefaultState(String text, int bgColor) {
		updateState(0, text, bgColor);
		resetStateIndex();
		changeState();
	}

	private void updateState(int i, String text, int bgColor) {
		updateState(i, text);
		if(i<buttonStates.size()){
			ButtonState state=buttonStates.get(i);
			state.setBgColor(bgColor);
		}
		
	}



	public void addState(ButtonState buttonState){
		buttonStates.add(buttonState);
	}

	public void addMessage(Message message){
		addMessageSet(new Message[]{message});
	}

	public void addMessageSet(Message[] messageSet){
		messages.add(messageSet);
	}


	private void resetStateIndex(){
		currStateIndex=-1;
	}

	@Override
	public void reset(){
		resetStateIndex();
		changeState();
		resetMessageIndex();
		pressedTimer.reset();
	}

	private void resetMessageIndex() {
		currMessageIndex=0;

	}



	public void setPreferredTextSize(int preferredTextSize) {
		// TODO Auto-generated method stub
		this.preferredTextSize=preferredTextSize;
	}



	public int getPreferredTextSize() {
		// TODO Auto-generated method stub
		return preferredTextSize;
	}



	public boolean hasPreferredTextSize() {
		// TODO Auto-generated method stub
		return preferredTextSize>0;
	}



	public void updateState(int indexOfState, String text) {
		if(indexOfState<buttonStates.size()){
			ButtonState state=buttonStates.get(indexOfState);
			state.setText(text);
		}
		
	}



	public void updateDefaultState(String text) {
		updateState(0, text);
		
	}





}
