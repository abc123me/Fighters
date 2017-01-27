package net.net16.jeremiahlowe.fighters.ai;

import java.util.Random;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.fighter.ActionBase;
import net.net16.jeremiahlowe.fighters.fighter.Fighter;
import net.net16.jeremiahlowe.fighters.fighter.FighterController;

public interface Actions {
	public static final float KILL_SCORE = 5;
	public static final float MISS_SCORE = -0.3f;
	public enum Movement{
		Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight, Forward, Backward, Rightward, Leftward, Stop;

		public static Movement getRandom(Random rng) {
			return values()[rng.nextInt(values().length - 1)];
		}
	}
	public enum Fighting{
		Shoot, Turn_towards_target, Turn_away_from_target, Icrease_FOV, Decrease_FOV, Nothing;
		
		public static Fighting getRandom(Random rng) {
			return values()[rng.nextInt(values().length - 1)];
		}
	}
	public class Fight extends ActionBase{
		private Fighting fighting = Fighting.Nothing;
		
		public Fight(Fighting fighting) {
			super("Fight " + fighting, 0);
			this.fighting = fighting != null ? fighting : Fighting.Nothing;
		}

		@Override
		public void onPerform(FighterController f) {
			switch(fighting){
				case Decrease_FOV:
					float newRot = f.fighter.fov.getAngleDegrees() - 5;
					if(newRot < 5) newRot = 5;
					f.fighter.fov.setAngleDegrees(newRot);
					super.reward = 0.1f;
					break;
				case Icrease_FOV:
					float newRot2 = f.fighter.fov.getAngleDegrees() + 5;
					if(newRot2 > 105) newRot2 = 105;
					f.fighter.fov.setAngleDegrees(newRot2);
					super.reward = 0.1f;
					break;
				case Shoot:
					f.fighter.shoot();
					super.reward = 1f;
					break;
				case Turn_away_from_target:
					f.fighter.turnAwayFromTarget(f.fighter.turnSpeed);
					super.reward = -1f;
					break;
				case Turn_towards_target:
					f.fighter.turnTowardsTarget(f.fighter.turnSpeed);
					super.reward = 1.5f;
					break;
				default:
					super.reward = -0.1f;
					break;
			}
		}
	}
	public class Move extends ActionBase{
		private Movement movement = Movement.Stop;
		
		public Move(Movement movement) {
			super("Move " + movement, 0);
			this.movement = movement != null ? movement : Movement.Stop;
		}

		@Override
		public void onPerform(FighterController f) {
			float speed = f.fighter.speed;
			Vector2 dir = f.fighter.looking.toDirection(speed);
			switch(movement){
				case Forward:
					super.reward = 0.2f;
					f.fighter.velocity.y = dir.y;
					break;
				case Backward:
					super.reward = 0.15f;
					f.fighter.velocity.y = -dir.y;
					break;
				case Leftward:
					super.reward = 0.15f;
					f.fighter.velocity.x = -dir.x;
					break;
				case Rightward:
					super.reward = 0.15f;
					f.fighter.velocity.x = dir.x;
					break;
				case Down:
					super.reward = 0.1f;
					f.fighter.velocity.y = -speed;
					break;
				case DownLeft:
					super.reward = 0.15f;
					f.fighter.velocity.x = -speed;
					f.fighter.velocity.y = -speed;
					break;
				case DownRight:
					super.reward = 0.15f;
					f.fighter.velocity.y = -speed;
					f.fighter.velocity.x = speed;
					break;
				case Left:
					super.reward = 0.1f;
					f.fighter.velocity.x = -speed;
					break;
				case Right:
					super.reward = 0.1f;
					f.fighter.velocity.x = speed;
					break;
				case Up:
					super.reward = 0.1f;
					f.fighter.velocity.y = speed;
					break;
				case UpLeft:
					super.reward = 0.15f;
					f.fighter.velocity.x = -speed;
					f.fighter.velocity.y = speed;
					break;
				case UpRight:
					super.reward = 0.15f;
					f.fighter.velocity.y = speed;
					f.fighter.velocity.x = speed;
					break;
				case Stop:
					if(f.fighter.velocity.x == 0 && f.fighter.velocity.y == 0) super.reward = 0.15f;
					else super.reward = -1;
					f.fighter.velocity.y = 0;
					f.fighter.velocity.x = 0;
					break;
				default: 
					super.reward = -0.05f;
					break;
			}
			if(f.fighter.isOnSides()){
				super.reward = -1;
			}
		}
	}
	class ActionUtils{
		public static boolean canMove(Fighter f, Vector2 velo){
			if(f.position.x + velo.x >= Fighters.gui.drawCanvas.getWidth() - f.getSize().x) return false;
			if(f.position.x + velo.x <= f.getSize().x) return false;
			if(f.position.y + velo.y >= Fighters.gui.drawCanvas.getHeight() - f.getSize().y) return false;
			if(f.position.y + velo.y <= f.getSize().y) return false;
			return true;
		}
	}
}
