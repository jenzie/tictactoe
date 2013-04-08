package tictactoe.players;

import junit.framework.Assert;
import junit.framework.TestCase;
import tictactoe.Board;
import tictactoe.TicTacToe;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 08, 2013
 * language: Java
 * project: tictactoe
 */

/**
 * GoodAIPlayerTest.
 * Tests the GoodAIPlayer for two games.
 */
public class GoodAIPlayerTest extends TestCase {
	private final Board board = new Board();
	private TicTacToe tictactoe;
	private GoodAIPlayer good;

	/**
	 * Tests the chooseMove() method where this AI wins.
	 * @throws Exception
	 */
	public void testChooseMoveWin() throws Exception {
		int[] choice = new int[2];
		tictactoe = new TicTacToe("0", "2");
		good = (GoodAIPlayer) tictactoe.getPlayerO();
		good.setBoard(board);

		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 1);
		board.setTile(good.getID(), choice[0], choice[1]);
		board.setTile('X', 0, 0);

		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 0 && choice[1] == 1);
		board.setTile(good.getID(), choice[0], choice[1]);
		board.setTile('X', 2, 2);

		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 1);
		board.setTile(good.getID(), choice[0], choice[1]);
		board.setTile('X', 0, 2);
	}

	/**
	 * Tests the chooseMove() method where this AI ties.
	 * @throws Exception
	 */
	public void testChooseMoveTie() throws Exception {
		int[] choice = new int[2];
		tictactoe = new TicTacToe("0", "2");
		good = (GoodAIPlayer) tictactoe.getPlayerO();
		good.setBoard(board);

		board.setTile('X', 1, 1);
		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 0 && choice[1] == 0);
		board.setTile(good.getID(), choice[0], choice[1]);

		board.setTile('X', 2, 2);
		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 0 && choice[1] == 2);
		board.setTile(good.getID(), choice[0], choice[1]);

		board.setTile('X', 0, 1);
		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 1);
		board.setTile(good.getID(), choice[0], choice[1]);

		board.setTile('X', 2, 0);
		choice = good.chooseMove();
		Assert.assertTrue(choice[0] == 1 && choice[1] == 0);
		board.setTile(good.getID(), choice[0], choice[1]);

		board.setTile('X', 1, 2);
		Assert.assertEquals(0, board.getValidMoves().length);
	}
}
