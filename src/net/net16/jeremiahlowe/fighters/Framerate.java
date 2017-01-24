package net.net16.jeremiahlowe.fighters;

public class Framerate {
	private int milliseconds = 0;
	private Runnable run;
	private Thread runThread;
	public Framerate(float fps, Runnable run){
		if(fps <= 0) throw new RuntimeException("FPS can't be under or equal to zero idiot");
		else if(fps == Float.POSITIVE_INFINITY) milliseconds = 0;
		else milliseconds = Math.round((1f / fps) * 1000);
		System.out.println("FPS set to " + fps + " fps or " + milliseconds + "ms/t");
		this.run = run;
	}
	public void start(){
		if(runThread == null) runThread = new Thread(run);
		runThread.start();
	}
	public void stop(){
		runThread.interrupt();
	}
}
