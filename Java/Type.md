## "목차" <br>
1. [Primitive Type](##primitive-type)
2. [Wrapper Class](##wrapper-class)
3. [Reference Type](##reference-type)
4. [String](##string)

---
## Primitive Type <br>

Java에서 기본형 타입이 해당된다. <br>
자바에서 문자들은 모두 유니코드(Unicode)를 사용하며 유니코드는 한문자를 2바이트의 코드로 표준화하고 있다. <br>
- **정수 :** byte, short, int, long
- **실수 :** float, double
- **문자 :** char
- **논리 :** boolean

각 타입마다 기본 값이 있으므로 null이 존재하지 않는다. <br>
primitive type의 변수는 thread의 stack memory에 저장된다. <br>

---
## Wrapper Class <br>

Primitive 형은 객체가 아니어서 Object로 받는 다형성을 지원받을 수 없기 때문에 기본 데이터를 객체로 변환시켜 전달하는 데에 목적을 갖는다. <br>

1. 객체 또는 클래스가 제공하는 메소드를 사용 목적
2. 클래스가 제공하는 상수 사용 목적
3. 숫자, 문자로의 형변환 또는 진법 변환 시 사용 목적
4. 기본형 타입을 Collection, Generic

**Boxing, UnBoxing** <br>
Wrapper 클래스는 산술연산을 위해 정의된 클래스가 아니기 때문에 이 클래스의 인스턴스에 저장된 값은 변경이 불가능하며, 값을 저장하는 새로운 객체의 생성 및 참조만 가능하다. <br>
<br>
Boxing: 기본 자료형을 Wrapper 클래스의 객체로 변경하는 과정 <br>
UnBoxing: 각각의 객체를 기본 자료형으로 변경하여 사용하는 과정 <br>
**JDK 1.5버전 이후에는 자동으로 처리되도록 AutoBoxing, AutoUnBoxing을 제공한다.** <br>

---
## Reference Type <br>

Java에서 객체 참조 타입에 해당된다. <br>
기본형을 제외한 모든 타입은 Reference Type이며 java.lang.Object를 상속받는다. <br>
**Class, Interface, Array, Enum Type**등이 있다. <br>
<br>
Reference Type은 기본 1Byte + @(class variable size)합의 메모리를 사용한다. <br>
아무런 참조 값이 없으면 null을 리턴한다. <br>

---
## String <br>

String은 char배열이고 배열은 참조 유형이다. [제임스고슬링]<br>
```java
// String 객체를 생성하는 두 가지 방법
public class StringMemory {
    public static void main(String[] args) {
        String literal = "funch";
        String object = new String("funch");

        System.out.println(literal == object);
        System.out.println(literal.equals(object));
    }
}
```
위의 코드는 String 객체를 생성하는 두 가지 방법을 보여준다. <br>
동등 연산자(==)에서 primitive type의 ==연산은 값을 비교하지만, <br>
reference type에서는 주소를 비교한다. <br>
<br>
또한 리터럴 방식을 통해 만든 객체와 new 키워드를 통해 만든 객체는 서로 저장되는 메모리가 다르다. <br>
**reference 타입에서 new로 만든 객체는 heap영역에 저장되고, 리터럴 방식으로 만들어진 객체는 String Constant Pool영역에 저장되기 때문이다.** <br>
<br>
String도 reference Type이지만 다른 reference Type과 다른 점은 JVM에서 Spring constant Pool을 이용해 메모리 관리를 하는 것입니다. <br>
<br>
String은 immutable Class이므로 setter가 없고 재할당하게 되면 새로운 메모리 참조주소를 갖게 된다. <br>

---
## ⭐️ 참조자료 (감사합니다 :)) ⭐️ <br>

[이경환님의 JAVA Primitive, Reference Type 그리고 String](https://againsee.com/2018/06/15/java-datatype/) <br>
