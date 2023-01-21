package model;

import java.util.Random;

public class GenBoundedRandom implements GenStrategy {

    private double min;
    private double max;

    public GenBoundedRandom(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public double generate() {
        Random random = new Random();
        return random.nextDouble(min, max);
    }

}
