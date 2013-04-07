package tictactoe.players;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public abstract class Player {
	private Character playerID;
	private String type;

	public Player(Character id) {
		this.playerID = id;
		this.type = "None";
	}

	public Character getID() {
		return this.playerID;
	}

	public String getType() {
		return this.type;
	}

	public abstract boolean playMove(int x, int y);

	public String printMove(int x, int y) {
		return "Player" + this.playerID + " has placed a tile " +
				"on (" + x + "," + y + ").";
	}
}
