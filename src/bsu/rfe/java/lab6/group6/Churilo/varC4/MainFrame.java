package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    private Field field = new Field();

    public MainFrame() {
        super("МЯЧИК И КИРПИЧИ");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Ball
        JMenu ballMenu = new JMenu("Ball");
        Action addBallAction = new AbstractAction("Create BALL") {
            public void actionPerformed(ActionEvent e) {
                pauseMenuItem.setEnabled(true);
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);

        //Control
        JMenu controlMenu = new JMenu("Control");
        menuBar.add(controlMenu);

        Action pauseAction = new AbstractAction("Pause") {
            public void actionPerformed(ActionEvent e) {
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Resume") {
            public void actionPerformed(ActionEvent e) {
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
