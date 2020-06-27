package com.sndk2.entities;

import java.util.Random;

public class Fish {

    public static final int FISH = 1;
    public static final int SHARK = 2;
    public static int BREED_AGE = 5;

    protected World world;
    protected int row;
    protected int col;
    protected int age;
    protected int type;

    public Fish(World world, int col, int row, int age){
        this.world = world;
        this.col = col;
        this.row = row;
        this.age = age;
        type = FISH;
    }

    public void update(){
        age++;
        move();
    }

    protected void breed(){
        world.addEntity(new Fish(world, col, row, 0));
    }

    protected void move(){
        Random random = new Random();
        int[] movesX = new int[9];
        int[] movesY = new int[9];
        int counter = 0;

        for (int i=col-1; i<=col+1; i++){
            for (int j=row-1; j<=row+1; j++){
                if (world.isEmpty(i, j) && i != col && j != row){
                    movesX[counter] = i;
                    movesY[counter] = j;
                    counter++;
                }
            }
        }

        if (counter > 0){
            int move = random.nextInt(counter);
            world.moveEntity(this, movesX[move], movesY[move]);
            if (age >= BREED_AGE){
                breed();
                age = 0;
            }
            this.col = movesX[move];
            this.row = movesY[move];
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
