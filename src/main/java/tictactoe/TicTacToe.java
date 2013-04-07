package tictactoe;

import tictactoe.players.*;
import java.util.LinkedList;
import java.util.Queue;
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
	private Board Game;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		String userInput1, userInput2;

		System.out.println("Welcome to Tic-Tac-Toe!");

		while (true) {
			System.out.println("Who would you like to play against?");
			System.out.println("[0] The AI.\n[1] A Human.");

			userInput1 = input.next();
			if (userInput1.equals("0") || userInput1.equals(("1"))) {
				if (userInput1.equals("0")) {
					while (true) {
						System.out.println(
								"What difficulty do you want the AI?");
						System.out.println(
								"[0] Easy.\n[1] Medium.\n[2] Difficult.");
						userInput2 = input.next();
						if (userInput2.equals("0") || userInput2.equals("1") ||
								userInput2.equals("2")) {
							new TicTacToe(userInput1, userInput2);
							return;
						} else
							System.err.println("Error: Invalid AI difficulty.");
					}
				} else {
					new TicTacToe(userInput1, null);
					return;
				}
			} else
				System.err.println("Error: Invalid player option.");
		}
	}

	public TicTacToe(String otherPlayer, String difficulty) {
		this.Game = new Board();
		this.PlayerX = new HumanPlayer('X', this.Game);

		if (otherPlayer.equals("0")) {
			if (difficulty.equals("0"))
				this.PlayerY = new RandomAIPlayer('Y', this.Game);
			else if (difficulty.equals("1"))
				this.PlayerY = new OkayAIPlayer('Y', this.Game);
			else if (difficulty.equals("2"))
				this.PlayerY = new GoodAIPlayer('Y', this.Game);
			else
				System.err.println("Fatal Error: Invalid AI difficulty.");
		} else
			this.PlayerY = new HumanPlayer('Y', this.Game);

		this.gameLoop();
	}

	private void gameLoop() {
		int[] choice = new int[2];
		boolean madeMove = false;
		Queue<Player> players = new LinkedList<Player>();
		players.add(PlayerX);
		players.add(PlayerY);
		Player curPlayer;

		while(true) {
			curPlayer = players.poll();
			System.out.println(
					"Player " + curPlayer.getID() + " chooses a move.");
			choice = curPlayer.chooseMove();
			madeMove = Game.setTile(curPlayer.getID(), choice[0], choice[1]);
			System.out.println(Game.toString());

			if(madeMove) {
				String gameStatus =
						isOver(choice[0], choice[1], curPlayer.getID());
				if(gameStatus == null) {
					players.add(curPlayer);
				} else if(gameStatus.equals("tie")) {
					System.out.println(
							this.gameOver(null, false, null, choice));
					break;
				} else
					System.out.println(
							this.gameOver(curPlayer, true, gameStatus, choice));
					break;
			} else {
				players.add(curPlayer);
				curPlayer = players.poll();
				System.out.print("curr:" + curPlayer.getID());
				players.add(curPlayer);
				System.err.println("Error: Invalid move.");
			}
		}
	}

	private String isOver(int lastX, int lastY, Character lastPlayer) {
		int winLength = Math.min(Game.getLength(), Game.getWidth());
		int currX = lastX;
		int currY = lastY;
		int total = 0;

		for(int moveX = -1; moveX <= 1; moveX++) {
			for(int moveY = -1; moveY <= 0; moveY++) {
				if((moveX == 0 && moveY == 0) || (moveX == 1 && moveY == 0))
					continue;

				// back up in the check
				while(Game.validRange(currX, currY) //check if match
						&& Game.getPieceAt(currX, currY) == lastPlayer) {
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
					if(moveY == 0)
						return "row";
					if(moveX == 0)
						return "column";
					if(moveX != 0 && moveY != 0)
						return "diagonal";
				}
			}
		}
		if(Game.getValidMoves() == null)
			return "tie";
		return null;
	}

	private String gameOver(
			Player player, boolean win, String winType, int[] lastMove) {
		if(win && winType.equals("row"))
			return "Game over!\nPlayer " + player.getID() + " has won on " +
					winType + " " + lastMove[0] + "!\n";
		else if(win && winType.equals("column"))
			return "Game over!\nPlayer " + player.getID() + " has won on " +
					winType + " " + lastMove[0] + "!\n";
		else if(win && winType.equals("diagonal"))
			return "Game over!\nPlayer " + player.getID() + " has won on a " +
					winType + "!\n";
		else
			return "Game over!\nThere is a tie!\n";
	}
}
