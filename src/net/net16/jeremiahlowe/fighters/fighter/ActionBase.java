package net.net16.jeremiahlowe.fighters.fighter;

public abstract class ActionBase{
	public String name = "Nothing";
	public float reward = -0.2f;
	public ActionBase(String name, float reward){
		this.name = name;
		this.reward = reward;
	}
	public abstract void onPerform(FighterController f);
}