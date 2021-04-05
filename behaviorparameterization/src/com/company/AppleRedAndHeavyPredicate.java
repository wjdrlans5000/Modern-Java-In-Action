package com.company;

//빨갛고 무게가 150보다 큰 사과만 선택하는 프레디케이트
//빨갛고 무게가 150보다 큰 사과만 선택하는 전략
public class AppleRedAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean tests(Apple apple) {
        return apple.getColor() == FilteringApples.Color.RED && apple.getWeight() > 150;
    }

}
