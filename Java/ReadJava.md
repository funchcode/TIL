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
> A Object가 B Object에 의존해 사용하는 관계일 때\
> 의존성이 높은 경우에는 B Object에 로직이나 메서드 명이 변경될 시에 A Object에도 영향을 미치게 된다.\
> '어떻게 의존성을 줄일 수 있을까'라는 고민에서 시작된 패턴 중 하나가 DI이다 [*다른 패턴들은 뭐가 있을까(나중에 정리)*]

.


### 도움을 받은 사이트 & 블로그
> [SpringFramework](https://www.tutorialspoint.com/)(튜토리얼)
> [SLiPP WiKi](https://www.slipp.net/wiki/pages/viewpage.action?pageId=25527606)(SpringFramework)