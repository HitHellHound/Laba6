package bsu.rfe.java.lab6.group6.Churilo.varC4;

import javafx.geometry.Point2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable{
    private static final int MAX_RADIUS = 25;
    private static final int MIN_RADIUS = 3;
    private static final int MAX_SPEED = 10;

    private Field field;
    private int radius;
    private Color color;

    private double x;
    private double y;
    private double angle;

    private int speed;
    private double speedX;
    private double speedY;

    public BouncingBall(Field field){
        this.field = field;
        radius = new Double(Math.random() * (MAX_RADIUS - MIN_RADIUS)).intValue() + MIN_RADIUS;
        speed = new Double(Math.round(5 * MAX_SPEED / radius)).intValue();

        if (speed > MAX_SPEED)
            speed = MAX_SPEED;

        angle = Math.random() * 2 * Math.PI;
        speedX = speed * Math.cos(angle);
        speedY = speed * Math.sin(angle);

        color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());

        x = Math.random() * (field.getSize().getWidth() - 2 * radius) + radius;
        y = Math.random() * (field.getSize().getHeight() - 2 * radius) + radius;

        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public Point2D getCoordinates(){
        return new Point2D(x, y);
    }

    public int getRadius(){
        return radius;
    }

    public Point2D getSpeed(){
        return new Point2D(speedX, speedY);
    }

    public void run(){
        try{
            while (true){
                field.canMove(this);
                if (x + speedX <= radius) {
                    speedX = -speedX;
                    x = radius;
                } else
                if (x + speedX >= field.getWidth() - radius) {
                    speedX = -speedX;
                    x = new Double(field.getWidth()-radius).intValue();
                } else
                if (y + speedY <= radius) {
                    speedY = -speedY;
                    y = radius;
                } else
                if (y + speedY >= field.getHeight() - radius) {
                    speedY = -speedY;
                    y = new Double(field.getHeight()-radius).intValue();
                } else {
                    BreakableBlock block = field.blockTouched(this);
                    if (block != null){
                        double w = block.getWidth();
                        double h = block.getHeight();
                        double xb = block.getCoordinates().getX();
                        double yb = block.getCoordinates().getY();

                        double x_new = x + speedX;
                        double y_new = y + speedY;

                        if (xb <= x && x <= xb + w && y < yb) {
                            speedY = -speedY;
                        } else
                        if (xb <= x && x <= xb + w && y > yb + h) {
                            speedY = -speedY;
                        } else
                        if (yb <= y && y < yb + h && x < xb) {
                            speedX = -speedX;
                        } else
                        if (yb <= y && y < yb + h && x > xb + w) {
                            speedX = -speedX;
                        } else
                        if (x < xb && y < yb){
                            if (Math.abs(x - xb) / speedX < Math.abs(y - yb) / speedY){
                                speedX = -speedX;
                            }
                            else {
                                speedY = -speedY;
                            }
                        } else
                        if (x > xb + w && y < yb){
                            if (Math.abs(x - xb - w) / speedX < Math.abs(y - yb) / speedY){
                                speedX = -speedX;
                            }
                            else {
                                speedY = -speedY;
                            }
                        } else
                        if (x < xb && y > yb + h){
                            if (Math.abs(x - xb) / speedX < Math.abs(y - yb - h) / speedY){
                                speedX = -speedX;
                            }
                            else {
                                speedY = -speedY;
                            }
                        } else
                        if (x > xb + w && y > yb + h){
                            if (Math.abs(x - xb - w) / speedX < Math.abs(y - yb - h) / speedY){
                                speedX = -speedX;
                            }
                            else {
                                speedY = -speedY;
                            }
                        }
                        block.reduceHP();
                    } else {
                        x += speedX;
                        y += speedY;
                    }
                }


                Thread.sleep(11 - speed);
            }
        }
        catch (InterruptedException ex){ }
    }

    public void paint(Graphics2D canvas){
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }
}
