package net.net16.jeremiahlowe.fighters.fighter;

import java.awt.Color;
import java.awt.Graphics;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.GraphicsUtil;
import net.net16.jeremiahlowe.fighters.Rotation;
import net.net16.jeremiahlowe.fighters.bullet.Bullet;
import net.net16.jeremiahlowe.fighters.bullet.BulletController;
import net.net16.jeremiahlowe.fighters.physics.BaseCollider;

public class Fighter extends BaseCollider{
	public Vector2 position = new Vector2(0, 0);
	public Rotation fov = new Rotation(30);
	public Rotation looking = new Rotation(0);
	public Team team = Team.Red;
	public boolean visible = false;
	public float fighterSize = 24;
	public Fighter[] inFrontOf;
	public Fighter target;
	public Team[] enemyTeams;
	public float speed = 2.5f;
	public float turnSpeed = 3f;
	
	public Fighter(Vector2 position, Rotation looking, Team team, boolean visible){
		this.position = position;
		this.looking = looking;
		this.visible = visible;
		this.team = team;
		enemyTeams = team.getOpposing();
	}
	public Fighter(Vector2 position, Rotation looking, Team team){
		this(position, looking, team, true);
	}
	
	public void draw(Graphics g, int w, int h){
		if(visible){
			Color teamColor = team.getColor();
			Rotation fov1 = null, fov2 = null;
			fov1 = new Rotation(fov.getAngleDegrees() / 2 + looking.getAngleDegrees());
			fov2 = new Rotation(looking.getAngleDegrees() - fov.getAngleDegrees() / 2);
			//Draw FOV group
			GraphicsUtil.drawAngle(g, teamColor, fov1, position, w + h);
			GraphicsUtil.drawAngle(g, teamColor, fov2, position, w + h);
			if(Fighters.DEBUG_MODE) GraphicsUtil.drawAngle(g, Color.ORANGE, looking, position, w + h);
			//Draw body
			GraphicsUtil.fillCenteredCircle(fighterSize, position, g, teamColor);
			GraphicsUtil.drawCenteredCircle(fighterSize, position, g, Color.black);
			GraphicsUtil.drawCenteredCircle(fighterSize - 2, position, g, Color.black);
		}
	}
	public void setRotation(Rotation looking){
		this.looking = looking;
	}
	
	@Override
	public Vector2 getSize(){
		return new Vector2(fighterSize, fighterSize);
	}
	@Override
	public Vector2 getPosition() {
		return position;
	}
	public void shoot() {
		Vector2 velocity = looking.toDirection();
		velocity.x *= Fighters.BULLET_SPEED.x;
		velocity.y *= Fighters.BULLET_SPEED.y;
		Vector2 origin = new Vector2(position.x, position.y);
		Bullet b = new Bullet(origin, velocity, FighterController.findFighterController(this));
		BulletController.registerBullet(b);
	}
	public void turnTowardsTarget(float turnSpeed) {
		
	}
	public void turnAwayFromTarget(float turnSpeed) {
		
	}
	public void stepTargetting(){
		
	}
}
