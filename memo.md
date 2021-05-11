# 메모장  


스프링 ```@Configuration``` 어노테이션은 어노테이션 기반 __환경구성__ 을 돕는다.
  
스프링 ```@Order``` 어노테이션은 같은 타입의 빈이 컬렉션에 __Autowired__ 될 때 순서를 나타낸다.  
숫자가 __낮을__ 수록 우선순위가 __높다.__  

---

Webpack
*  오픈 소스 자바스크림트 모듈 번들러이다.  
  * 번들러는 지정한 단위로 파일들을 하나로 만들어서 요청에 대한 응답으로 전달할 수 있는 환경을 만들어주는 역할을 한다.

Babel  
* JavaScript 엔진에서 실행할 수 있는 이전 버전과 호환되는 JavaScript 버전으로 변환해주는 무료 오픈 소스 JavaScript 트랜스 컴파일러이다.

React JSX  
* React 요소는 변경 불가능하다. 특정 시간대의 UI를 보여주는 것이다.
* UI를 업데이트하는 방법은 새로운 요소를 만드는 것이며, 이 새로운 요소를 ReactDOM.render()로 전달하는 것이다.  

```yarn add react-router-dom```

---

@MappedSuperclass  
Entity 객체 클래스 중 공통 속성을 하나의 객체로 분리하여 관리하고 싶은 경우에 어노테이션을 사용한다.  
  
Mock 객체  
실제 객체를 만들어 테스트하기에 시간이나 비용, 객체 구조 상 의존성으로 인한 어려운 환경인 경우 가짜 객체(=Mock)로 대체하여 사용하는 방법을 제공하는 프레임워크이다.  
  
Junit 테스트 종류  
@SpringBootTest  
@WebMvcTest  
@DataJpaTest  
@RestClientTest  
@JsonTest  
  
AuditingEntityListener  
@CreatedDate, @LastModifiedData와 같은 편의성을 가진 어노테이션을 이용하기 위해서는 대상 엔터티 객체에 Auditing 기능 사용을 명시적으로 포함시켜야한다.  
엔터티 객체 위에 @EntityListeners(AuditingEntityListener.class) 선언  
Spring Boot의 경우 Application 클래스에 @EnableJpaAuditing을 선언해야한다.  
  
Lombok 기능 중 @Slf4j  
LoggerFactory를 통해 선언하는 방법도 있지만, 더 간단한 방법으로 Lombok 라이브러리에서 제공하는 @Slf4j 어노테이션을 선언하는 방법도 있다.  