package ie.tudublin.d22126511;

public class Follow {
    private String word;
    private int count;

    public Follow (String word, int count){
        this.word = word;
        this.count = count;
    }

    public void setWord(String word){
        this.word = word;
    }

    public String getWord(){
       return this.word;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public void increaseCount(){
        this.count ++;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) \t", word, count);
    }

}
