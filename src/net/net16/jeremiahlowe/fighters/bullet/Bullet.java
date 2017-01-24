package net.net16.jeremiahlowe.fighters.bullet;

import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.GraphicsUtil;
import net.net16.jeremiahlowe.fighters.Rotation;
import net.net16.jeremiahlowe.fighters.fighter.Team;
import net.net16.jeremiahlowe.fighters.physics.BaseCollider;

public class Bullet extends BaseCollider{
	public final Vector2 origin;
	public Vector2 velocity;
	public Vector2 position;
	public final Team[] toHit;
	public final Team from;
	public boolean visible = true;
	private final int size = 8;
	
	public Bullet(Vector2 origin, Vector2 velocity, Team from){
		this.velocity = velocity;
		this.origin = origin;
		this.position = origin;
		this.from = from;
		this.toHit = from.getOpposing();
	}
	
	public void setVelocityFromRotation(Rotation r, int multiplier){
		velocity = r.toDirection(multiplier);
	}
	
	protected synchronized void step() {
		position.x += velocity.x;
		position.y += velocity.y;
	}
	
	public synchronized void draw(Graphics g){
		if(visible){
			GraphicsUtil.fillCenteredCircle(size, origin, g, from.getColor());
		}
	}
	public void onRegister() {
		
	}
	
	@Override
	public Vector2 getPosition() {
		return position;
	}
	@Override
	public Vector2 getSize() {
		return new Vector2(size, size);
	}
}
