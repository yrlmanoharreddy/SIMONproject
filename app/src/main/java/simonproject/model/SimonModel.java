package simonproject.model;

import simonproject.GameObserver;

import java.util.ArrayList;
import java.util.Random;

public class SimonModel {
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private int blocksCount;
    private Random random;
    private int buttonLightUp;
    private int patternlength;
    private ArrayList<Integer> pattern;
    private ArrayList<Integer> userPattern;
    private int z;
    private String winStatus;

    public SimonModel() {
        this.random = new Random();
        this.pattern = new ArrayList<Integer>();
        this.userPattern = new ArrayList<Integer>();
        this.z = 0;
        this.winStatus = "STARTED";
    }

    public void randombuttonLightUp(int blocksCount) {
        buttonLightUp = this.random.nextInt(blocksCount) + 1;
        pattern.add(buttonLightUp);
    }

    public void generatePattern(int blocksCount) {
        System.out.println("Game strt");
        winStatus = "STARTED";
        z = 0;
        userPattern.clear();
        pattern.clear();

        this.blocksCount = blocksCount;
        this.patternlength = random.nextInt(blocksCount) + 1;
        System.out.println("patternlen :" + patternlength);
        for (int i = 0; i < patternlength; i++) {
            randombuttonLightUp(blocksCount);
        }
        notifyObservers();
    }

    public int getButtonLightUp() {
        return buttonLightUp;
    }

    public int getPatternLength() {
        return patternlength;
    }

    public ArrayList<Integer> getPattern() {
        return pattern;
    }

    public void setUserPattern(int blockNumber)
    {
        userPattern.add(blockNumber);
        z++;
        comparePatterns();
    }

    public void comparePatterns()
    {
        int m = userPattern.get(userPattern.size()-1);
        int n = pattern.get(z-1);
        System.out.println("userpressed:"+m+":systempattern:"+n);
        if(m != n)
        {
            winStatus = "LOST";
            notifyObservers();
            return;
        }

        if(userPattern.size() == pattern.size())
        {
            winStatus = "WIN";
            notifyObservers();
        }
    }

    public String isGameOver()
    {
        return winStatus;
    }

    public void register(GameObserver observer) {
        observers.add(observer);
    }

    public void unregister(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }
}