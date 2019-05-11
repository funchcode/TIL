## "목차" <br>
1. [ORM](#orm-)
2. [Hibernate](#hibernate-)
3. [JPA](#jpa-)

---
## ORM <br>

ORM(Object-Relational-Mapping)는 사물을 추상화시켜 이해하려는 OOP적 사고방식과 DataModel을 정형화하여 관리하려는 RDB 사이를 연결하려는 계층의 역할을 한다. <br>
<br>
ORM을 이용한 개발은 객체와 데이터베이스의 변형에 유연하게 대처할 수 있게 해준다. <br>
JDBC와 관련된 복잡한 코드를 작성하지 않아도 된다. <br>

---
## Hibernate <br>

Hibernate ORM은 자바 언어를 위한 객체 매핑 프레임워크이다. <br>
재사용성이 좋고 DB종류가 바뀌어도 대응이 쉽고, 객체로 쿼리 결과가 자동으로 매핑되므 편하다는 장점이 있다. <br>
Hibernate는 JPA의 구현체라고 볼 수 있다. <br>

---
## JPA <br>

JPA는 기술명세이다. 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스이다. <br>
JPA는 단순한 명세이기 때문에 구현이 없다. <br>
JPA에 대한 구현은 Hibernate가 하기 때문에 마치 Java의 Interface와 interface를 구현한 class와 같은 관계이다. <br>
<br>
**Spring Data JPA**는 JPA를 쓰기 편하게 만들어놓은 모듈이다. <br>
DB에 접근할 필요가 있는 상황에서 Repository를 정의해 사용했는데 이 Repository가 Spring Data JPA이다. <br>
사용자가 인터페이스의 규칙에 맞게 메소드를 입력하면 Spring이 알아서 해당 메서드 이름에 적합한 쿼리를 날리는 구현체를 만들어 빈으로 등록한다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[수환님의 JPA, Hibernate, 그리고 Spring Data JPA의 차이점](https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/) <br>

---
🏷  JPA, QueryDSL, Code, Hibernate

