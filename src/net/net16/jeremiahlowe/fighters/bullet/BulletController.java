package net.net16.jeremiahlowe.fighters.bullet;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BulletController {
	protected static List<Bullet> bullets = new ArrayList<Bullet>();
	private static List<Bullet> toRemove = new ArrayList<Bullet>();
	private static int width = 0, height = 0;
	
	public static void registerBullet(Bullet bullet){
		bullet.onRegister();
		bullets.add(bullet);
	}
	public static void stepBullets(){
		for(Bullet b : bullets){
			if(b.getPosition().x == 0 || b.getPosition().y == 0 || b.getPosition().x == width || b.getPosition().y == height) toRemove.add(b);
			else b.step();
		}
		for(Bullet b : toRemove) bullets.remove(b);
		toRemove.clear();
	}
	public static synchronized void drawBullets(Graphics g, int width, int height) {
		BulletController.width = width;
		BulletController.height = height;
		for(Bullet b : bullets) b.draw(g);
	}
	public synchronized static List<Bullet> getBullets(){
		return bullets;
	}
}
