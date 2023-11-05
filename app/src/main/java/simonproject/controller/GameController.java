package simonproject.controller;

import simonproject.ControllerInterface;
import simonproject.model.SimonModel;
import simonproject.view.SimonGUI;

public class GameController implements ControllerInterface
{
    private SimonModel model;
    private SimonGUI view;

    public GameController(SimonModel model)
    {
        this.model = model;

        this.view = new SimonGUI(this, model);
    }

    @Override
    public void userPressed(int blocksCount)
    {
        System.out.println("In controller");
        model.generatePattern(blocksCount);
    }
}