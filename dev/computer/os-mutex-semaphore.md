# \[OS] 뮤텍스(Mutex)와 세마포어(Semaphore)

## 개념이 생긴 이유 <a href="#concept" id="concept"></a>

프로세스 간 메세지를 전송, 공유 메모리를 통한 공유된 자원에 여러 개의 프로세스가 동시에 접근하면 Critical Section 문제가 발생할 수 있다.

{% hint style="info" %}
Critical Section(임계 영역)

`Critical: (앞으로의 상황에 영향을 미친다는 점에서) 대단히 중요한`

여러 프로세스가 데이터를 공유하며 동작할 때 각 프로세스에서 공유 데이터를 접근하는 프로그램 코드 블록을 말한다.

동일한 자원을 동시에 참조하여 값이 오염될 위험 가능성이 있는 영역을 말한다.
{% endhint %}

이를 해결하기 위해 데이터를 한 번에 하나의 프로세스만 접근할 수 있도록 제한을 두는 동기화 방식을 취해야 한다.

이 때 대표적인 동기화 방식으로 **뮤텍스(Mutex)**와 **세마포어(Semaphore)**가 있다.



## 뮤텍스(Mutex) 기법

**상호 배제(mutual exclusion)**을 구현하는 동기화 객체다.

"_오직 하나의 스레드_" 또는 "_오직 하나의 프로세스_" 만이 뮤텍스를 소유하고 다른 스레드나 프로세스는 뮤텍스를 획득하기 위해 대기해야 한다.

뮤텍스를 소유하고 있는 스레드가 뮤텍스를 해제하기 전까지 다른 스레드나 프로세스는 해당 자원에 접근할 수 없다.



### 뮤텍스 적용을 고려할 만한 상황 <a href="#mutex-usecase" id="mutex-usecase"></a>

**1) 공유 데이터베이스 접근**

&#x20;    \- 데이터베이스에 동시 접근으로 인한 데이터 일관성 문제를 방지할 수 있다.

**2) 파일 I/O 관리**

&#x20;    \- 여러 스레드가 동일한 파일에 동시에 쓰기 작업을 방지하여 파일의 일관성 유지 및 데이터 손실을 방지할 수 있다.

**3) 자료 구조 보호**

&#x20;    \- 여러 스레드가 공유 자료 구조(큐, 리스트, 해시 테이블 등)에 동시에 접근하는 경우, 자료 구조에 대한 접근을 동기화할 수 있다.

&#x20;    \- 이를 통해 자료 구조의 무결성을 유지하고 동시성 문제를 해결할 수 있다.



### Java로 뮤텍스 기법 적용 (java.util.concurrent.locks.ReentrantLock) <a href="#mutex-reentrantlock" id="mutex-reentrantlock"></a>

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static final int ITERATIONS = 10000;
    private static int sharedResource = 0;
    private static Lock mutex = new ReentrantLock();
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(MutexExample::increment);
        Thread thread2 = new Thread(MutexExample::increment);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (e) {}
        System.out.println(sharedResource);
    }
    
    public static void increment() {
        for (int i = 0 ; i < ITERATIONS ; i++) {
            mutex.lock();
            try {
                sharedResource++;
            } finally {
                mutex.unlock();
            }
        }
    }
}
```

`java.util.concurrent` 패키지의 ReentrantLock 클래스를 사용해서 뮤텍스를 적용한 코드이다.

`increment` 메서드에서는 공유 자원인 `sharedResource`에 접근하여 값 1을 더하는 간단한 메서드다.

두 개의 스레드가 각 10000번 씩 `increment` 메서드를 수행하기 때문에 기대하는 결과 값은 20000이다.

`main`메서드를 실행해보면 원하는 결과가 콘솔에 출력되는 것을 확인할 수 있다.



뮤텍스를 제거한 후에 동일하게 실행해보자.

```java
public static void increment() {
    sharedResource++;
}
```

`main` 메서드를 실행해보면 콘솔에 20000이 아닌 다른 값이 콘솔에 출력되는 것을 볼 수 있다.



## 세마포어(Semaphore) 기법

**카운팅 기반**의 동기화 객체

**1) 특정 리소스의 사용 가능한 개수를 나타내는 카운터**

**2) P(Produce) 연산**

&#x20;    \- 세마포어의 값을 감소시킨다.

&#x20;    \- 세마포어의 값이 0보다 작아지면 호출하는 스레드, 프로세스는 대기 상태로 전환된다.

**3) V(Verhogen) 연산**

&#x20;    \- 세마포어의 값을 증가시킨다.

&#x20;    \- 대기 중인 스레드, 프로세스 중 하나를 깨우고 실행할 수 있도록 한다.



### 세마포어 적용을 고려할 만한 상황 <a href="#semaphore-usecase" id="semaphore-usecase"></a>

**1) 제한된 리소스 관리**

&#x20;    \- 한정된 개수의 공유 자원 접근에 대해 제어할 수 있다.

&#x20;    \- 예로 데이터베이스 커넥션 풀을 생각해볼 수 있는데 동시에 데이터베이스에 접근할 수 있는 커넥션 개수를 제한할 수 있다.

&#x20;    \- 이를 통해 과도한 커넥션 요청으로 인한 부하를 방지할 수 있다.

**2) 스레드 풀 관리**

&#x20;    \- 스레드 풀에서 작업이 수행되는 동안 세마포어를 사용하여 동시에 실행되는 스레드 수를 제한할 수 있다.

&#x20;    \- 스레드 풀의 작업 큐에 새로운 작업을 추가하는데 세마포어를 사용할 수 있다.

**3) 작업 큐 관리**

&#x20;    \- 여러 스레드가 작업 큐에 작업을 넣거나 빼낼 때 세마포어를 사용하여 작업의 동시 실행을 제어할 수 있다.

&#x20;    \- 이를 통해 작업 처리의 순서를 조절하거나 병렬 처리의 수를 제한할 수 있다.
