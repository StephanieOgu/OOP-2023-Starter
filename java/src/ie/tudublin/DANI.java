package ie.tudublin;

import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import ie.tudublin.d22126511.Follow;
import ie.tudublin.d22126511.Word;
import processing.core.PApplet;

public class DANI extends PApplet {

	ArrayList <Word> loadedWords = new ArrayList<>();
	int sonneteLen = 14;
    String[] sonnet = new String[sonneteLen];

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    public String[] writeSonnet() {
		String[] sonnet = new String[sonneteLen];;
		for(int i = 0; i< this.sonneteLen; i++){
			sonnet[i] = writeSonneteString();
		}

        return sonnet;
    }

	public String writeSonneteString(){
		Word currentWord = loadedWords.get(getRandomVal(loadedWords.size()-1, 0));
		String sonnetRow = currentWord.getWord() + " ";

		int i = 0;
		while(i<8){
			if(currentWord.getFollowsSize()== 0){
				break;
			}
			Follow randomFollow = currentWord.getFollows().get(getRandomVal(0, currentWord.getFollowsSize()-1));
			currentWord = this.findWord(randomFollow.getWord());
			sonnetRow += currentWord.getWord() + " ";
			i++;
		}
		//System.out.println(sonnetRow);
		return sonnetRow;
	}

	public void printSonneteInConsole(){
		System.out.println("\n\n\n----HERE IS OUR SONNET\n\n\n");
		for(int i = 0; i< this.sonneteLen; i++){
			System.out.println(sonnet[i]);
		}
	}

	private int getRandomVal(int min, int max){
		return new Random().nextInt(max - min + 1 + min);
	}

	public void setup() {
		colorMode(HSB);
		loadFile();
		printModel();
		this.sonnet = writeSonnet();
		printSonneteInConsole();
	}

	public void printModel(){
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
				currentWord = this.findWord(thisWord);
				if(currentWord == null){
					currentWord = new Word(thisWord);
					loadedWords.add(currentWord);
				}
				if(i+1 < words.length){
					String nextWord = words[i+1].replaceAll("[^\\w\\s]","");
					this.addFolow(nextWord, currentWord);
				}
			}
		}
		return lines;
	}

	//check if word is already met in a loadedWords
	public Word findWord(String str){
		if(!this.isWordsEmpty()){
			for (Word word : loadedWords) {
				if(Objects.equals(word.getWord(), str)){
					return word;
				}
			}
		}
		return null;
	}

	public void addFolow(String follow, Word word){
		int index = findFollowIndex(word, follow);
		if(index == -1){
			word.addFollow(follow);
		} else {
			word.changeFellowByIndex(X);
		}
	}

	//check if word is already met in a folllows
	public int findFollowIndex(Word word, String mightFollow){

		for (int i = 0; i<word.getFollows().size(); ++i){
			if(word.getFollows().get(i).getWord() == mightFollow){
				return i; 
			}
		}
		return -1;
	}

	//checks if words arraylist is empty
	public boolean isWordsEmpty(){
		return loadedWords.size()== 0;
	}
}
