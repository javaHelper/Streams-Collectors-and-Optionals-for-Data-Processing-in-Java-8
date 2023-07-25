package org.example.sec04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUsingOptional {
    public static void main(String[] args) {
        List<Double> result = new ArrayList<>();

        Function<Double, Stream<Double>> flatMapper = d -> NewMath.inv(d)
                .flatMap(inv -> NewMath.sqrt(inv))
                .map(sqrt -> Stream.of(sqrt))
                .orElseGet(() -> Stream.empty());

        List<Double> rightResult = ThreadLocalRandom.current()
                .doubles(10_000)
                .parallel()
                .map(d -> d * 20 - 10)
                .boxed()
                .flatMap(flatMapper)
                .collect(Collectors.toList());

        System.out.println("# rightResult = " + rightResult.size());
    }
}