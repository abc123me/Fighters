package net.net16.jeremiahlowe.fighters.ai;

import java.util.ArrayList;
import java.util.List;

public class GenePool {
	public static List<Gene> genes = new ArrayList<Gene>();
	public static void registerGene(Gene g){
		genes.add(g);
	}
	public static Gene[] getSortedGenes(boolean leastToGreatest){
		Gene[] out = new Gene[genes.size()];
		return out;
	}
	public static void clear(){
		genes.clear();
	}
	public static void mutateGenePool(float errorChance){
		for(Gene g : genes) 
			g.mutate(errorChance);
	}
}
