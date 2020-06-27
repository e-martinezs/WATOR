package com.sndk2.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static int CELL_SIZE = 32;
    private int cols;
    private int rows;
    private int[][] world;
    private List<Fish> entities = new ArrayList<Fish>();

    public World(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
        world = new int[rows][cols];
    }

    public void addEntity(Fish fish){
        entities.add(fish);
    }

    public void removeEntity(Fish fish){
        entities.remove(fish);
    }

    public void update(){
        updateEntities();
    }

    private void updateEntities(){
        for (int i=0; i<entities.size(); i++){
            entities.get(i).update();
        }
    }

    public void render(Graphics2D g2d){
        drawBackground(g2d);
        drawGrid(g2d);
    }

    private void drawBackground(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, CELL_SIZE*cols, CELL_SIZE*rows);
    }

    private void drawGrid(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        for (int i=0; i<cols+1; i++){
            g2d.drawLine(i*World.CELL_SIZE, 0, i*CELL_SIZE, CELL_SIZE*rows);
        }
        for (int i=0; i<rows+1; i++){
            g2d.drawLine(0, i*CELL_SIZE, CELL_SIZE*cols, i*CELL_SIZE);
        }
    }
}
