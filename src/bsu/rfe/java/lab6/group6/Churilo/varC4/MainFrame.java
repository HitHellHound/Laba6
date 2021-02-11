package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private static final int WIDTH = 816;
    private static final int HEIGHT = 661;

    private JMenuItem addBallMenuItem;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem addBlockMenuItem;

    private Field field = new Field();

    public MainFrame() {
        super("МЯЧИК И КИРПИЧИ");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        //setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Ball
        JMenu ballMenu = new JMenu("Ball");
        menuBar.add(ballMenu);
        Action addBallAction = new AbstractAction("Create BALL") {
            public void actionPerformed(ActionEvent e) {
                field.addBall();
                addBallMenuItem.setEnabled(false);
                pauseMenuItem.setEnabled(true);
                addBlockMenuItem.setEnabled(true);
            }
        };
        addBallMenuItem = ballMenu.add(addBallAction);
        addBallMenuItem.setEnabled(true);
        //

        //Control
        JMenu controlMenu = new JMenu("Control");
        menuBar.add(controlMenu);

        Action pauseAction = new AbstractAction("Pause") {
            public void actionPerformed(ActionEvent e) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Resume") {
            public void actionPerformed(ActionEvent e) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        //

        //Blocks
        JMenu blocksMenu = new JMenu("Blocks");
        menuBar.add(blocksMenu);

        Action addBlockAction = new AbstractAction("Add BLOCK") {
            public void actionPerformed(ActionEvent e) {
                field.addBlock();
            }
        };
        addBlockMenuItem = blocksMenu.add(addBlockAction);
        addBlockMenuItem.setEnabled(false);
        //

        getContentPane().add(field, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
