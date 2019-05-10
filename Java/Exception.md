## "목차" <br>
1. [Exception](#exception-)
2. [RuntimeException](#runtimeexception-)
3. [예외처리방법](#예외처리방법)
4. [예외복구](#예외복구)
5. [예외처리회피](#예외처리회피)
6. [예외전환](예외전환)

---
## Exception <br>

**Error, Exception**이 있는데 Error 같은 경우는 시스템에 비정상적인 상황이 발생했을 경우를 말한다. <br>
개발자가 미리 예측하여 처리할 수 없기 때문에 애플리케이션에서 오류에 대한 처리를 신경쓰지 않아도 된다. <br>
Exception은 개발자가 구현한 로직에서 발생한다. <br>
즉, 예외는 개발자가 발생활 상황을 미리 알고 처리할 수 있다. <br>
<br>
모든 예외클래스는 Throwable 클래스를 상속받고 있으며 Throwable은 최상위 객체인 Oject의 자식클래스이다. <br>

---
## RuntimeException <br>

Exception은 수많은 자식 클래스를 가지고 있지만 RuntimeException을 주목해야 한다. <br>
CheckedException과 UncheckedException을 구분하는 기준이 되기 때문이다. <br>
RuntimeException과 그 자식 클래스는 UncheckedException이고 나머지 Exception은 CheckedException이다. <br>
<br>
UncheckedException과 CheckedException의 구분하는 기준은 "꼭" 처리해야하는가에 있다. <br>
CheckedException는 반드시 로직을 try/catch로 감싸거나 throw로 던져서 처리해야한다. <br>
반면에 UncheckedException은 명시적인 예외처리를 하지 않아도 된다. <br>
<br>
예외를 확인할 수 있는 시점에서도 구분할 수 있다. <br>
컴파일 단계에서 명확하게 Exception체크가 가능한 것은 CheckedException이고 실행과정 중 특정 논리에 의해 발견되는 Exception을 UncheckedException이라고 한다. <br>
컴파일 단계에서 확인할 수 없다고 해서 UncheckedException이라고하며, 실행과정 중 발견된다고 해서 RuntimeException이라 하는 것이다. <br>

---
## 예외처리방법 <br>

1. 예외복구
2. 예외처리 회피
3. 예외 전환

---
## 예외복구 <br>

```java
int maxretry = MAX_RETRY;
while(maxretry!=0) {
    try {
        // 예외가 발생할 수 있는 작업
        return; // 작업 성공 시
    } catch (SomeException e) {
        // 로그 출력
    } finally {
        // 리소스 반납 및 정리 작업
    }
}
throw new RetryFailedException(); // 최대 재시도 횟수를 초과하면 예외를 발생시킨다.
```
예외복구의 핵심은 예외가 발생하더라도 애플리케이션은 정상적인 흐름으로 동작한다는 것을 말한다. <br>

---
## 예외처리 회피 <br>

```java
public void add() throws SQLException {
    // 구현로직
}
```
예외가 발생하면 호출한 쪽으로 예외를 던지고 그 처리를 회피하는 것을 말한다. <br>
호출한 쪽에서 예외를 받아서 처리해야한다. <br>

---
## 예외 전환 <br>

```java
catch(SQLException e) {
    throw DuplicateUserIdException();
}
```
예외를 잡아서 다른 예외를 던지는 것을 말한다. <br>
어떤 예외인지 분명해야 처리하기 수월하기 때문이다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[nextree님의 Java 예외(Exception) 처리에 대한 작은 생각](http://www.nextree.co.kr/p3239/) <br>    