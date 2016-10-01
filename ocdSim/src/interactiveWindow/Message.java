package interactiveWindow;

public abstract class Message {
	
	private MessageData data; 
	
	public Message(MessageData data){
		this.data=data;
	}
	
	
	public abstract void send();

}
