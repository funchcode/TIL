## "목차" <br>

---
## 동기(synchronous)와 비동기(Asynchronous) <br>

동기란 상대방의 일정 신호에 의해서 다음 동작이 이루어지는 것을 말한다. <br>
비동기란 상대방의 상태와 관계없이 일방적으로 동작하는 것을 말한다. <br>
<br>
다른 표현으로 어떤 루틴을 완전히 끝내고 제어를 반납하는 것이 동기식 <br>
동작이 안 끝났어도 일단 제어권을 반납한 후 자기 할 일을 계속하면 비동기식 <br>
<br>

## Blocking과 Non-Blocking <br>

Blocking I/O 모델에서는 System Call이 끝날때까지 프로그램은 대기해야하고 System Call이 완료되면 그때야 Return한다. <br>
Non-Blocking I/O 모델에서는 System Call이 완료되지 않아도 대기하지 않고 return해버린다. <br>
