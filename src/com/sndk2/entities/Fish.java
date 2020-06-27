package com.sndk2.entities;

public class Fish {

    protected static int breedAge;
    protected World world;
    protected int row;
    protected int col;
    protected int age;

    public Fish(World world, int row, int col, int age){
        this.world = world;
        this.row = row;
        this.col = col;
        this.age = age;
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
}
