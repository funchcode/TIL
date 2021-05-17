# JPA

**JPA**(**Java Persistence API**)는 자바 ORM(Object Relational Mapping) 기술에 대한 표준 명세이며, 자바에서 제공하는 API를 말한다.  

## 바로가기

### [▪︎ 연관관계 매핑](#연관관계-매핑)

* [단방향, 양방향 연관관계](#단방향,-양방향-연관관계)
* [1:N(일대다), N:1(다대일) 매핑](#1:N(일대다),-N:1(다대일)-매핑)

### [▪︎ Spring Data JPA](#Spring-Data-JPA)

* [PagingAndSortingRepository 인터페이스](#PagingAndSortingRepository-인터페이스)

### [▪︎ 레퍼런스](#Reference)

---

## 연관관계 매핑

`@JoinColumn`을 명시하지 않을 경우 Hibernate에서 연관관계를 맺는 양쪽 테이블을 위한 별도의 테이블을 생성하는 쿼리를 날린다.  
> ```spring.jpa.show-sql= true```  
> Hibernate 옵션을 통해 실제로 실행되는 Query를 볼 수 있다.  

### 단방향, 양방향 연관관계

* **객체 연관관계와 테이블 연관관계의 차이점**  
  * [객체] 연관관계의 방향은 **언제나 단방향**이다.  
  * [객체] A → B (```A.B```로 접근)
  * [테이블] 연관관계는 외래키 하나로 **양방향** 조인을 할 수 있다.  
  * [테이블] 외래키를 통해 ```A JOIN B```와 ```B JOIN A``` 둘 다 가능하다.

객체 참조의 단방향을 해결하기 위해서는 반대 방향으로 참조를 만들어줘야한다.  
> 완전한 양방향이 아닌, 서로 다른 단방향 관계로 봐야한다.  

* **객체 양방향 매핑**
  * 양방향 매핑 시 두 연관관계 중 하나를 연관관계의 **주인**으로 지정해줘야한다.
  * 연관관계 상 **주인**으로 설정된 객체에서만 데이터베이스 연관관계와 매핑되며 외래키를 관리(등록, 수정, 삭제)할 수 있다.
  * 주인이 아닌 객체에서는 **읽기**만 가능하다.

JPA에서 제공하는 어노테이션의 ```mappedBy``` 엘리먼트 값을 통해 **주인** 관계를 지정할 수 있다.  
**주인이 아닌 객체에 ```mappedBy```를 지정한다.**

```java
/**
* 현재 코드 구간에서는 [Member, Team] 두 개의 클래스가 존재한다.
* 두 객체간의 관계는 [다 대 1]이다.
*/
public class Member {
  private String id;
  private String userName;
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
}

public class Team {
  private String id;
  private String name;
  @OneToMany(mappedBy = "team")
  private List<Member> members = new ArrayList<>();
}
```

### 1:N(일대다), N:1(다대일) 매핑

* ```@OneToMany``` 일대다 매핑 어노테이션
* ```@ManyToOne``` 다대일 매핑 어노테이션

---

## Spring Data JPA

데이터 접근 계층을 개발할 때 지루하게 반복되는 CRUD 로직를 위한 편리한 기능을 제공한다.

### PagingAndSortingRepository 인터페이스

```Pageable``` 인터페이스와 ```Sort``` 객체를 활용하여 쉽게 페이징, 정렬 처리 조회가 가능하다.

## Reference

* 🔗 [ORM과 JPA 패러다임 정리된 글](https://velog.io/@adam2/JPA%EB%8A%94-%EB%8F%84%EB%8D%B0%EC%B2%B4-%EB%AD%98%EA%B9%8C-orm-%EC%98%81%EC%86%8D%EC%84%B1-hibernate-spring-data-jpa)
* 🔗 [JPA 단방향/양방향 연관관계 정리된 글](https://cornswrold.tistory.com/350)