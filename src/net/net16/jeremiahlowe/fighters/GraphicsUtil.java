package net.net16.jeremiahlowe.fighters;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class GraphicsUtil {
	public static void drawCenteredCircle(float d, Vector2 pos, Graphics g, Color c){
		drawCenteredOval(new Vector2(d, d), pos, g, c);
	}
	public static void drawCenteredOval(Vector2 size, Vector2 pos, Graphics g, Color c){
		Color orig = g.getColor();
		g.setColor(c);
		Vector2 cpos = new Vector2(pos.x, pos.y);
		cpos.x -= size.x / 2;
		cpos.y -= size.y / 2;
		int w = Math.round(size.x), h = Math.round(size.y);
		int x = Math.round(cpos.x), y = Math.round(cpos.y);
		g.drawOval(x, y, w, h);
		g.setColor(orig);
	}
	public static void fillCenteredCircle(float d, Vector2 pos, Graphics g, Color c){
		fillCenteredOval(new Vector2(d, d), pos, g, c);
	}
	public static void fillCenteredOval(Vector2 size, Vector2 pos, Graphics g, Color c){
		Color orig = g.getColor();
		g.setColor(c);
		Vector2 cpos = new Vector2(pos.x, pos.y);
		cpos.x -= size.x / 2;
		cpos.y -= size.y / 2;
		int w = Math.round(size.x), h = Math.round(size.y);
		int x = Math.round(cpos.x), y = Math.round(cpos.y);
		g.fillOval(x, y, w, h);
		g.setColor(orig);
	}
}
