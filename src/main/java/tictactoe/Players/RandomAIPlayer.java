package tictactoe.players;

import tictactoe.Board;

import java.util.Random;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class RandomAIPlayer extends Player {
	private Random randomGen;

	public RandomAIPlayer(Character id, Board board) {
		super(id, "RandomAI", board);
		randomGen = new Random();
	}

	public RandomAIPlayer(Character id, Board board, int seed) {
		super(id, "RandomAI", board);
		randomGen = new Random(seed);
	}

	public int[] chooseMove() {
		int[][] validMoves = board.getValidMoves();
		return validMoves[randomGen.nextInt(validMoves.length)];
	}
}
