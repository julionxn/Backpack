package me.julionxn.solvers;

import me.julionxn.Item;
import me.julionxn.Option;

import java.util.*;

public class BinarySolver extends Solver {

    public BinarySolver(List<Item> items, double maxCapacity) {
        super(items, maxCapacity);
    }

    @Override
    public List<Item> calculate() {
        int itemsSize = items.size();
        int iterations = pow(itemsSize);
        List<boolean[]> options = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            boolean[] option = new boolean[itemsSize];
            for (int j = 0; j < itemsSize; j++) {
                option[j] = (i & (1 << j)) != 0;
            }
            options.add(option);
        }
        List<Option> valids = getOptions(items, maxCapacity, options);
        Optional<Option> max = valids.stream().max((o1, o2) -> {
            if (Objects.equals(o1.benefit(), o2.benefit())) return 0;
            if (o1.benefit() < o2.benefit()) return -1;
            return 1;
        });
        if (max.isPresent()){
            Option bestOption = max.get();
            boolean[] choose = bestOption.choose();
            List<Item> best = new ArrayList<>();
            for (int i = 0; i < choose.length; i++) {
                boolean b = choose[i];
                if (b){
                    best.add(items.get(i));
                }
            }
            return best;
        }
        return null;
    }

    private int pow(int b) {
        return (int) Math.pow(2, b);
    }

    private List<Option> getOptions(List<Item> items, Double capacity, List<boolean[]> options) {
        List<Option> valids = new ArrayList<>();
        for (boolean[] option : options) {
            double totalWeight = 0;
            double totalBenefit = 0;
            for (int i = 0; i < option.length; i++) {
                boolean b = option[i];
                if (b) {
                    Item item = items.get(i);
                    totalWeight += item.weight();
                    totalBenefit += item.benefit();
                }
            }
            if (totalWeight <= capacity){
                valids.add(new Option(option, totalWeight, totalBenefit));
            }
        }
        return valids;
    }

}
