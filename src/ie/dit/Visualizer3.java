package ie.dit;

import ddf.minim.AudioInput;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;


public class Visualizer3 
{
	float tot;
	int bufferSize;
	PApplet parent;
	FFT fft;
	AudioInput in;
	int sampleRate;

	Visualizer3(PApplet p, AudioInput in, int sampleRate)
	{
		parent = p;
		this.in = in;
		this.sampleRate = sampleRate;
		
		fft = new FFT(parent.width, sampleRate);
	}
	
	void animation() 
	{	
		fft.window(FFT.HAMMING);
		fft.forward(in.left);
		parent.stroke(0, 255, 255);
		for (int i = 0 ; i < fft.specSize() ; i ++)
		{
			//parent.line(i, parent.height, i, parent.height - fft.getBand(i)*100);
			parent.noStroke();
			parent.fill(255,0,0);
			parent.ellipse(i,(parent.height-50) - fft.getBand(i)*100, 5, 5);
		}
	}
	
}