---
description: 조슈아 블로크 저
---

# ▪️ Effective Java

## \[2] Builder <a href="#builder" id="builder"></a>

개발을 하다 보면 중요한 도메인 클래스가 비즈니스 상황이 변경될 때 마다 덩치가 조금씩 커지는 것을 경험할 수 있다.

속성의 갯수가 많은 클래스의 경우 생성자는 필수 속성을 베이스로 선택적 속성 별로 생성자가 계속해서 늘어나는 특징이 있었다.

사용은 할 수 있지만 코드를 읽거나 사용하기 어려지는 문제가 발생한다.



### 자바빈즈 패턴의 세터(setter) <a href="#javabeans-setter" id="javabeans-setter"></a>

생성자로 인스턴스를 생성한 후에 속성을 세터(setter)로 완성하는 흐름이다.

이는 객체가 완전히 생성되기 전까지 일관성(consistency)이 무너진 상태에 놓인다.



### Builder pattern

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

## \[11] hashCode 재정의 <a href="#hashcode" id="hashcode"></a>

hashCode 메서드는 객체의 주소 값을 이용해서 해싱(hashing) 기법을 통해 해시 코드를 생성 후 반환한다.

해시코드는 주소 값 그대로가 아니라, 주소 값으로 만든 고유한 숫자값이다.

```java
public class Object {
    // ...
    public native int hashCode();
    // ...
}
```

{% hint style="info" %}
native 키워드

JNI(Java Native Interface)는 C나 저수준 언어로 작성된 native 코드를 JVM에 적재시키고 실행해주는 응용프로그램인데 native 코드 중 하나가 `hashCode()`이다.
{% endhint %}



Object equals() 메서드를 오버라이딩한 객체(Product)를 만들었다고 하자.

Product 상품 객체에는 식별 가능한 code 필드를 갖고 있고 code가 동일한 Product 객체에 대해서는 같은 대상으로 간주한다고 정의했다.

```java
public class Product {
    public String code;
    public Product(String code) {
        this.code = code;
    }
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Product)) return false;
        Product p = (Product) o;
        return Objects.equls(this.code, p.code);
    }
}

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("001");
        Product p2 = new Product("001");
        System.out.println(p1.equals(p2)); // true
    }
}
```

p1과 p2가 동등 객체인지 여부는 원하는 것과 같이 동등하다고 나온다.



hashCode() 오버라이딩 하지 않고 Collection Framework(HashSet, HashMap, HashTable) 하나를 사용해본다.

HashSet에 p1, p2를 밀어 넣었을 때 원하는 결과로 둘은 동등한 객체이니 하나만 담겨야할테다.

```java
Set<Product> productSet = new HashSet<>();
productSet.add(p1);
productSet.add(p2);
System.out.println(productSet.size());    // 2
```

기대한 결과와는 다른 2 라는 값이 나왔다.

논리적으로는 p1과 p2를 같다고 정의했지만 해시코드가 다르기 때문에 중복된 데이터가 컬렉션에 추가된 것이다.



Hash 값을 사용하는 Collection에서 객체가 논리적으로 같은지 비교할 때 아래 과정을 거친다.

> **→ hashCode() → equals()**
>
> : 모두 같다면 동등 객체
>
> : 하나라도 다르다면 다른 객체



equals()와 마찬가지로 Product의 code 값이 같다면 hashCode()도 같다는 처리를 해준다.

```java
@Override
public int hashCode() {
    return Objects.hash(this.code);
}
```

위의 코드를 넣고 main() 메서드를 실행하면 원하는 결과 값인 1이 나오는 것을 확인할 수 있다.
