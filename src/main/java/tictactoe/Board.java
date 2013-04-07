package tictactoe;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class Board {
	private final int LENGTH, WIDTH;
	private Character[][] board;
	private int numEmpty = 0;

	public Board() {
		this(3, 3);
	}

	public Board(int length, int width) {
		LENGTH = length;
		WIDTH = width;
		this.numEmpty = LENGTH * WIDTH;

		this.board = new Character[LENGTH][WIDTH];
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {
				board[row][col] = '_';
			}
		}
	}

	private boolean isEmpty(int x, int y) {
		return board[x][y] == null;
	}

	protected boolean setTile(Character value, int x, int y) {
		if(isEmpty(x, y)) {
			this.board[x][y] = value;
			this.numEmpty--;
			return true;
		} return false;
	}

	public String toString() {
		String board = "";
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {
				board += this.board[row][col] + " ";
			}
			board += "\n";
		} return board;
	}

	public int[][] getValidMoves() {
		int index = 0;
		int[][] validMoves = new int[numEmpty][2];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board.length; col++) {
				if(board[row][col] == '_') {
					validMoves[index][0] = row;
					validMoves[index][1] = col;
					index++;
				}
			}
		}
		return validMoves;
	}
}
