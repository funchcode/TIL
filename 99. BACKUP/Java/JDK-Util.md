# JDK 유틸 정리

## Supplier<T>

> java.util.function.Supplier

Optional 클래스의 `orElseThrow` 메서드를 보다가 궁금해졌다.  
아래는 `Supplier<T>` 인터페이스 내부 코드다.

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

로직만 봤을 때 `get()` 메서드가 존재하는데, 특징으로 매개변수를 받지 않고 무언가를 반환하고 있다.  
제네릭 타입으로 정의된 타입으로 어떠한 값을 리턴할 수 있다.  

다시 Optional 클래스의 `orElseThrow` 메서드를 보면,

```java
public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (value != null) {
        return value;
    } else {
        throw exceptionSupplier.get();
    }
}
```

value의 값이 Null인 경우 넘겨받은 예외 타입으로 지정된 Supplier 객체로 인스턴스를 가져와 예외를 발생시킨다.
