# 메모장  

스프링 ```@Configuration``` 어노테이션은 어노테이션 기반 __환경구성__ 을 돕는다.

---  

스프링 ```@Order``` 어노테이션은 같은 타입의 빈이 컬렉션에 __Autowired__ 될 때 순서를 나타낸다.  
숫자가 __낮을__ 수록 우선순위가 __높다.__  

---

HttpBasicConfigurer<HttpSecurity>


HttpBasicConfigurer
 AbstractHttpConfigurer


AbstractHttpConfigurer
 SecurityConfigurerAdapter


HttpSecurity
 AbstractConfiguredSecurityBuilder
 SecurityBuilder
 HttpSecurityBuilder


DefaultSecurityFilterChain
 SecurityFilterChain

