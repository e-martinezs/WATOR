package com.sndk2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Wator extends JPanel {

    private static final int CELL_SIZE = 32;

    private BufferedImage buffer;
    private Graphics2D g2d;

    private int cols = 40;
    private int rows = 21;
    private int[][] world = new int[rows][cols];

    private float cameraX;
    private float cameraY;
    private float cameraSpeed = 0.1f;

    private boolean rightKey;
    private boolean leftKey;
    private boolean upKey;
    private boolean downKey;

    public Wator(){
        createBuffer();
    }

    private void createBuffer(){
        buffer = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
    }

    public void update(double delta){
        if (rightKey){
            cameraX += cameraSpeed*delta;
        }
        else if (leftKey){
            cameraX -= cameraSpeed*delta;
        }
        if (upKey){
            cameraY -= cameraSpeed*delta;
        }
        else if (downKey){
            cameraY += cameraSpeed*delta;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        drawBackground();
        drawGrid();
        g.drawImage(buffer, (int)-cameraX , (int)-cameraY, this);
    }

    private void drawBackground(){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, CELL_SIZE*cols, CELL_SIZE*rows);
    }

    private void drawGrid(){
        g2d.setColor(Color.BLACK);
        for (int i=0; i<cols+1; i++){
            g2d.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, CELL_SIZE*rows);
        }
        for (int i=0; i<rows+1; i++){
            g2d.drawLine(0, i*CELL_SIZE, CELL_SIZE*cols, i*CELL_SIZE);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            downKey = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            downKey = false;
        }
    }
}
