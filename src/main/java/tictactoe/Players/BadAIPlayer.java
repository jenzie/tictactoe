package tictactoe.players;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class BadAIPlayer extends Player {
	public BadAIPlayer(Character id) {
		super(id);
	}

	public boolean playMove(int x, int y) {
		return true;
	}

	private int[] chooseMove() {
		return new int[2];
	}
}
