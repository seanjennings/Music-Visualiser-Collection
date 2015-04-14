package ie.dit;

import processing.core.PApplet;



public class Visualizer8 
{
	float speaker;
	PApplet parent;
	float x,y,z;

	Visualizer8(PApplet p)
	{
		parent = p;
	}
	
	void animation(float speaker, float x, float y, float z) 
	{	
		int s = (int) speaker;
		
		parent.fill(0);
		parent.stroke(255);
		parent.pushMatrix();
		parent.translate(parent.width/2,parent.height/2,0);
		parent.rotateX(x);
	    parent.rotateY(y);
	    parent.rotateZ(z);
	    //parent.curveDetail((int) speaker);
	    parent.sphere(70+speaker);
	    x += (float) .01;
	    y += (float) .01;
	    z += (float) .01;
	    parent.popMatrix();
	}
	
}