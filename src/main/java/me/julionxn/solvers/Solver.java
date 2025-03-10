package me.julionxn.solvers;

import me.julionxn.Item;

import java.util.List;

public abstract class Solver {

    protected final List<Item> items;
    protected final int maxCapacity;

    public Solver(List<Item> items, int maxCapacity) {
        this.items = items;
        this.maxCapacity = maxCapacity;
    }

    public abstract List<Item> calculate();

}
