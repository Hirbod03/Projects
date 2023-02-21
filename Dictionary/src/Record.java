/*
Hirbod Hosseini
Monday, October 10th
CS 2210
Professor Solis-Oba
Description: Record object implementation
 */
public class Record {
    // instance variable declarations
    private int score, level;
    private String key;

    // constructor
    public Record(String key, int score, int level){
        this.key = key;
        this.score = score;
        this.level = level;
    }

    // key getter method
    public String getKey(){
        return key;
    }

    // score getter method
    public int getScore(){
        return score;
    }

    // level getter method
    public int getLevel(){
        return level;
    }

}
