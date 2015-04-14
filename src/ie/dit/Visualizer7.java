package ie.dit;

import processing.core.PApplet;
import processing.core.PConstants;


public class Visualizer7 
{
	float speaker;
	PApplet parent;
	
	Visualizer7(PApplet p)
	{
		parent = p;
	}


	void animation(float speaker) 
	{	
		parent.fill(0,255,0,100);
		parent.pushMatrix();
		float sec = 360 / 60 * PApplet.second();
	    parent.translate(parent.width/2,parent.height/2);
	    parent.rotate(PApplet.radians(sec));
	    parent.arc(0,0,400, 400, PConstants.PI-PConstants.QUARTER_PI-speaker,PConstants.PI+speaker);
	    parent.arc(0,0,400, 400,-PConstants.QUARTER_PI-speaker,0+speaker);
	    parent. popMatrix();
		
	    parent.fill(255,0,0,100);
	    parent.pushMatrix();
	    parent.translate(parent.width/2,parent.height/2);
	    parent.rotate(PApplet.radians(-sec));
	    parent.arc(0,0,400, 400, PConstants.PI-speaker, PConstants.PI+PConstants.QUARTER_PI+speaker);
	    parent.arc(0,0,400, 400, 0-speaker, PConstants.QUARTER_PI+speaker);
	    parent.popMatrix();
	    
	    parent.pushMatrix();
	    parent.translate(parent.width/2,parent.height/2);
	    parent.rotate(PApplet.radians(sec));
	    parent.arc(0,0,200, 200, PConstants.PI-PConstants.QUARTER_PI,PConstants.PI);
	    parent.arc(0,0,200, 200,-PConstants.QUARTER_PI,0);
	    parent.popMatrix();
		
	    parent.fill(0,255,0,100);
	    parent.pushMatrix();
	    parent.translate(parent.width/2,parent.height/2);
	    parent.rotate(PApplet.radians(-sec));
	    parent.arc(0,0,200, 200, PConstants.PI, (PConstants.PI+PConstants.QUARTER_PI));
	    parent.arc(0,0,200, 200, 0, (PConstants.QUARTER_PI));
	    parent.popMatrix();
		
	}
	
}
