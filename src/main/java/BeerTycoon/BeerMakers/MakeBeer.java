package BeerTycoon.BeerMakers;

public class MakeBeer implements BeerMaker{
    private double beer = 1;
    private double cost = 0;
    private String name = "Make Beer";

    public double makeBeer() {
        return beer;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
