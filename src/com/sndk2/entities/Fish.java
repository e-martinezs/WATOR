package com.sndk2.entities;

public class Fish {

    public static final int FISH = 1;
    public static final int SHARK = 2;
    protected static int breedAge;
    protected World world;
    protected int row;
    protected int col;
    protected int age;
    protected int type;

    public Fish(World world, int row, int col, int age){
        this.world = world;
        this.row = row;
        this.col = col;
        this.age = age;
        type = FISH;
    }

    public void update(){

    }

    public static int getBreedAge() {
        return breedAge;
    }

    public static void setBreedAge(int breedAge) {
        Fish.breedAge = breedAge;
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
