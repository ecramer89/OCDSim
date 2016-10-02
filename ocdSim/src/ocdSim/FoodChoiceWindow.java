package ocdSim;

import interactiveWindow.*;

public class FoodChoiceWindow extends Window {
	private int defaultColor;
	private int defaultFontSize;
	private Panel foodChoicePanel;
	private TextBox chooseFoodText;
	private Image chooseFoodImage;
	
	private Panel checkOptionPanel;

	private OCDSimulator ocdSimulator;

	public FoodChoiceWindow(OCDSimulator simulator){
		this.ocdSimulator=simulator;
		defaultFontSize=(int)(ocdSimulator.width/40);
		defaultColor=0;
		initializeFoodChoicePanel();
		addPanel(foodChoicePanel);

	}

	private void initializeFoodChoicePanel(){
		foodChoicePanel=new Panel();
		foodChoicePanel.setBackground(defaultColor);
		foodChoicePanel.setCenter(ocdSimulator.width/2, ocdSimulator.height/2);
		foodChoicePanel.setSize(ocdSimulator.width*.8f, ocdSimulator.height*.8f);

		//initialize the text that asks if you want to eat the (X)
		chooseFoodText=new TextBox();
		chooseFoodText.setText("Testing");
		chooseFoodText.setTextColor(255-defaultColor);
		chooseFoodText.setSize(foodChoicePanel.getWidth()*.66f, foodChoicePanel.getHeight()*.15f);
		chooseFoodText.setCenter(foodChoicePanel.getCenterX(), foodChoicePanel.getUpperY()+chooseFoodText.getHeight()/2);
		chooseFoodText.setTextSize(defaultFontSize);
		foodChoicePanel.add(chooseFoodText);

		
		//initialize Buttons (yes, no, check)
		Button yes=new Button();
		int buttonColor=(255-defaultColor)/2;
		float bWidth=foodChoicePanel.getWidth()*.8f/3;
		float bHeight=foodChoicePanel.getHeight()*.1f;
		float bGap=foodChoicePanel.getWidth()*.05f;
		yes.setSize(bWidth, bHeight);
		yes.setCenter(foodChoicePanel.getLeftX()+bGap+bWidth/2, foodChoicePanel.getLowerY()-bHeight);
		yes.setBackground(buttonColor);
		yes.setPreferredTextSize(defaultFontSize);
		yes.setDefaultState(new ButtonState(yes, "Yes", 120));
		foodChoicePanel.add(yes);
		foodChoicePanel.addMouseListener(yes);

		Button no=new Button();
		no.setSize(bWidth, bHeight);
		no.setCenter(foodChoicePanel.getLeftX()+bGap*2+bWidth/2+bWidth, foodChoicePanel.getLowerY()-bHeight);
		no.setBackground(buttonColor);
		no.setPreferredTextSize(defaultFontSize);
		no.setDefaultState(new ButtonState(no, "No", 120));

		foodChoicePanel.add(no);
		foodChoicePanel.addMouseListener(no);

		Button check=new Button();
		check.setSize(bWidth, bHeight);
		check.setCenter(foodChoicePanel.getLeftX()+bGap*3+bWidth/2+bWidth*2, foodChoicePanel.getLowerY()-bHeight);
		check.setBackground(buttonColor);
		check.setPreferredTextSize(defaultFontSize);
		check.setDefaultState(new ButtonState(no, "Check", 120));

		foodChoicePanel.add(check);
		foodChoicePanel.addMouseListener(check);
		
		//initialize the image that shows the current food object.
		chooseFoodImage=new Image();
		chooseFoodImage.setCenter(foodChoicePanel.getCenterX(), foodChoicePanel.getCenterY());
        chooseFoodImage.setSize(foodChoicePanel.getWidth()*.33f, .80f*(yes.getUpperY()-chooseFoodText.getLowerY()));
        chooseFoodImage.setBackground(defaultColor);
        foodChoicePanel.add(chooseFoodImage); 
	}
	
	
	private void initializeCheckOptionPanel(){
		// TODO Auto-generated method stub
	}
	
	public void updatePanels(FoodObjectData data){
		updateFoodChoicePanel(data);
		updateCheckOptionPanel(data);
	}

	private void updateCheckOptionPanel(FoodObjectData data) {
		chooseFoodText.setText(data.getRequestToEat());
		chooseFoodImage.setPImage(data.getImage());
		foodChoicePanel.reset();
		
	}

	private void updateFoodChoicePanel(FoodObjectData data) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}