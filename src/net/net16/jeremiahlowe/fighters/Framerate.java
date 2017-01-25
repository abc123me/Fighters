package net.net16.jeremiahlowe.fighters;

public class Framerate {
	private int milliseconds = 0;
	private Runnable run;
	private Thread runThread;
	private int avgtime = 0;
	
	public Framerate(float fps, Runnable run){
		if(fps <= 0) throw new RuntimeException("FPS can't be under or equal to zero idiot");
		else if(fps == Float.POSITIVE_INFINITY) milliseconds = 0;
		else milliseconds = Math.round((1f / fps) * 1000);
		System.out.println("FPS set to " + fps + " fps or " + milliseconds + "ms/t");
		this.run = run;
	}
	public void start(){
		if(runThread == null) runThread = new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					while(!Thread.interrupted()){
						long lastTime = System.currentTimeMillis();
						if(milliseconds != 0) Thread.sleep(milliseconds);
						run.run();
						avgtime = ((int) (System.currentTimeMillis() - lastTime) + avgtime) / 2; 
					}
				}
				catch(InterruptedException i){}
				catch(Exception e){
					System.err.println("Fatal error in framerate controller: " + e);
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
		runThread.start();
	}
	public void stop(){
		runThread.interrupt();
	}
	public synchronized float getActualFramerate(){
		return (1f / avgtime) * 1000f;
	}
}
