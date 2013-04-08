package tictactoe.players;

import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * Player
 * Represents a player in a game.
 */
public abstract class Player {
	private Character playerID;
	private String type;
	protected Board board;

	/**
	 * Constructor.
	 * @param id identification of this player
	 * @param type type of this player
	 * @param board the game
	 */
	public Player(Character id, String type, Board board) {
		this.playerID = id;
		this.type = type;
		this.board = board;
	}

	/**
	 * Gets the identification of this player.
	 * @return the id of this player.
	 */
	public Character getID() {
		return this.playerID;
	}

	/**
	 * Gets the type of this player.
	 * @return the player type.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Algorithm to determine the move based on the player's strategy.
	 * @return integer array [x, y]
	 */
	public abstract int[] chooseMove();

	/**
	 * Gets player's move message.
	 * @param x row this player moved to.
	 * @param y column this player moved to.
	 * @return string representing the move.
	 */
	public String printMove(int x, int y) {
		return "Player" + this.playerID + " has placed a tile " +
				"on (" + x + "," + y + ").";
	}
}