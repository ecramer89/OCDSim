package ocdSim;

public class GameParameters {

	private static int jewelsCollected;
	
	public GameParameters(){
		jewelsCollected=0;
	}
	
	public static void updateJewelsCollected(int change){
		jewelsCollected+=change;
	}
	
	public static int getJewelsCollected(){
		return jewelsCollected;
	}
	
	
}
