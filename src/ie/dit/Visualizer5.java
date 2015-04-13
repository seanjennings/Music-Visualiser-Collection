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
	  pause = false;
	 
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
	
	void refresh(float[] totArrayLog)
	{
	  /*for (int row = 0 ; row < boardHeight ; row ++)
	  {
		parent.println("tot is " + tot);
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      float f = parent.random(0, 1000);
	      
	      if (tot < 15 && f > 994f)
	      {
	        board[row][col] = false;
	      }
	      
	      else if (tot > 15 && f > 999f)
	      {
	        board[row][col] = true;
	      }
	      
	      else if (tot > 20 && f > 996.7f)
	      {
	        board[row][col] = true;
	      }
	      	      
	      else if (tot > 30 && f > 996.3f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 60 && f > 994f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 90 && f > 992f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 120 && f > 990f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 140 && f > 500)
	      {
	    	  board[row][col] = true;
	      
	      }
	    }
	  }*/
	int counter = 0;
	for (int row = 0 ; row < boardHeight ; row ++)
	{
		for (int col = 0 ; col < boardWidth ; col += 30)
		{
			if (counter > 17)
			{
				counter = 0;
			}
			
			float f = parent.random(0, 1);
			
			if ((row * cellHeight) < totArrayLog[counter] && f > 0.96)
			{
				board[row][col] = true;
				
				if (col > 10 && col < boardWidth - 10)
				{
					board[row][col + 4] = true;
					board[row][col + 8] = true;
				}
			}
			
			
			counter++;
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

	void animation(float[] totalArrayLog, boolean doClearScrn)
	{
	  parent.background(0, 0, 0);
	  refresh(totalArrayLog);
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
	  
	  if (doClearScrn)
	  {
		  clrscr();
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
