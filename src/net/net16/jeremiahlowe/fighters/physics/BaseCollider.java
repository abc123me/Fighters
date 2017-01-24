package net.net16.jeremiahlowe.fighters.physics;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public abstract class BaseCollider {
	public BaseCollider checkCollidingWith(BaseCollider[] with){
		for(int i = 0; i < with.length; i++) if(checkCollidingWith(with[i])) return with[i]; 
		return null;
	}
	public abstract Vector2 getPosition();
	public abstract Vector2 getSize();
	public boolean pointInCollider(Vector2 point){
		Vector2[] edges = getHitboxEdges();
		if(point.x >= edges[0].x && point.x <= edges[3].x)
			if(point.y >= edges[0].y && point.y <= edges[3].y)
				return true;
		return false;
	}
	public boolean checkCollidingWith(BaseCollider with){
		for(Vector2 v : with.getHitboxEdges())
			if(pointInCollider(v)) 
				return true;
		return false;
	}
	public Vector2[] getHitboxEdges(){
		Vector2[] out = new Vector2[4];
		Vector2 pos = new Vector2(getPosition().x - getSize().x / 2, getPosition().y - getSize().y / 2);
		out[0] = pos;
		out[1] = new Vector2(pos.x, pos.y + getSize().y);
		out[2] = new Vector2(pos.x + getSize().x, pos.y);
		out[3] = new Vector2(pos.x + getSize().x, pos.y + getSize().y);
		return out;
	}
	public void drawHitbox(Graphics g){
		Color orig = g.getColor();
		g.setColor(Color.orange);
		Vector2[] edges = getHitboxEdges();
		g.drawRect(edges[0].getXI(), edges[0].getYI(), getSize().getXI(), getSize().getYI());
		g.setColor(orig);
	}
}
