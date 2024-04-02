//Implementation by Garick Mendez Padilla
//COSC 3327-01 F22
package tictactoe;
import java.util.ArrayList;


public class TicTacToeBoardImpl_Mendez implements TicTacToeBoard {
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;
	protected int[] movesArray;
	
	public TicTacToeBoardImpl_Mendez() 
	 { 
	  final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT; 
	  movesArray = new int[CELL_COUNT]; 
	  for(int i = 0; i < CELL_COUNT; i++) 
	  { 
	   movesArray[i] = NO_MOVE; 
	  } 
	 }
	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of post: rv == null <==> the (row, column) spot on the
	// board is empty
	public Mark getMark(int row, int column) {
		assert row < ROW_COUNT && row >= 0 : "Invalid row count";
		assert column < COLUMN_COUNT && column >= 0 : "Invalid column count";
		
		Mark newMark = Mark.valueOf("X");
		int findMarkSpot = findMarkLocation(row,column); //finds where the mark will be located
		
		// Search the array to see if the mark is not empty
		if(search_movesArray(findMarkSpot))
		{
			// if the spot isnt empty then it will find the index inside the array to dictate the turn
			int index = search_movesArrayIndex(findMarkSpot);
			if(index%2==0)
			{
				newMark = Mark.valueOf("X");
			}
			
			if(index%2==1)
			{
				newMark = Mark.valueOf("O");
			}
		}
		else
		{
			newMark= null;
		}
		//if spot is not found itll just return null
		return newMark;
	}

	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of pre: getMark(row, column) == null
	//part of pre: !isGameOver()
	//post: Left to student
	public void setMark(int row, int column) {
		assert row < ROW_COUNT && row >= 0 : "Invalid row count";
		assert column < COLUMN_COUNT && column >= 0 : "Invalid row count";
		assert getMark(row,column) == null : "Mark already filled";
		assert !isGameOver() : "Game is over";
		
		int setMarkSpot = findMarkLocation(row,column); //finds where the mark will be located
		
		// Find the closest NO_MOVE, and if found sets that position to setMarkSpot.
		if(search_movesArray(NO_MOVE))
		{
			movesArray[search_movesArrayIndex(NO_MOVE)] = setMarkSpot;
		}
	}

	//part of post: rv == null <==> it is neither player's turn (i.e.
	// game is over)
	//part of post: ìnumber of Marks on board is evenîË rv == Mark.X
	//part of post: ìnumber of Marks on board is oddî Ë rv == Mark.O
	public Mark getTurn() {
		Mark newMark = Mark.valueOf("X");
		// if array is not full, game is not over, find the length of the list then find 
		// through remainder if turn is even(X) or odd for (O)
		if(search_movesArray(NO_MOVE) && !isGameOver())
		{
			int index = search_movesArrayIndex(NO_MOVE);
			if(index%2==0)
			{
				newMark = Mark.valueOf("X");
			}
			if(index%2==1)
			{
				newMark = Mark.valueOf("O");
			}
		}
		else
		{
			newMark = null;
		}
		return newMark;
	}

	//part of post: Left to student (see Tic-tac-toe rules in order
	// to fill this out)
	// check to see if array doesn't contain NO_MOVE 
	public boolean isGameOver() {
		boolean isGameOver = false;
		ArrayList<Integer> xMoves = findXs();
		ArrayList<Integer> oMoves = findOs();
		if(!search_movesArray(NO_MOVE))
		{
			isGameOver = true;
		}
		else {
			if(diagonalWinner(xMoves) || diagonalWinner(oMoves) || columnsWinner(xMoves) || columnsWinner(oMoves) ||  rowsWinner(xMoves) || rowsWinner(oMoves))
			{
				isGameOver = true;
			}
		}		
		return isGameOver;
	}

	//part of pre: isGameOver()
	//part of post: rv == null <==> neither player won (i.e. the game
	// ended in a tie)
	public Mark getWinner() {
		assert isGameOver() : "Game is not over";
		
		Mark newMark = Mark.valueOf("X");
		
		ArrayList<Integer> xMoves = findXs();
		ArrayList<Integer> oMoves = findOs();
		if(diagonalWinner(xMoves) || columnsWinner(xMoves) ||  rowsWinner(xMoves))
		{
			newMark = Mark.valueOf("X");
		}
		else if(diagonalWinner(oMoves) || columnsWinner(oMoves) ||  rowsWinner(oMoves))
		{
				newMark = Mark.valueOf("O");
		}
		else
		{
			newMark = null;
		}
		return newMark;
	}
	public String toString()
	{
		String board = "";
		for(int i=0; i<3;i++)
		{
			for(int j =0; j<3; j++)
			{
				if(getMark(i,j) == null)
				{
					board += "-";
				}
				else
				{
					board += getMark(i,j); 
				}
			}
			board += "\n";
		}
		return board;
	}

	//find all the X moves in movesArray
	private ArrayList<Integer> findXs()
	{
		ArrayList<Integer> xMovesInArray = new ArrayList<Integer>();
		for(int i = 0; i<movesArray.length;i++)
		{
			if(movesArray[i] == NO_MOVE)
			{
				break;
			}
			if(i%2==0)
			{
				xMovesInArray.add(movesArray[i]);
			}
		}
		return xMovesInArray;
	}
	//find all the O moves in movesArray
	private ArrayList<Integer> findOs()
	{
		ArrayList<Integer> oMovesInArray = new ArrayList<Integer>();
		for(int i = 0; i<movesArray.length;i++)
		{
			if(movesArray[i] == NO_MOVE)
			{
				break;
			}
			if(i%2==1)
			{
				oMovesInArray.add(movesArray[i]);
			}
 		}
		return oMovesInArray;
	}
	//post: returns True if the Move is located in the array
	private boolean search_movesArray(int move)
	{
		boolean contains = false;
		for(int i = 0; i<movesArray.length;i++)
		{
			if(movesArray[i] == move)
			{
				contains=  true;
				break;
			}
		}
		return contains;
	}
	
	//Finds the index inside of the array for the mark the user is looking for
	private int search_movesArrayIndex(int move)
	{
		int index = NO_MOVE;
		for(int i = 0; i<movesArray.length;i++)
		{
			if(movesArray[i] == move)
			{
				index =  i;
				break;
			}
		}
		return index;
	}
	private int findMarkLocation(int row,int column)
	{
		return (row*3) + column;
	}
	private boolean diagonalWinner(ArrayList<Integer> list)
	{
		boolean isWinner = false;
		if(list.contains(2) && list.contains(4) && list.contains(6))
		{
			isWinner= true;
		}
		if(list.contains(0) && list.contains(4) && list.contains(8))
		{
			isWinner= true;
		}
		return isWinner;
	}
	private boolean columnsWinner(ArrayList<Integer> list)
	{
		boolean isWinner = false;
		if(list.contains(0) && list.contains(3) && list.contains(6))
		{
			isWinner= true;
		}
		if(list.contains(1) && list.contains(4) && list.contains(7))
		{
			isWinner= true;
		}
		if(list.contains(2) && list.contains(5) && list.contains(8))
		{
			isWinner= true;
		}
		return isWinner;
	}
	private boolean rowsWinner(ArrayList<Integer> list)
	{
		boolean isWinner = false;
		if(list.contains(0) && list.contains(1) && list.contains(2))
		{
			isWinner= true;
		}
		if(list.contains(3) && list.contains(4) && list.contains(5))
		{
			isWinner= true;
		}
		if(list.contains(6) && list.contains(7) && list.contains(8))
		{
			isWinner= true;
		}
		return isWinner;
	}
}
