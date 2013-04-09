package tictactoe.players;

import tictactoe.Board;

import java.util.Scanner;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * HumanPlayer
 * Player that takes in user input for moves.
 */
public class HumanPlayer extends Player {
	private static Scanner input; // gets user input

	/**
	 * Constructor.
	 * @param id identification of this player.
	 * @param board the game.
	 */
	public HumanPlayer(Character id, Board board) {
		super(id, "Human", board);
		this.input = new Scanner(System.in);
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

				if(board.validRange(choice[0], choice[1]) && board.isEmpty(choice[0], choice[1]))
					return choice;
				System.err.println("Error: Invalid move!");
			} catch(NumberFormatException ignored){
				System.err.println("Error: Values are not integers.");
			}
		}
	}
}
