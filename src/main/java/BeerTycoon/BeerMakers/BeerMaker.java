package BeerTycoon.BeerMakers;

public abstract class BeerMaker {
    protected double cost;
    protected double production;
    protected String name;

    public BeerMaker(String name, double cost, double production) {
        this.name = name;
        this.cost = cost;
        this.production = production;
    }

    // Default constructor for decorators or special subclasses
    public BeerMaker() {}

    public double getCost() {
        return cost;
    }

    public double makeBeer() {
        return production;
    }

    public String getName() {
        return name;
    }
}