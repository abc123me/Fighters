package net.net16.jeremiahlowe.fighters.ai;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.fighter.ActionBase;
import net.net16.jeremiahlowe.fighters.fighter.FighterController;

public interface Actions {
	public enum Movement{
		Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight, Forward, Backward, Rightward, Leftward, Stop
	}
	public enum Fighting{
		Shoot, Turn_towards_target, Turn_away_from_target, Icrease_FOV, Decrease_FOV, Nothing
	}
	public class Move extends ActionBase{
		private float speed = 3f;
		private Movement movement;
		public Move(Movement movement, float speed) {
			super("Move " + movement, 0.2f);
			this.speed = speed;
		}

		@Override
		public void onPerform(FighterController f) {
			Vector2 dir = f.fighter.looking.toDirection(speed);
			switch(movement){
			case Forward:
				f.fighter.position.y += dir.y;
				break;
			case Backward:
				f.fighter.position.y -= dir.y;
				break;
			case Leftward:
				f.fighter.position.x -= dir.x;
			case Rightward:
				f.fighter.position.x += dir.x;
			case Down:
				f.fighter.position.y -= speed;
				break;
			case DownLeft:
				f.fighter.position.x -= speed;
				f.fighter.position.y -= speed;
				break;
			case DownRight:
				f.fighter.position.y -= speed;
				f.fighter.position.x += speed;
				break;
			case Left:
				f.fighter.position.x -= speed;
				break;
			case Right:
				f.fighter.position.x += speed;
				break;
			case Up:
				f.fighter.position.y += speed;
				break;
			case UpLeft:
				f.fighter.position.x -= speed;
				f.fighter.position.y += speed;
				break;
			case UpRight:
				f.fighter.position.y += speed;
				f.fighter.position.x += speed;
				break;
			default: break;
			}
		}
	}
}
