package tictactoe.players;

import tictactoe.Board;

import java.util.Random;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class GoodAIPlayer extends Player {
	private int numMoves;
	private int[][] prevMoves;
	private int length, width;

	public GoodAIPlayer(Character id, Board board, int length, int width) {
		super(id, "GoodAI", board);
		numMoves = 0;
		prevMoves = new int[length * width][2];
		this.length = length;
		this.width = width;
	}

	public int[] chooseMove() {
		int[] altPlayerLastMove = board.getLastMove();
		int[][] validMoves = board.getValidMoves();
		int[] choice = new int[2];

		// if this is our first move
		if(prevMoves.length == 0) {
			// get the center tile if possible
			if(board.isEmpty((length/2), (width/2))) {
				choice[0] = length/2;
				choice[1] = width/2;
			} else { // otherwise, get a corner
				choice[0] = length/2 - 1;
				choice[1] = width/2 - 1;
			}
			prevMoves[numMoves] = choice;
			numMoves++;
			return choice;
		}

		// choose a move based on other player's moves
		int winLength = Math.min(this.length, this.width);
		int currX = altPlayerLastMove[0];
		int currY = altPlayerLastMove[1];
		int total = 0;

		for(int moveX = -1; moveX <= 1; moveX++) {
			for(int moveY = -1; moveY <= 1; moveY++) {
				currX = altPlayerLastMove[0];
				currY = altPlayerLastMove[1];

				if((moveX == 0 && moveY == 0) || (moveX == 1 && moveY == 0))
					continue;
				/*
				// back up in the check
				while(board.validRange(currX, currY) //check if match
						&& board.getPieceAt(currX, currY) == lastPlayer) {
					currX += moveX;
					currY += moveY;
				}
				currX -= moveX;
				currY -= moveY;
				total = 0; //reset total

				// move forwards in the check
				while(total < winLength && Game.validRange(currX, currY)
						&& Game.getPieceAt(currX, currY) == lastPlayer) {
					total += 1;
					currX -= moveX;
					currY -= moveY;
				}

				//check if won and with what direction
				if(total >= winLength) {
					if(moveX == 0)
						return "row";
					if(moveY == 0)
						return "column";
					if(moveX != 0 && moveY != 0)
						return "diagonal";
				}*/
			}
		}

		return null;
	}

	private int[][] getEmptyNeighbors(int[] curTile, Character id){
		int[][] neighbors = new int[length * width][2];
		int index = 0;

		for(int moveX = -1; moveX <= 1; moveX++) {
			for(int moveY = -1; moveY <= 1; moveY++) {
				if(board.isEmpty(curTile[0] + moveX, curTile[1] + moveY)) {
					neighbors[index][0] = curTile[0] + moveX;
					neighbors[index][1] = curTile[1] + moveY;
				}
			}
		}
		return neighbors;
	}
}
