package tictactoe;

import java.util.ArrayList;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class Board {
	private final int LENGTH, WIDTH;
	private Character[][] board;

	public Board() {
		this(3, 3);
	}

	public Board(int length, int width) {
		LENGTH = length;
		WIDTH = width;

		this.board = new Character[LENGTH][WIDTH];
	}

	private boolean isEmpty(int x, int y) {
		return board[x][y] == null;
	}

	private boolean setTile(Character value, int x, int y) {
		if(isEmpty(x, y)) {
			this.board[x][y] = value;
			return true;
		} return false;
	}

	public String toString() {
		String board = null;
		for(int row = 0; row < LENGTH; row++) {
			for(int col = 0; col < WIDTH; col++) {

			}
		} return board;
	}
}
