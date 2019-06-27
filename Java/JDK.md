### "INDEX"

1. [Lambda Expressions](#lambda-expressions)

---

# JDK 8

### Lambda Expressions

자바에서 **함수적 프로그래밍** 을 지원하기 위해 Lambda Expressions이 나왔다.
람다식이란 **식별자없이 실행가능한 함수** 를 의미한다.

**람다식 사용방법**

```JAVA
(매개변수, ...) -> { 실행문... }
```

매개변수는 중괄호 `{}` 블록을 실행하기 위해 필요한 값을 제공하는 역할을 한다.

<BR>

**Functional Interface**

```JAVA
@FunctionalInterface
interface Attack {
  int hit(int damage);
}
```

FunctionalInterface란 함수가 하나만 존재하는 인터페이스라는 의미를 나타낸다.

<BR>

**장점**

- 코드가 간결하고 식에 개발자의 의도가 명확히 드러내므로 가독성이 향상된다.
- 함수를 만드는 과정없이 한번에 처리할 수 있기에 코딩시간이 줄어든다.

**단점**

- 만든 무명함수의 재사용이 불가능하다.
- 코드가 지저분해질 수 있다.
