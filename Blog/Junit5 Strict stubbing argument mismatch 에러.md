# Junit5 Strict stubbing argument mismatch 에러

## 환경

- SpringBoot: 2.5.4
- Junit: 5.7.2

## 현상

아래의 Service Layer 테스트 로직을 작성

```java
@Entity
...
public class URIComposition {
    ...
    public static URIComposition of(Consumer consumer) {
        ...
        return new URIComposition(...);
    }
}

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTest {

    @InjectMocks
    private ServiceLayer serviceLayer;
    @Mock
    private RepositoryLayer mockRepositoryLayer;

    @Test
    void create() {
        // given
        Consumer consumer = new Consumer("a");
        URIComposition aComposition = URIComposition.of("a", ...);
        given(mockRepositoryLayer.save(aComposition))   // 문제의 코드
            .willReturn(aComposition);
        // when, then
        assertNotNull(serviceLayer.create(consumer));   // serviceLayer.create 메서드 안에서 respositoryLayer.save() 를 호출한다.
    }
}
```

위의 코드를 보면 `given()` BDDMockito 메서드를 사용하는데 보면 인자로 사용하는 객체와 반환하는 객체가 동일하다.

## 예외 내용

위에 작성된 테스트 케이스를 동작시키면 아래와 같은 예외 메세지가 발생한다.

```console
Strict stubbing argument mismatch. Please check:
 - this invocation of 'save' method:
    mockRepositoryLayer.save(
    URIComposition(name=a, ...)
);
    -> at a.b.c.ServiceLayer.create(ServiceLayer.java:27)
 - has following stubbing(s) with different arguments:
    1. mockRepositoryLayer.save(
    URIComposition(name=a, .....)
);
However, there are legit scenarios when this exception generates false negative signal:
  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API
    Please use 'will().given()' or 'doReturn().when()' API for stubbing.
  - stubbed method is intentionally invoked with different arguments by code under test
    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).
```

## 원인

선언한 `given(mockRepositoryLayer.save(aComposition))` Mock과 실제 `serviceLayer.create(consumer)` 로직이 실행하며 생성된 `aComposition`의 객체가 달라서 발생한 문제다.

Log를 보면 육안으로는 객체의 구성 내용이 동일하기 때문에 문제가 없어보이지만 내부적으로는 `@Equals` 비교를 거치는데 나의 경우 `@Equals` 오버라이딩이 안되어 있기 때문에 결국 객체의 `HashCode`를 통해 동일한지 확인한다.

## 해결 방법

직접 `@Equals`를 오버라이딩하면 되지만, 프로젝트에서 Lombok을 사용하고 있기 때문에 `@EqualsAndHashCode`를 사용했다.

```java
import lombok.*;

@Entity
@EqualsAndHashCode
...
public class URIComposition {
    ...
    public static URIComposition of(Consumer consumer) {
        ...
        return new URIComposition(...);
    }
}
```

다른 방법으로는 새로운 인스턴스를 생성되지 않도록 로직을 분리하는 방법이 있겠다.  

- 분리하기 간편하다면...