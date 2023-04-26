package ie.tudublin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import ie.tudublin.d22126511.Follow;
import ie.tudublin.d22126511.Word;
import processing.core.PApplet;

public class DANI extends PApplet {

	private ArrayList <Word> loadedWords = new ArrayList<>();
	private int sonneteLen = 14;
    private String[] sonnet = new String[sonneteLen];

	@Override
	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

	@Override
	public void keyPressed() {
		if(key == ' '){
			generateNewSonnete();
		}
	}

	@Override
	public void setup() {
		colorMode(HSB);
		loadFile();
		printModel();
		generateNewSonnete();
	}

	@Override
	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
		printSonneteAtTheScreen();
	}

	//load shakespere.txt file
	private String[] loadFile() {
		Word currentWord;
		String[] lines = loadStrings("shakespere.txt"); // Load a text file into a String array
		for (String line : lines) {
			//get each file line converted into lower case
			line = line.toLowerCase();

			//get splited words
			String words[] = split(line, ' '); 
			for(int i = 0; i<words.length; ++i) {
				// Remove punction characters
				String thisWord = words[i].replaceAll("[^\\w\\s]",""); 
				currentWord = this.findWord(thisWord);

				//if word is not already in a words array - add
				if(currentWord == null){
					currentWord = new Word(thisWord);
					loadedWords.add(currentWord);
				}

				//if next word exists, add to current word new follow
				if(i+1 < words.length){
					String nextWord = words[i+1].replaceAll("[^\\w\\s]","");
					this.addFolow(nextWord, currentWord);
				}
			}
		}
		return lines;
	}


	//check if word is already met in a loadedWords
	private Word findWord(String str){
		if(!this.isWordsEmpty()){
			for (Word word : loadedWords) {
				if(Objects.equals(word.getWord(), str)){
					return word;
				}
			}
		}
		return null;
	}

	//add follow to particular word
	private void addFolow(String follow, Word word){
		int index = findFollowIndex(word, follow);
		if(index == -1){
			word.addFollow(follow);
		} else {
			word.increaseFollowCountByIndex(X);
		}
	}
	
	//check if word is already met in a follows
	private int findFollowIndex(Word word, String mightFollow){

		for (int i = 0; i<word.getFollows().size(); ++i){
			if(word.getFollows().get(i).getWord() == mightFollow){
				return i; 
			}
		}
		return -1;
	}

	//checks if words arraylist is empty
	private boolean isWordsEmpty(){
		return loadedWords.size()== 0;
	}

	//print a model
	private void printModel(){
		for (Word string : loadedWords) {
			System.out.println(string);
		}
	}
	
	//genarates sonnet into the field and prints it in a console
	private void generateNewSonnete(){
		this.sonnet = writeSonnet();
		printSonneteInConsole();
	}

	//creating a new sonnete
    private String[] writeSonnet() {
		String[] sonnet = new String[sonneteLen];
		for(int i = 0; i< this.sonneteLen; i++){
			sonnet[i] = writeSonneteString(); 
		}
        return sonnet;
    }

	//generating sonette string which contains no more than 8 words
	private String writeSonneteString() {
		Word currentWord = loadedWords.get(getRandomVal(0, loadedWords.size()-1));
		String sonnetRow = currentWord.getWord() + " ";

		int i = 0;
		while(i<8){
			//if no follows - stop
			if(currentWord.getFollowsSize()== 0){
				break;
			}

			//get random follow word and find it in a word array
			Follow randomFollow = currentWord.getFollows().get(getRandomVal(0, currentWord.getFollowsSize()-1));
			currentWord = this.findWord(randomFollow.getWord());
			sonnetRow += currentWord.getWord() + " ";
			i++;
		}
		return sonnetRow;
	}


	//find a random value in a min and max range
	private int getRandomVal(int min, int max){
		return new Random().nextInt(max - min + 1 + min);
	}


	//print sonnete in console
	private void printSonneteInConsole(){
		System.out.println("\n\n\n----HERE IS OUR SONNET\n\n\n");
		for(int i = 0; i< this.sonneteLen; i++){
			System.out.println(sonnet[i]);
		}
	}

	//print sonette at the screen
	private void printSonneteAtTheScreen(){
		text("Sonnete:", width/2-10, 20);

		for(int i = 1; i<= this.sonneteLen; i++){
			//map where to display the string
			float y = map(i-1, 0, 14, 100, width - 100);
			text(sonnet[i-1], 500, y);
		}
	}
}
