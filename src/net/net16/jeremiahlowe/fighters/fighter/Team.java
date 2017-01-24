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
	public Team[] getOpposing(){
		switch(this){
			case Red: return new Team[]{Blue, Yellow, Green};
			case Blue: return new Team[]{Red, Yellow, Green};
			case Yellow: return new Team[]{Blue, Red, Green};
			case Green: return new Team[]{Blue, Yellow, Red};
		}
		return new Team[]{Blue, Yellow, Green, Red};
	}
}
