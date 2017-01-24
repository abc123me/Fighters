package net.net16.jeremiahlowe.fighters.fighter;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.GraphicsUtil;
import net.net16.jeremiahlowe.fighters.Rotation;

public class Fighter {
	public Vector2 position = new Vector2(0, 0);
	public Rotation fov = new Rotation(105);
	public Rotation looking = new Rotation(23);
	public Team team = Team.Red;
	public boolean visible = false;
	public float fighterSize = 24;
	public Fighter[] inFrontOf;
	
	public Fighter(Vector2 position, Rotation looking, Team team, boolean visible){
		this.position = position;
		this.looking = looking;
		this.visible = visible;
		this.team = team;
	}

	public void move(Vector2 amount){
		position.x = position.x + amount.x;
		position.y = position.y + amount.y;
	}
	
	public void draw(Graphics g, int w, int h){
		if(visible){
			Color teamColor = team.getColor();
			drawBody(g, teamColor);
			drawLooking(g, teamColor, w, h);
		}
	}
	private void drawFOV(Graphics g, Color temColor, int w, int h){
		
	}
	private void drawLooking(Graphics g, Color teamColor, int w, int h){
		g.setColor(teamColor);
		float xf = position.x, yf = position.y;
		
		float len = 400;
		
		float x2 = (float) (xf + len * Math.cos(looking.getAngleRadians()));
		float y2 = (float) (yf + len * Math.sin(looking.getAngleRadians()) * -1);
		
		int x1 = Math.round(xf), y1 = Math.round(yf);
		g.drawLine(0, y1, w, y1);
		g.drawLine(x1, 0, x1, h);
		g.drawLine(x1, y1, Math.round(x2), Math.round(y2));
	}
	private void drawBody(Graphics g, Color teamColor){
		GraphicsUtil.fillCenteredCircle(fighterSize, position, g, teamColor);
		GraphicsUtil.drawCenteredCircle(fighterSize, position, g, Color.black);
		GraphicsUtil.drawCenteredCircle(fighterSize - 2, position, g, Color.black);
	}
}
