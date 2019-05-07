## "목차" <br>
1. @RunWith()
2. @ContentConfiguration()
3. @Builder
4. @NotNull
5. @Column(nallable=false)
6. @GeneratedValue(속성)
7. @EntityListeners
8. @MappedSuperclass

---
## @RunWith() <br>

>👉🏽 JPA 테스트를 진행하다가 (Spring Boot ririweb 토이프로젝트)

```java
When a class is annotated with @RunWith or extends a class annotated with @RunWith, JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
```
**해석:** 클래스가 @RunWith로 주석을 달거나 @RunWith로 주석 된 클래스를 확장하면 JUnit은 JUnit에 내장 된 러너 대신 해당 클래스에서 테스트를 실행하기 위해 클래스를 호출합니다. <br>
스프링의 테스트 컨텍스트 프레임워크인 Junit 확장 기능을 지정하는 역할이다. <br>
<br>
**Junit 특징**<br>
- 각 각의 테스가 서로 영향을 주지 않고 독립적으로 실행됨을 원칙으로 하기 때문에 @Test마다 오브젝트를 생성한다.  
- 이와 같은 특성 때문에 ApplicationContext도 매번 생성되므로 테스트가 느려지는 단점이 있다.
- 그러나 @RunWith 애노테이션은 각각의 테스트 별로 오브젝트가 생성되더라도 싱글톤의 ApplicationContext를 보장한다.
- SpringJUnit4ClassRunner.class

---
## @ContextConfiguration()

>👉🏽 JPA 테스트를 진행하다가 (Spring Boot ririweb 토이프로젝트)

Spring Bean 메타 설정 위치를 지정할 때 사용되는 어노테이션이다. <br>
경로를 지정하지 않으면 테스트 클래스 파일이 있는 패키지 내에서 다음의 설정파일을 사용한다. <br>
- ContextConfigLocationTest-context.xml (대소문자 구분없음)

---
## @Builder

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

Class에 @Builder를 선언하기 위해서는 모든 필드를 가진 생성자를 클래스가 갖고 있어야한다. <br>
<br>
**사용시에 이점** <br>
- 각 인자가 어떤 의미인지 알기가 쉽다.
- setter 메소드 없이 사용가능하므로 변경 불가능 객체로 만들 수 있다.
- 한 번에 객체를 생성하므로 객체 일관성이 깨지지 않는다.
- 객체를 생성할 때 실수 발생 확률을 줄일 수 있다.
- 유지보수에 용이하다.

---
## @NotNull

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

null을 허용하지 않는다는 의미이다. <br>
@Notnull은 JSR 303 Bean Validation 주석이다. <br>
이 말은 데이터베이스 제약 조건 자체와는 아무런 관련이 없다는 얘기이다. <br>
그러나 Hibernate는 JSR 303의 레퍼런스 구현이기 때문에 이러한 제약 조건을 지능적으로 선택하고 이를 데이터베이스 제약조건으로 변환한다. <br>

---
## @Column(nullable = false)

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

칼럼을 Null이 아닌 값으로 선언하는 JPA방식이다. <br>
데이터베이스 스키마 세부 사항을 나타내기 위함이다. <br>

---
## @GeneratedValue(속성)

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

@Id가 붙은 필드는 해당 필드가 테이블의 primary key 역할을 한다는 것을 나타낸다. <br>
@GeneratedValue는 주키의 값을 위한 자동 생성 전략을 명시하는데 사용한다. <br>
선택적 속성으로는 **generate**와 **strategy**가 있다. <br>
<br>
**속성: generate**
- AUTO(디폴트 값) : persistence provider가 특정 DB에 맞게 자동 선택한다.
- IDENTITY : DB의 identity 칼럼을 이용한다.
- SEQUENCE : DB의 시퀀스 칼럼을 이용한다.
- TABLE : 유일성이 보장된 데이터베이스 테이블을 이용한다.

AUTO와 SEQUENCE는 INSERT 쿼리가 발생하기 전에 아래의 쿼리를 통해서 주키를 가져온다.
```java
select nextval('hibernate_sequence')
```

---
## @EntityListeners

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

엔티티를 DB에 적용하기 **이전 이후**에 커스텀 콜백을 요청할 수 있는 어노테이션이다. <br>
```java
 * Specifies the callback listener classes to be used for an 
 * entity or mapped superclass. This annotation may be applied 
 * to an entity class or mapped superclass.
```

---
## @MappedSuperclass

>👉🏽 JPA Entity를 선언하다가 (Spring Boot ririweb 토이프로젝트)

상속 관계 매핑 전략에서 부모 클래스와 자식 클래스 모두 데이터베이스 테이블과 매핑을 한다. <br>
부모 클래스를 자식 클래스에 상속 정보만 제공하고 싶을 때 MappedSuperclass를 사용한다. <br>
엔티티 종류와 상관없이 공통적으로 가지고 있어야 하는 정보(생성시간, 수정시간 등)가 있다면 공통 클래스로 추출하고 이를 상속받는 형식으로 구현하면 된다. <br>
<br>
엔티티는 엔티티만 상속받을 수 있기 때문에 엔티티가 아닌 클래스를 상속받기 위해서는 @MappedSuperclass를 사용한다. <br>
<br>
생성시간, 수정시간을 Auditing하기 위해서는 @EnableJpaAuditing을 설정해야한다.

---


💊 
