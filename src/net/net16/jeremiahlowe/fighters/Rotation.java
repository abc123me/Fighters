package net.net16.jeremiahlowe.fighters;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;

public class Rotation {
	private float rotation = 0;
	public Rotation(float rotation){
		float newRot = correctRotation(rotation);
		this.rotation = newRot;
	}
	public float getAngleDegrees(){
		return rotation;
	}
	public void setAngleDegrees(float rotation){
		this.rotation = rotation;
	}
	public static float correctRotation(float rotation){
		while(rotation > 360) rotation -= 360;
		while(rotation < 0) rotation += 360;
		return rotation;
	}
	public float getAngleRadians() {
		return (float) Math.toRadians(rotation);
	}
	public void setAngleRadians(float rotation){
		this.rotation = (float) Math.toDegrees(rotation);
	}
	//Directions
	public Vector2 toDirection(float length){
		float x = (float) (length * Math.cos(getAngleRadians()));
		float y = (float) (length * -Math.sin(getAngleRadians()));
		return new Vector2(x, y);
	}
	public static Vector2 toDirection(Rotation r, float length){
		float x = (float) (length * Math.cos(r.getAngleRadians()));
		float y = (float) (length * -Math.sin(r.getAngleRadians()));
		return new Vector2(x, y);
	}
	public static Vector2 toDirection(Rotation r){
		return toDirection(r, 1);
	}
	public Vector2 toDirection(){
		return toDirection(1);
	}
	public static Rotation pointTo(Vector2 origin, Vector2 target){
		Vector2 atan2Pos = new Vector2(target.x - origin.x, target.y - origin.y);
		double radAtan2 = Math.atan2(atan2Pos.x, atan2Pos.y);
		Rotation out = new Rotation((float) Math.toDegrees(radAtan2));
		out.rotateBy(-90);
		return out;
	}
	@Override
	public String toString(){
		return (getAngleDegrees() + "°");
	}
	public void rotateBy(float angle){
		rotation = correctRotation(rotation + angle);
	}
}
