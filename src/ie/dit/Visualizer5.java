package ie.dit;

import processing.core.PApplet;


public class Visualizer5 {
	/* if the cell is alive
	   if < 2 neighbours, the cell dies
	   if 2 or 3 neighbours it survives
	   if > 3 neighbours, it dies due to overcrowding
	 if the cell is dead
	   if it has exactly 3 neighbours it comes back to life

	*/
	PApplet parent;

	Visualizer5(PApplet p)
	{
		parent = p;
	  boardWidth = parent.width / cellWidth;
	  boardHeight = parent.height / cellHeight;
	  boardCentX = boardWidth / 2;
	  boardCentY = boardHeight / 2;
	  board = new boolean[boardHeight][boardWidth];
	  updateBoard = new boolean[boardHeight][boardWidth];
	  //randomise();
	  pause = false;
	 
	  board[88][57] = true;
	  board[88][58] = true;
	  board[89][56] = true;
	  board[89][57] = true;
	  board[90][57] = true; 
	}

	boolean[][] board;
	boolean[][] updateBoard;
	boolean pause;
	boolean done;
	int cellWidth = 5;
	int cellHeight = 5;
	int boardCentX;
	int boardCentY;
	int boardWidth;
	int boardHeight;
	int fps = 20;
	//color c = color(0, 0, 0);

	int countNeighbours(int row, int col)
	{
	  int count = 0;
	  
	  // Put your code in here...
	  // Top left
	  if ((row > 0) && (col > 0) && (board[row - 1][col -1]))
	  {
	    count ++;
	  }
	  // Top
	  if ((row > 0) && board[row -1][col])
	  {
	    count ++;
	  }
	  // Top right
	  if ((row > 0) && (col < (boardWidth - 1)) && (board[row -1][col + 1]))
	  {
	    count ++;
	  }
	  // Left
	  if ((col > 0) && (board[row][col -1]))
	  {
	    count ++;
	  }
	  // Right
	  if ((col < (boardWidth -1)) && board[row][col + 1])
	  {
	    count ++;
	  }
	  // Bottom left
	  if ((col > 0) && (row < (boardHeight - 1)) 
	    && board[row + 1][col - 1])
	  {
	    count ++;
	  }
	  // Bottom
	  if ((row < (boardHeight -1)) && (board[row + 1][col]))
	  {
	    count ++;
	  }
	  // Bottom right
	  if ((col < (boardWidth - 1)) && (row < (boardHeight - 1)) 
	    && board[row + 1][col + 1])
	  {
	    count ++;
	  }
	  return count;
	}
	
