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
	  randomise(0.0f);
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
	
	void randomise(float tot)
	{
	  for (int row = 0 ; row < boardHeight ; row ++)
	  {
		parent.println("tot is " + tot);
	    for (int col = 0 ; col < boardWidth ; col ++)
	    {
	      float f = parent.random(0, 1000);
	      
	      if (tot > 15 && f > 995f)
	      {
	        board[row][col] = true;
	      }
	      
	      else if (tot > 20 && f > 994f)
	      {
	        board[row][col] = true;
	      }
	      	      
	      else if (tot > 30 && f > 993f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 60 && f > 0.992f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 90 && f > 0.991f)
	      {
	    	  board[row][col] = true;
	      }
	      
	      else if (tot > 120 && f > 0.990f)
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

	void animation(float tot)
	{
	  parent.background(0, 0, 0);
	  randomise(tot);
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
