package ie.tudublin.d22126511;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;

    public Word(String word){
        this.word = word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    public boolean isNoFollows(){
        return this.follows.size() == 0;
    }

    @Override
    public String toString() {
        String res = this.word + ":\n";
        if(!isNoFollows()){
            for (Follow follow : follows) {
                res += follow.toString();
            }
        }

        return res;
    }


}
