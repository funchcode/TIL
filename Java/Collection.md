## "목차" <br>
1. [Collection](#collection-) <br>
2. [Iterator](#iterator) <br>

---
## Collection <br>

Collection은 배열과 같은 다른 객체를 저장, 핸들링하는 것을 목적으로 하는 객체이다. <br>
배열과 달리 Collection에서는 요소의 일부를 반환 및 정렬 등의 메서드를 포함하고 있다. <br>
<br>
**배열과 다르게 Collection은 primitive type을 Type으로 지정할 수 없다.**<br>
대신에 primitive Type에 대응하는 Wrapper Type을 사용할 수 있다. <br>
<br>
모든 Collection객체는 궁극적으로 java.util.Collection인터페이스를 구현한다. <br>
하지만, 직접적으로 구현하는 것이 아니라 하위 인터페이스들이 있고 하위 인터페이스들이 Collection의 기능을 결정한다. <br>
**List, Quere, Set**인터페이스는 모두 Collection인터페이스를 상속받지만, 구조상의 차이로 Map 인터페이스는 별도로 정의된다. <br>

---
## Iterator <br>

대표적으로 데이터들의 집합체 Map, Set, List의 특성을 살펴보면, <br>
- Map : 키와 값을 가지는 순서가 보장되지 않고 키 중복을 허용하지 않는 데이터 집합이다.
- Set : 순서를 유지하지 않고 데이터의 중복을 허용하지 않는 데이터의 집합이다.
- List : 중복을 허용하고 순서가 보장되어 있는 데이터의 집합이다.

집합체를 다룰 때 개별적인 집합체에 대한 데이터를 읽을 방법을 알아야하는 문제점이 있고 각 컬렉션에 접근이 어려워진다. <br>
Iterator를 사용하면 어떤 컬렉션이라도 동일한 방식으로 접근하는 방법을 제공하기 때문에 사용한다.(다형성) <br>
<br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[Java Iterator 설명](https://shxrecord.tistory.com/74) <br>
[Java – Collection 개요 (자료구조)](http://blog.breakingthat.com/2018/05/07/java-collection-%EA%B0%9C%EC%9A%94-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0/) <br>