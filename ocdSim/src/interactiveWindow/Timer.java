package interactiveWindow;

import processing.core.PApplet;

public class Timer {

	private TimerListener listener;
	private int maxTicks;
	private int currTicks;
	private int framesPerTick;

	public Timer(TimerListener listener, int maxTicks){
		this.maxTicks=maxTicks;
		this.listener=listener;
		framesPerTick=1;
	}

	public Timer(TimerListener listener, int maxTicks, int framesPerTick){
		this(listener,maxTicks);
		this.framesPerTick=framesPerTick;
	}

	public void update(PApplet pApplet){
		if(!isDone()){
			if(pApplet.frameCount%framesPerTick==0){
				currTicks++;
				listener.onTick();
			}
			if(isDone()){
				listener.timerDone();
			}
		}
	}

	public boolean isDone() {
		// TODO Auto-generated method stub
		return currTicks==maxTicks;
	}

	public void reset() {
		// TODO Auto-generated method stub
		currTicks=0;

	}

}
