package net.net16.jeremiahlowe.fighters.fighter;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.Fighters;
import net.net16.jeremiahlowe.fighters.Rotation;
import net.net16.jeremiahlowe.fighters.ai.Gene;

public class Spawner {
	public static Team[] availableTeams = Team.values();
	public static FighterController createFighterWithController(){
		FighterController out = new FighterController(createFighter());
		out.createGene(250);
		return out;
	}
	public static Fighter createFighter(){
		Vector2 pos = new Vector2();
		pos.x = Fighters.rng.nextInt(Fighters.gui.drawCanvas.getWidth());
		pos.y = Fighters.rng.nextInt(Fighters.gui.drawCanvas.getHeight());
		Rotation rot = new Rotation(Fighters.rng.nextInt(360));
		Team team = availableTeams[Fighters.rng.nextInt(3)];
		Fighter out = new Fighter(pos, rot, team);
		return out;
	}
	public static FighterController spawnFighterWithController(int geneLength){
		FighterController a = Spawner.createFighterWithController();
		a.createGene(geneLength);
		FighterController.registerFighterController(a);
		return a;
	}
	public static FighterController spawnFighterFromGene(Gene gene){
		FighterController a = Spawner.createFighterWithController();
		a.gene = gene;
		FighterController.registerFighterController(a);
		return a;
	}
}
