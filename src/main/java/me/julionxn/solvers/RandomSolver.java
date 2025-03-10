package me.julionxn.solvers;

import me.julionxn.Item;
import me.julionxn.Option;

import java.util.*;
import java.util.stream.LongStream;

public class RandomSolver extends Solver {

    private final Random random = new Random();

    public RandomSolver(List<Item> items, int maxCapacity) {
        super(items, maxCapacity);
    }

    @Override
    public List<Item> calculate() {
        List<boolean[]> options = new ArrayList<>();
        int size = items.size();
        long iterations = factorial(size);
        for (int i = 0; i < iterations; i++) {
            boolean[] selections = new boolean[size];
            for (int j = 0; j < size; j++) {
                boolean choosen = random.nextBoolean();
                selections[j] = choosen;
            }
            options.add(selections);
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

    private List<Option> getOptions(List<Item> items, Integer capacity, List<boolean[]> options) {
        List<Option> valids = new ArrayList<>();
        for (boolean[] option : options) {
            int totalWeight = 0;
            int totalBenefit = 0;
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

    private long factorial(int n) {
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

}
