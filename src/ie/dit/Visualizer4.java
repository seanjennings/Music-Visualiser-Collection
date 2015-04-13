<<<<<<< HEAD
package ie.dit;

import processing.core.PApplet;


public class Visualizer4 extends PApplet
{
	float tot;
	float bufferSize;
	float xValue;
	int weight;
	PApplet parent;

	Visualizer4(PApplet p)
	{
		parent = p;
		weight = 110;
	}
	
	void animation(float[] totalArrayLog, float transp) 
	{	
		parent.noStroke();
		
		parent.fill(0,0,255,255*(transp*3.0f));
		for (int i = 0; i < 18; i++)
		{
			int max = (int)totalArrayLog[i];
			for (int j = parent.height; j > (parent.height - max); j -= 30)
			{
				xValue = i * weight;
					parent.print(max + "\n");			
				if (j > parent.height / 1.5)
				{
					parent.fill(0, 255, 0);
				}
				
				
				else if (j > parent.height / 3)
				{
					parent.fill(255, 155, 0);
				}
				else
				{
					parent.fill(255, 0, 0);
				}
				parent.rect(xValue, j, 100, 20);
				
			}
		}
	}
	
}
=======
package ie.dit;

import processing.core.PApplet;


public class Visualizer4 extends PApplet
{
	float tot;
	float bufferSize;
	float xValue;
	int weight;
	PApplet parent;

	Visualizer4(PApplet p)
	{
		parent = p;
		weight = 110;
	}
	
	void animation(float[] totalArrayLog, float transp) 
	{	
		parent.noStroke();
		
		parent.fill(0,0,255,255*(transp*3.0f));
		for (int i = 0; i < 18; i++)
		{
			int max = (int)totalArrayLog[i];
			for (int j = parent.height; j > (parent.height - max); j -= 30)
			{
				xValue = i * weight;
					parent.print(max + "\n");			
				if (j > parent.height / 1.5)
				{
					parent.fill(0, 255, 0);
				}
				
				
				else if (j > parent.height / 3)
				{
					parent.fill(255, 155, 0);
				}
				else
				{
					parent.fill(255, 0, 0);
				}
				parent.rect(xValue, j, 100, 20);
				
			}
		}
	}
	
}
>>>>>>> a6e715d74bf94b52a171b39d7ec469dc2d5bffd0
