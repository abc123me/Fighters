package net.net16.jeremiahlowe.fighters.fighter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.bettercollections.Line;
import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.GraphicsUtil;
import net.net16.jeremiahlowe.fighters.bullet.Bullet;
import net.net16.jeremiahlowe.fighters.bullet.BulletController;
import net.net16.jeremiahlowe.fighters.physics.BaseCollider;

public class Fighter extends BaseCollider{
	public static final float FIGHTER_OFFSCREEN_SCORE = -3f;
	public static final float FIGHTER_TARGETTING_SCORE = 0.1f;
	public static final float FIGHTER_KEEP_TARGET_SCORE = 0f;
	public Vector2 position = new Vector2(0, 0);
	public Vector2 velocity = new Vector2(0, 0);
	public Rotation fov = new Rotation(30);
	public Rotation looking = new Rotation(0);
	public Team team = Team.Red;
	public boolean visible = false;
	public float fighterSize = 24;
	public List<FighterController> inFrontOf;
	public FighterController target;
	public Team[] enemyTeams;
	public float speed = 2.5f;
	public float turnSpeed = 3f;
	private int lastAmountOfEnemiesInFrontOfFighter = 0;
	
	public Fighter(Vector2 position, Rotation looking, Team team, boolean visible){
		this.position = position;
		this.looking = looking;
		this.visible = visible;
		this.team = team;
		inFrontOf = new ArrayList<FighterController>();
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
		if(target != null){
			Rotation correctRot = Rotation.pointTo(position, target.fighter.position);
			//float lr = looking.getAngleDegrees();
			//float cr = correctRot.getAngleDegrees();
			looking.setAngleDegrees(correctRot.getAngleDegrees());
		}
	}
	public void turnAwayFromTarget(float turnSpeed) {
		
	}
	public void stepTargetting(){
		Rotation fov1 = null, fov2 = null;
		fov1 = new Rotation(fov.getAngleDegrees() / 2 + looking.getAngleDegrees());
		fov2 = new Rotation(looking.getAngleDegrees() - fov.getAngleDegrees() / 2);
		Line top = new Line(position, fov1.toDirection().add(position));
		Line btm = new Line(position, fov2.toDirection().add(position));
		//List<FighterController> oldInFrontOf = inFrontOf;
		inFrontOf.clear();
		for(FighterController f : FighterController.getFighterControllers()){
			Vector2 pos = f.fighter.position.clone();
			if(pos.y > btm.getY(pos.x) && pos.y < top.getY(pos.x)){
				inFrontOf.add(f);
			}
		}
		if(inFrontOf.size() >= 1 && inFrontOf.size() != lastAmountOfEnemiesInFrontOfFighter){
			FighterController f = FighterController.findFighterController(this);
			if(f != null){
				lastAmountOfEnemiesInFrontOfFighter = inFrontOf.size();
				f.gene.score += FIGHTER_KEEP_TARGET_SCORE;
				System.out.println("Found a target, Adding score");
			}
		}
		FighterController oldTarget = target;
		target = null;
		float closest = Float.MIN_VALUE;
		for(FighterController f : inFrontOf){
			float dist = (float) Vector2.distance2(position, f.fighter.position);
			if(dist > closest){
				target = f;
				closest = dist;
			}
		}
		if(target != null && target != oldTarget){
			FighterController f = FighterController.findFighterController(this);
			if(f != null){
				f.gene.score += FIGHTER_TARGETTING_SCORE;
				System.out.println("New target acquired, Adding score");
			}
		}
	}
	public void stepVelocity(){
		position.x += velocity.x;
		position.y += velocity.y;
		int w = Fighters.gui.drawCanvas.getWidth(), h = Fighters.gui.drawCanvas.getHeight();
		if(position.x <= 0 || position.y <= 0 || position.x >= w || position.y >= h){
			position.x = w / 2;
			position.y = h / 2;
			FighterController f = FighterController.findFighterController(this);
			f.gene.score += FIGHTER_OFFSCREEN_SCORE;
			System.out.println("Fighter went offscreen, discounting points!");
		}
	}
	public boolean isOnSides(){
		if(position.x < fighterSize / 2) return true;
		if(position.y < fighterSize / 2) return true;
		int w = Fighters.gui.drawCanvas.getWidth(), h = Fighters.gui.drawCanvas.getHeight();
		if(position.x > w - fighterSize / 2) return true;
		if(position.y > h - fighterSize / 2) return true;
		return false;
	}
}
