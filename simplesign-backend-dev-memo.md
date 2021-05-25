# Simple-Sign 백엔드 개발 기술 노트

## lombok

* ```@Value```
  * 생성한 클래스를 값 객체로 운용하고 싶을 때 해당 어노테이션 사용.
  * 어노테이션을 설정할 경우 모든 필드는 ```private final```하며, ```@Getter```, ```@AllArgsConstructor```, ```@ToString```하게 객체가 정의된다.
  
