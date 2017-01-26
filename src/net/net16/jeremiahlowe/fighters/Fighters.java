package net.net16.jeremiahlowe.fighters;

import java.util.Random;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.ai.GenerationController;
import net.net16.jeremiahlowe.fighters.bullet.BulletController;
import net.net16.jeremiahlowe.fighters.fighter.FighterController;

public class Fighters {
	public static final boolean DEBUG_MODE = true;
	public static final int MAX_FRAMERATE = 120;
	public static final int GENE_LENGTH = 2500;
	public static final float ERROR_CHANCE = 0.10f;
	public static final Vector2 BULLET_SPEED = new Vector2(5, 5);
	public static Framerate g_fps; 
	public static boolean paused = false;
	public static GUI gui;
	public static Random rng;
	public static void main(String[] args) {
		rng = new Random();
		if(DEBUG_MODE) rng.setSeed(0);
		else rng.setSeed(System.currentTimeMillis() * System.nanoTime());
		gui = new GUI();
		gui.setVisible(true);
		addFPSsystem();
		GenerationController.spawnNewGenerationFromScratch(GENE_LENGTH, true, 0);
	}
	private static int stepIterator = 0;
	public static void stepGlobal(){
		stepIterator++;
		if(stepIterator >= 10){
			for(FighterController f : FighterController.getFighterControllers()){
				f.fighter.stepTargetting();
				f.fighter.stepVelocity();
				f.step();
			}
			stepIterator = 0;
		}
		BulletController.stepBullets();
	}
	public static void addFPSsystem(){
		g_fps = new Framerate(MAX_FRAMERATE, new Runnable(){
			@Override
			public void run(){
				gui.drawCanvas.repaint();
				stepGlobal();
			}
		}); 
		g_fps.start();
	}
	public static void onNewGeneration(){
		g_fps.stop();
		while(g_fps.isRunning()){}
		GenerationController.killCurrentGeneration();
		GenerationController.spawnNewGenerationFromGenePool(ERROR_CHANCE, true, 0);
		g_fps.start();
	}
	public static void onReset() {
		GenerationController.killCurrentGeneration();
		GenerationController.spawnNewGenerationFromScratch(GENE_LENGTH, true, 0);
	}
	public static void onPause() {
		if(!paused){
			g_fps.stop();
			paused = true;
		}
		else{
			g_fps.start();
			paused = false;
		}
	}
}
