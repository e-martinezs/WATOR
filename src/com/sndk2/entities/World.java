package com.sndk2.entities;

import com.sndk2.Variables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    public static final int CELL_SIZE = Variables.CELL_SIZE;
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
            int age = random.nextInt(Variables.BREED_AGE_FISH);
            addEntity(new Fish(this, col, row, age));
        }
        for (int i=0; i<numSharks; i++){
            do {
                col = random.nextInt(cols);
                row = random.nextInt(rows);
            } while (!isEmpty(col, row));
            int age = random.nextInt(Variables.BREED_AGE_SHARK);
            int energy = random.nextInt(Variables.MAX_ENERGY_SHARK);
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
        int modCol = modCols(col);
        int modRow = modRows(row);
        Fish fish = world[modRow][modCol];
        entities.remove(fish);
        world[modRow][modCol] = null;
    }

    public void moveEntity(Fish fish, int col, int row){
        int modCol = modCols(col);
        int modRow = modRows(row);
        world[fish.getRow()][fish.getCol()] = null;
        world[modRow][modCol] = fish;
    }

    public boolean isEmpty(int col, int row){
        int modCol = modCols(col);
        int modRow = modRows(row);
        return world[modRow][modCol] == null;
    }

    public boolean hasFish(int col, int row){
        int modCol = modCols(col);
        int modRow = modRows(row);
        if (world[modRow][modCol] == null){
            return false;
        }
        return world[modRow][modCol].getType() == Fish.FISH;
    }

    public int modCols(int col){
        if (col < 0){
            col = cols+col;
        }
        return col%cols;
    }

    public int modRows(int row){
        if (row < 0){
            row = rows+row;
        }
        return row%rows;
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

    public void printWorld(){
        for (int j=0; j<rows; j++){
            for (int i=0; i<cols; i++){
                if (world[j][i] != null){
                    System.out.print(world[j][i].getType());
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
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
