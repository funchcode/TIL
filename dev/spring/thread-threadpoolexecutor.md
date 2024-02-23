# \[Thread] ThreadPoolExecutor 정리

## Java의 ThreadPoolExecutor

Thread Pool은 애플리케이션의 특징 및 JVM을 구동하는 하드웨어 사양에 따라 세밀하게 조정해야 한다.

Java 1.5 이 후 ExecutorService 구현체인 `ThreadPoolExecutor`를 통해 Thread Pool을 직접 설정하여 사용할 수 있다.



```java
package java.util.concurrent;

public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,       
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    // ... init code
}
```

<table><thead><tr><th width="191">파라미터</th><th>설명</th></tr></thead><tbody><tr><td>corePoolSize</td><td>Thread Pool에 계속해서 유지할 Thread 수</td></tr><tr><td>maximumPoolSize</td><td>Thread Pool에 최대한으로 가질 수 있는 Thread 수<br>* workQueue가 가득 찼을 때 Thread 개수를 늘린다.</td></tr><tr><td>keepAliveTime</td><td>기본으로 유지하고 있는 Thread 외에 증가한 Thread를 유지할 시간 설정</td></tr><tr><td>unit</td><td>keepAliveTime에서 사용할 TimeUnit 타입</td></tr><tr><td>workQueue</td><td>Task가 corePoolSize를 초과할 때 보관할 BlockingQueue 정의</td></tr><tr><td>threadFactory</td><td>(not required) Thread Pool에서 Thread를 생성할 때 사용할 threadFactory 정의</td></tr><tr><td>handler</td><td>(not required) Thread Pool이 가득차서 더 이상 작업을 수용할 수 없을 때 사용되는 핸들러</td></tr></tbody></table>

{% hint style="info" %}
Task 요청이 들어오면 아래의 순서로 ThreadPoolExecutor가 작동한다.

_corePoolSize 증가 → workQueue 증가 → maximumPoolSize 증가_
{% endhint %}



### 테스트 코드 작성 <a href="#example" id="example"></a>

maximumPoolSize, queueSize 내에서 실행 가능한 크기로 사용.

```java
@Test
void threadPoolTaskExecutorTest() {
    int corePoolSize = 5;
    int maximumPoolSize = 10;
    int queueSize = 5;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        corePoolSize, 
        maximumPoolSize,
        10,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(queueSize));
    
    IntStream.range(0, 15).forEach(i -> {
        executor.execute(() -> {
            // 무거운 작업
        });
        System.out.println(executor);
    });
    
    try {
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECOUNDS);
    } catch (InterruptedException e) {
        // ...
    }
}
```

```
[Running, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
[Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
[Running, pool size = 3, active threads = 3, queued tasks = 0, completed tasks = 0]
[Running, pool size = 4, active threads = 4, queued tasks = 0, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 0, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 2, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 4, completed tasks = 0]
[Running, pool size = 5, active threads = 5, queued tasks = 5, completed tasks = 0]
[Running, pool size = 6, active threads = 6, queued tasks = 5, completed tasks = 0]
[Running, pool size = 7, active threads = 7, queued tasks = 5, completed tasks = 0]
[Running, pool size = 8, active threads = 8, queued tasks = 5, completed tasks = 0]
[Running, pool size = 9, active threads = 9, queued tasks = 5, completed tasks = 0]
[Running, pool size = 10, active threads = 10, queued tasks = 5, completed tasks = 0]
```

사용 가능한 Thread가 있을 때는 Thread를 가져다 사용하므로 corePoolSize 만큼 active threads를 사용한다.

그 이후 corePoolSize 사용량이 다 찬 경우 queued tasks가 채워지는 것을 확인할 수 있다.

queued tasks의 capacity 만큼 Queue가 가득 찬 경우 maximumPoolSize 만큼 active threads가 늘어난다.



#### maximumPoolSize보다 더 많은 작업을 돌리는 경우 <a href="#example-01" id="example-01"></a>

```java
// ...
IntStream.range(0, 20).forEach(i -> {
    // ...
});
// ...
```

```
java.util.concurrent.RejectedExecutionException: 
    Task com.example.funch.DemoApplicationTests$$Lambda/0x0000020ba4588230@27896d3b 
        rejected from java.util.concurrent.ThreadPoolExecutor@5fb07347
            [Running, pool size = 10, active threads = 10, queued tasks = 5, completed tasks = 0]
```

위와 같이 `RejectedExecutionException` 예외가 발생한다.

예외를 처리하기 위해선 ThreadPoolTaskExecutor를 생성할 때 `RejectedExecutionHandler` 로 제어할 수 있다.

## Spring의 ThreadPoolTaskExecutor <a href="#threadpoolexecutor" id="threadpoolexecutor"></a>

```java
package org.springframework.scheduling.concurrent;

public class ThreadPoolTaskExecutor extends ExecutorConfigurationSupport 
        implements AsyncListenableTaskExecutor, SchedulingTaskExecutor {
    private int corePoolSize = 1;
    private int maxPoolSize = Integer.MAX_VALUE;
    private int keepAliveSeconds = 60;
    private int queueCapacity = Integer.MAX_VALUE;
    // ...
    @Override
    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        BlockingQueue<Runnable> queue = this.createQueue(this.queueCapacity);
        // ...
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, (long)this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler) {
            // ...
        }
        // ...
        return executor;
    }
    // ...
}
```

ThreadPoolTaskExecutor에서 초기화할 때 내부적으로 `ThreadPoolExecutor`를 생성하여 사용하고 있다.
