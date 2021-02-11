package bsu.rfe.java.lab6.group6.Churilo.varC4;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BreakableBlock {
    private static final int MAX_HEAT_POINTS = 5;
    private static final int MIN_HEAT_POINTS = 1;

    private static final BasicStroke BLOCK_STROKE = new BasicStroke(2.0f);

    private int heatPoints;
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public BreakableBlock(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());

        heatPoints = new Double(Math.random() * (MAX_HEAT_POINTS - MIN_HEAT_POINTS)).intValue() + MIN_HEAT_POINTS;
    }

    public void reduceHP(){
        heatPoints--;
    }

    public void paint(Graphics2D canvas){
        canvas.setColor(Color.BLACK);
        canvas.setStroke(BLOCK_STROKE);
        Rectangle2D.Double rec = new Rectangle2D.Double(x, y, width, height);
        canvas.draw(rec);
        canvas.setPaint(Color.ORANGE);
        canvas.fill(rec);
    }
}
