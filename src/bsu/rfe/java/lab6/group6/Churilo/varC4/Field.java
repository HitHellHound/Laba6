package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {
    private boolean paused;
    public final int blocksInRow = 20;
    public final int MAX_COUNT_OF_BLOCKS = blocksInRow * blocksInRow - 4;

    private BounclingBall ball;

    private BreakableBlock[][] blocks = new BreakableBlock[blocksInRow][blocksInRow];

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        //ball.paint(canvas);
    }

    public void addBall(){

    }

    public synchronized void pause() {
        paused = true;
    }
    
    public synchronized void resume(){
        paused = false;
        notifyAll();
    }

    public synchronized void canMove(BounclingBall ball) throws  InterruptedException{
        if (paused){
            wait();
        }
    }
}
