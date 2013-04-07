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
		this.LENGTH = length;
		this.WIDTH = width;
		this.numEmpty = LENGTH * WIDTH;

		this.board = new Character[LENGTH][WIDTH];
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {
				board[row][col] = '_';
			}
		}
	}

	protected int getLength() {
		return this.LENGTH;
	}

	protected int getWidth() {
		return this.WIDTH;
	}

	public boolean validRange(int row, int col){
		return (row >= 0 && row < this.LENGTH) && (col >= 0 && col < this.WIDTH);
	}

	public boolean isEmpty(int x, int y) {
		return board[x][y] == '_';
	}

	protected boolean setTile(Character value, int x, int y) {
		if(!validRange(x, y))
			return false;
		if(isEmpty(x, y)) {
			this.board[x][y] = value;
			this.numEmpty--;
			return true;
		} return false;
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

	protected Character getPieceAt(int x, int y) {
		return this.board[x][y];
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
}
