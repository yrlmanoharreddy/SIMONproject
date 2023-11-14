package simonproject.view;

import simonproject.ControllerInterface;
import simonproject.GameObserver;
import simonproject.model.SimonModel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimonGUI implements GameObserver, ActionListener {
    private ControllerInterface controller;
    private SimonModel model;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private SimonButtons buttons;
    private JButton startButton;
    private JPanel victPanel;
    private JLabel victLabel;
    private int flag;

    public SimonGUI(ControllerInterface controller, SimonModel model) {
        this.controller = controller;
        this.model = model;

        this.model.register(this);

        this.mainFrame = new JFrame("S I M O N");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(new Color(227, 206, 245));
        this.mainPanel.setPreferredSize(new Dimension(600, 600));
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.buttons = new SimonButtons(model, controller, this);
        this.buttons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.buttons.setPreferredSize(new Dimension(300, 300));

        this.mainPanel.add(buttons);

        this.startButton = new JButton("Start");
        this.startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.startButton.setBackground(Color.CYAN);
        this.startButton.setOpaque(true);
        this.startButton.setFocusable(false);
        this.startButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
        this.startButton.setMaximumSize(new Dimension(200, this.startButton.getMaximumSize().height));
        this.startButton.setMinimumSize(new Dimension(0, this.startButton.getMinimumSize().height));
        this.startButton.setPreferredSize(new Dimension(70, 70));
        this.startButton.addActionListener(this);
        this.mainPanel.add(this.startButton);

        this.mainFrame.add(mainPanel);

        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (flag == 1) {
            removeDeftPanel();
            enableButtons();
            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Waiting...!!!");
                }
            });
            timer.setRepeats(false);
            timer.start();
            flag = 0;
            return;
        } else {
            System.out.println("Clicked btn");
            this.controller.userPressed(4);
        }
    }

    public void victory() {
        System.out.println("This is victory");
        this.buttons.setVisible(false);
        this.startButton.setVisible(false);
        victPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        victPanel.setBackground(Color.BLUE);
        victLabel = new JLabel("Super!!!");
        victLabel.setFont(new Font("Arial", Font.BOLD, 30));
        victPanel.add(victLabel);
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.add(victPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVictPanel();
                System.out.println("test1");
                enableButtons();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void enableButtons() {
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        if (flag == 1) {
            this.mainPanel.add(this.startButton);
        }
        this.buttons.setVisible(true);
        this.startButton.setVisible(true);
        reInit();
        // Timer timer = new Timer(4000, new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // reInit();
        // }
        // });
        // timer.setRepeats(false);
        // timer.start();
    }

    public void reInit() {
        this.controller.userPressed(4);
    }

    public void removeVictPanel() {
        if (victPanel != null) {
            mainPanel.remove(victPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
            victPanel = null;
        }
    }

    public void defeat() {
        System.out.println("This is Defeat");
        // JButton stb = this.startButton;
        this.buttons.setVisible(false);
        this.startButton.setVisible(false);
        victPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        victPanel.setBackground(Color.RED);
        // stb.setVisible(true);
        // stb.addActionListener(this);
        // victPanel.add(stb);
        this.startButton.setVisible(true);
        victPanel.add(this.startButton);
        flag = 1;
        this.mainPanel.add(victPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void removeDeftPanel() {
        if (victPanel != null) {
            mainPanel.remove(victPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
            victPanel = null;
        }
    }

    @Override
    public void update() {
        buttons.startModification();
    }
}
