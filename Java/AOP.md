## "목차" <br>
1. [AOP](#aop-)
2. [AOP의 주요 개념](#aop의-주요-개념-)
3. [코드로 분석해보기](#코드로-분석해보기-)

---
## AOP <br>

Spring에서 가장 큰 특징 DI(Dependency Injection), IoC(Inversion of Controller), AOP(Aspect Orient Programming)가 있다. <br>
<br>
AOP는 OOP를 대신하는 개념이 아니라 OOP를 더욱 OOP답게 사용할 수 있도록 하는 개념이다. <br>
OOP를 사용하므로써 객체를 재사용하게 되고 개발자들은 반복되는 코드의 양을 많이 줄일 수 있었다. <br>
하지만 객체의 재사용에도 불구하고 반복되는 코드를 없앨 수 없었는데 로그, 권한체크, 인증, 예외 처리 등 필수적으로 해야하는 것들 때문에 소스에서 반복될 수 밖에 없는 부분이 있다. <br>
<br>
AOP는 이러한 문제를 해결해준다. <br>
기능을 비지니스 로직과 공통 모듈로 구분한 다음에 개발자의 코드 밖에서 원하는 시점에 비지니스 로직에 삽입하여 실행되도록 하는 것이다. <br>
즉, 개발자는 계정, 게시판, 계좌이체와 같은 기능을 만들고 공통적인 관심을 처리하는 모듈을 분리해서 개발한 후 필요한 시점에 자동으로 소스코드가 삽입되게 하는 것이다. <br>

---
## AOP의 주요 개념 <br>

#### 관점(Aspect) <br>

구현하고자 하는 횡단 관심사의 기능을 말한다.<br>
한 개 이상의 포인트 컷과 어드바이스의 조합으로 만들어진다. <br>

#### 조인포인트(JoinPoint) <br>

관점(Aspect)를 삽입하여 어드바이스가 적용될 수 있는 위치를 말한다. <br>

#### 어드바이스(Advice) <br>

관점(Aspect)의 구현체로 조인 포인트에 삽입되어 동작하는 코드이다. <br>
어드바이스는 조인포인트와 결합하여 동작하는 시점에 따라 5개로 구분한다. <br>
1. BeforeAdvice 조인포인트 전에 실행되는 Advice
2. After returnning advice 조인포인트에서 성공적으로 리턴 후 실행되는 Advice
3. After throwing advice 예외가 발생했을 경우 실행되는 Advice
4. After advice 조인포인트에서 메서드의 실행결과와 상관없이 무조건 실행되는 advice, 자바의 finally와 비슷한 역할을 한다.
5. Around Advice 조인포인트 전 과정에 수행되는 advice

#### 포인트컷(Pointcut) <br>

Advice를 적용할 조인 포인트를 선별하는 과정이나 그 기능을 정의하는 모듈이다. <br>
패턴매칭을 이용하여 어떤 조인포인트를 사용할 것인지 결정한다. <br>

#### 타겟(Target) <br>

Advice를 받을 대상, 즉 객체를 의미한다. <br>
비지니스 로직을 수행하는 클래스일수도 있지만, 프록시 객체가 될 수도 있다. <br>

---
## 코드로 분석해보기 <br>

```java
@Around("execution(* first..controller.*Controller.*(..)) or execution(* first..service.*Impl.*(..)) or execution(*first..dao.*DAO.*(..))")
```
Advice는 어노테이션이 붙은 메서드를 이용해 정의한다. <br>
@Before, @AfterRunning, @AfterThrowing, @After, @Around가 있다.<br>
<br>
그 다음으로 execution이 있는데 Pointcut 표현식 부분이다. <br>
execution(), within(), this, target, args, @target 등 등 <br>
execution()이 제일 정교한 pointcut을 만들 수 있다. <br>
<br>
Pointcut 표현 조합식을 조합할 수도 있는데 or, and, not(||, &&, !)으로 표현할 수도 있다.<br>
<br>
> 여기까지 설명에서 볼 수 있는 장점은 <br> AOP 설정을 하기 위해서는 하나의 Advice와 하나의 Pointcut을 명시해야하는데 위의 예시 코드를 보면 따로따로 정의하지 않고 어노테이션을 사용하여 Advice에 Pointcut을 직접 지정할 수 있음을 볼 수 있다.

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[김인우님의 AOP설정하](https://addio3305.tistory.com/category/Spring?page=3) <br>
