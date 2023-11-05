package simonproject.view;

import simonproject.model.SimonModel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimonButtons extends JPanel
{

    private SimonModel model;
    private JButton buttons[];

    public SimonButtons(SimonModel model)
    {
        this.model = model;
        this.setLayout(new GridLayout(2, 2));
        this.buttons = new JButton[4];
        Color colors[] = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
        for(int i=0;i<4;i++)
        {
            this.buttons[i] = new JButton(Integer.toString(i));
            this.buttons[i].setBackground(Color.YELLOW);
            // this.buttons[i].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            this.add(this.buttons[i]);
        }
    }
}