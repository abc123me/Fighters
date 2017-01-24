package net.net16.jeremiahlowe.fighters.fighter;

import java.awt.Color;

public enum Team {
	Red, Blue, Yellow, Green;

	public Color getColor() {
		switch(this){
			case Red: return Color.red;
			case Blue: return Color.blue;
			case Yellow: return Color.yellow;
			case Green: return Color.green;
		}
		return Color.black;
	}
}
