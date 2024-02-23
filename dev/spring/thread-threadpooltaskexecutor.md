# \[Thread] ThreadPoolTaskExecutor 정리

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



## Spring의 ThreadPoolTaskExecutor

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
