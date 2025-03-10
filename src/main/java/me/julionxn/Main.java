package me.julionxn;

import me.julionxn.solvers.BinarySolver;
import me.julionxn.solvers.Solver;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Item> items = new ArrayList<>() {{
        add(new Item("1", 20, 50));
        add(new Item("2", 25, 35));
        add(new Item("3", 20, 45));
        add(new Item("4", 20, 20));
        add(new Item("5", 35, 35));
        add(new Item("6", 40, 40));
        add(new Item("7", 35, 50));
        add(new Item("8", 30, 50));
        add(new Item("9", 35, 45));
        add(new Item("10", 25, 40));
    }};

    public static void main(String[] args) {
        Solver binarySolver = new BinarySolver(items, 120);
        List<Item> best = binarySolver.calculate();
        printBest(best);
    }

    public static void printBest(List<Item> best) {
        for (Item item : best) {
            System.out.println(item);
        }
        int weight = best.stream().map(Item::weight).mapToInt(Integer::intValue).sum();
        int benefit = best.stream().map(Item::benefit).mapToInt(Integer::intValue).sum();
        System.out.println("Peso total: " + weight);
        System.out.println("Benefit: " + benefit);
    }

}