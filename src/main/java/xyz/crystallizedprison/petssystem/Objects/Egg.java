package xyz.crystallizedprison.petssystem.Objects;

import xyz.crystallizedprison.petssystem.Pet;

public class Egg {

    private final Tiers tier;
    private double Current;
    private final double Final;

    public double getCurrent() {
        return Current;
    }

    public void setCurrent(double current) {
        Current = current;
    }

    public Tiers getTier() {
        return tier;
    }

    public double getFinal() {
        return Final;
    }


    public Egg(Tiers tier, double current, double aFinal) {
        this.tier = tier;
        Current = current;
        Final = aFinal;
    }
}
