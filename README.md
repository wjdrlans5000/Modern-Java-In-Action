# 모던자바 인 액션

- CHAPTER2 - 동작 파라미터화 코드 전달하기
  - 동작 파라미터화(behavior parameterization)을 이용하면 자주 바뀌는 요구사항에 효과적으로 대응할 수 있다.
  - 동작 파라미터화란 아직은 어떻게 실행할 것인지 결정하지 않은 코드블록을 의미.
  - 익명클래스 : 이름이 없는 클래스, 클래스 선언과 인스턴스화를 동시에 할수 있다. 즉, 즉석에서 필요한 구현을 만들어 사용할수 있음.
  - 람다표현식을 사용하여 더 간단하게 구현할수 있음.

- CHAPTER3 - 람다표현식
  - 람다표현식은 익명클래스처럼 이름이 없는 함수면서 메서드를 인수로 전달할수 있으므로 익명클래스와 비슷
  - 즉, 메서드로 전달할수 있는 익명함수를 단순화한것.
  
- 기존 코드(익명클래스 사용)
```java
Comparator<Apple> byWeight = new Comparator<Apple>(){
  public int compare(Apple a1, Apple a2){
    return a1.getWeight().compareTo(a2.getWeight());
    }
}
```
- 람다 코드
```java
Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```
- 람다 표현식에는 return이 함축되어 있으므로 return 문을 명시적으로 사용하지 않아도 된다.
- 람다 표현식(expression)
  - (param) -> "expression"
  - (param) -> 42
- 람다 구문(statement)
  - (param) -> { return "statements"; }
  - (param) -> { return "statements" + i; }
