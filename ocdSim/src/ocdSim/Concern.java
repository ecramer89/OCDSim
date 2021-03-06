package ocdSim;

public class Concern extends OCDSimComponent {
	private boolean active; 
	private String label;
	private String confirm="Yes";
	private String disaffirm="No";
	private float probabilityUnsafe;


	public Concern(String label, float probabilityUnsafe) {
		this.label=label;
		this.probabilityUnsafe=probabilityUnsafe;
	}



	public String getMessage(boolean result) {
		return result? confirm : disaffirm;
	}

	public boolean computeResult() {
		float result=ocdSimulator.random(1);
		return (result<=probabilityUnsafe);
	}

	public void activate() {
		active=true;
	}

	public boolean isActive() {
		return active;
	}

	public String getLabel() {
		return label;
	}

}
