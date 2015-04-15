package ie.dit;

import java.util.ArrayList;
import processing.core.PConstants;
import processing.core.PApplet;


public class Visualizer9 
{
	float vol;
	float x,y,z;
	int rainWidth = 200;
	PApplet parent;
	ArrayList<Rain> rains = new ArrayList<Rain>();

	Visualizer9(PApplet p)
	{
		parent = p;
		rains.add(new Rain(parent.random(parent.width/2,parent.width), 0 - rainWidth*2));
	}
	
	
	class Rain
	{
		float x1;
		float y1;
		float speed;
		float gravity;
		float opacity;
		float counter;
		
		
		Rain(float tempX, float tempY)
		{
			x1 = tempX;
		    y1 = tempY;
		    opacity=255;
		 
		    speed = 0;
		    gravity = 0.05f;
		}
		 
		void move()
		{
			// Add gravity to speed
		    speed = speed + gravity;
		    // Add speed to y location
		    y1 = y1 + speed;
		    
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
		
		 
		void display(float vol)
		{
			parent.fill(123);
			

			parent.stroke(255);
			parent.pushMatrix();
			parent.translate(x1,y1,0);
			parent.rotateX(x);
			parent.rotateY(y);
		    parent.rotateZ(z);
		    parent.sphereDetail((int)vol/5);
		    parent.sphere(20);
		    y += (float) .001;
		    parent.popMatrix();
		}
		
		void diminish()
		{
			opacity = opacity-0.5f;
		}
	
	}
	void animation(float vol, float x, float y, float z) 
	{	
		
		

		
		if (parent.random (0, 1000)>940)
		{
			rains.add(new Rain(parent.random(0,parent.width), 0 - rainWidth*2));
		}
		
		for (int i = rains.size()-1; i >= 0; i--)
		{
			Rain rain = (Rain) rains.get(i);
			 
			rain.move();  
			rain.display(vol);
			
			if (rain.finishedByOpacity () ) 
			{
				rains.remove(i);
			}
		}
		
		parent.sphereDetail(30);
		
		parent.pushMatrix();
		parent.noFill();
		parent.noStroke();
		parent.translate(parent.width/2,parent.height/2,0);
		parent.rotateX(x);
		parent.sphere(200);
		parent.strokeWeight(10);
		parent.strokeCap(PConstants.ROUND);
		parent.stroke(255);
		parent.lights();
		parent.ellipse(0,0,300,300);
	    parent. rotateY(y);
	    parent.lights();
	    parent.ellipse(0,0,300,300);
	    parent.strokeWeight(1);
	    parent.noStroke();
	    parent.fill(0,255,0);
	    parent.lights();
	    parent.sphere(100);
	    parent.popMatrix();
	    
	    parent.pushMatrix();
	    parent.noFill();
	    parent.noStroke();
	    parent.translate(parent.width/4,parent.height/2,0);
	    parent.rotateX(x);
	    parent.sphere(200);
	    parent.strokeWeight(10+(vol/5));
	    parent.stroke(255,223,0);
	    parent.lights();
	    parent.ellipse(0,0,150+(vol/2),150+(vol/2));
	    parent.rotateX(y);
	    parent.strokeWeight(1);
	    parent.noStroke();
	    parent.fill(255,0,0);
	    parent.lights();
	    parent.sphere(50);
	    parent.popMatrix();
	    
	    parent.pushMatrix();
	    parent.noFill();
	    parent.noStroke();
	    parent.translate((parent.width/2+parent.width/4),parent.height/2,0);
	    parent.rotateX(x);
	    parent.sphere(200);
	    parent.strokeWeight(10+(vol/5));
	    parent.stroke(255,223,0);
	    parent.lights();
	    parent.ellipse(0,0,150+(vol/2),150+(vol/2));
	    parent.strokeWeight(1);
	    parent.noStroke();
	    parent.fill(0,0,255);
	    parent.lights();
	    parent.sphere(50);
	    parent.popMatrix();
		
		
	}
	
}
