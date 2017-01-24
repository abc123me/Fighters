package net.net16.jeremiahlowe.fighters.fighter;

import java.util.ArrayList;
import java.util.List;

public class FighterController {
	protected static List<Fighter> fighters = new ArrayList<Fighter>();
	public Fighter fighter;
	public FighterController(Fighter fighter){
		this.fighter = fighter;
	}
	public synchronized static List<Fighter> getFighters() {
		return fighters;
	}
	public static void registerFighter(Fighter f) {
		fighters.add(f);
	}
	public void performAction(ActionBase a){
		
	}
}
