package tictactoe.players;

import tictactoe.Board;
import tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 05, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * GoodAIPlayer
 * Player chooses moves based on a MinMax Algorithm.
 * This player can only win or tie.
 */
public class GoodAIPlayer extends Player {
	private int numMoves; // number of moves this player made
	private int[][] prevMoves; // list of moves this player have made
	private int length, width; // dimensions of the board
	private Character otherPlayer; // identification of the other player

	/**
	 * Constructor.
	 * @param id identification of this player.
	 * @param board the game.
	 * @param length the length of the board.
	 * @param width the width of the board.
	 */
	public GoodAIPlayer(Character id, Board board, int length, int width) {
		super(id, "GoodAI", board);
		numMoves = 0;
		prevMoves = new int[length * width][2];
		this.length = length;
		this.width = width;
		this.otherPlayer = TicTacToe.getOtherPlayer(this.getID());
	}

	@Override
	public int[] chooseMove(){
		Integer[] move = new Integer[2];
		int[] choice = new int[2];
		int[][] validMoves = board.getValidMoves();
		Character[][] boardCopy = board.getBoard();

		// want to convert list to a more convenient form
		List<Integer[]> validMovesList = new ArrayList<Integer[]>();
		for(int[] validMove : validMoves){
			move = new Integer[validMove.length];
			move[0] = validMove[0];
			move[1] = validMove[1];
			validMovesList.add(move);
		}

		// starting case; make the first move
		if(validMovesList.size() >= length * width - 1) {
			// go to center, heuristic
			if(board.isEmpty(length/2, width/2)) {
				choice[0] = length/2;
				choice[1] = width/2;
			} else {
				choice[0] = 0;
				choice[1] = 0;
			}
		}

		// otherwise, apply Min-Max Theorem
		else {
			int score = -2;
			for(Integer[] validMove : validMovesList){
				System.out.println(validMovesList.size());
				// place a piece on scratch board
				boardCopy[validMove[0]][validMove[1]] = getID();

				String status = TicTacToe.isOver(validMove[0], validMove[1],
						getID(), boardCopy, validMovesList.size() - 1);
				if(status != null && !status.equals("tie")){
					choice[0] = validMove[0];
					choice[1] = validMove[1];
					break;
				}

				// create new move list without the move just made
				List<Integer[]> newMoveList = new ArrayList<Integer[]>();
				newMoveList.addAll(validMovesList);
				newMoveList.remove(validMove);

				int curScore = minimizeScore(newMoveList, makeCopy(boardCopy));
				if(curScore > score){
					score = curScore;
					choice[0] = validMove[0];
					choice[1] = validMove[1];
				}

				// undo move for future simulation
				boardCopy[validMove[0]][validMove[1]] = '_';
			}
		} return choice;
	}

	/**
	 * Minimize the possibility of a worst case loss by evaluating moves based
	 * on a score. We want the AI to prefer winning over sooner, so leaf nodes
	 * are given a lower score than branch nodes.
	 *
	 * @param validMoveList list of empty, valid tiles.
	 * @param boardCopy copy of the game.
	 * @return score evaluated based on valid moves.
	 */
	private int minimizeScore(
			List<Integer[]> validMoveList, Character[][] boardCopy) {
		int score = 2;
		if(validMoveList.size() == 1) {
			Integer[] move = validMoveList.get(0);
			boardCopy[move[0]][move[1]] = otherPlayer;
			String status = TicTacToe.isOver(move[0], move[1], otherPlayer,
					boardCopy, 0);
			if(status.equals("tie"))
				score = 0;
			else
				score = -2;
		} else {
			for(Integer[] validMove : validMoveList) {

				// place piece on scratch board
				boardCopy[validMove[0]][validMove[1]] = otherPlayer;

				String status = TicTacToe.isOver(validMove[0], validMove[1],
						otherPlayer, boardCopy, validMoveList.size() - 1);

				if(status != null && status.equals("tie")) {
					score = 0;
					break;
				} else if(status != null) {
					score = -1;
					break;
				}

				// create new move list without the move just made
				List<Integer[]> newMoveList = new ArrayList<Integer[]>();
				newMoveList.addAll(validMoveList);
				newMoveList.remove(validMove);

				int curScore = maximizeScore(newMoveList, makeCopy(boardCopy));
				if(curScore < score)
					score = curScore;

				// undo move for future simulation
				boardCopy[validMove[0]][validMove[1]] = '_';
			}
		} return score;
	}

	/**
	 * Maximize the possibility of a best case win by evaluating moves based
	 * on a score.
	 * @param validMoveList list of empty, valid tiles.
	 * @param boardCopy copy of the game.
	 * @return score evaluated based on valid moves.
	 */
	private int maximizeScore(List<Integer[]> validMoveList,
							  Character[][] boardCopy) {
		int score = -2;

		if(validMoveList.size() == 1) {
			Integer[] move = validMoveList.get(0);
			boardCopy[move[0]][move[1]] = getID();
			String status = TicTacToe.isOver(move[0], move[1], getID(),
					boardCopy, 0);
			if(status.equals("tie"))
				score = 0;
			else
				score = 1;
		} else{
			for(Integer[] validMove : validMoveList){

				// place piece on scratch board
				boardCopy[validMove[0]][validMove[1]] = getID();

				String status = TicTacToe.isOver(validMove[0], validMove[1],
						getID(), boardCopy, validMoveList.size() - 1);
				if(status != null && status.equals("tie")) {
					score = 0;
					break;
				} else if(status != null) {
					score = 2;
					break;
				}

				// create new move list without the move just made
				List<Integer[]> newMoveList = new ArrayList<Integer[]>();
				newMoveList.addAll(validMoveList);
				newMoveList.remove(validMove);

				int curScore = minimizeScore(newMoveList, makeCopy(boardCopy));
				if(curScore > score)
					score = curScore;

				// undo move for future simulation
				boardCopy[validMove[0]][validMove[1]] = '_';
			}
		} return score;
	}

	/**
	 * Gets a deep copy of the board.
	 * @param boardCopy safe copy of the board from Board.
	 * @return a copy of the game.
	 */
	private Character[][] makeCopy(Character[][] boardCopy) {
		Character[][] clone =
				new Character[boardCopy.length][boardCopy[0].length];
		for(int row = 0; row < boardCopy.length; row++){
			for(int col = 0; col < boardCopy[1].length; col++){
				clone[row][col] = boardCopy[row][col].charValue();
			}
		}
		return clone;
	}

	/**
	 * For testing GoodAIPlayer only.
	 * @param board board to set to.
	 */
	public void setBoard(Board board) {
		super.board = board;
	}
}
