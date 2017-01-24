package net.net16.jeremiahlowe.fighters;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.fighters.fighter.Fighter;

public class DrawCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	private BufferedImage buffer;
	public List<Fighter> fighters = new ArrayList<Fighter>();
	@Override
	public void update(Graphics g){
		buffer = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
		Graphics bg = buffer.getGraphics();
		bg.setColor(Color.white);
		bg.fillRect(0, 0, getWidth(), getHeight());
		for(Fighter f : fighters) f.draw(bg, getWidth(), getHeight());
		g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
	@Override
	public void paint(Graphics g){}
}
