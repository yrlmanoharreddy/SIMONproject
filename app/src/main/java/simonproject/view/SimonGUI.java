package simonproject.view;

import simonproject.ControllerInterface;
import simonproject.GameObserver;
import simonproject.model.SimonModel;

public class SimonGUI implements GameObserver
{
    private ControllerInterface controller;
    private SimonModel model;

    public SimonGUI(ControllerInterface controller, SimonModel model)
    {
        this.controller = controller;
        this.model = model;

        this.model.register(this);
    }

    @Override
    public void update()
    {

    }
}
