package net.net16.jeremiahlowe.fighters;

import net.net16.jeremiahlowe.bettercollections.vector.Vector2;
import net.net16.jeremiahlowe.fighters.fighter.Fighter;
import net.net16.jeremiahlowe.fighters.fighter.Team;

public class Fighters {

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
		Vector2 pos = new Vector2(gui.drawCanvas.getWidth() / 2, gui.drawCanvas.getHeight() / 2);
		Rotation rot = new Rotation(45);
		Fighter f = new Fighter(pos, rot, Team.Blue, true);
		gui.drawCanvas.fighters.add(f);
		gui.drawCanvas.repaint();
	}

}
