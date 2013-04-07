package tictactoe.players;

import tictactoe.Board;

import java.util.Scanner;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class HumanPlayer extends Player {
	private static Scanner input;
	public HumanPlayer(Character id, Board board) {
		super(id, board);
		this.input = new Scanner(System.in);
	}

	public boolean playMove(int x, int y) {
		return true;
	}

	@Override
	public int[] chooseMove() {
		int[] choice = new int[2];

		while(true) {
			System.out.println("Choose your move...");

			try{
				System.out.println("What row do you want to move to?");
				choice[0] = Integer.parseInt(input.nextLine());

				System.out.println("What column do you want to move to?");
				choice[1] = Integer.parseInt(input.nextLine());

				if(board.isEmpty(choice[0], choice[1]))
					return choice;
				System.err.println("Error: Invalid move!");
			} catch(NumberFormatException ignored){
				System.err.println("Error: Values are not integers.");
			}
		}
	}
}
