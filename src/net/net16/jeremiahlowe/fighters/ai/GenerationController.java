package net.net16.jeremiahlowe.fighters.ai;

import net.net16.jeremiahlowe.fighters.fighter.FighterController;
import net.net16.jeremiahlowe.fighters.fighter.Spawner;
import net.net16.jeremiahlowe.fighters.fighter.Team;

public class GenerationController {
	public static Team[] teams = Team.values();
	public static int playersPerTeam = 1;
	public static synchronized void killCurrentGeneration(){
		for(FighterController f : FighterController.getFighterControllers()){
			FighterController.unregisterFighter(f, true);
		}
	}
	public static void spawnNewGenerationFromGenePool(float errorChance, boolean resetScores, float newScore){
		Gene theBest = GenePool.getBestGene();
		if(theBest != null){
			for(Team team : teams){
				for(int i = 0; i < playersPerTeam; i++){
					FighterController f = Spawner.spawnFighterFromGene(team, theBest);
					theBest.mutate(errorChance);
					if(resetScores) f.gene.score = newScore;
				}
			}
		}
		else{
			System.out.println("No good genes! WTF?!");
		}
	}
	public static void spawnNewGenerationFromScratch(int GENE_LENGTH, boolean resetScores, float newScore){
		for(Team team : teams){
			for(int i = 0; i < playersPerTeam; i++){
				FighterController f = Spawner.spawnFighterWithController(team, GENE_LENGTH);
				if(resetScores) f.gene.score = newScore;
			}
		}
	}
	public static void resetGenePoolScores(float score){
		for(Gene g : GenePool.genes) g.score = score;
	}
}
