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
		float y = (float) (length * Math.sin(getAngleRadians()) * -1);
		return new Vector2(x, y);
	}
	public static Vector2 toDirection(Rotation r, float length){
		float x = (float) (length * Math.cos(r.getAngleRadians()));
		float y = (float) (length * Math.sin(r.getAngleRadians()) * -1);
		return new Vector2(x, y);
	}
	public static Vector2 toDirection(Rotation r){
		return toDirection(r, 1);
	}
	public Vector2 toDirection(){
		return toDirection(1);
	}
	//TODO: Rays
	/**
	public Vector2 toRay(float length){
		float x = (float) (length * Math.cos(getAngleRadians()));
		float y = (float) (length * Math.sin(getAngleRadians()) * -1);
		return new Vector2(x, y);
	}
	public static Vector2 toRay(Rotation r, float length){
		float x = (float) (length * Math.cos(r.getAngleRadians()));
		float y = (float) (length * Math.sin(r.getAngleRadians()) * -1);
		return new Vector2(x, y);
	}
	public static Vector2 toRay(Rotation r){
		return toRay(r, 1);
	}
	public Vector2 toRay(){
		return toRay(1);
	}
	**/
}
