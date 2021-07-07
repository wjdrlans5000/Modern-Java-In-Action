package com.company.behaviorparameterization;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {
    enum Color {
        RED,
        GREEN
    }

    public static void main(String... args) {


        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        // 색을 파라미터화 [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println(greenApples);

        // 색을 파라미터화 [Apple{color=RED, weight=120}]
        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println(redApples);

        // 색을 검사하는 전략을 런타임시 실행 [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
        System.out.println(greenApples2);

        // 무게를 검사하는 전략을 런타임시 실행 [Apple{color=GREEN, weight=155}]
        List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
        System.out.println(heavyApples);

        // 빨갛고 무게가 150보다 큰 사과만 선택하는 전략을 런타임시 실행 []
        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        // 익명클래스를 사용하여 색을 검사 [Apple{color=RED, weight=120}]
        // 복잡
        List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean tests(Apple a) {
                return a.getColor() == Color.RED;
            }
        });
        System.out.println(redApples2);

        // 람다표현식을 사용하여 위 코드를 간단하게 구현
        List<Apple> ramda = filter(inventory, (Apple apple) -> Color.GREEN.equals(apple.getColor()) && apple.getWeight() > 150);
        System.out.println("람다 " + ramda);

    }

    //1. 녹색 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }

    //2. 색을 파라미터화
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }

    //3. 무게를 파라미터화
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    //프레디케이트 객체로 사과 검사조건을 캡슐화
    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.tests(apple)) {
                result.add(apple);
            }
        }
        return result;
    }




}

