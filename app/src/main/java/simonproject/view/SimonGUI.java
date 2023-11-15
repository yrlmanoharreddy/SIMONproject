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
    private JFrame startFrame;
    private JPanel startGamePanel;
    private JButton startGameButton;
    private JFrame loadFrame;
    private JButton loadGameButton;
    protected boolean state;
    private JButton quitGameButton;
    private JPanel beginPanel;
    private boolean state_flag;
    private int patternLength;

    public SimonGUI(ControllerInterface controller, SimonModel model) {
        this.controller = controller;
        this.model = model;

        this.model.register(this);

        this.startFrame = new JFrame("SIMON");
        this.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.startFrame.setResizable(false);

        startGamePanel = new JPanel();
        startGamePanel.setPreferredSize(new Dimension(600, 600));
        startGamePanel.setLayout(new BoxLayout(startGamePanel, BoxLayout.Y_AXIS));
        startGamePanel.setBackground(new Color(241, 245, 179));

        Font font_start_menu = new Font("Forte", Font.BOLD, 20);

        JLabel titleLabel = new JLabel("S I M O N");
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 80));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(142, 70, 143));

        JRadioButton radioButton1 = new JRadioButton("Easy");
        radioButton1.setFont(new Font("forte", Font.BOLD, 20));
        radioButton1.setForeground(new Color(142, 70, 143));
        radioButton1.setBackground(new Color(241, 245, 179));
        radioButton1.setSelected(true);
        radioButton1.setFocusable(false);

        JRadioButton radioButton2 = new JRadioButton("Medium");
        radioButton2.setFont(new Font("forte", Font.BOLD, 20));
        radioButton2.setForeground(new Color(142, 70, 143));
        radioButton2.setBackground(new Color(241, 245, 179));
        radioButton2.setFocusable(false);

        JRadioButton radioButton3 = new JRadioButton("Hard");
        radioButton3.setFont(new Font("forte", Font.BOLD, 20));
        radioButton3.setForeground(new Color(142, 70, 143));
        radioButton3.setBackground(new Color(241, 245, 179));
        radioButton3.setFocusable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        startGameButton = new JButton("Start new game");
        startGameButton.setBackground(new Color(142, 70, 143));
        startGameButton.setForeground(Color.white);
        startGameButton.setFocusable(false);
        startGameButton.setFont(font_start_menu);
        startGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (state_flag) {
                    System.out.println("111");
                    loadFrame.dispose();
                }
                if (radioButton1.isSelected()) {
                    System.out.println("Option 1 selected. Starting game...");
                    // Add your logic for starting the game with Option 1
                    disableGameStartPanel();
                    System.out.println("Easy");
                    patternLength = 2;
                } else if (radioButton2.isSelected()) {
                    System.out.println("Option 2 selected. Starting game...");
                    // Add your logic for starting the game with Option 2
                    disableGameStartPanel();
                    System.out.println("Medium");
                    patternLength = 4;
                } else if (radioButton3.isSelected()) {
                    System.out.println("Option 3 selected. Starting game...");
                    // Add your logic for starting the game with Option 3
                    disableGameStartPanel();
                    System.out.println("Hard");
                    patternLength = 6;
                } else {
                    System.out.println("No option selected. Please select an option.");
                    System.out.println("patt length: " + patternLength);
                    disableGameStartPanel();
                    // patternLength = 2;
                }
            }

        });

        loadGameButton = new JButton("Load game");
        loadGameButton.setBackground(new Color(142, 70, 143));
        loadGameButton.setForeground(Color.WHITE);
        loadGameButton.setFocusable(false);
        loadGameButton.setFont(font_start_menu);
        loadGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!state) {
                    showNoStatePanel();
                }
            }

        });

        quitGameButton = new JButton("Quit");
        quitGameButton.setBackground(new Color(142, 70, 143));
        quitGameButton.setForeground(Color.WHITE);
        quitGameButton.setFocusable(false);
        quitGameButton.setFont(font_start_menu);
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
            }
        });

        beginPanel = new JPanel();
        beginPanel.setLayout(new BoxLayout(beginPanel, BoxLayout.Y_AXIS));
        beginPanel.setBackground(new Color(241, 245, 179));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(241, 245, 179));
        titlePanel.add(titleLabel);

        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(new Color(241, 245, 179));
        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        radioPanel.add(radioButton3);

        JPanel buttonPanel_menu = new JPanel();
        buttonPanel_menu.setLayout(new BoxLayout(buttonPanel_menu,
                BoxLayout.Y_AXIS));
        buttonPanel_menu.setBackground(new Color(241, 245, 179));
        buttonPanel_menu.add(startGameButton);
        buttonPanel_menu.add(Box.createVerticalStrut(10));
        buttonPanel_menu.add(loadGameButton);
        buttonPanel_menu.add(Box.createVerticalStrut(10));
        buttonPanel_menu.add(quitGameButton);
        buttonPanel_menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel_menu.setMaximumSize(new Dimension(200, 200));

        beginPanel.add(titlePanel);
        beginPanel.add(radioPanel);
        beginPanel.add(buttonPanel_menu);

        startGamePanel.add(Box.createVerticalGlue());
        startGamePanel.add(beginPanel);
        startGamePanel.add(Box.createVerticalGlue());

        this.startFrame.add(startGamePanel);
        this.startFrame.pack();
        this.startFrame.setVisible(true);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    private void showNoStatePanel() {
        this.loadFrame = new JFrame("Load game");
        this.loadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loadFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(new Color(255, 0, 0));
        panel.add(this.startGameButton);

        this.loadFrame.add(panel);
        this.loadFrame.pack();
        this.loadFrame.setVisible(true);
        state_flag = true;
    }

    private void disableGameStartPanel() {
        startFrame.setVisible(false);
        enableGamePlayPanel();
    }

    private void enableGamePlayPanel() {
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
            System.out.println("Clicked btn"+patternLength);
            this.controller.userPressed(patternLength);
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
        // Timer timer = new Timer(1000, new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // reInit();
        // }
        // });
        // timer.setRepeats(false);
        // timer.start();
    }

    public void reInit() {
        System.out.println("*1"+patternLength);
        this.controller.userPressed(patternLength);
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
        this.buttons.setVisible(false);
        this.startButton.setVisible(false);
        victPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        victPanel.setBackground(Color.RED);
        this.startButton.setVisible(true);
        victPanel.add(this.startButton);
        flag = 1;
        this.mainPanel.add(victPanel);
        System.out.println("FINAL STEP");
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
