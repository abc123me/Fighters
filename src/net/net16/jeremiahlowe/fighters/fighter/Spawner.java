package net.net16.jeremiahlowe.fighters.fighter;

import net.net16.jeremiahlowe.bettercollections.Rotation;
import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.ai.Gene;

public class Spawner {
	public static Team[] availableTeams = Team.values();
	public static FighterController createFighterWithController(Team team){
		FighterController out = new FighterController(createFighter(team));
		out.createGene(250);
		return out;
	}
	public static Fighter createFighter(Team team){
		Vector2 pos = new Vector2();
		pos.x = Fighters.rng.nextInt(Fighters.gui.drawCanvas.getWidth());
		pos.y = Fighters.rng.nextInt(Fighters.gui.drawCanvas.getHeight());
		Rotation rot = new Rotation(Fighters.rng.nextInt(360));
		Fighter out = new Fighter(pos, rot, team);
		return out;
	}
	public static FighterController spawnFighterWithController(Team team, int geneLength){
		FighterController a = Spawner.createFighterWithController(team);
		a.createGene(geneLength);
		FighterController.registerFighterController(a);
		return a;
	}
	public static FighterController spawnFighterFromGene(Team team, Gene gene){
		FighterController a = Spawner.createFighterWithController(team);
		a.gene = gene;
		FighterController.registerFighterController(a);
		return a;
	}
}
