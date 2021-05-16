# Bean Validation

어플리케이션을 개발하다보면 객체에 담긴 값이 null인지 혹은 값이 비어있는지 확인하는 로직을 반복적으로 사용하게 된다.  
이는 코드가 지저분하게 보일 수 있으며 개발 중 제약이 변경될 경우 많은 곳에서 수정이 발생할 수 있다.  
비즈니스 로직에서 데이터 유효성 검사 로직을 분리하여 개발할 수 있도록 도와주는 프레임워크가 **'Bean Validation'**이다.  

## 구현체

Bean Validation Specification을 구현한 모듈들이 존재한다.  
구현 모듈 별로 예외를 핸들링하는 처리와 같은 약간의 차이점이 있다.  
> 모듈 별 예외 핸들링 방식을 다르게 하는 것으로 인해 디버깅 삽질을 한 이력이 있으니 다음부턴 주의!

* spring-boot-starter-validation
* hibernate-validation

## 기본으로 제공하는 Annotation 정보

버전 2.0 명세서 기준으로 22개의 기본 어노테이션을 제공하고 있다.  
더 많은 정보를 확인하고 싶은 경우 [Jakarta Bean Validation Specification](https://beanvalidation.org/2.0/spec/#introduction) 사이트를 참조하자.  

1. @Null
2. @NotNull
3. @Min
4. @Max
5. @Size
6. @NotEmpty
7. @NotBlank

## 예외 모델

버전 2.0 명세서 기준으로 7개의 예외 모델을 정의하고 있다.  

1. ConstraintViolationException

## Spring Boot

아래 Dependency 정보를 선언하여 Bean Validation을 적용했었다.  
```implementation 'org.springframework.boot:spring-boot-starter-validation'```  

클라이언트에서 Controller로 보내는 요청 값을 검증하고 데이터 유효성에 맞지 않을 경우 그에 대한 응답을 한 곳에서 처리하고 싶었다.  

```java
/**
* Bean Validation 확인을 위한 컨트롤러 샘플 코드
*/
@RestController
public class Controller {
    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> register(
            // 데이터 유효성에 실패할 경우 MethodArgumentNotValidException 예외가 발생.
            @RequestBody @Valid LoginRequest requestDto
    ) {
        return ResponseEntity.ok().build();
    }
}

/**
* DTO 샘플 코드
*/
@Getter
@Setter
public class LoginRequest {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}
```

API의 Method는 POST 방식이며, `@RequestBody`를 통해 요청 값을 자동매핑 처리하도록 작성했다.  
`@Valid` 어노테이션은 Argument의 타입 앞에 선언되어 있는데 타입 내에 정의된 명세에 대한 검증을 수행한다.  

**Spring Boot에서는 데이터 유효성 검증에 실패할 경우 ```MethodArgumentNotValidException``` 예외가 발생하며 Response Code ```400```을 응답하게 되어있다.**  

> API의 Method가 POST이며, 요청 데이터를 매핑하는 방식을 ```@ModelAttribute```로 선언할 경우 **BeanPropertyBindingResult** 결과로 BindException 처리가 되어버린다.

```java
/**
* Bean Validation 확인을 위한 컨트롤러 샘플 코드
*/
@Validated
@RestController
public class Controller {
    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> register(
        // 데이터 유효성 검증에 실패한 경우 ConstraintViolationException 예외 발생.
        @RequestParam("cnt") @Min(5) int cnt
    ) {
        return ResponseEntity.ok().build();
    }
}

/**
* Controller에서 발생한 예외를 Catch하여 처리하는 코드
*/
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    protected ResponseEntity<?> handleException(ConstraintViolationException constraintViolationException) {
        return ResponseEntity
                .badRequest()
                .build();
    }
}
```

* `@Validated`: 매개 변수에 대한 데이터 유효성 검증을 수행하기 위해 Spring에 작업을 지시
* `@RestControllerAdvice`: RestController 전역에서 발생할 수 있는 예외를 잡아 처리하기 위한 어노테이션.

## Reference

* 🔗 [Jakarta Bean Validation 명세서](https://beanvalidation.org/2.0/spec/#introduction)
* 🔗 [NHN 블로그: Bean Validation 기능 소개 및 사용방법](https://meetup.toast.com/posts/223)
* 🔗 [Bean Validation - Spring Boot 적용에 대한 글](https://reflectoring.io/bean-validation-with-spring-boot/)