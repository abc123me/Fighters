package net.net16.jeremiahlowe.fighters;

import net.net16.jeremiahlowe.fighters.bullet.BulletController;

public class Fighters {
	public static final boolean DEBUG_MODE = true;
	public static Framerate g_fps; //For graphics ONLY (Unlimited)
	public static Framerate p_fps; //For physics (Limited to 120 fps)
	public static final int PHYSICS_FRAMERATE_MAX = 10;
	public static final int PHYSICS_FRAMERATE_MIN = 90;
	public static GUI gui;
	public static void main(String[] args) {
		gui = new GUI();
		gui.setVisible(true);
		addFPSsystem();
	}
	public static void onPhysics(){
		BulletController.stepBullets();
	}
	public static void addFPSsystem(){
		//Graphics FPS
		//As fast as we can go HOORAH!
		g_fps = new Framerate(Float.POSITIVE_INFINITY, new Runnable(){
			@Override
			public void run(){
				gui.drawCanvas.repaint();
			}
		}); g_fps.start();
		//Physics FPS 
		//Must be between 90-120 constantly otherwise physics
		//calculation will be performed too slowly
		p_fps = new Framerate(PHYSICS_FRAMERATE_MAX, new Runnable(){
			@Override
			public void run(){
				onPhysics();
			}
		}); p_fps.start();
	}
}
