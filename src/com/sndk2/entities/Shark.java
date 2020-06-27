package com.sndk2.entities;

public class Shark extends Fish {

    private static int fishEnergy;
    private int energy;

    public Shark(World world, int row, int col, int age, int energy) {
        super(world, row, col, age);
        this.energy = energy;
        type = SHARK;
    }

    @Override
    public void update(){
        super.update();
    }

    public static int getFishEnergy() {
        return fishEnergy;
    }

    public static void setFishEnergy(int fishEnergy) {
        Shark.fishEnergy = fishEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
