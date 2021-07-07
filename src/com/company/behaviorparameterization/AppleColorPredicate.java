package com.company.behaviorparameterization;

//초록색 사과만 선택하는 프레디케이트
//초록색 사과만 선택하는 전략
public class AppleColorPredicate implements ApplePredicate {
    @Override
    public boolean tests(Apple apple) {
        return apple.getColor() == FilteringApples.Color.GREEN;
    }
}
