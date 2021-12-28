# Async

---

## Async

Asynchronous Communication 비동기 통신

## 순차 프로그래밍, 병렬 프로그래밍

### 순차 프로그래밍

수행 시간이 1초 걸리는 메서드를 100번 호출 시 100초가 걸린다.

### 병렬 프로그램밍

수행 시간이 1초 걸리는 메서드를 100개의 쓰레드에 동시에 할당하여 실행하면 1초가 걸린다.

## Spring Boot

Spring의 `@Async`는 AOP에 의해 동작한다.

### 사용방법

#### `@EnableAsync` 

어노테이션 선언을 통해 비동기 로직을 사용함을 암시한다.

#### `@Async`

비동기로 동작하길 원하는 메서드 위에 선언한다.

### AOP 내부 로직

`@Async` 어노테이션을 처리하는 AOP 내부 로직에는 __리턴__ 타입에 따라 4가지 케이스가 구현되어 있다.

1. CompletableFuture
2. ListenableFuture
3. Future
4. void

### 리턴타입: void

호출한 쪽에서 기대하는 리턴 값이 없기 때문에 다른 작업을 바로 수행할 수 있다. (__논블로킹__)

### AOP 구현체

Spring은 기본적으로 `SimpleAsyncTaskExecutor` 구현체를 이용한다.

#### SimpleAsyncTaskExecutor

해당 구현체는 호출할 때마다 새로운 스레드가 생성된다.

기본 구현체를 재정의하는 방법으로 __Application 레벨__, __Method 레벨__ 수준에서 재정의 할 수 있다.

- Application
  - AsyncConfigurer 인터페이스를 구현(implements)
  - 기본 설정을 재정의하는 것이므로 `@Async`를 선언한 메서드는 재정의한 Executor로 동작한다.

- Method
  - `@Configuration`을 지정하여 Bean으로 등록한다.
  - Bean name을 주는 경우 `@Async('bean-name')` 선언을 통해 구현체를 이용가능하다.