package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tictactoe.Mark;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Kart;

public class TicTacToeBoardAllTests_MINI
{
	/**********************************************************************************************************************/
	@Points(value=5)
	@Test(timeout=3000)
	public void emptyBoardTest()
	{
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Kart();
		final Mark EMPTY = null;
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				assertTrue(studentBoard.getMark(i, j) == EMPTY);
			}
		}
	}	
	
	@Points(value=5)
	@Test(timeout=3000)
	public void oneSymbolTest()
	{	
		final int ROW = 0;
		final int COLUMN = 2;

		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Kart();
		studentBoard.setMark(ROW, COLUMN);
		assertEquals(Mark.X, studentBoard.getMark(ROW, COLUMN));
	}	
	
	@Points(value=5)
	@Test(timeout=3000)
	public void isGameOverTest()
	{	
		TicTacToeBoard studentBoard = new TicTacToeBoardImpl_Kart();
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			studentBoard.setMark(i, 0);
		}
		assertTrue(!studentBoard.isGameOver());
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			studentBoard.setMark(i, 1);
		}
		assertTrue(!studentBoard.isGameOver());
		studentBoard.setMark(0,2);
		assertTrue(studentBoard.isGameOver());
	}	
}
