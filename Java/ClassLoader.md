## "목차" <br>
1. [JVM의 클래스로더](##JVM의-클래스로더)
2. [클래스 로더 동작 예제](##클래스-로더-동작-예제)
3. [클래스 로더 계층 구조](##클래스-로더-계층-구조)
4. [Delegation Model](##delegation-model)

---
## JVM의 클래스로더 <br>    
자바 프로그램은 한 개 혹은 여러 개의 클래스들의 조합으로 실행된다. <br>
실행 시에 모든 클래스 파일이 한 번에 JVM 메모리에 로딩되지 않고 요청되는 순간에 로딩된다. <br>
이는 자바의 클래스 로더가 이런 역할을 수행한다. <br>
<br>
클래스 로더란 '.class'바이트 코드를 읽어들여 class 객체를 생성하는 역할을 담당한다. <br>
즉 클래스 로더는 클래스가 요청될 때 파일로부터 읽어 메모리에 로딩하는 역할을 하며 자바 가상 머신의 중요한 요소 중 하나이다. <br>
<br>
클래스 로더가 classpath라는 환경 변수에 등록된 디렉토리에 있는 모든 클래스들을 먼저 JVM에 로딩한다. <br>
JVM에 로딩된 클래스만이 JVM에서 객체로 사용할 수 있다. <br>
클래스 로딩은 클래스를 로딩하는 시점 또는 실행 중간에도 할 수 있다. <br> 
<br>
자바의 클래스로더는 세부적으로 로딩, 링크, 초기화라는 세가지 단계를 거친다. <br>
- 로딩: 클래스 파일을 바이트 코드로 읽어 메모리로 가져오는 과정
- 링크: 가장 복잡한 과정으로 읽은 바이트 코드가 자바 규칙을 따르는지 검증 및 클래스에 정의된 필드, 메서드, 인터페이스들을 나타내는 데이터 구조를 준비하며 그 클래스가 참조하는 다른 클래스를 로딩한다.
- 초기화: 슈퍼 클래스 및 정적 필드를 초기화한다.

---
## 클래스 로더 동작 예제 <br>
```java
// Hello.class
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
```
위 코드를 보면 Hello 클래스를 로드할 때 우선 이 클래스가 참조하고 있는 Object, String, System 클래스가 아직 로드되지 않았으므로 Hello 클래스를 로드하는 일을 잠시 중단하고 우선 이 클래스들을 로딩한다. <br>이 처럼 한 클래스의 로드 타임에 필요한 다른 클래스들을 동적 로딩하는 것을 로드타임 **동적 로딩**이라고 한다. <br>
<br>
```java
public interface PrintInterface {
    public void print();
}

public class ClassLoadingSample1 implements PrintInterface {
    @Override
    public void print() {
        System.out.println("Sample1");
    }
}

public class ClassLoadingSample2 implements PrintInterface {
    @Override
    public void print() {
        System.out.println("Sample2");
    }
}

public class RuntimeLoading {
    public static void main(String[] args) {
        RuntimeLoading runtimeLoading = new RuntimeLoading();

        try {
            Class<?> cls = Class.forName(args[0]);
            Object obj = cls.newInstance();
            PrintInterface print = (PrintInterface)obj;
            print.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
```
위의 코드는 실행할 때 매개변수로 입력받은 클래스를 런타임시 동적으로 로딩하는 예시이다. <br>
Class.forName()메소드로 리턴 되는 클래스 객체는 해당 클래스에 대한 객체가 아닌 해당 클래스에 대한 메타 정보를 가지는 클래스 객체이다. <br>
cls.newInstance()를 통해 메타 데이터를 이용해서 해당 객체를 생성한다. <br>
<br>
클래스 로더가 최초 클래스를 로딩할 때 '.class'바이트 파일에서 여러 정보를 추출하여 Class객체로 만든다. <br>
Class 클래스는 클래스의 이름, 메소드, 생성자, 필드명, 부모 클래스의 정보를 개발자가 실행하거나 참조할 수 있다. <br>
이와 같은 기법을 **리플렉션 기법**이라고 한다. <br>
<br>
**newInstance()를 호출하면 String객체를 제외한 모든 클래스는 Object를 리턴한다. String 클래스만 유일하게 String객체를 리턴한다.** <br>


---
## 클래스 로더 계층 구조 <br>

1. Bootstrap Class Loader
2. Extensions Class Loader
3. System Class Loader
4. User Defined Class Loader

**Bootstrap Class Loader** <br>
- JVM이 실행될 때 맨 처음 실행되는 클래스 로더로 $JAVA_HOME/jre/lib에 있는 JVM 실행에 기본적으로 필요한 가장 기본적인 라이브러리를 로딩하는 역할을 한다. 이 클래스 로더는 자바가 아닌 네이티브 코드로 구현되어 있다.

**Extensions Class Loader** <br>
- Bootstrap 클래스 로더 로딩 후 기본적으로 로딩되는 클래스로 $JAVA_HOME/jre/lib/ext 에 있는 클래스들이 로딩되는데 이 클래스들은 별도로 classpath에 잡혀 있지 않아도 로딩된다.

**System Class Loader** <br>
- Class PATH에 정의되거나 JVM option에서 -cp, -classpath에 지정된 클래스가 로딩된다.

**User Defined Class Loader** <br>
- 사용자가 직접 생성해서 사용하는 클래스 로더이다.

클래스 로더 계층 구조에서 하위 클래스 로더는 부모 클래스 로더가 로딩한 클래스를 찾을 수 있지만 그 반대는 불가능하다. <br>
클래스명이 같더라도 패키지명이 다르면 구분가능하다. <br>

---
## Delegation Model <br>

클래스 로더가 클래스 로딩을 요청받게 되면 **캐시, 부모클래스 로더, 자신 클래스 로더 순서**로 클래스 로딩이 된다. <br>
<br>
**이전에 로딩된 클래스**는 해당 클래스 로더의 캐시에 저장되고 다음 번 요청시 캐시에 저장된 내용을 사용한다. <br>
**해당 클래스를 로딩한 적이 없다면** 상위 부모 클래스 로더에게 클래스 요청을 위임한다. <br>
요청을 위임받은 부모 클래스 로더도 자신의 캐시를 먼저 확인하고 해당 클래스를 이전에 로딩한 적이 없다면 그 클래스의 부모 클래스 로더에게 위임하는 동일한 과정을 반복한다. <br>
**최상위 부모 클래스**에게까지 요청이 위임되었고 이전에 클래스가 로딩된 적이 없었다면 최상위 부모부터 자식 클래스 로더 순서로 클래스 로딩을 시도한다. <br>
**이러한 방식을 델리게이션 모델**이라고 한다. <br>
