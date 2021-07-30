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
  
- 한개의 메소드 호출은 중괄호로 감쌀 필요가 없음
    - process(() -> System.out.println("lambda"));

- @FunctionalInterface
    - 함수형 인터페이스임을 가리키는 어노테이션
    - @FunctionalInterface를 선언했지만 실제로 함수형 인터페이스가 아니면 컴파일러가 에러 발생

- 함수형 인터페이스 사용
    - 함수형 인터페이스는 오직 하나의 추상메서드를 지정
    - 함수형 인터페이스의 추상 메서드는 람다표현식의 시그니처를 묘사한다.
    - 함수형 인터페이스의 추상 메서드 시그니처를 함수 디스크립터 라고 한다.
    - 자바 API의 대표적인 함수형 인터페이스
        - Comparable,Runnable, Callable등
    - java.util.function 패키지 내의 여러 함수형 인터페이스
        - function 패키지에서 지원하고 있는 함수형 인터페이스는 별도에 인터페이스 정의 없이 바로 사용할 수 있다.
        - Predicate
            - test라는 추상 메서드를 정의하며 제네릭 형식 T의 객체를 인수로 받아 불리언을 반환
            - 자바 독 명세를 보면 AND 나 OR, NOT 같은 메서드도 있음을 알수 있다.
        - Consumer
            - accept라는 추상 메서드를 정의하며 제네릭 형식 T의 객체를 인수로 받아 void를 반환
            - T형식의 객체를 인수로 받아서 특정 동작을 수행하고 싶을때 사용할수 있다.
        - Function
            - apply라는 추상 메서드를 정의하며 제네릭 형식 T의 객체를 인수로 받아서 제네릭 형식 R객체를 반환
            - 입력을 출력으로 매핑하는 람다를 정의할때 사용할수 있다.
        - 기본형 객체를 입출력으로 사용할때 오토박싱 동작을 피할수 있도록 제공해주는 함수형 인터페이스
            - IntPredicate, DoubleConsumer,LongBinaryOperator,IntFunction 등 처럼 인터페이스 이름 앞에 형식명이 붙는다.
        
- 지역 변수 사용
    - 람다표현식에서는 익명함수가 하는 것처럼 자유변수(파라미터로 넘겨진 변수가 아닌 외부에서 정의된 함수)를 활용
    할수 있다. 이와같은 동작을 람다 캡처링 이라고한다.
    - 자유변수의 제약
        - 람다는 인스턴스변수와 정적변수를 자유롭게 캡처할수 있다.
        - 지역변수는 명시적으로 final로 선언되거나 final로 선언된 변수와 똑같이 사용되어야 한다.
        - 즉, 한번만 할당할수 있는 지역변수를 캡처할수 있다.
        ```java
          int portNumber = 1337;
          Runnable r = () -> System.out.println(portNumber);
          portNumber = 31137; // <- 변수를 재 할당하고 있으므로 컴파일할수 없는 코드
        ```
        - 인스턴스 변수는 힙에 저장되는 반면 지역변수는 스택에 위치한다. 람다가 스레드에서 실행된다면,
        변수를 할당한 스레드가 사라져서 변수할당이 해제되었는데도 람다를 실행하는 스레드에서는 해당 변수에 접근하려고 할수있다.
        따라서, 자바 구현에서는 원래 변수에 접근을 허용하는것이 아니라 자유 지역번수의 복사본을 제공한다.
        따라서, 복사본의 값이 바뀌지 않아야하므로 지역변수에는 한번만 값을 할당해야 한다는 제약이 생긴것이다. 
        - 지역변수의 제약때문에 외부 변수를 변환시키는 일반적인 명령형프로그래밍 패턴에 제동을 걸수 있다.
        