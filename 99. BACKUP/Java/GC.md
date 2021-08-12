## GC(Garbage Collection)을 이해한다.

JVM의 Runtime Data Areas의 Heap영역에서 GC이 활동한다.

대표적으로 minor GC와 major GC가 있다.

GC의 동작은 알고리즘에 의해 자동으로 실행되고 자동으로 제거한다.

minor GC는 Young영역에서 참조되지 않는 객체를 제거하고 major GC는 Old영역에서 참조되지 않는 객체를 찾아 제거한다.

eden영역에 메모리가 꽉 차게 되면 minor GC가 동작하고 Survivor영역으로 이동하게 된다.

Young영역에 메모리가 꽉 차게 된다면 그 때 Old영역에 살아남은 객체들이 복사된다. 또는 일정 age가 넘게되면 Old영역으로 복사된다.

Old영역은 major GC에 의해 참조되지 않은 객체들이 제거되는데 시간이 오래 걸리고 실행되는 동안 프로세스가 정지된다.
'stop-the-world'가 된다고도 한다.

참조되지 않은 오브젝트를 알아내는 방법
1. Java 스택 메서드 실행 시에 사용되는 지역변수, 파라미터들에 의한 참조
2. Native 스택에 의해 생성된 객체에 대한 참조
3. Method 영역의 정적 변수에 의한 참조
4. Heap 내의 다른 객체의 의한 참조

Full GC
- Serial GC
Young영역과 Old영역을 연속적으로 처리되며 하나의 CPU를 사용한다.
- Parallel GC
Young영역의 콜렉션을 병렬처리한다 이는 대기상태를 최소화시키기 위한 방법이고 많은 CPU를 사용한다.
- Parallel Old GC
Old영역도 콜렉션을 병렬처리한다.

MSC(Mark-Sweep-Compact) 알고리즘
Mark : 살아있는 객체를 식별한다.
Sweep : 쓰레기 객체를 식별한다.
Compact : 쓰레기 객체들을 삭제한 후 살아있는 객체를 한 곳에 모은다.
