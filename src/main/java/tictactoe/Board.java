package tictactoe;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * Board.
 * Represents a game board of fixed, specified dimensions.
 */
public class Board {
	private final int LENGTH, WIDTH;
	private Character[][] board;
	private int numEmpty = 0; // number of empty tiles left
	private int lastX, lastY; // coordinates of the last move

	/**
	 * Constructor with default dimensions.
	 */
	public Board() {
		this(3, 3);
	}

	/**
	 * Constructor with specified dimensions.
	 * @param length number of rows in the board.
	 * @param width number of columns in the board.
	 */
	public Board(int length, int width) {
		this.LENGTH = length;
		this.WIDTH = width;
		this.numEmpty = LENGTH * WIDTH;

		// populate board with underscores representing empty tiles
		this.board = new Character[LENGTH][WIDTH];
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {
				board[row][col] = '_';
			}
		}
	}

	/**
	 * @return length of the board.
	 */
	protected int getLength() {
		return this.LENGTH;
	}

	/**
	 * @return width of the board.
	 */
	protected int getWidth() {
		return this.WIDTH;
	}

	/**
	 * Check if coordinates are within range of the board size.
	 * @param x row/x-coordinate of the move.
	 * @param y column/y-coordinate of the move.
	 * @return true if move is within range.
	 */
	public boolean validRange(int x, int y){
		return (x >= 0 && x < this.LENGTH) && (y >= 0 && y < this.WIDTH);
	}

	/**
	 * Check if tile at coordinates is empty on the board.
	 * @param x row/x-coordinate of the move.
	 * @param y column/y-coordinate of the move.
	 * @return true if tile is empty.
	 */
	public boolean isEmpty(int x, int y) {
		return board[x][y] == '_';
	}

	/**
	 * Makes a change to the tile at the coordinates and fill it with the value.
	 * @param value identification of the tile/player.
	 * @param x row/x-coordinate of the move.
	 * @param y column/y-coordinate of the move.
	 * @return true if move was made.
	 */
	public boolean setTile(Character value, int x, int y) {
		// check if coordinates are within the size of board.
		if(!validRange(x, y))
			return false;

		// check if coordinates have an empty tile
		if(isEmpty(x, y)) {
			this.board[x][y] = value;
			this.numEmpty--;
			this.lastX = x;
			this.lastY = y;
			return true;
		} return false;
	}

	/**
	 * @return list of empty tiles in the board.
	 */
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
		} return validMoves;
	}

	/**
	 * @param id identification of this player.
	 * @return the identification of the other player.
	 */
	public Character getOtherPlayer(Character id) {
		for(int row = 0; row < this.LENGTH; row++) {
			for(int col = 0; col < this.WIDTH; col++) {
				if(!this.board[row][col].equals(id) &&
						!this.board[row][col].equals('_'))
					return this.board[row][col];
			}
		} return null;
	}

	/**
	 * Get the list of previous moves made by a player.
	 * @param id identification of this player.
	 * @param otherPlayer true if checking for the other player.
	 * @return list of moves made by a player.
	 */
	public int[][] getPlayerMoves(Character id, boolean otherPlayer) {
		Character match = id;
		int[][] moves = new int[this.LENGTH * this.WIDTH - numEmpty][];
		int index = 0;

		// get the id of the other player, if requested
		if(otherPlayer)
			match = this.getOtherPlayer(id);

		// look for tiles that match the id
		for(int row = 0; row < this.LENGTH; row++) {
			for(int col = 0; col < this.WIDTH; col++) {
				if(this.board[row][col].equals(match)) {
					moves[index][0] = row;
					moves[index][1] = col;
					index++;
				}
			}
		} return moves;
	}

	/**
	 * @return the coordinates of the last move made on the board.
	 */
	public int[] getLastMove() {
		int[] move = new int[2];
		move[0] = this.lastX;
		move[1] = this.lastY;
		return move;
	}

	/**
	 * @param x row/x-coordinate of the move.
	 * @param y column/y-coordinate of the move.
	 * @return the identification of the player who made that move;
	 * 			return an underscore if no player made that move.
	 */
	protected Character getPieceAt(int x, int y) {
		return this.board[x][y];
	}

	/**
	 * @return string representing the board.
	 */
	public String toString() {
		String board = "";
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {
				board += this.board[row][col] + " ";
			}
			board += "\n";
		} return board;
	}

	/**
	 * @return a safe copy of the board, specifically for GoodAIPlayer to
	 * 			determine moves.
	 */
	public Character[][] getBoard() {
		Character[][] clone = new Character[this.LENGTH][this.WIDTH];
		for(int row = 0; row < this.LENGTH; row++) {
			for(int col = 0; col < this.WIDTH; col++)
				clone[row][col] = board[row][col].charValue();
		} return clone;
	}
}
