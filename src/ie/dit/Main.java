package ie.dit;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;

//ctrl shift o

public class Main extends PApplet {
	
	Minim minim;
	Oscil wave;
	AudioInput in;
	AudioOutput out;
	Visualizer1 visualizer1;
	Visualizer2 visualizer2;
	Visualizer3 visualizer3;
	Visualizer4 visualizer4;
	Visualizer5 visualizer5;
	AudioPlayer song;
	int spazio = 50;
	int value;
	int counter = 0;
	int counter2 = 0;
	float min,max,avg,tot;
	int sampleRate = 44100;
	float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f, 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    String[] spellings = {"D,", "E,", "F,", "G,", "A,", "B,", "C", "D", "E", "F", "G", "A", "B","c", "d", "e", "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "d''"};
    int currentVisualiser = 0;
    int line1;
    FFT fft;
    float[] totalArrayLog;
	
	public void setup() {
		size(2048, 500);
		
		minim = new Minim(this);
		in = minim.getLineIn(Minim.MONO, width, sampleRate, 16);
		
		// use the getLineOut method of the Minim object to get an AudioOutput object
		out = minim.getLineOut();
		  
		fft = new FFT(width, sampleRate);
		
		line1 = (height / 2);
		min = Float.MIN_VALUE;
		max = Float.MAX_VALUE;
		
		totalArrayLog = new float[18];
		for (int i = 0; i < 18; i++)
		{
			totalArrayLog[i] = 0;
		}
		
		visualizer1 = new Visualizer1(this);
		visualizer2 = new Visualizer2(this);
		visualizer3 = new Visualizer3(this, in, sampleRate);
		visualizer4 = new Visualizer4(this);
		visualizer5 = new Visualizer5(this);
	}
	
	public void draw() {
		background(0);
		stroke(255);
		tot = 100;
		max = 0;
		
		for(int i=0;i<in.bufferSize();i++) 
		{
			float sample = in.left.get(i);
			//print("Sample before " + sample + "\n");
			sample *= 600;
			//print("Sample after " + sample + "\n");
			//line(i,(height/2),i, (height/2)+sample);
			tot += abs(in.left.get(i));
			
			if (i == 0 || sample > max)
			{
				max = sample;
			}
		}
		float average = (tot / in.bufferSize()) * 600;
		totalArrayLog[counter] = average;
		
		if (counter == 17)
		{
			counter = 0;
		}
		else
		{
			counter++;
		}
		
		tot = tot / in.bufferSize();
		tot-=0.02;
		
		//print("BufferSize is " + in.bufferSize() + "tot is " + tot + " ");
		
		float transp = tot;
		//print(transp+"\n");
		tot = tot * 300;
		
		smooth();
		noStroke();
		
		//print("Current Visualiser: "+currentVisualiser+"\n");
		switch(currentVisualiser)
		{
			case 0:
				stroke(0, 255, 0);
				for (int i = 0; i < in.bufferSize() - 1; i++)
				{
				    line( i, line1 + in.left.get(i)*50, i+1, line1 + in.left.get(i+1)*50 );
				}
				break;
				
			case 1:
				visualizer1.animation(tot,transp);
				break;
				
			case 2:
				visualizer2.animation(tot);
				break;
				
			case 3:
				visualizer3.animation();
				break;
				
			case 4:
				visualizer4.animation(totalArrayLog, transp);
				break;
				
			case 5:
				visualizer5.animation(totalArrayLog, false);
				counter2++;
				if (counter2 == 1000)
				{
					visualizer5.animation(totalArrayLog, true);
					counter2 = 0;
				}
				break;
			default: 
				
			break;
		}
		
		/*
		visualizer1.animation(tot,transp);
		smooth();
		noStroke();
		visualizer2.animation(tot);
		*/
		
		//fill(255);
		
		int zeroCrossings = countZeroCrossings();
		/*
		if(zeroCrossings < 200)
		{
			text("Zero Crossings: "+zeroCrossings, 20, 20);
			float freq = (float) (zeroCrossings * (1 / 0.023)) / 2;
			text("Frequency: "+freq, 20, 40);
			text("Note: "+spell(freq), 20, 60);
		}
		*/
		//print(frequencies.length + "\n");
		//print(spellings.length + "\n");
	}
	
	public void keyPressed() {
		switch(key)
		{
			case '0':
				currentVisualiser = 0;
				break;
				
			case '1':
				currentVisualiser = 1;
				break;
				
			case '2':
				currentVisualiser = 2;
				break;
				
			case '3':
				currentVisualiser = 3;
				break;
				
			case '4':
				currentVisualiser = 4;
				break;
				
			case '5':
				currentVisualiser = 5;
				visualizer5 = new Visualizer5(this);
				break;
				
			case ' ':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    break;
			    
			case 'a':
				song = minim.loadFile("Do1.mp3");
				song.play();
				value = 0;
				break;
				
			case 'w':
				song = minim.loadFile("Do#1.mp3");
				song.play();
				break;
				
			case 's':
				song = minim.loadFile("Re1.mp3");
				song.play();
				value = 1;
				break;
				
			case 'e':
				song = minim.loadFile("Re#1.mp3");
				song.play();
				break;
				
			case 'd':
				song = minim.loadFile("Mi1.mp3");
				song.play();
				value = 2;
				break;
			
			case 'f':
				song = minim.loadFile("Fa1.mp3");
				song.play();
				value = 3;
				break;
				
			case 't':
				song = minim.loadFile("Fa#1.mp3");
				song.play();
				break;
				
			case 'g':
				song = minim.loadFile("Sol1.mp3");
				song.play();
			    value = 4;
				break;
				
			case 'y':
				song = minim.loadFile("Sol#1.mp3");
				song.play();
				break;
				
			case 'h':
				song = minim.loadFile("La1.mp3");
				song.play();
				value = 5;
				break;
				
			case 'u':
				song = minim.loadFile("La#1.mp3");
				song.play();
				break;
				
			case 'j':
				song = minim.loadFile("Si1.mp3");
				song.play();
			    value = 6;
				break;
				
			case 'k':
				song = minim.loadFile("Do2.mp3");
				song.play();
				value = 7;
				break;
				
			case 'l':
				
				break;
			    
			case 'z':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 440, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			 
			    break;
			case 'x':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 880, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case 'c':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 1320, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case 'v':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 1860, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case 'b':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 2300, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case 'n':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 2740, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case 'm':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 3180, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			    
			case ',':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 3620, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;
			
			case '.':
			    if (wave!=null&&out!=null)
			      wave.unpatch(out);
			    //wave.unpatch(out);
			    // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
			    wave = new Oscil( 4060, 0.1f, Waves.SINE );
			    // patch the Oscil to the output
			    wave.patch( out );
			    break;    
		}
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
