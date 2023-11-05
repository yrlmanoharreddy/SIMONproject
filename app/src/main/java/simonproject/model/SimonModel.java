package simonproject.model;

import simonproject.GameObserver;

import java.util.ArrayList;
import java.util.Random;

public class SimonModel
{
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private int blocksCount;
    private Random random;
    private int buttonLightUp;
    private int patternlength;
    private ArrayList<Integer> pattern;

    public SimonModel()
    {
        this.random = new Random();
        this.pattern = new ArrayList<Integer>();
    }

    public void randombuttonLightUp(int blocksCount)
    {
        buttonLightUp = this.random.nextInt(blocksCount) + 1;
        pattern.add(buttonLightUp);
        notifyObservers();
    }

    public void generatePattern(int blocksCount)
    {
        this.blocksCount = blocksCount;
        this.patternlength = random.nextInt(blocksCount) + 1;
        System.out.println("patternlen ;"+patternlength);
        for(int i=0; i<patternlength; i++)
        {
            randombuttonLightUp(blocksCount);
        }
    }

    public int getButtonLightUp()
    {
        return buttonLightUp;
    }

    public int getPatternLength()
    {
        return patternlength;
    }

    public ArrayList<Integer> getPattern()
    {
        return pattern;
    }

    public void clearPattern()
    {
        pattern.clear();
    }

    public void register(GameObserver observer)
    {
        observers.add(observer);
    }

    public void unregister(GameObserver observer)
    {
        observers.remove(observer);
    }

    public void notifyObservers()
    {
        for(GameObserver observer : observers)
        {
            observer.update();
        }
    }
}