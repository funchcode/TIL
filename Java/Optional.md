## "목차" <br>

---
## Optional <br>

Optional은 존재할 수도 있지만 안 할 수도 있는 객체, 즉 null이 될 수도 있는 객를 감싸고 있는 일종의 래퍼 클래스이다. <br>
<br>
null일 경우 "Null Pointer Exception" 예외를 발생시킬 수 있다. <br>
이러한 경우 때문에 코드에 null 체크 하는 로직이 생기고 코드 가독성과 유지보수성이 떨어지게 되었다. <br>
<br>
Optional로 객체를 감싸서 사용하게 된다면 <br>
NPE를 유발할 수 있는 null을 직접다루지 않아도 된다. <br>
명시적으로 해당 변수가 null일 수도 있다는 가능성을 표현할 수 있다. <br>