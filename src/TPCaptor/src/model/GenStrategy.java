package model;

public interface GenStrategy {

    public default double generate() {
        return (int) (Math.random() * (100 + 1));
    }

}
