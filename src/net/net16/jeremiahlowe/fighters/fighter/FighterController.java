package net.net16.jeremiahlowe.fighters.fighter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.ai.Gene;
import net.net16.jeremiahlowe.fighters.ai.GenePool;

public class FighterController {
	//Thanks to Robert + Patricia Liguori for writing the Java Pocket Guide for whenever
	// you need to get quick Java reference when internet is down
	protected static List<FighterController> fighters = new CopyOnWriteArrayList<FighterController>();
	
	public Fighter fighter;
	public int actionIterator = 0;
	public Gene gene;
	
	public FighterController(Fighter fighter){
		gene = new Gene(Fighters.rng, fighter.team);
		this.fighter = fighter;
	}
	
	public void performAction(ActionBase a){
		a.onPerform(this);
		gene.score += a.reward;
	}
	public void createGene(int length){
		gene.create(length);
	}
	public void step(){
		if(!(actionIterator < gene.actions.size())) resetActionIterator();
		ActionBase action = gene.actions.get(actionIterator);
		if(action != null){
			performAction(action);
			actionIterator++;
		}
	}
	public void resetActionIterator(){
		actionIterator = 0;
	}
	
	public static List<FighterController> getFighterControllers() {
		return fighters;
	}
	public static void registerFighterController(FighterController f) {
		fighters.add(f);
	}

	public static void unregisterFighter(FighterController toRemove, boolean keepGene) {
		fighters.remove(toRemove);
		System.out.println("Removed fighter");
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
