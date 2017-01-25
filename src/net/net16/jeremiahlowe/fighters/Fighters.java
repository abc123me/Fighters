package net.net16.jeremiahlowe.fighters;

import java.util.Random;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.bullet.BulletController;
import net.net16.jeremiahlowe.fighters.fighter.FighterController;
import net.net16.jeremiahlowe.fighters.fighter.Spawner;

public class Fighters {
	public static final boolean DEBUG_MODE = true;
	public static Framerate g_fps; //For graphics ONLY (Unlimited)
	public static Framerate t_fps; //For physics and stuff (Limited to 120 fps)
	public static final int PHYSICS_FRAMERATE_MAX = 120;
	public static final int GENE_LENGTH = 2500;
	public static final Vector2 BULLET_SPEED = new Vector2(5, 5);
	public static GUI gui;
	public static Random rng;
	public static void main(String[] args) {
		rng = new Random();
		if(DEBUG_MODE) rng.setSeed(0);
		else rng.setSeed(System.currentTimeMillis() * System.nanoTime());
		gui = new GUI();
		gui.setVisible(true);
		addFPSsystem();
		FighterController a = Spawner.createFighterWithController();
		FighterController b = Spawner.createFighterWithController();
		a.createGene(GENE_LENGTH);
		b.createGene(GENE_LENGTH);
		FighterController.registerFighterController(a);
		FighterController.registerFighterController(b);
	}
	private static int stepIterator = 0;
	public static void stepGlobal(){
		stepIterator++;
		if(stepIterator >= 10){
			for(FighterController f : FighterController.getFighterControllers()){
				f.fighter.stepTargetting();
				f.step();
			}
			stepIterator = 0;
		}
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
		t_fps = new Framerate(PHYSICS_FRAMERATE_MAX, new Runnable(){
			@Override
			public void run(){
				stepGlobal();
			}
		}); t_fps.start();
	}
}
