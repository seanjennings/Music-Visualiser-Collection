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
		//int s = (int) speaker;
		
		parent.noFill();
		parent.stroke(255);
		parent.pushMatrix();
		parent.translate(parent.width/2,parent.height/2,0);
		parent.rotateX(x);
		parent.rotateY(y);
		parent.rotateZ(z);
		parent.sphereDetail((int)speaker*6);
		parent.sphere(100+speaker);
		x += (float) .01;
		y += (float) .01;
		z += (float) .01;
		parent.popMatrix();
	    
		parent.noFill();
		parent.stroke(255);
		parent.pushMatrix();
		int ran = (int) parent.random(0,5);
		parent.translate(parent.width/4,parent.height/2,0);
		parent.rotateX(x);
		parent.rotateY(y);
		parent.rotateZ(z);
		parent.sphereDetail((int)speaker*ran);
		parent.sphere(50+speaker);
	    x += (float) .01;
	    y += (float) .01;
	    z += (float) .01;
	    parent.popMatrix();
		
	    
	    parent.noFill();
	    parent.stroke(255);
	    parent.pushMatrix();
	    parent.translate((parent.width/2+ parent.width/4),parent.height/2,0);
	    parent.rotateX(x);
	    parent.rotateY(y);
	    parent.rotateZ(z);
	    parent.sphereDetail((int)speaker*ran);
	    parent.sphere(50+speaker);
	    x += (float) .01;
	    y += (float) .01;
	    z += (float) .01;
	    parent.popMatrix();
	}
	
}
