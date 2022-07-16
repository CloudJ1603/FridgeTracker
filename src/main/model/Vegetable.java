package model;

public class Vegetable implements process {
    private String name;
    private int packedDate; // how to represent date ??
    private int expireDate;
    private int weight; // in kg

    @Override
    public void pickle() {
        expireDate = 9999;
    }

    @Override
    public void freeze() {
        expireDate = 9999;
    }

    @Override
    public void cook(){

    }
}
