## "INDEX"

1. 외래키(Foreign Key)
2. 식별관계(Identifying Relationship)
3. 비식별관계(Non Identifying Relationship)

---

## 외래키(Foreign Key)

> 예를 들어서 상품과 주문 테이블이 있다고 가정한다. <br>
> 상품테이블(상품번호(PK), 상품카테고리, 상품이름, 상품가격) <br>
> 주문테이블(주문번호(PK)) <br>
> 하나의 상품은 여러 주문에서 구매할 수 있고, 하나의 주문은 여러 상품을 구매할 수 있다. <br>

위의 예시의 관계가 다대다(N:M)관계라고 하는데 보통 ERD상으로 다대다 관계는 중간에 관계 테이블을 추가해서 사용한다. <br>
<br>
위의 예시에서 관계 테이블로 "주문_상품"테이블을 추가한다. <br>
**"상품(1:다)주문_상품"**와 **"주문_상품(다:1)주문"**의 관계로 만들어진다. <br>
주문_상품(주문번호(FK), 상품번호(FK)) 해당 Column들은 관계를 찾아가기 위한 참조키로써 외래키로 부른다. <br>

---

## 식별관계(Identifying Relationship)

엔티티는 인스턴스의 집합이라고 한다. <br>
여러개의 집합체를 담고 있는 하나의 통(엔티티)에서 각각을 구분할 수 있는 논리적인 이름이 필요하겠다. <br>
이 구분자를 식별자(Identifier)라고 한다. <br>
식별자란 하나의 엔티티에 구성되어 있는 여러 개의 속성 중에 엔티티를 대표할 수 있는 속성을 의미하며 하나의 엔티티는 반드시 하나의 유일한 식별자가 존재해야 한다. <br>
부모테이블의 기본키가 자식테이블의 기본키이거나 기본키 그룹의 구성원으로 전이되어지는 관계를 말한다. <br>

> 회원(회원번호(PK), 아이디, 비밀번호, 이름, 전화번호 ...) <br>
> 프로필(회원번호(FK), 키, 몸무게 ...) <br>
> 위의 예시 테이블을 보면 이해될지도..

<br>
주 식별자의 특징으로 **유일성, 최소성, 불변성, 존재성**을 만족해야 한다. <br>
👉🏽 유일성, 최소성, 불변성, 존재성 <br>

---

## 비식별관계(Non Identifying Relationship)

부모테이블의 기본키가 자식테이블의 Attribute의 구성원으로 전이되는 관계를 말한다. <br>

> 회원(회원번호(PK), 아이디, 비밀번호, 이름, 전화번호, 등급코드(FK) ...) <br>
> 회원등급(등급코드, 준회원, 정회원...) <br>

---
