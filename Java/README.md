## 주말을 이용하여 Github Blog에 꼭 정리하자.
> <h3>규칙</h3>
> 1. **Java** 관련 대략적인 개념 정리를 해 놓는 창고이다.<br/>
> 2. <span style="color:red"><b><u>밑줄</u></b></span> : 좀 더 학습할 필요가 있는 주제(블로그에 정리)<br/>
> 3. 최신 이력을 상단에 노출<br/>
---

> <span style="color:red"><h3><u>Thread</u></h3></span>

동작하고 있는 프로그램을 프로세스라고 한다.<br/>
보통 한 개의 프로세스는 한 가지의 일을 하지만, 스레드를 이용하면 한 프로세스 내에서 두 가지 또는 그 이상의 일을 동시에 할 수 있다.<br/>
프로세스는 작업의 단위이면, 스레드는 실행의 단위이다.<br/>
방법 1 : `public class Test extends Thread`<br/>
방법 2 : `public class Test implements Runnable`<br/>
스레드는 순서에 상관없이 동시에 실행된다.<br/>
**실행단위 :** New > Runnable-Blocked-Running > Dead<br/>

---
> <span style="color:red"><h3><u>입출력 스트림</u></h3></span>

**스트림 :** 자바에서 입출력과 같이 데이터를 주고 받는데 사용되는 연결통로를 의미한다.
	- 스트림은 하나의 스트림으로 입력/출력을 동시에 처리할 수 없기 때문에 두개의 스트림이 필요하다.
**보조스트림 :** 스트림으로 단독으로 사용할 수 없고, 스트림의 기능을 향상시키거나 새로운 기능을 추가할 수 있는 말 그대로 보조스트림이다.
	- 보조스트림을 사용했을 경우, 성능차이가 상당하기 때문에 대부분 사용한다.
자바는 C언어와 달리 문자를 의미하는 char형이 2byte이기 때문에 바이트 스트림으로 문자를 처리하는데 어려움이 있다.

---
# Spring Framework 정리
- IoC와 DI를 가능케 해주는 모듈
	- " spring-context "

## IoC (Inversion of Control 제어의 역전)
- MetaData["XML","Java Code","Java Config"], Java PoJo class
	- ApplicationContextContainer
	- BeanFactoryContainer
- 역할
	- Bean 생명주기 관리
	- DI Pattern 제공
	- 비즈니스 로직에 집중
- Bean객체들을 물리적 공간이 아닌 논리적으로 관리한다.
	- 즉, 메모리에 상주한다는 의미이다.
	- JVM에 의해 관리되는 공간에 있다.
- DI 지원
	- 사용객체, 의존 객체 모두 Bean객체로 등록되어야 사용가능하다.
	- Bean 대상이 되는 객체는 반드시 빈 생성자가 필요하다.

## DI (Dependency Injection)
- DI는 Pattern의 한 종류이다.
- **@Configuration**
	- 설정 메타정보를 포함한 Class라고 선언한 애노테이션
- **@Bean(name = "")**
	- 설정 메타정보를 정의한 Class에서 @Bean 애노테이션을 등록한 메서드의 return 클래스를 Bean으로 등록하겠다는 의미이다.
	- 각 각의 메서드는 name="" 속성값을 이용해 지정할 수 있다.
- Spring DI 동작
	- AnnotationConfigApplicaion(ApplicationConfig.class)으로 ApplicationContext의 인스턴스를 만든다.
	- 인스턴스.getBean("", class)를 통해 원하는 클래스를 조회한다.

## Bean
- IoC Container에 저장된 객체를 Bean이라고 한다.
- Bean을 제대로 알려면 IoC Container를 선행학습하자.
- Bean을 등록하는 방법 (*Need코드 학습 필요*)
	- @Bean
	- @ComponentScan
	- @Autowired
		- 의존관계 자동 설정 (생성, 필드, 메서드)
		- 빈 객체에 연결한다.
	- @Qualifier
		- 동일한 빈 객체를 두 개 이상 정의했을 때 사용(*Need학습 더 필요*)
	- @Resource
		- 이름을 기준으로 찾는다(빈 객체에 연결)
	- @Inject
		- 의존 설정 필요
		- Framework에 종속되지 않을 때 사용하면 좋다(*Need왜 일까 학습*)
	- @Named
		- 사용할 빈 이름을 지정.
		- @Qualifier과 동일한 기능을 한다.

## ConponentScan(basePackages)
- Java 애노테이션은 다른 애노테이션을 붙여 사용 가능하다.
	- Service, Repository, Controller 등
> [basePackages]
>  설정 시에 하위 클래스(@Component)를 모두 Bean으로 등록

> **의존의 문제점**
> A Object가 B Object에 의존해 사용하는 관계일 때<br/>
> 의존성이 높은 경우에는 B Object에 로직이나 메서드 명이 변경될 시에 A Object에도 영향을 미치게 된다.<br/>
> '어떻게 의존성을 줄일 수 있을까'라는 고민에서 시작된 패턴 중 하나가 DI이다 [*다른 패턴들은 뭐가 있을까(나중에 정리)*]

.


### 도움을 받은 사이트 & 블로그
> [SpringFramework](https://www.tutorialspoint.com/)(튜토리얼)<br/>
> [SLiPP WiKi](https://www.slipp.net/wiki/pages/viewpage.action?pageId=25527606)(SpringFramework)