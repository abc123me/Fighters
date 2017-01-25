package net.net16.jeremiahlowe.fighters.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.net16.jeremiahlowe.fighters.ai.Actions.Fighting;
import net.net16.jeremiahlowe.fighters.ai.Actions.Movement;
import net.net16.jeremiahlowe.fighters.fighter.ActionBase;
import net.net16.jeremiahlowe.fighters.fighter.Team;

public class Gene {
	public List<ActionBase> actions;
	public float score = 0;
	private Random rng;
	private static float movementChance = 0.85f;
	public final Team parentTeam;
	
	public Gene(Random rng, Team parentTeam){
		this.rng = rng;
		this.parentTeam = parentTeam;
		actions = new ArrayList<ActionBase>();
	}
	public void mutate(float errorChance){
		for(int i = 0; i < actions.size(); i++)
			if(rng.nextFloat() <= errorChance)
				actions.set(i, getRandomAction());
	}
	public void create(int length){
		for(int i = 0; i < length; i++) actions.add(getRandomAction());
	}
	public ActionBase getRandomAction(){
		if(rng.nextFloat() <= movementChance){
			return new Actions.Move(Movement.getRandom(rng));
		}
		else{
			return new Actions.Fight(Fighting.getRandom(rng));
		}
	}
	public static float getMovementChance(){
		return movementChance;
	}
	public static void setMovementChance(float movementChance){
		Gene.movementChance = movementChance;
	}
}
