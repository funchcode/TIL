### "INDEX"

1. [Generic이란](#generic이란)
2. [Generic 사용법](#generic-사용법)
3. [Generic Wildcard 사용법](#generic-wildcard-사용법)

---

## Generic이란

Generic의 사전적 의미는 **일반적인** 이란 뜻을 가진 단어이다.
일반적인 코드를 작성하고 이 코드를 **다양한 타입의** 객체에 대해서 **재사용** 하는 프로그래밍 기법이다.
클래스에서 **사용할 타입을 클래스 외부에서 설정** 하는 타입이다.
주로 Java Collection에서 많이 사용된다.

---

## Generic 사용법

**Interface에서 Generic 사용법**

```java
// Generic 2개 사용한 인터페이스
interface InterfaceSample<T1, T2> {
    T1 testMethod1(T2, t);
    T2 testMethod2(T1, t);
}

// 인터페이스 구현
class TestSampleInterfaceImp implements InterfaceSample<String, Integer> {
    @Override
    public String testMethod1(Integer t) {
        return null;
    }

    @Override
    public Integer testMethod2(String t) {
        return null;
    }
}
```

**Method에서 Generic사용법**

```java
// 메소드의 파라미터에 <T>가 선언되면 리턴타입 앞에 <T>를 선언해야한다.
class TestMethod {
    public static <T> List<T> method(List<T> list, T item) {
        list.add(item);
        return list;
    }
}
```

**메소드의 파라미터에 <T>가 선언되면 리턴타입 앞에 <T>를 선언해야한다.**

---

## Generic WildCard 사용법

**Unbounded Wildcards <?>**

- 모든 타입을 인자로 받을 수 있음을 말한다. 이 방법이 두가지 유효한 사용법이 있다.
- [1] Object클래스에서 제공되는 기능만을 사용할 경우
- [2] 제네릭을 사용한 클래스의 메소드들 중에 List.size, List.clear처럼 타입 파라미터에 의존하지 않는 메소드들만을 사용할 경우

**Upper Bounded Wildcards <? extends 타입>**

- ```<? extends Number>``` 지정 시 Number타입 또는 상속받는 클래스들 Integer, Double 타입으로 제한된다.

**Lower Bounded Wildcards <? super 타입>**

- ```<? super Integer>``` 지정 시 Integer타입 또는 상위 클래스인 Number, Object타입으로 제한된다.
