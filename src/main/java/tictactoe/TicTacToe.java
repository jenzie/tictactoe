package tictactoe;

import java.util.Scanner;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

public class TicTacToe {
	private static Scanner input;
	private Player PlayerX, PlayerY;

	public static void main() {
		input = new Scanner(System.in);
		String userInput = null;

		System.out.println("Welcome to Tic-Tac-Toe!");

		while(true) {
			System.out.println("Who would you like to play against?");
			System.out.println("[0] The AI.\n[1] A Human.");

			userInput = input.next();
			if(userInput.equals("0") || userInput.equals(("1"))) {
				new TicTacToe(userInput);
				break;
			} else
				System.err.println("Error: Invalid player option.");
		}
	}

	public TicTacToe(String otherPlayer) {
		this.PlayerX = new HumanPlayer('X');

		if(otherPlayer.equals("0"))
			this.PlayerY = new AIPlayer('Y');
		else
			this.PlayerY = new HumanPlayer('Y');
	}
}
