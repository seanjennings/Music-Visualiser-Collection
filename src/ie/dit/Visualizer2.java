package ie.dit;

import java.util.ArrayList;

import processing.core.PApplet;


public class Visualizer2 extends PApplet
{
	float tot;
	int bufferSize;
	PApplet parent;
	ArrayList<Ball> balls = new ArrayList<Ball>();
	int ballWidth = 200;

	Visualizer2(PApplet p)
	{
		parent = p;
		
		balls.add(new Ball(parent.width/2, 0 - ballWidth*2, ballWidth, color(random(0, 255), random(0, 255), random(0, 255))));
	}
	
	class Ball
	{
		float x;
		float y;
		int col;
		float speed;
		float gravity;
		float opacity;
		float w;
		
		Ball(float tempX, float tempY, float tempW, int tempmyColor1)
		{
			x = tempX;
		    y = tempY;
		    w = tempW;
		 
		    col=tempmyColor1;
		 
		    speed = 0;
		    gravity = 0.1f;
		    opacity=255;   // opacity
		}
		 
		void move()
		{
			// Add gravity to speed
		    speed = speed + gravity;
		    // Add speed to y location
		    y = y + speed;
		    // If reaches the bottom
		    // Reverse speed
		    if (y >= parent.height-19)
		    {
		    	// Dampening
		    	speed = speed * -0.8f;
		    	y = parent.height-19;
		    }
		}
		 
		boolean finishedByOpacity() 
		{    
			if (opacity<=0) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		 
		void display(float tot)
		{
			// Display the circle
			parent.fill(col, opacity);
			parent.ellipse(x, y, w*(tot/50), w*(tot/50));
		}
		 
		void diminish()
		{
			opacity = opacity-0.5f;
		}
	}
	
	void animation(float tot) 
	{	
		// delete
		parent.background(0);
		
		if (random (0, 1000)>940)
		{
			// A new ball object is added to the ArrayList
			balls.add(new Ball(random(parent.width), 0 - ballWidth*2, ballWidth, color (random(0, 255), random(0, 255), random(0, 255))));
		}
		  
		for (int i = balls.size()-1; i >= 0; i--)
		{
			Ball ball = (Ball) balls.get(i);
			 
			ball.move();
			ball.diminish();   
			ball.display(tot);	//Use noise level passed in from main draw method to modify ball objects
			
			if (ball.finishedByOpacity () ) 
			{
				balls.remove(i);
			}
		}
	}
}

