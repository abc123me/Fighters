package net.net16.jeremiahlowe.fighters;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import net.net16.jeremiahlowe.fighters.bullet.Bullet;
import net.net16.jeremiahlowe.fighters.bullet.BulletController;
import net.net16.jeremiahlowe.fighters.fighter.FighterController;

public class DrawCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	private BufferedImage buffer;
	@Override
	public void update(Graphics g){
		buffer = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
		Graphics bg = buffer.getGraphics();
		bg.setColor(Color.white);
		bg.fillRect(0, 0, getWidth(), getHeight());
		for(FighterController f : FighterController.getFighterControllers()) 
			f.fighter.draw(bg, getWidth(), getHeight());
		bg.setColor(Color.black);
		if(Fighters.g_fps != null) bg.drawString("FPS: " + Fighters.g_fps.getActualFramerate(), 3, bg.getFontMetrics().getHeight());
		BulletController.drawBullets(bg, getWidth(), getHeight());
		if(Fighters.DEBUG_MODE){
			for(FighterController f : FighterController.getFighterControllers()) 
				f.fighter.drawHitbox(bg);
			for(Bullet b : BulletController.getBullets()) b.drawHitbox(bg);
		}
		g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
	@Override
	public void paint(Graphics g){}
}
