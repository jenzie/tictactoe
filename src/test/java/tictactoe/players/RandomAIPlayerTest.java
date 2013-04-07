package tictactoe.players;

import junit.framework.Assert;
import junit.framework.TestCase;
import tictactoe.Board;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 07, 2013
 * language: Java
 * project: tictactoe
 */

public class RandomAIPlayerTest extends TestCase {

	private final Board board = new Board();
	private final RandomAIPlayer bad = new RandomAIPlayer('X', board, 100);

	public void testChooseMove() throws Exception {
		int[] choice = bad.chooseMove();
		Assert.assertTrue(choice[0] == 2 && choice[1] == 1);
	}
}
