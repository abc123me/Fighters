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
	public static void spawnNewGenerationFromGenePool(float errorChance){
		for(Gene g : GenePool.genes){
			g.mutate(errorChance);
			Spawner.spawnFighterFromGene(g.parentTeam, g);
		}
	}
	public static void spawnNewGenerationFromScratch(int GENE_LENGTH){
		for(Team team : teams)
			for(int i = 0; i < playersPerTeam; i++)
				Spawner.spawnFighterWithController(team, GENE_LENGTH);
	}
}
