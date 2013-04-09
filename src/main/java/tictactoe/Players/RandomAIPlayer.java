package tictactoe.players;

import tictactoe.Board;

import java.util.Random;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * RandomAIPlayer
 * Player that generates random moves based on a list of available moves.
 */
public class RandomAIPlayer extends Player {
	private Random randomGen;

	/**
	 * Constructor.
	 * @param id identification of this player.
	 * @param board the game.
	 */
	public RandomAIPlayer(Character id, Board board) {
		super(id, "RandomAI", board);
		randomGen = new Random();
	}

	/**
	 * Constructor is for testing purposes to have a set "random" number gen.
	 * @param id identification of this player.
	 * @param board the game.
	 * @param seed to maintain a repeatable game.
	 */
	public RandomAIPlayer(Character id, Board board, int seed) {
		super(id, "RandomAI", board);
		randomGen = new Random(seed);
	}

	@Override
	public int[] chooseMove() {
		// get a list of empty tiles; choose one of them randomly
		int[][] validMoves = board.getValidMoves();
		return validMoves[randomGen.nextInt(validMoves.length)];
	}
}
