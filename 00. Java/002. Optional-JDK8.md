# 002. Optional-JDK8.md

> Optional API는 JDK8부터 사용 가능하다.

## Optional 객체 생성

### empty()

`null`을 담고 있는 비어있는 Optional 객체를 받는다.

Optional 내부적으로 미리 생성해놓은 싱글턴 인스턴스다.

### of(value)

`null`이 아닌 객체를 담고 있는 Optional 객체를 생성한다.

`null`이 넘어오는 경우 **NPE(NullPointerException)**을 던지기 때문에 주의해야한다.

### ofNullable(value)

`null`인지 아닌지 확실하지 않는 객체를 담고 있는 Optional 객체를 생성한다.

넘겨받을 객체가 `null`인지 아닌지 확신할 수 없는 상황에서 사용한다.