	void randomise()
	{
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      float f = parent.random(0, 1);
	      if (f > 0.5f)
	      {
	        board[row][col] = true;
	      }
	    }
	  }
	}

	void update()
	{
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      int count = countNeighbours(row, col);
	      if (board[row][col])
	      {
	        if (count<2)
	        {
	          updateBoard[row][col] = false;
	        } 
	        else if ((count == 2) || (count == 3))
	        {
	          updateBoard[row][col] = true;
	        }
	        else if (count > 3)
	        {
	          updateBoard[row][col] = false;
	        }
	      }
	      else
	      {
	        if (count == 3)
	        {
	          updateBoard[row][col] = true;
	        }
	        
	      }
	    }
	  }
	  
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      board[row][col] = updateBoard[row][col];
	    }
	  }
	}

	void animation()
	{
	  parent.background(0, 0, 0);
	  
	  if (pause == false)
	  {
	  
	  parent.frameRate(fps);
	  
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      int x = cellWidth * col;
	      int y = cellHeight * row;
	      if (board[row][col])
	       {
	         parent.fill(0, 255, 0);
	         parent.rect(x, y, cellWidth, cellHeight);
	       } 
	       else
	       {
	         parent.fill(0);         
	       }
	    }
	  }
	  
	  update();
	  }
	  
	  else
	  {
	    parent.println("Paused!");
	    
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      int x = cellWidth * col;
	      int y = cellHeight * row;
	      if (board[row][col])
	       {
	         parent.fill(0, 255, 0);
	         parent.rect(x, y, cellWidth, cellHeight);
	       } 
	       else
	       {
	    	   parent.fill(0);         
	       }
	    }
	  }
	  }
	}

	void keyPressed()
	{
	  if (parent.key == '+')
	  {
	    fps++;
	  }
	  
	  else if ((parent.key == '-') && (fps > 0))
	  {
	    fps--;
	  }
	  
	  else if (parent.key == ' ')
	  {   
	    if (pause == false)
	    {
	      pause = true;
	    }
	    
	    else
	    {
	      pause = false;
	    }
	  }
	  
	  else if (parent.key == 'c')
	  {
	    clrscr();
	  }
	  
	  else if (parent.key == 'r')
	  {
	    randomise();
	  }
	  
	  else if (parent.key == '0')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row + 1][col] = true;
	           board[row + 1][col + 1] = true;
	           done = true;
	         }
	      }
	    }
	  }

	  else if (parent.key == '1')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row + 1][col - 1] = true;
	           board[row + 1][col + 2] = true;
	           board[row + 2][col] = true;
	           board[row + 2][col + 1] = true;
	           done = true;
	         }
	      }
	    }
	  }

	  else if (parent.key == '2')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row + 1][col - 1] = true;
	           board[row + 1][col + 2] = true;
	           board[row + 2][col] = true;
	           board[row + 2][col + 2] = true;
	           board[row + 3][col + 1] = true;
	           done = true;
	         }
	      }
	    }
	  }

	  else if (parent.key == '3')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row + 1][col] = true;
	           board[row + 1][col + 2] = true;
	           board[row + 2][col + 1] = true;
	           done = true;
	         }
	      }
	    }
	  }

	  else if (parent.key == '4')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row][col + 2] = true;
	           done = true;
	         }
	      }
	    }
	  } 

	  else if (parent.key == '5')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row][col + 2] = true;
	           board[row + 1][col - 1] = true;
	           board[row + 1][col] = true;
	           board[row + 1][col + 1] = true;
	           done = true;
	         }
	      }
	    }
	  }   
	  
	  else if (parent.key == '6')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row + 1][col] = true;
	           board[row + 1][col + 1] = true;
	           board[row + 2][col + 2] = true;
	           board[row + 2][col + 3] = true;
	           board[row + 3][col + 2] = true;
	           board[row + 3][col + 3] = true;
	           done = true;
	         }
	      }
	    }
	  }  
	  
	   else if (parent.key == '7')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row][col - 1] = true;
	           board[row][col - 2] = true;
	           board[row - 1][col - 4] = true;
	           board[row - 2][col - 4] = true;
	           board[row - 3][col - 4] = true;
	           board[row - 1][col + 1] = true;
	           board[row - 2][col + 1] = true;
	           board[row - 3][col + 1] = true;
	           board[row - 5][col] = true;
	           board[row - 5][col - 1] = true;
	           board[row - 5][col - 2] = true;
	           
	           board[row][col + 6] = true;
	           board[row][col + 5] = true;
	           board[row][col + 4] = true;
	           board[row - 1][col + 3] = true;
	           board[row - 2][col + 3] = true;
	           board[row - 3][col + 3] = true;
	           board[row - 1][col + 8] = true;
	           board[row - 2][col + 8] = true;
	           board[row - 3][col + 8] = true;
	           board[row - 5][col + 6] = true;
	           board[row - 5][col + 5] = true;
	           board[row - 5][col + 4] = true;
	           
	           board[row + 2][col] = true;
	           board[row + 2][col - 1] = true;
	           board[row + 2][col - 2] = true;
	           board[row + 3][col - 4] = true;
	           board[row + 4][col - 4] = true;
	           board[row + 5][col - 4] = true;
	           board[row + 3][col + 1] = true;
	           board[row + 4][col + 1] = true;
	           board[row + 5][col + 1] = true;
	           board[row + 7][col] = true;
	           board[row + 7][col - 1] = true;
	           board[row + 7][col - 2] = true;
	           
	           board[row + 2][col + 6] = true;
	           board[row + 2][col + 5] = true;
	           board[row + 2][col + 4] = true;
	           board[row + 3][col + 3] = true;
	           board[row + 4][col + 3] = true;
	           board[row + 5][col + 3] = true;
	           board[row + 3][col + 8] = true;
	           board[row + 4][col + 8] = true;
	           board[row + 5][col + 8] = true;
	           board[row + 7][col + 6] = true;
	           board[row + 7][col + 5] = true;
	           board[row + 7][col + 4] = true;
	           
	           done = true;
	         }
	      }
	    }
	  }

	  else if (parent.key == '8')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row][col] = true;
	           board[row + 1][col + 1] = true;
	           board[row + 1][col + 2] = true;
	           board[row + 2][col] = true;
	           board[row + 2][col + 1] = true;
	           
	           done = true;
	         }
	      }
	    }
	  } 

	  else if (parent.key == '9')
	  {
	    done = false;
	    for (int row = 0 ; row < boardHeight ; row ++)
	    {
	      for (int col = 0 ; col < boardWidth ; col ++)
	      {
	        if ((boardCentX > col) && (boardCentX < (col + cellWidth)) && (boardCentY > row) && (boardCentY < row + (cellHeight)) && (done == false))
	         {
	           board[row -1][col + 1] = true;
	           board[row - 1][col + 2] = true;
	           board[row][col] = true;
	           board[row][col + 1] = true;
	           board[row][col + 2] = true;
	           board[row][col + 3] = true;
	           board[row + 1][col] = true;
	           board[row + 1][col + 1] = true;
	           board[row + 1][col + 3] = true;
	           board[row + 1][col + 4] = true;
	           board[row + 2][col + 2] = true;
	           board[row + 2][col + 3] = true;
	           done = true;
	         }
	      }
	    }
	  }    
	}

	void mouseDragged()
	{
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      int x = cellWidth * col;
	      int y = cellHeight * row;
	      if ((parent.mouseX > x) && (parent.mouseX < (x + cellWidth)) && (parent.mouseY > y) && (parent.mouseY < (y + cellHeight)))
	      {
	        board[row][col] = true;
	      }
	    }
	  }
	}

	void clrscr()
	{
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      board[row][col] = false;
	      updateBoard[row][col] = false;
	    }
	  }
	}

}
