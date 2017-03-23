package net.net16.jeremiahlowe.fighters.ai;

import java.util.ArrayList;
import java.util.List;

public class GenePool {
	private static float lastGenerationMaxPoints = 0;
	public static float getLastGenMaxPoints(){
		return lastGenerationMaxPoints;
	}
	public static List<Gene> genes = new ArrayList<Gene>();
	public static void registerGene(Gene g){
		genes.add(g);
	}
	public static Gene getBestGene(){
		float highestScore = Float.MIN_VALUE;
		for(Gene g : genes) highestScore = g.score > highestScore ? g.score : highestScore;
		System.out.println("Highest score: " + highestScore);
		lastGenerationMaxPoints = highestScore;
		for(Gene g : genes) if(g.score == highestScore) return g;
		return null;
	}
	public static void clear(){
		genes.clear();
	}
	public static void mutateGenePool(float errorChance){
		for(Gene g : genes) g.mutate(errorChance);
	}
}
