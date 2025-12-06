package beerTycoon.beerMakers;

public class BeerMakerFactory {
    public BeerMakerFactory() {}

    public BeerMaker getBeerMaker(BeerMakerType type) {
        return switch (type) {
            case BeerMakerType.MakeBeer -> new MakeBeer();
            case BeerMakerType.BeerDude -> new BeerDude();
            case BeerMakerType.LiquorStore -> new LiquorStore();
            case BeerMakerType.BeerSilo -> new BeerSilo();
            case BeerMakerType.BeerFactory -> new BeerFactory();
            case BeerMakerType.BeerOcean -> new BeerOcean();
            default -> new MakeBeer();
        };
    }
}

//{new MakeBeer(), new BeerDude(), new LiquorStore(), new BeerSilo(), new BeerFactory(), new BeerOcean()};