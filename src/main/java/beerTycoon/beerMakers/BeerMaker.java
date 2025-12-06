package beerTycoon.beerMakers;

public abstract class BeerMaker {
    protected double cost;
    protected double production;
    protected String name;
    protected boolean upgradable;

    public BeerMaker(String name, double cost, double production) {
        this.name = name;
        this.cost = cost;
        this.production = production;
        this.upgradable = true;
    }

    public boolean isUpgradable() {
        return upgradable;
    }

    public double getUpgradeCost() {
        return cost * 0.25;
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