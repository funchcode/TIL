# ▪️ Effective Java

## \[2] Builder

개발을 하다 보면 중요한 도메인 클래스가 비즈니스 상황이 변경될 때 마다 덩치가 조금씩 커지는 것을 경험할 수 있다.

속성의 갯수가 많은 클래스의 경우 생성자는 필수 속성을 베이스로 선택적 속성 별로 생성자가 계속해서 늘어나는 특징이 있었다.

사용은 할 수 있지만 코드를 읽거나 사용하기 어려지는 문제가 발생한다.



자바빈즈 패턴의 세터(setter)

생성자로 인스턴스를 생성한 후에 속성을 세터(setter)로 완성하는 흐름이다.

이는 객체가 완전히 생성되기 전까지 일관성(consistency)이 무너진 상태에 놓인다.



Builder pattern

점층적 생성자 패턴이 가진 안정성과 자바빈즈 패턴의 가독성을 가져온 Builder Pattern을 사용할 수 있다.

```java
public class ComplexObject {
    // 모든 속성 불변으로 관리할 수 있다.
    private final String width;
    private final String height;
    private final String color;
    ...
    public static class Builder {
        // 필수
        private final String width;
        private final String height;
        // 선택 (초기값 설정)
        private String color = "";
        private String text = "";
        ...
        public Builder(String width, String height) {
            // validate code...
            this.width = width;
            this.height = height;
        }
        public Builder color(String color) {
            // validate code...
            this.color = color;
            return this;
        }
        public Builder text(String text) {
            // validate code...
            this.text = text;
            return this;
        }
        ...
        public ComplexObject build() {
            // validate code...
            return new ComplexObject(this);
        }
    }
    
    // 불변 객체로 사용
    // 일관성 확보
    private ComplexObject(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.color = builder.color;
        ...
    }
}
```

```java
public static void main(String[] args) {
    // 메서드 호출이 흐르듯 연결
    // = 플루언트 API라고도 말한다.
    ComplxObject complexObj = new ComplexObject.Builder("120", "80")
        .color("red").text("rokmc").build();
}
```
