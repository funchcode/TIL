## JVM (Java Virtual Machine) : 자바 가상 머신을 이해한다.

JVM의 역할로는
Java와 OS 사이의 중개자 역할을 해준다.
메모리관리와 가바지컬렉션을 수행한다.

프로그램이 실행되면 JVM은 OS로부터 이 프로그램이 필요로하는 메모리를 할당받고 메모리를 용도에 따라 여러 영역으로 나눈 후 관리한다.
.java로 된 파일을 자바 컴파일러가 자바 바이너리 파일로 변환시켜준다.

JVM내에 Class Loader가 자바 바이너리 파일을 Runtime Data Areas에 로딩한다.
Runtime Data Areas에 로드 된 .class 파일들은 Execution Engine에 의해 해석된다.

Execution Engine이 바이트 코드를 기계어로 번역하는 데에 두 가지 방법을 이용하는데,
하나는 인터프리터 방식 다른 하나는 JIT(Just-In-Time)의 방식으로 해석한다.

인터프리터 방식은 명령어를 그때그때 하나 씩 해석하는 것이고 인터프리터 특징처럼 속도가 느리다는 특징이 있다.
반면에 JIT 방식은 적절한 시점에 전체 바이트 코드를 기계어로 번역한다.

Runtime Data Areas에는 5가지의 영역이 존재한다.
1. PC Register
2. JVM Stack
3. Native Method
4. Heap
5. Method Area

PC Register는 스레드가 생성될 때마다 갖게 된다.
Thread가 어떤 명령어로 실행되어야 하는지 기록되는 부분으로 JVM 명령 값을 갖고 있다.

JVM Stack 이 곳에는 지역변수, 매개변수, 연산 중 발생하는 임시 데이터 등이 보관된다.

Native Method는 Java가 아닌 다른 언어로 작성된 코드가 저장되는 공간이다.

Heap은 Heap내에서도 3가지 영역으로 나뉘는데 Young영역, Old영역, Permanent영역이 있다.
Young영역은 Eden, Survivor1, Survivor2 영역이 있는데 새로 생성되는 객체가 저장되는 공간이다.
Eden영역이 가득차게되면 Minor GC가 발생하면서 Survivor1 혹은 Survivor2영역으로 Eden에 있는 객체의 주소 값이 이동한다.
Young영역이 가득차게 되면 Major GC가 발생하면서 Old영역으로 객체의 주소 값이 이동한다.
Permanent영역은 classLoader에 의해 로드되는 클래스, 메서드의 주소 값이 저장되는데 reflection 사용 시에 JVM이 참조하는 부분이다.

Method Area영역은 클래스 정보를 처음 메모리에 올릴 때 초기화되는 대상을 저장하기 위한 메모리 공간이다.

🚨정리 안된 개념들 (추후에 정리) 🚨
왜 Survivor1, Survivor2 둘 중 하나는 비워둬야 할까.
