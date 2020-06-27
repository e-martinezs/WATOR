package com.sndk2.entities;

import com.sndk2.Variables;

import java.util.Random;

public class Shark extends Fish {

    private int energy;

    public Shark(World world, int col, int row, int age, int energy) {
        super(world, col, row, age);
        this.energy = energy;
        type = SHARK;
    }

    @Override
    public void update(){
        super.update();
        loseEnergy();
    }

    private void loseEnergy(){
        energy -= Variables.ENERGY_DRAIN_SHARK;
        if (energy <= 0){
            world.removeEntity(this);
        }
    }

    @Override
    protected void breed(){
        if (age >= Variables.BREED_AGE_SHARK) {
            world.addEntity(new Shark(world, col, row, 0, Variables.MAX_ENERGY_SHARK));
            age = 0;
        }
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
            breed();
            this.setCol(movesX[move]);
            this.setRow(movesY[move]);
            this.energy += Variables.FISH_EAT_ENERGY;
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
