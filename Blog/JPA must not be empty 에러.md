# JPA metamodel must not be empty 에러

## 상황

> 환경  
> SpringBoot                version:2.5.4  
> spring-boot-starter-test  version: 2.5.4  

`@WebMvcTest` 애노테이션을 사용해서 컨트롤러 테스트 코드를 작성 후 실행했을 때 Application이 로드되면서 발생했다.

### 코드

```java
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService mockUserService;    // UserController 객체 안에서 주입받고 있음.

    @Test
    @DisplayName("메서드 테스트")
    void register() throw Exception {
        User.Request userDto = new User.Request().setEmail("funchcode@gmail.com").setName("정채환");
        Gson gson = new Gson();
        String body = gson.toJson(userDto);
        mvc.perform(
            post("/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        ).andExpect(status().isCreated());
    }
}
```

### Exception 메세지

```console
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaAuditingHandler': Cannot resolve reference to bean 'jpaMappingContext' while setting constructor argument; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaMappingContext': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: JPA metamodel must not be empty!
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:342)
	...
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaMappingContext': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: JPA metamodel must not be empty!
	...
Caused by: java.lang.IllegalArgumentException: JPA metamodel must not be empty!
	...
```

### 발생 원인

`@WebMvcTest(value = UserController.class)` 애노테이션은 UserController만 로드하게 한다.  
즉, JPA 빈들은 로드하지 않는다.  

Spring 컨테이너를 요구하는 테스트는 기본으로 **XXXApplication 클래스가 항상 로드**하는데, `@EnableJpaAuditing`이 해당 클래스에 등록되어 있어서 모든 테스트들이 항상 JPA 관련 Bean을 요구하여 발생하는 문제이다.

### 해결 방법

#### 1번 @MockBean 추가

`@MockBean`으로 테스트 로드 시 주입하는 방법.

```java
@WebMvcTest(value = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
    ...
}
```

#### 2번 @Configuration 분리

`XXXApplication`에 선언한 `@EnableJpaAuditing` 애노테이션을 분리한다.

```java
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}
```

## 참고

[Jinhong Github 블로그](https://xlffm3.github.io/spring%20&%20spring%20boot/JPAError/)