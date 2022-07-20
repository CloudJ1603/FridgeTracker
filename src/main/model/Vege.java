package model;


public class Vege extends Food {

    // construct a Vege object

    public Vege() {
        super();
    }

    @Override
    public void pickle() {
        expiry += 100;
    }

    @Override
    public void freeze() {
        expiry += 300;
    }

    @Override
    public void cook() {
        expiry += 10;
    }
}
