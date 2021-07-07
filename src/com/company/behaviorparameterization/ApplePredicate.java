package com.company.behaviorparameterization;

//선택조건을 결정하는 인터페이스 정의
//참 또는 거짓을 번환하는 함수를 프레디케이트라고 한다.
//사과 선택 전략을 캡슐화한(전략 디자인 패턴 - 런타임시 알고리즘을 선택)
//알고리즘 패밀리
//함수형 인터페이스 : 구현해야 할 추상 메소드가 하나만 정의된 인터페이스
@FunctionalInterface
public interface ApplePredicate {
    boolean tests(Apple a);
}
