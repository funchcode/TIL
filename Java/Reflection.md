## "목차" <br>
1. [리플렉션이란](##리플렉션이란)
2. [리플렉션 개념 익히기](##리플렉션-개념-익히기)
3. [리플렉션의 특성](##리플렉션의-특성)
4. [코드로 이해하기](##코드로-이해하기)

---
## 리플렉션이란 <br>

Reflection의 사전적의미로는 **투영, 반사**라는 의미를 가지고 있다. <br>
자바에서 리플렉션이란 객체를 통해 클래스의 정보를 분석해 내는 프로그램 기법을 말한다. <br>

---
## 리플렉션 개념 익히기 <br>

일반적으로 클래스를 통해 객체를 생성했다. <br>
코딩할 때 클래스에 명시된 필드와 메서드를 알고 있기 때문에 클래스의 메소드 또는 필드를 이용하는데 아무런 불편함이 없다. <br>
객체를 생성할 때 클래스의 메타데이터를 먼저 읽는다. <br>
메타데이터를 이용해 객체를 생성할 수도 있다. <br>

---
## 리플렉션의 특성 <br>

**리플렉션 특징** <br>
- 런타임 중에 객체를 이용해서 객체의 정보를 조사할 수 있다.
- 런타임 중에 프로그램의 상태나 기능을 동적으로 조작할 수 있다.
- 동적이며 유연한 프로그래밍이 가능하기 때문에 프레임워크에서 주로 사용된다.

**리플렉션을 통해 얻을 수 있는 정보** <br>
- 클래스 이름
- 클래스 제어자(public, private, static)
- 패키지 정보(java.lang.System)
- 클래스의 부모 클래스
- 클래스의 생성자
- 클래스의 메서드
- 클래스의 변수
- 클래스의 Annotation

---
## 코드로 이해하기 <br>

```java
public class WorkerValue {
    public static int POSITION_MANAGER = 0;
    public static int POSITION_ASSISTANT = 1;
    public static int POSITION_EMPLOYEE = 2;

    private String name;
    private int position;

    public WorkerValue() {
        name = null;
        position = Integer.MAX_VALUE;
    }
    public WorkerValue(String name, int position) {
        this.name = name;
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WorkerValue [name=");
        sb.append(name);
        sb.append(", position =");
        sb.append(position);
        sb.append("]");
        return sb.toString();
    }
}
```
위의 코드는 리플렉션 예제를 위한 코드이다. <br>
리플랙션을 하기 위해서는 java.lang.Class 클래스가 필요하다. <br>

```java
// 1. 대상 객체에서 제공하는 getClass() 메소드 이용
// 사용법 : Class [객체이름 = 대상 객체.getClass()];
String str = new String();
WorkerValue workerValue = new WorkerValue();
Class class1 = str.getClass();
Class class2 = workerValue.getClass();

// 2. 대상 클래스를 이용하여 Class 객체를 받아오는 방ㅇ법
// 사용법 : Class [객체이름 = 대상객체.class];
Class class3 = String.class;
Class class4 = WorkerValue.class;

// 3. 대상 클래스의 이름을 이용하여 Class 객체를 받아오는 방법
// 사용법 : Class [객체이름 = Class.forName([패키지명을 포함한 클래스 이름])]
Class class5 = Class.forName("java.lang.String");
Class class6 = Class.forName("com.abc.WorkerValue");
```

실제로 객체를 생성해보면 아래 코드 <br>

```java
public class ReflectionSample1 {
    public static void main(String[] args) {
        WorkerValue workerValue = new WorkerValue("Funch", WorkerValue.POSITION_MANAGER);
        Class class1 = WorkerValue.class;
        Class class2 = workerValue.getClass();
        Class class3 = null;

        try {
            class3 = Class.forName("WorkerValue");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("class1 : "+System.identityHashCode(class1)); // class1 : 1639705018
        System.out.println("class2 : "+System.identityHashCode(class2)); // class1 : 1639705018
        System.out.println("class3 : "+System.identityHashCode(class3)); // class1 : 1639705018
    }
}
```

HashCode가 같으면 같은 객체이다. <br>

Class 클래스에는 Annotaion이나 제네릭과 같은 정보를 확인할 수 있는 메소드들도 있다. <br>
리플렉션은 단순히 해당 클래스에 어떤 메소드, 필드가 있냐에 그치지 않고 반환받는 Method, Field 또는 Constructor 객체를 사용하여 동적으로 실행하는 프로그래밍을 할 수 있다. <br>