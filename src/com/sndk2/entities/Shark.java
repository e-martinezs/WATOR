package com.sndk2.entities;

import java.util.Random;

public class Shark extends Fish {

    public static int FISH_ENERGY = 1;
    public static int MAX_ENERGY = 5;
    public static int ENERGY_DRAIN = 1;
    private int energy;

    public Shark(World world, int col, int row, int age, int energy) {
        super(world, col, row, age);
        this.energy = energy;
        type = SHARK;
    }

    @Override
    public void update(){
        loseEnergy();
        super.update();
    }

    private void loseEnergy(){
        energy -= ENERGY_DRAIN;
        if (energy <= 0){
            world.removeEntity(this);
        }
    }

    @Override
    protected void breed(){
        world.addEntity(new Shark(world, col, row, 0, MAX_ENERGY));
    }

    @Override
    protected void move(){
        Random random = new Random();
        int[] movesX = new int[9];
        int[] movesY = new int[9];
        int counter = 0;

        for (int i=col-1; i<=col+1; i++){
            for (int j=row-1; j<=row+1; j++){
                if (world.hasFish(i, j) && i != col && j != row){
                    movesX[counter] = i;
                    movesY[counter] = j;
                    counter++;
                }
            }
        }

        if (counter > 0){
            int move = random.nextInt(counter);
            world.removeEntityAt(movesX[move], movesY[move]);
            world.moveEntity(this, movesX[move], movesY[move]);
            if (age >= BREED_AGE){
                breed();
                age = 0;
            }
            this.col = movesX[move];
            this.row = movesY[move];
            this.energy += FISH_ENERGY;
        }else{
            super.move();
        }
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
