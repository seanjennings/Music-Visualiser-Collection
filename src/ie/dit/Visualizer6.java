package ie.dit;

import processing.core.PApplet;


public class Visualizer6 
{
	float speaker;
	PApplet parent;

	Visualizer6(PApplet p)
	{
		parent = p;
	}
	
	void animation(float speaker) 
	{	
		
		parent.stroke(0);
		parent.fill(255);
		
		parent.ellipse(parent.width/2,parent.height/2,400,400);
		
		parent.ellipse(parent.width/4,parent.height/4,200,200);
		
		parent.ellipse((parent.width/2 + parent.width/4),parent.height/4,200,200);
		
		parent.fill(0);
		
		parent.triangle(parent.width/2, (parent.height/2)-20, (parent.width/2)-20, (parent.height/2)+20, (parent.width/2)+20, (parent.height/2)+20);
		
		parent.ellipse((parent.width/2)+80,parent.height/3,100,100);
		parent.ellipse((parent.width/2)-80,parent.height/3,100,100);
		
		parent.ellipse((parent.width/4)+40,parent.height/5,50,50);
		parent.ellipse((parent.width/4)-40,parent.height/5,50,50);
		
		parent.ellipse((parent.width/2 + parent.width/4)+40,parent.height/5,50,50);
		parent.ellipse((parent.width/2 + parent.width/4)-40,parent.height/5,50,50);
		
		
		parent.fill(255);
		parent.ellipse((parent.width/2)+80,parent.height/3,75,75);
		parent.ellipse((parent.width/2)-80,parent.height/3,75,75);
		
		parent.ellipse((parent.width/4)+40,parent.height/5,35,35);
		parent.ellipse((parent.width/4)-40,parent.height/5,35,35);
		
		parent.ellipse((parent.width/2 + parent.width/4)+40,parent.height/5,35,35);
		parent.ellipse((parent.width/2 + parent.width/4)-40,parent.height/5,35,35);
		
		parent.fill(0);
		
		parent.ellipse((parent.width/2)+80,parent.height/3,35,35);
		parent.ellipse((parent.width/2)-80,parent.height/3,35,35);
		
		parent.ellipse((parent.width/4)+40,parent.height/5,20,20);
		parent.ellipse((parent.width/4)-40,parent.height/5,20,20);
		
		parent.ellipse((parent.width/2 + parent.width/4)+40,parent.height/5,20,20);
		parent.ellipse((parent.width/2 + parent.width/4)-40,parent.height/5,20,20);
		
		
		parent.ellipse(parent.width/2,(parent.height/2)+100, 100 ,  speaker);
		
		parent.ellipse(parent.width/4,(parent.height/4)+50, 50, speaker);
		
		parent.ellipse((parent.width/2 + parent.width/4),parent.height/4+50, 50, speaker);
		
	}
	
}
