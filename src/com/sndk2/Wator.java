package com.sndk2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Wator extends JPanel {

    private static final int CELL_SIZE = 32;

    private BufferedImage buffer;
    private Graphics2D g2d;

    private int cols = 40;
    private int rows = 21;
    private int[][] world = new int[rows][cols];

    public Wator(){
        createBuffer();
    }

    private void createBuffer(){
        buffer = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
    }

    public void update(double delta){

    }

    @Override
    public void paintComponent(Graphics g) {
        drawBackground();
        drawGrid();
        g.drawImage(buffer, 0 ,0, this);
    }

    private void drawBackground(){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
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
}
