package tictactoe.players;

import tictactoe.Board;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class OkayAIPlayer extends Player {
	private int numMoves;
	private int[][] prevMoves;
	private Random randomGen;

	public OkayAIPlayer(Character id, Board board, int length, int width) {
		super(id, board);
		numMoves = 0;
		prevMoves = new int[length * width][2];
		randomGen = new Random();
	}

	public int[] chooseMove() {
		int[][] validMoves = board.getValidMoves();
		int[] choice = new int[2];

		if(prevMoves.length == 0) {
			choice = validMoves[randomGen.nextInt(validMoves.length)];
			prevMoves[numMoves] = choice;
			numMoves++;
			return choice;
		}

		for(int index = 0; index < numMoves; index++) {
			int[] prevMove = prevMoves[index];

			// loops through the x-direction
			for(int row = -1; row < 1; row++) {
				// loops through the y-direction
				for(int col = -1; col < 1; col++) {
					if(row == 0 && col == 0)
						continue;

					// select an adjacent tile
					choice[0] = row + prevMove[0];
					choice[1] = col + prevMove[1];

					// check if this tile adjacent to a previous tile is empty
					if(board.isEmpty(choice[0], choice[1]) &&
							board.validRange(choice[0], choice[1])) {
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
