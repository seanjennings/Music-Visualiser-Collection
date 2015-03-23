package ie.dit;

import processing.core.PApplet;


public class Visualizer1 
{
	float tot;
	int bufferSize;
	PApplet parent;

	Visualizer1(PApplet p)
	{
		parent = p;
	}
	
	void animation(float tot, float transp) 
	{	
		
		parent.noStroke();
		
		for(int i=1 ; i<4 ; i++) 
		{
			int size = (i == 2) ? 200 : 100 ;
			parent.fill(0,0,255,255*(transp*3.0f));
			parent.ellipse(parent.width*i/4,parent.height/2,size+tot,size+tot);
			parent.fill(0);
			parent.ellipse(parent.width*i/4,parent.height/2,(size+tot)/2,(size+tot)/2);
		}
		
		
	}
	
}

