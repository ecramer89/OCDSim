package ocdSim;

public class ModeTransitioner extends PComponent {
	 /*method invoked when the player collides with a food object in the overworld.*/
	  
	
	void encounteredFood(OverworldFood foodObject) {
		System.out.println("Encountered Food Object- implement me!!!");
	    //interfaceMode.newFoodEncounter(foodObject.getFoodData());
	    //OCDSimulator.setCurrentGameMode(interfaceMode);
	  }


	  /*method invoked when the player encounters and event in the overworld. Param will need to be the event; model after the food*/
	  void encounteredEvent() {
	  }

	  /*method invoked when the player chooses to eat a food from the choice screens.*/
	  void ateFood() {
	  }

	  /*method invoked when the player chooses to not eat a food from the choice screens.*/
	  void didNotEatFood() {
	  }

	  /*method invoked when the player clicks through all of the screens corresponding to an event from the event screen.*/
	  void eventCompleted() {
	  }

	  /*method invoked when the player runs out of energy.*/
	  void gameOver() {
	  }
}