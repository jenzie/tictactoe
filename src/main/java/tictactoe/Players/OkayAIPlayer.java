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
 * OkayAIPlayer
 * Player that chooses moves adjacent to own tiles, but does not defend.
 */
public class OkayAIPlayer extends Player {
	private int numMoves; // number of moves made by this player
	private int[][] prevMoves; // list of previous moves made by this player
	private Random randomGen; // RGN

	/**
	 * Constructor.
	 * @param id identification of this player.
	 * @param board the game.
	 * @param length the length of the board.
	 * @param width the width of the board.
	 */
	public OkayAIPlayer(Character id, Board board, int length, int width) {
		super(id, "OkayAI", board);
		numMoves = 0;
		prevMoves = new int[length * width][2];
		randomGen = new Random();
	}

	/**
	 * Constructor is for testing purposes only.
	 * @param id identification of this player.
	 * @param board the game.
	 * @param length the length of the board.
	 * @param width the width of the board.
	 * @param seed to maintain a repeatable game.
	 */
	public OkayAIPlayer(
			Character id, Board board, int length, int width, int seed) {
		super(id, "OkayAI", board);
		numMoves = 0;
		prevMoves = new int[length * width][2];
		randomGen = new Random(seed);
	}

	@Override
	public int[] chooseMove() {
		int[][] validMoves = board.getValidMoves();
		int[] choice = new int[2];

		// base case to choose a random first move
		if(numMoves == 0) {
			choice = validMoves[randomGen.nextInt(validMoves.length)];
			prevMoves[numMoves] = choice;
			numMoves++;
			return choice;
		}

		// case to choose a move adjacent to this player's previous moves
		for(int index = 0; index < numMoves; index++) {
			int[] prevMove = prevMoves[index];

			// loops through the x-direction
			for(int row = -1; row <= 1; row++) {
				// loops through the y-direction
				for(int col = -1; col <= 1; col++) {
					if(row == 0 && col == 0)
						continue;

					// select an adjacent tile
					choice[0] = row + prevMove[0];
					choice[1] = col + prevMove[1];

					// check if this tile adjacent to a previous tile is empty
					if(board.validRange(choice[0], choice[1]) &&
							board.isEmpty(choice[0], choice[1])) {
						prevMoves[numMoves] = choice;
						numMoves++;
						return choice;
					}
				}
			}
		}
		return null;
	}
}
