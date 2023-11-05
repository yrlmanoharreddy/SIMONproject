package simonproject.model;

import simonproject.GameObserver;

import java.util.ArrayList;

public class SimonModel
{
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

    public void SimonModel()
    {

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