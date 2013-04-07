package tictactoe.players;

import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class GoodAIPlayer extends Player {
	public GoodAIPlayer(Character id, Board board) {
		super(id, board);
	}

	public int[] chooseMove() {
		return new int[2];
	}
}
