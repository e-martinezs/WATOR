package com.sndk2.entities;

public class Shark extends Fish {

    public static int FISH_ENERGY = 1;
    public static int MAX_ENERGY = 5;
    private int energy;

    public Shark(World world, int col, int row, int age, int energy) {
        super(world, col, row, age);
        this.energy = energy;
        type = SHARK;
    }

    @Override
    public void update(){
        super.update();
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
