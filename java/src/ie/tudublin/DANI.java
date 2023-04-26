package ie.tudublin;

import java.util.ArrayList;

import ie.tudublin.d22126511.Word;
import processing.core.PApplet;

public class DANI extends PApplet {

	ArrayList <Word> loadedWords = new ArrayList<>();

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
		loadFile();

		// file()
		for (Word string : loadedWords) {
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

	//load shakespere.txt file
	public String[] loadFile() {
		Word currentWord;
		String[] lines = loadStrings("shakespere.txt"); // Load a text file into a String array
		for (String line : lines) {
			line = line.toLowerCase(); // Convert a string to lower case 
			String words[] = split(line, ' '); // Split a string into an array of words
			for(int i = 0; i<words.length; ++i){
				String thisWord = words[i].replaceAll("[^\\w\\s]",""); // Remove punction characters
				// String nextWord = words[i+1].replaceAll("[^\\w\\s]","");
				currentWord = this.findWord(thisWord);
				if(currentWord ==  null){
					currentWord = new Word(thisWord);
					loadedWords.add(currentWord);
				}
				// this.addFolow(nextWord, currentWord);
			}
		}
		return lines;
	}

	//check if word is already met
	public Word findWord(String str){
		if(!this.isWordsEmpty()){
			for (Word word : loadedWords) {
				if(word.getWord() == str){
					return word;
				}
			}
		}
		return null;
	}

	public void addFolow(String follow, Word word){
		
	}

	//checks if words arraylist is empty
	public boolean isWordsEmpty(){
		return loadedWords.size()== 0;
	}
}
