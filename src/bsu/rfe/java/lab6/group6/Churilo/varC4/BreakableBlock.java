package bsu.rfe.java.lab6.group6.Churilo.varC4;

import java.awt.*;
import java.awt.datatransfer.MimeTypeParseException;
import java.awt.geom.Rectangle2D;

public class BreakableBlock {
    private static final int MAX_HEAT_POINTS = 5;
    private static final int MIN_HEAT_POINTS = 1;

    private int HeatPoints;
    private double x;
    private double y;
    private double x_size;
    private double y_size;
    private Color color;

    public BreakableBlock(double x, double y, double x_size, double y_size){
        this.x = x;
        this.y = y;
        this.x_size = x_size;
        this.y_size = y_size;

        color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());

        HeatPoints = new Double(Math.random() * (MAX_HEAT_POINTS - MIN_HEAT_POINTS)).intValue() + MIN_HEAT_POINTS;
    }

    public void paint(Graphics2D canvas){
        canvas.setColor(Color.BLACK);
        canvas.setPaint(Color.ORANGE);
        Rectangle2D.Double rec = new Rectangle2D.Double(x, y, x_size, y_size);
        canvas.draw(rec);
        canvas.fill(rec);
    }
}
