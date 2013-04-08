package tictactoe.players;

import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public abstract class Player {
	private Character playerID;
	private String type;
	protected final Board board;

	public Player(Character id, String type, Board board) {
		this.playerID = id;
		this.type = type;
		this.board = board;
	}

	public Character getID() {
		return this.playerID;
	}

	public String getType() {
		return this.type;
	}

	public abstract int[] chooseMove();

	public String printMove(int x, int y) {
		return "Player" + this.playerID + " has placed a tile " +
				"on (" + x + "," + y + ").";
	}
}
