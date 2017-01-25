package net.net16.jeremiahlowe.fighters.fighter;

import java.util.ArrayList;
import java.util.List;

import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.ai.Gene;
import net.net16.jeremiahlowe.fighters.ai.GenePool;

public class FighterController {
	protected static List<FighterController> fighters = new ArrayList<FighterController>();
	public Fighter fighter;
	public int actionIterator = 0;
	public Gene gene;
	
	public FighterController(Fighter fighter){
		gene = new Gene(Fighters.rng);
		this.fighter = fighter;
	}
	
	public void performAction(ActionBase a){
		a.onPerform(this);
	}
	public void createGene(int length){
		gene.create(length);
	}
	public synchronized void step(){
		if(!(actionIterator < gene.actions.size())) resetActionIterator();
		ActionBase action = gene.actions.get(actionIterator);
		System.out.println("Performing action: " + action.name);
		if(action != null){
			performAction(action);
			actionIterator++;
		}
	}
	public void resetActionIterator(){
		actionIterator = 0;
	}
	
	public synchronized static List<FighterController> getFighterControllers() {
		return fighters;
	}
	public static void registerFighterController(FighterController f) {
		fighters.add(f);
	}

	public static void unregisterFighter(FighterController toRemove, boolean keepGene) {
		fighters.remove(toRemove);
		if(keepGene) GenePool.registerGene(toRemove.gene);
	}
	
	public void addPoints(float points){
		gene.score += points;
	}
	
	public static FighterController findFighterController(Fighter f){
		for(FighterController fc : fighters) 
			if(fc.fighter == f) return fc;
		return null;
	}
}
