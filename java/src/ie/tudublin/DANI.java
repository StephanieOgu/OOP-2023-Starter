package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;

    public String[] writeSonnet() {
        return null;
    }

	public void setup() {
		colorMode(HSB);

		for (String string : loadFile()) {
			System.out.println(string);
		}
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
	}

	public String[] loadFile() {
		String[] lines = loadStrings("shakespere.txt"); // Load a text file into a String array
		for (String line : lines) {
			line.toLowerCase(); // Convert a string to lower case 
			String words[] = split(line, ' '); // Split a string into an array of words
			for (String word : words) {
				word = word.replaceAll("[^\\w\\s]",""); // Remove punction characters
			}
		}
		return lines;
	}
}
