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
	private int lastX, lastY;

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

	public boolean setTile(Character value, int x, int y) {
		if(!validRange(x, y))
			return false;
		if(isEmpty(x, y)) {
			this.board[x][y] = value;
			this.numEmpty--;
			this.lastX = x;
			this.lastY = y;
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

	public int[][] getPlayerMoves(Character id, boolean otherPlayer) {
		Character match = id;
		int[][] moves = new int[this.LENGTH * this.WIDTH - numEmpty][];
		int index = 0;

		// get the id of the other player if requested
		if(otherPlayer) {
			for(int row = 0; row < this.LENGTH; row++) {
				for(int col = 0; col < this.WIDTH; col++) {
					if(!this.board[row][col].equals(id))
						match = this.board[row][col];
				}
			}
		}

		// look for tiles that match the id
		for(int row = 0; row < this.LENGTH; row++) {
			for(int col = 0; col < this.WIDTH; col++) {
				if(this.board[row][col].equals(match)) {
					moves[index][0] = row;
					moves[index][1] = col;
					index++;
				}
			}
		}
		return moves;
	}

	public int[] getLastMove() {
		int[] move = new int[2];
		move[0] = this.lastX;
		move[1] = this.lastY;
		return move;
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
