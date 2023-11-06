package simonproject.view;

import simonproject.model.SimonModel;
import simonproject.ControllerInterface;

import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class SimonButtons extends JPanel implements ActionListener{

    private SimonModel model;
    private ArrayList<Integer> pattern = new ArrayList<>();
    private AudioInputStream audioIn[];
    private JButton buttons[];
    private int button = 0;
    private ControllerInterface controller;

    public SimonButtons(SimonModel model, ControllerInterface controller) {
        this.controller = controller;
        this.model = model;
        this.setLayout(new GridLayout(2, 2));
        this.buttons = new JButton[4];
        this.audioIn = new AudioInputStream[4];
        Color colors[] = { new Color(92, 1, 1), new Color(1, 1, 92), new Color(133, 138, 3),
                new Color(1, 92, 1) };
        for (int i = 0; i < 4; i++) {
            this.buttons[i] = new JButton(Integer.toString(i + 1));
            this.buttons[i].setOpaque(true);
            this.buttons[i].setBackground(colors[i]);
            this.buttons[i].setForeground(Color.WHITE);
            this.buttons[i].setBorder(BorderFactory.createLineBorder(colors[i], 5));
            this.buttons[i].setFocusable(false);
            this.buttons[i].addActionListener(this);
            this.add(this.buttons[i]);
        }
        this.setBackground(new Color(227, 206, 245));

        try {
            for (int i = 0; i < 4; i++) {
                URL url = getClass().getResource("/beep" + (i + 1) + ".wav");
                System.out.println(url);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                this.audioIn[i] = audioIn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        System.out.println("1");
        JButton pressedButton = (JButton)event.getSource();
        int x = Integer.parseInt(pressedButton.getText());
        System.out.println(x);
        this.controller.userPatternPressed(x);
    }

    public void blinkButtons(ArrayList<Integer> pattern) {
        this.pattern = pattern;
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn[pattern.get(button) - 1]);
            blinkButton(pattern.get(button), clip);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void blinkButton(int buttonIndex, Clip clip) {
        Timer timer = new Timer(50 * (buttonIndex + 2), new ActionListener() {
            int count = 0;
            JButton currentButton = buttons[buttonIndex - 1];
            // Clip clip;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    setButtonColor(currentButton, buttonIndex);
                }
                if (count == 5) {
                    resetButtonColor(currentButton, buttonIndex);
                    ((Timer) e.getSource()).stop();
                    button += 1;
                    if (button < pattern.size()) {
                        blinkButtons(pattern);
                    }
                    clip.stop();
                }
                count++;
            }
        });
        timer.start();
    }

    private void setButtonColor(JButton button, int buttonIndex) {
        switch (buttonIndex - 1) {
            case 0:
                button.setBackground(new Color(247, 7, 7));
                break;
            case 1:
                button.setBackground(new Color(72, 84, 250));
                break;
            case 2:
                button.setBackground(new Color(246, 255, 3));
                break;
            case 3:
                button.setBackground(new Color(62, 245, 56));
                break;
        }
    }

    private void resetButtonColor(JButton button, int buttonIndex) {
        switch (buttonIndex - 1) {
            case 0:
                button.setBackground(new Color(92, 1, 1));
                break;
            case 1:
                button.setBackground(new Color(1, 1, 92));
                break;
            case 2:
                button.setBackground(new Color(133, 138, 3));
                break;
            case 3:
                button.setBackground(new Color(1, 92, 1));
                break;
        }
    }

    public void startModification()
    {
        if(model.isGameOver().equals("LOST"))
        {
            System.out.println("You Lost the game");
            //Task 2: Disable all buttons and add Start Game button to Restart the game
        }
        else if (model.isGameOver().equals("WIN"))
        {
            System.out.println("You WON");
            //Task ==1: write code for some annimation and delay
            this.controller.userPressed(4);
            System.out.println("Statement after controller called");
        }
        else
        {
        this.button = 0;
        ArrayList<Integer> pattern = model.getPattern();
        System.out.println("pattern * " + model.getButtonLightUp() + "LIST:" + pattern);
        blinkButtons(pattern);
        }
    }
}