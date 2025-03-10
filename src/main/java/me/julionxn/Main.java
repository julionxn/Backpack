package me.julionxn;

import me.julionxn.solvers.BinarySolver;
import me.julionxn.solvers.Solver;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<Item> items = new ArrayList<>() {{
        add(new Item("1", 0.09, 50));
        add(new Item("2", 0.09075, 45));
        add(new Item("3", 0.005625, 40));
        add(new Item("4", 0.004, 30));
        add(new Item("5", 0.00216, 25));
        add(new Item("6", 0.000432, 20));
        add(new Item("7", 0.00225, 30));
        add(new Item("8", 0.005888, 25));
        add(new Item("9", 0.000864, 30));
        add(new Item("10", 0.001536, 25));
        add(new Item("11", 0.0005, 25));
        add(new Item("12", 0.009375, 20));
        add(new Item("13", 0.002, 40));
        add(new Item("14", 0.009375, 5));
        add(new Item("15", 0.002592, 15));
        add(new Item("16", 0.027, 35));
        add(new Item("17", 0.0015, 15));
        add(new Item("18", 0.1215, 40));
        add(new Item("19", 0.0018, 10));
        add(new Item("20", 0.0014, 10));
    }};

    public static void main(String[] args) {
        Solver binarySolver = new BinarySolver(items, 0.28);
        List<Item> best = binarySolver.calculate();
        printBest(best);
    }

    public static void printBest(List<Item> best) {
        for (Item item : best) {
            System.out.println(item);
        }
        double weight = best.stream().map(Item::weight).mapToDouble(Double::doubleValue).sum();
        int benefit = best.stream().map(Item::benefit).mapToInt(Integer::intValue).sum();
        System.out.println("Peso total: " + weight);
        System.out.println("Benefit: " + benefit);
    }

}