package ie.dit;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
//ctrl shift o

public class Main extends PApplet {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "Bryan";
		
		System.out.println("Hello " + name);
		String name1 = "Bryan1";
		System.out.println("Hello " + name1);
	}*/
	
	Minim minim;
	AudioInput in;
	Visualizer1 visualizer1 = new Visualizer1(this);
	float min,max,avg,tot;
	int sampleRate = 44100;
	float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f, 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    String[] spellings = {"D,", "E,", "F,", "G,", "A,", "B,", "C", "D", "E", "F", "G", "A", "B","c", "d", "e", "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "d''"};
	
	public void setup() {
		size(2048,500);
		
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO, width, sampleRate, 16);
		
		min = Float.MIN_VALUE;
		max = Float.MAX_VALUE;
		
		
	}
	
	public void draw() {
		background(0);
		stroke(255);
		tot = 100;
		
		for(int i=0;i<in.bufferSize();i++) {
			float sample = in.left.get(i);
			sample *= 600;
			line(i,(height/2),i, (height/2)+sample);
			tot += abs(in.left.get(i));
		}
		
		visualizer1.animation(in.bufferSize(), tot);
		
		fill(255);
		
		int zeroCrossings = countZeroCrossings();
		
		if(zeroCrossings < 200)
		{
			text("Zero Crossings: "+zeroCrossings, 20, 20);
			float freq = (float) (zeroCrossings * (1 / 0.023)) / 2;
			text("Frequency: "+freq, 20, 40);
			text("Note: "+spell(freq), 20, 60);
		}
		//print(frequencies.length + "\n");
		//print(spellings.length + "\n");
	}
	
	public int countZeroCrossings() {
		int count = 0;
		float prev = 0;
		
		for(int i=0;i<in.bufferSize();i++) {
			
			if((prev > 0 && in.left.get(i) < 0) || (prev < 0 && in.left.get(i) > 0)) {
				count++;
			}
			prev = in.left.get(i);
		}
		return count/2;
	}
	
	public String spell(float frequency) {
		float min_dist = Float.MAX_VALUE;
		int min_dist_index = 0;
		
		for(int i=0 ; i < frequencies.length ; i++) {
			float temp = abs(frequency - frequencies[i]);
			if(temp < min_dist) {
				min_dist = temp;
				min_dist_index = i;
			}
		}
		return spellings[min_dist_index];
	}
	
	public static void main(String args[][]) {
		PApplet.main(new String[] {"--present", "ie.dit.Main"});
	}

}