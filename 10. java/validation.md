데이터 유효성 검사 로직은 애플리케이션 전체에 분산되어 있다.  
코드 중복이 나타난다.  

데이터 유효성 검사의 이런 이슈로 인해 Java에서는 2009년부터 Bean Validation이라는 데이터 유효성 검사 프레임워크를 제공하고 있다.  

Java에서 'jakarta bean validation'의 Specification을 사용할 수 있다.  
이 Specification을 구현한 모듈이 'Hibernate Validator'이며, 해당 모듈 안에 jakarta-bean-validation이 포함되어 있다.  

# Exception Log
'javax.validation.constraints.NotEmpty' validating type 'java.lang.Integer'  
@NotEmpty는 문자열과 컬렉션 검사를 위한 어노테이션이다.  
Integer의 경우 @NotNull으로 대체하여 사용할 수 있다.  

# MethodArgumentNotValidException  
Controller 클래스의 메서드에 @RequestBody와 @Valid 어노테이션을 선언할 경우 발생하는 예외다.  
Spring의 경우 해당 예외가 발생하면 기본적으로 400 응답 코드를 뱉어낸다.  
> @Valid 어노테이션을 선언한 곳에서 Validation 예외가 발생할 경우 ConstraintViolationException 예외만 발생하는 것으로 착각했었다.  

# ConstraintViolationException 
메서드 파라미터나 리턴 값에 Validation 에러가 발생할 경우 ConstraintViolationException 예외가 발생한다.  
Spring의 경우 해당 예외가 발생하면 기본적으로 500 응답 코드를 뱉어낸다.  

## 참고링크  
[https://kapentaz.github.io/spring/Spring-Boo-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#](https://kapentaz.github.io/spring/Spring-Boo-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#)  
> Bean Validation 관련된 내용이 잘 정리되어 있는 블로그다.  
