## "목차" <br>

---
## 자료구조 <br>

자료구조란 데이터에 편리하게 접근하고, 변경하기 위해서 데이터를 저장하거나 조작하는 방법을 말한다. <br>
모든 목적에 맞는 자료구조는 없기 때문에 각 자료구조가 갖는 장점과 한계를 잘 아는 것이 중요하다. <br>
자료구조를 배우는 이유는 메모리의 효율적인 사용을 위함이라고 할 수 있다. <br>

---
## Java 자료구조 <br>

자바의 자료구조는 크게 4가지가 있다.
1. List
2. Set
3. Map
4. Queue

Set, List, Queue는 Collection 인터페이스를 구현하고 있다. <br>
Map은 별도의 인터페이스로 선언되어 있다. <br>

---
## List <br>

List 인터페이스를 구현한 자료구조는 크게 ArrayList, Vector, Queue, LinkedList가 있다. <br>
ArrayList와 Vector 클래스는 매우 유사하다. <br>
둘 다 확장 가능한 배열이라고 생각하면되는데 차이점으로는 Vector는 Thread Safe하고 ArrayList는 그렇지 않다는 점이다. <br>
<br>
배열과 ArrayList의 차이점은 배열은 크기를 미리 지정해줘야하는데 ArrayList는 크기가 유동적이기 때문이다. <br>
그렇지만 ArrayList의 크기를 늘려주는 작업이 진행되면 프로그램의 성능이 저하된다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[초보몽키님의 알고리즘, 자료구조 개요](https://wayhome25.github.io/cs/2017/04/17/cs-18/) <br>