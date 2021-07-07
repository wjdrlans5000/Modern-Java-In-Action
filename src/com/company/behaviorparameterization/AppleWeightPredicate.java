package com.company.behaviorparameterization;

//150보다 무거운 사과만 선택하는 프레디케이트
//무거운 사과만 선택하는 전략
public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean tests(Apple apple) {
        return apple.getWeight() > 150;
    }
}
