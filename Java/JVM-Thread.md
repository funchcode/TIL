# Java Thread를 정리한다.

## Java Thread는 어디에서 생성될까

`= JVM 내에서 생성된다.`

### JVM에서의 스레드와 메모리 구성

Java Application(프로그램)을 실행하면 새로운 JVM 프로세스가 시작되고 __main thread가__ 생성된다.

JVM은 프로세스를 실행하면서 __OS로부터__ Runtime Data Area 메모리를 할당 받는다.

__<< JVM 구성 >>__

__1. Method Area__

- 클래스 정보, 정적 변수 등 JVM 프로세스가 시작될 때 데이터 할당

__2. Heap__

- 동적으로 데이터가 할당되는 영역
- Reference 타입 객체 생성 시에 실제 객체가 올라가는 메모리 영역

__3. PC(Program Counter) Register__

- 해당 스레드에서의 명령 흐름을 추적하고 관리
- 현재 수행중인 JVM 명령 주소 저장
- 연산을 위해 필요한 피연산자를 임시로 저장

__4. Stack(= call stack, execution stack)__

- 메서드 호출 시 호출된 메서드를 위한 Frame 생성
- 메서드 Scope 내에서 발생하는 지역변수, 매개변수, 리턴값 등이 Frame에 저장
- 메서드 Scope가 종료(리턴)되면 Frame은 반환됨

__5. Native Method Stack__

- 자바가 아닌 다른 언어를 JNI를 통해 실행하기 위한 코드 공간

__<< 스레드 생성 시 구성 >>__

__공유하는 메모리 영역__

- Method Area
- Heap

__새로 할당되는 영역__

- PC Register
- JVM Stack
- Native Method Stack

## Thread Pool

### 생성 비용

__Thread는__ 프로세스가 할당받은 메모리를 사용한다.

JVM이 할당받은 메모리 내에서 메모리를 재할당하기 때문에 쓰레드의 생성비용은 고스란히 JVM 메모리의 소비로 이어진다.

64비트 Java8과 Java11에서는 기본적으로 1MB의 메모리를 예약 할당한다.

스택의 깊이가 최대로 늘어나면 1MB까지 할당. 최소는 16KB 이상의 메모리를 소비한다.

### 역할

문제의 상황

1. 1000개의 요청이 들어와서 1000개의 스레드가 생성
2. 1000개의 스레드가 작업을 진행하려고 시도
3. (어떤 스레드부터 시작?)

Thread Pool 도입

1. 1000개의 요청이 들어 옴.
2. 일정 쓰레드가 이미 생성되어 Thread Pool에 의해 라이프 사이클이 우선순위를 포함하여 관리되고 있음.
3. 우선순위에 따라 처리 됨.

### Fork Join Thread Pool

`Fork(분할) + Join(합치다)`

Thread Pool의 문제점

- 배분된 1,2,3의 작업을 진행
- 1번 작업이 처리 됨
- 1번 위치에 새로운 요청이 들어오기 전까지 리소스가 낭비

놀고 있는 스레드를 최소화를 위한 __Fork Join Thread Pool__

- 하나의 작업 큐를 갖고 있다.
- Fork Join Pool에서 관리하는 여러 스레드에서 접근하여 작업들을 처리한다.
- 여러 스레드는 가져간 작업들을 내부 큐(inbound queue)에 담아 관리한다.
- 스레드들은 서로의 큐에 접근하여 작업들을 가져가 처리한다.