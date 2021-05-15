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

## Reference

* 🔗 [Jakarta Bean Validation 명세서](https://beanvalidation.org/2.0/spec/#introduction)
* 🔗 [NHN 블로그: Bean Validation 기능 소개 및 사용방법](https://meetup.toast.com/posts/223)