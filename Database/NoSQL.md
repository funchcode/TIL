## "INDEX"

1. [NoSQL](#nosql-)
2. [RDB와의 차이점](#rdb와의-차이점-)
3. [CAP](#cap-)
4. [Key-Value DB](#key-value-db-)
5. [Big Table Database](#big-table-database-)
6. [Document Database](#document-database-)
7. [Graph Database](#graph-database-)

---

## NoSQL

NoSQL의 약자는 사람에 따라 해석이 다르지만 **Not Only SQL로** 풀어 설명한다.
이 말의 의미는 기존 관계형 DBMS가 갖고 있는 특성뿐만 아니라, **다른 특성들을 부가적으로 지원** 한다는 것을 의미한다.

기존의 관계형 데이터베이스 시스템의 주요 특성을 보장하는 ACID 특성을 제공하지 않는, 그렇지만 뛰어난 확장성과 성능 등의 특성을 갖는 수 많은 비관계형, 분산 데이터 베이스들이 등장했고 NoSQL이라는 용어가 보편적으로 사용되었다.
기존의 관계형 데이터베이스보다 더 융통성 있는 데이터 모델을 사용하고, 데이터의 **저장 및 검색을 위한 특화된 매커니즘을 제공한다.**

이를 통해 NoSQL 데이터베이스는 단순 검색 및 추가 작업에 있어서 매우 최적화된 **키-값** 저장 기법을 사용하여 응답속도나 처리효율 등에 매우 뛰어난 성능을 나타낸다.

**NoSQL의 종류**

1. Key - Value DB
2. Wide Columnar Store
3. Document DB
4. Graph DB

---

## RDB와의 차이점

- 관계형 모델을 사용하지 않으며 테이블간 **조인 기능이 없다.**
- 직접 프로그래밍을 하는 등의 비SQL 인터페이스를 통한 데이터 액세스.
- 대부분 여러대의 **데이터베이스를 묶어서(클러스터링) 하나의 데이터베이스를 구성.**
- 관계형 데이터베이스에서 지원하는 **Data의 완결성 처리(ACID) 미보장.**
- 데이터의 스키마와 속성들을 다양하게 수용 및 동적 정의(Schema-less)
- 데이터베이스의 중단 없는 서비스와 자동 복구 기능 지원.
- 다수의 데이터베이스가 OpenSource로 되어있음.
- **확장성, 가용성, 높은 성능.**

NoSQL은 초고용량 데이터 처리 등 성능에 특화된 목적을 위해 비관계형 데이터 저장소에 비구조적인 데이터를 저장하기 위한 분산 저장 시스템이라고 할 수 있다.

---

## CAP

분산형 구조는 **일관성(Consistency), 가용성(Availability), 분산 허용(Partitioning Tolerance)의** 3가지 특성을 가지고 있다.

1. 일관성 : 분산된 노드 중 어느 노드로 접근하더라도 **데이터 값이 일치해야한다.**
2. 가용성 : 클러스터링 된 노드 중 하나 이상의 노드가 실패하더라도 정상적으로 요청을 처리할 수 있는 기능을 제공한다.
3. 분산 허용 : 클러스터링된 노드 간에 통신하는 네트워크가 장애가 나더라도 정상적으로 서비스를 수행한다.

NoSQL은 가용성과 분산허용을 만족하던가 일관성과 분산허용을 만족하는 제품으로 나눌 수 있다.

---

## Key-Value DB

**단순한 저장구조를 가지며, 복잡한 조회 연산을 지원하지 않는다.**
고속 읽기와 쓰기에 최적화된 경우가 많다. key에 대한 단위 연산이 빠른 것이지 여러 key에 대한 연산은 느릴 수 있다.
**메모리를 저장소로 사용하는 경우, 매우 빠른 Put과 Get을 지원한다.**
**Value의 값** 으로 문자열이나 원시 타입이 들어갈 수도 있고, **또 다른 Key/Value가 들어갈 수도 있다.**

이를 **Column Family** 라고 하며, Key 안에 Column,Key 조합으로 된 여러 개의 필드를 갖는 것을 말한다.

> Redis, Oracle coherence 등

---

## Big Table Database

Key/Value와 데이터 저장 방식은 동일하나, 보통 NoSQL은 정렬기능을 제공해주지 않는데 이 모델은 내부적으로 **Key를 정렬한다.**

> Hbase, Cassandra 등

---

## Document Database

- **Key/Value의 확장된 형태로 Value에 Document라는 타입을 저장한다.**
- Document는 구조화된 문서 데이터인 **XML, Yaml, JSON을 말한다.**
- 복잡한 데이터 구조를 표현가능하다.
- Document id 또는 특정 속성 값으로 인덱스를 생성하는데 이 경우 해당 key값의 range에 대한 효율적인 연산이 가능해지므로 이에 대한 쿼리를 제공한다.
- **Sorting, Join, Grouping** 등이 가능해진다.
- 쿼리 처리에 있어서 데이터를 파싱해야하기 때문에 overhead가 key-value 모델보다 크다.
- 큰 크기의 Document를 다룰 때는 성능이 저하된다.

> MongoDB, CouchDB, Riak 등

---

## Graph Database

node들과 relationship들로 구성된 개념이다.
Key-Value이며 모든 노드들이 끊기지 않고 모두 연결되어 있다.
relationship은 direction, type, start node, end node에 대한 속성 등을 가지고, 보통 양적인 속성들을 가진다.

> Sones, AllegroGraph, neo4j 등

---

### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[NoSQL (개념, 특징과 장점, CAP 이론, 데이터모델 분류](https://sjh836.tistory.com/97) <br>
[SamsungSDS NoSQL이란 무엇, DBMS의 종류](https://www.samsungsds.com/global/ko/support/insights/1195843_2284.html) <br>
