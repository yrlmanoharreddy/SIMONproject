package simonproject.view;

import simonproject.ControllerInterface;
import simonproject.GameObserver;
import simonproject.model.SimonModel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimonGUI implements GameObserver
{
    private ControllerInterface controller;
    private SimonModel model;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private SimonButtons buttons;
    private JButton startButton;

    public SimonGUI(ControllerInterface controller, SimonModel model)
    {
        this.controller = controller;
        this.model = model;

        this.model.register(this);

        this.mainFrame = new JFrame("SIMON");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(new Color(227, 206, 245));
        this.mainPanel.setPreferredSize(new Dimension(500, 500));
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.buttons = new SimonButtons(model);
        this.buttons.setPreferredSize(new Dimension(400, 400));

        this.mainPanel.add(buttons);

        this.startButton = new JButton("Start");
        this.startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.startButton.setBackground(Color.RED);
        this.mainPanel.add(this.startButton);

        this.mainFrame.add(mainPanel);

        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    @Override
    public void update()
    {

    }
}