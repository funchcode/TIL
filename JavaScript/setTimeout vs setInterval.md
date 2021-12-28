# setTimeout vs setInterval

## 비교

`setTimeout`은 일정 시간이 지난 후에 함수를 실행.

`setInterval`은 일정 시간 간격을 두고 함수를 실행.

## setTimout

```javascript
function sayHi() {
    alert('execute');
}
setTimeout(sayHi, 1000); // 1초 뒤에 실행
```

### clearTimeout

`setTimeout()`을 실행하면 타이머 식별자(timer identifier)가 반환되는데, 이 식별자로 스케줄링을 취소할 수 있다.

```javascript
let timerId = setTimeout(...);
setClearTimeout(timerId);
```

## setInterval

`setTimeout`과 동일한 문법을 사용한다.

하지만, `setTimeout`은 함수를 단 한 번만 실행하는 것과는 달리 `setInterval`은 주기적으로 함수를 실행한다.

```javascript
// 2초마다 로그를 출력한다.
let timerId = setInterval(() => console.log('print log'), 2000);
```

### Delay

`setInterval`의 두 번째 인자인 `delay`, 설정한 지연 시간은 첫 번째 인자로 넘긴 `func()`의 실행시간과 중첩되어 계산된다.

`func()`의 실행 시간이 지정한 `delay` 보다 더 걸리는 경우, 엔진에서 `func()`의 동작을 기다린 후 종료 즉시 `func()`를 실행한다.

#### 중첩 setTimeout

지정한 `delay` 값을 보장받으면서 `func()`을 실행하는 방법으로 **중첩 setTimeout**을 사용할 수 있다.

```javascript
let i = 1;
setTimeout(function run() {
    fun(i++);
    setTimeout(run, 100);
}, 100);
```

## 가비지 컬렉션

`setInterval`, `setTimeout`에 함수를 넘기면, 함수에 대한 내부 참조가 새롭게 만들어지고 이 참조는 스케줄러에 저장된다.

따라서, 함수를 참조하는 것이 없어도 `setInteval`, `setTimeout`에 넘긴 함수는 가비지 컬렉션의 대상이 되지 않는다.
