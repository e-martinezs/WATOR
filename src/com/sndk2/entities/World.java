package com.sndk2.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    public static int CELL_SIZE = 32;
    private int cols;
    private int rows;

    private float percentageEntities;
    private float percentageFish;
    private int numFish;
    private int numSharks;

    private Fish[][] world;
    private List<Fish> entities = new ArrayList<Fish>();

    public World(int cols, int rows, float percentageEntities, float percentageFish){
        this.cols = cols;
        this.rows = rows;
        this.percentageEntities = percentageEntities;
        this.percentageFish = percentageFish;
        world = new Fish[rows][cols];
        generateEntities();
    }

    private void generateEntities(){
        int numEntities = (int)(cols*rows*(percentageEntities/100f));
        int numFish = (int)(numEntities*(percentageFish/100f));
        int numSharks = numEntities-numFish;
        Random random = new Random();

        int col, row;
        for (int i=0; i<numFish; i++){
            do {
                col = random.nextInt(cols);
                row = random.nextInt(rows);
            } while (!isEmpty(col, row));
            int age = random.nextInt(Fish.BREED_AGE);
            addEntity(new Fish(this, col, row, age));
        }
        for (int i=0; i<numSharks; i++){
            do {
                col = random.nextInt(cols);
                row = random.nextInt(rows);
            } while (!isEmpty(col, row));
            int age = random.nextInt(Fish.BREED_AGE);
            int energy = random.nextInt(Shark.MAX_ENERGY);
            addEntity(new Shark(this, col, row, age, energy));
        }
    }

    public void addEntity(Fish fish){
        entities.add(fish);
        world[fish.getRow()][fish.getCol()] = fish;
    }

    public void removeEntity(Fish fish){
        entities.remove(fish);
        world[fish.getRow()][fish.getCol()] = null;
    }

    public void removeEntityAt(int col, int row){
        Fish fish = world[row][col];
        entities.remove(fish);
        world[row][col] = null;
    }

    public void moveEntity(Fish fish, int col, int row){
        world[fish.getRow()][fish.getCol()] = null;
        world[row][col] = fish;
    }

    public boolean isEmpty(int col, int row){
        if (!isInsideBounds(col, row)){
            return false;
        }
        return world[row][col] == null;
    }

    public boolean hasFish(int col, int row){
        if (!isInsideBounds(col, row)){
            return false;
        }
        if (world[row][col] == null){
            return false;
        }
        return world[row][col].getType() == Fish.FISH;
    }

    public boolean isInsideBounds(int col, int row){
        if (col >= 0 && col < cols && row >= 0 && row < rows){
            return true;
        }
        return false;
    }

    public void update(){
        updateEntities();
        System.out.println(entities.size());
    }

    private void updateEntities(){
        for (int i=0; i<entities.size(); i++){
            entities.get(i).update();
        }
    }

    public void render(Graphics2D g2d){
        drawBackground(g2d);
        drawGrid(g2d);
        drawEntities(g2d);
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

    private void drawEntities(Graphics2D g2d){
        Fish fish = null;
        for (int i=0; i<entities.size(); i++){
            fish = entities.get(i);
            if (fish.getType() == Fish.FISH){
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.RED);
            }
            g2d.fillRect(fish.getCol()*CELL_SIZE, fish.getRow()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
