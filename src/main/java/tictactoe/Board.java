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
	private ArrayList<ArrayList<Character>> board;

	public Board() {
		this(3, 3);
	}

	public Board(int length, int width) {
		LENGTH = length;
		WIDTH = width;

		this.board = new ArrayList<ArrayList<Character>>();
	}
}
