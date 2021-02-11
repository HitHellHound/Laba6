package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JPanel {
    private boolean paused;
    public static final int BLOCKS_IN_ROW = 10;
    public static final int MAX_COUNT_OF_BLOCKS = BLOCKS_IN_ROW * BLOCKS_IN_ROW - 4;

    private BouncingBall ball;

    private BreakableBlock[][] blocks = new BreakableBlock[BLOCKS_IN_ROW][BLOCKS_IN_ROW];
    private int countOfBlocks = 0;

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
        if (ball != null)
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

    public boolean addBlock(){
        if (countOfBlocks >= MAX_COUNT_OF_BLOCKS)
            return false;

        int x = countOfBlocks % BLOCKS_IN_ROW;
        int y = countOfBlocks / BLOCKS_IN_ROW;
        double blockWidth = getWidth() / BLOCKS_IN_ROW - 1;
        double blockHeight = getHeight() / BLOCKS_IN_ROW - 1;
        blocks[y][x] = new BreakableBlock(x * getWidth() / BLOCKS_IN_ROW, y * getHeight() / BLOCKS_IN_ROW, blockWidth, blockHeight);
        countOfBlocks++;
        return true;
    }

    public synchronized BreakableBlock blockTouched(BouncingBall ball){
        double blockWidth = getWidth() / BLOCKS_IN_ROW - 1;
        double blockHeight = getHeight() / BLOCKS_IN_ROW - 1;

        double speedX = ball.getSpeed().getX();
        double speedY = ball.getSpeed().getY();
        double x = ball.getCoordinates().getX() + speedX;
        double y = ball.getCoordinates().getY() + speedY;
        int r = ball.getRadius();

        if (blocks[new Double(y / blockHeight).intValue()][new Double((x - r) / blockWidth).intValue()] != null){
            return blocks[new Double(y/blockHeight).intValue()][new Double((x - r) / blockWidth).intValue()];
        }
        if (blocks[new Double(y / blockHeight).intValue()][new Double((x + r) / blockWidth).intValue()] != null){
            return blocks[new Double(y / blockHeight).intValue()][new Double((x + r) / blockWidth).intValue()];
        }
        if (blocks[new Double((y - r) / blockHeight).intValue()][new Double(x / blockWidth).intValue()] != null){
            return blocks[new Double((y - r) / blockHeight).intValue()][new Double(x / blockWidth).intValue()];
        }
        if (blocks[new Double((y + r) / blockHeight).intValue()][new Double(x / blockWidth).intValue()] != null){
            return blocks[new Double((y + r) / blockHeight).intValue()][new Double(x / blockWidth).intValue()];
        }

        return null;
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
