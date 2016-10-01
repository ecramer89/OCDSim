package interactiveWindow;
import processing.core.*;

public class ButtonState {
	
	
	private TextBox text;
	private Image img;
	private int bgColor;

	
	public TextBox getText() {
		return text;
	}

	
	public int getBgColor() {
		return bgColor;
	}


	public Image getImg() {
		return img;
	}

	public void setBgColor(int bgColor){
		this.bgColor=bgColor;
	}
	
	
	
	public ButtonState(TextBox textBox, Image img){
		text=textBox;
		bgColor=120;
		this.img=img;
	}




	
	
	

}
