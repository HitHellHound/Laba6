package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JPanel {
    private boolean paused;
    public static final int BLOCKS_IN_ROW = 20;
    public static final int MAX_COUNT_OF_BLOCKS = BLOCKS_IN_ROW * BLOCKS_IN_ROW - 4;

    private BouncingBall ball;

    private BreakableBlock[][] blocks = new BreakableBlock[BLOCKS_IN_ROW][BLOCKS_IN_ROW];

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
        ball.paint(canvas);
        for(BreakableBlock[] mas_blocks: blocks){
            for (BreakableBlock block: mas_blocks){
                if (block != null)
                    block.paint(canvas);
            }
        }
    }

    public void addBall(){
        ball = new BouncingBall(this);
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume(){
        paused = false;
        notifyAll();
    }

    public synchronized void canMove(BouncingBall ball) throws  InterruptedException{
        if (paused){
            wait();
        }
    }
}
