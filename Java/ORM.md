## "Index"

1. [ORM](#orm)
2. [Hibernate](#hibernate)
3. [JPA](#jpa)

---

## ORM

**ORM(Object-Relational-Mapping)** 는 사물을 추상화시켜 이해하려는 OOP적 사고방식과 DataModel을 정형화하여 관리하려는 RDB 사이를 연결하려는 계층의 역할을 한다.

ORM을 이용한 개발은 객체와 데이터베이스의 변형에 **유연하게 대처** 할 수 있게 해준다.
JDBC와 관련된 복잡한 코드를 작성하지 않아도 된다.

---

## Hibernate

Hibernate ORM은 자바 언어를 위한 객체 매핑 **프레임워크**;이다.
Hibernate는 **JPA**;의 구현체이다.

**장점**
- 데이터베이스가 변경되어도 SQL 스크립트를 수정하는 등의 작업을 할 필요가 없다.
- 데이터를 객체지향적으로 관리할 수 있기 때문에 비즈니스 로직에 집중 할 수 있으며, 객체지향 개발이 가능하다.

**단점**
- 어렵다.
- 제대로 알지 못하고 사용할 경우 데이터의 손실이 발생할 수 있다.

---

## JPA

자바 ORM기술에 대한 API표준 명세로, **자바**;에서 제공하는 **API**;이다.
JPA는 단순한 명세이기 때문에 구현이 없다.

**Spring Data JPA**;는 JPA를 쓰기 편하게 만들어놓은 모듈이다.
DB에 접근할 필요가 있는 상황에서 Repository를 정의해 사용했는데 이 Repository가 Spring Data JPA이다.
사용자가 인터페이스의 규칙에 맞게 메소드를 입력하면 Spring이 알아서 해당 메서드 이름에 적합한 쿼리를 날리는 구현체를 만들어 빈으로 등록한다.

---

## Mybatis

개발자가 지정한 SQL, 저장 프로시저 그리고 몇 가지 고급 매핑을 지원하는 SQL Mapping이다.

**장점**
- SQL에 대한 모든 컨트롤을 하고자 할 때 매우 적합하다.

---

 **⭐️ 참조자료 (감사합니다:)) ⭐️**
[수환님의 JPA, Hibernate, 그리고 Spring Data JPA의 차이점](https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/)

---
🏷  JPA, QueryDSL, Code, Hibernate
