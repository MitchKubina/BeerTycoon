package BeerTycoon.BeerMakers;

public class BeerMakerFactory {
    public BeerMakerFactory() {}

    public BeerMaker getBeerMaker(String name) {
        return switch (name) {
            case "Make Beer" -> new MakeBeer();
            case "Beer Dude" -> new BeerDude();
            case "Liquor Store" -> new LiquorStore();
            case "Beer Silo" -> new BeerSilo();
            case "Beer Factory" -> new BeerFactory();
            case "Beer Ocean" -> new BeerOcean();
            default -> new MakeBeer();
        };
    }
}

//{new MakeBeer(), new BeerDude(), new LiquorStore(), new BeerSilo(), new BeerFactory(), new BeerOcean()};