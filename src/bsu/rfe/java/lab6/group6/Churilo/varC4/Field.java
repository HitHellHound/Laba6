package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JPanel {
    private boolean paused;
    private final int blocksInRow = 20;

    private BounclingBall ball;

    //private Blo

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });


}
