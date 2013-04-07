package tictactoe.players;

import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class OkayAIPlayer extends Player {
	private final Board board;

	public OkayAIPlayer(Character id, Board board) {
		super(id);
		this.board = board;
	}

	public boolean playMove(int x, int y) {
		return true;
	}

	public int[] chooseMove() {
		return new int[2];
	}
}
