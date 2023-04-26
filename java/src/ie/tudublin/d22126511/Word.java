package ie.tudublin.d22126511;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows = new ArrayList<>();

    public Word(String word){
        this.word = word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    public ArrayList<Follow> getFollows(){
        return this.follows;
    }

    public int getFollowsSize(){
        return this.follows.size();
    }

    public void addFollow(String word){
        this.follows.add(new Follow(word, 1));
    }

    public void changeFellowByIndex(int findFollowIndex){
        this.follows.get(findFollowIndex).increaseCount();
    }

    @Override
    public String toString() {
        String res = this.word + ":\t";
        for (Follow follow : follows) {
            res += follow.toString();
        }
        return res;
    }
}
