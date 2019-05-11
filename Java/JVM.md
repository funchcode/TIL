## "목차" <br>
1. [JVM](#jvm-)
2. [실행과정](#실행과정-)
3. [ClassLoader](#classloader-)
4. [Execution Engine](#execution-engine-)
5. [Runtime Data Areas](#runtime-data-areas-)
6. [PC Register](#pc-register-)
7. [JVM Stack](#jvm-stack-)
8. [Native Method Stack](#native-method-stack-)
9. [Method Area](#method-area-)
10. [Heap](#heap-)

---
## JVM <br>

JVM (Java Virtual Machine) 자바 가상 머신이라고 한다. <br>
기본적으로 JAVA프로그램은 JVM 위에서 구동된다. <br>
운영체제 위에 JVM위에 JAVA프로그램이 실행되는 형태인데 운영체제에 상관없이 JVM만 있으면 어느 운영체제에서든 구동이 가능하다. <br>
JVM 위에서 동작하려면 자바 바이트코드가 필요하다. <br>
JVM의 역할로는 Java와 OS 사이의 중개자 역할을 해준다. <br>
**메모리관리와 가바지컬렉션**을 수행한다. <br>

---
## 실행과정 <br>

1. 프로그램이 실행되면 JVM은 OS로부터 이 프로그램이 필요로하는 메모리를 할당받고 메모리를 용도에 따라 여러 영역으로 나누어 관리한다.
2. 자바 컴파일러(javac)가 자바소스(java)를 읽어들어 자바 바이트코드(class)로 변환시킨다.
3. 변경된 class들을 ClassLoader를 통해 JVM 메모리 영역으로 로딩한다.
4. 로딩된 class들은 Execution Engine을 통해 해석된다.
5. 해석된 바이트코드는 Runtime Data Areas에 배치되어 실질적인 수행이 이루어진다.
6. Runtime Data Areas에서 JVM은 필요에 따라 Thread Synchronization과 GC같은 관리 작업을 수행한다.

---
## ClassLoader <br>

3가지 기본 클래스로더가 있다. <br>
1. Bootstrap ClassLoader(/jre/lib/rt.jar (Object.class, String.class))
2. Extension ClassLoader(/jre/lib/ext/*.jar, java.ext/\*.jar)
3. Application ClassLoader(-cp, Manifest)

#### Bootstrap ClassLoader <br>

3가지 기본 클래스 로더 중에 최상위 클래스로더이다. <br>
쉽게 말하면 JDK 클래스 파일을 로딩한다. <br>
Native C로 구현되어 있다. <br>

#### Extension ClassLoader <br>

환경변수에 지정된 폴더에 있는 클래스 파일을 로딩한다. <br>
Java로 구현되어 있으며 sun.misc.Launcher 클래스 안에 static 클래스로 구현되어 있으며 URLClassLoader을 상속하고 있다. <br>

#### Application ClassLoader <br>

Application ClassLoader는 -cp나 JAR 파일 내에 있는 Manifest파일의 Class-path 속성 값으로 지정된 폴더에 있는 클래스를 로드한다. <br>
개발자가 애플리케이션 구동을 위해 직접 작성한 대부분의 클래스들은 이 애플리케이션 클래스로더를 의해 로딩된다. <br>
역시 Java로 구현되어 있으며 sun.misc.Launcher 클래스 안에 static 클래스로 구현되어 있고 URLClassLoader를 상속하고 있다. <br>

클래스 로더에는 3가지 원칙이 있다. <br>
1. Delegation Principle(위임 원칙)
2. Visibility Principle(가시범위 원칙)
3. Uniqueness Principle(유일성 원칙)

#### Delegation Principle <br>

클래스 로딩이 필요할 때 3가지 기본 클래스로더의 윗 방향으로 클래스로딩을 위임하는 것을 말한다. <br>
main()메서드에 포함된 ClassLoaderRunner 클래스에서 개발자가 직접 작성한 클래스를 로딩하는 과정을 살펴보자면 <br>
1. ClassLoaderRunner는 자기 자신을 로딩한 애플리케이션 클래스로더에게 로딩하고 싶은 클래스를 요청한다.
2. 요청을 받은 Application 클래스로더는 스스로 직접 로딩하지 않고 작업을 상위로 위임한다.
3. 요청을 받은 Extention 클래스로더도 스스로 직접 로딩하지 않고 작업을 상위로 위임한다.
4. 최상위인 Bootstrap 클래스로더가 요청을 받고 해당 클래스를 찾아 있으면 로딩 후 반환하고 없으면
5. 하위 Extension 클래스로더 클래스패스에서 해당 클래스를 찾는다. 찾아 있으면 로딩 후 반환 없으면
6. 하위 Application 클래스로더 클래스패스에서 찾고 있으면 로딩 후 반환 없으면 
7. ClassNotFoundException을 발생시킨다.

#### Visibility Principle <br>

가시범위 원칙은 하위 클래스로더는 상위클래스로더가 로딩한 클래스를 볼 수 있지만, 상위 클래스로더는 하위 클래스 로더가 로딩한 클래스를 볼 수 없다는 원칙이다. <br>
> 만약에 개발자가 만든 클래스를 로딩하는 애플리케이션 클래스로더가 부트스트랩 클래스로더에 의해 로딩된 String.class를 볼 수 없다면 애플리케이션은 String.class를 사용할 수 없을 것이다. <br> 따라서 하위는 상위를 볼 수 있어야 애플리케이션이 제대로 동작할 수 있다.

상위에서도 하위를 볼 수 있게 된다면 상하위 구분이 없어지게 되는 것이고 클래스로더를 3가지로 나눈 의미가 없어진다. <br>

#### Uniqueness Principle <br>

유일성 원칙은 하위 클래스로더는 상위 클래스로더가 로딩한 클래스를 다시 로딩하지 않기 위해 로딩된 클래스의 유일성을 보장하는 것이다. <br>
유일성을 식별하는 기준은 클래스의 binary name인데 이는 클래스의 풀 네임을 말한다. <br>

---
## Execution Engine <br>

ClassLoader에 의해 Runtime Data Areas에 바이트코드가 배치되었는데 이 Load된 클래스의 바이트코드를 실행하는 Runtime Module이 Execution Engine이다. <br>
바이트 코드를 그대로 사용하는 것이 아니라 바이트코드를 기계어로 변경해주는 역할을 한다. <br>
변경하는 방법으로는 <br>
1. Interpreter
2. JIT(Just-In-Time) Compiler

#### Interpreter <br>

인터프리터는 하나의 명령어를 그때그때 해석하여 실행하는 방식이다. <br>

#### JIT(Just-In-Time) compiler <br>

인터프리터의 단점인 성능과 속도를 개선하기 위해 도입되었다. <br>
실행 엔진이 인터프리터를 이용해 명령어를 하나씩 실행하지만 JIT 컴파일러는 적정한 시간에 전체 바이트 코드를 네이티브 코드로 변경한다. <br>
이후에는 실행 엔진이 인터프리터 대신 네이티브 코드로 컴파일된 코드를 실행한다. <br>

---
## Runtime Data Areas <br>

런타임 데이터 영역은 JVM이 운영체제 위에서 실행되면서 할당받은 메모리 영역을 말한다. <br>
런타임 데이터 영역은 총 5개의 영역으로 나눌 수 있다. <br>
1. PC Register
2. JVM Stack
3. Native Method Stack
4. Heap
5. Method Area(Runtime constant Pool)

---
## PC register <br>

레지스터는 각 스레드마다 하나씩 존재하며 스레드가 시작될 때 생성된다. <br>
PC Register는 Thread가 어떤 명령어로 실행되어야 할지에 대한 기록을 하는 부분으로 현재 수행중인 JVM명령의 주소를 갖고있다. <br>

---
## JVM Stack <br>

프로그램 실행과정에서 임시로 할당되었거나 메소드를 빠져나가면 바로 소멸되는 특성의 데이터를 저장하기 위한 영역이다. <br>
각종 형태의 변수나 임시데이터, 스레드나 메서드의 정보를 저장한다. <br>
호출된 메서드의 매개변수, 지역변수, 리턴 및 연산 시 일어나는 값들이 임시로 저장된다. <br>

---
## Native Method Stack <br>

자바 프로그램이 컴파일되어 생성되는 바이트 코드가 아닌 실제 실행할 수 있는 기계어로 작성된 프로그램을 실행시키는 영역이다. <br>
Java가 아닌 다른 언어로 작성된 코드를 위한 공간이다. <br>

---
## Method Area(=Class area, =Static area) <br>

클래스 정보를 처음 메모리 공간에 올릴 때 초기화되는 대상을 저장하기 위한 메모리 공간이다. <br>
사실상 컴파일 된 바이트코드의 대부분이 메소드 바이트코드이기 때문에 거의 모든 바이트코드가 올라간다고 봐도 상관없다. <br>
Runtime Constant Pool이라는 별도의 메모리 공간도 존재한다. <br>
이는 상수 자료형을 저장하여 참조하고 중복을 막는 역할을 한다. <br>
<br>
올라가는 정보 <br>
1. Field 정보 (멤버변수의 이름, 데이터 타입, 접근 제어자의 정보)
2. Method 정보 (메소드의 이름, 리턴타입, 매개변수, 접근 제어자의 정보)
3. Type 정보 (class/interface정보, 전체이름, super class의 정보)

Method Area는 클래스 데이터를 위한 공간이라면 Heap 영역은 객체를 위한 공간이다. <br>
Heap과 마찬가지로 GC의 관리대상에 포함된다. <br>

---
## Heap <br>

객체를 저장하는 가상 메모리 공간이다. new 연산자로 생성된 객체와 배열을 저장한다. <br>
Class Area 영역에 올라온 클래스들만 객체로 생성할 수 있다. <br>
<br>
Heap은 크게 세 부분으로 나눌 수 있다. <br>
1. Permanent Generation
2. New/Young Generation
3. Old Generation

#### Permanent Generation <br>

생성된 객체들의 정보의 주소를 가지고 있는 공간이다. <br>
Class Loader에 의해 Load되는 클래스, 메서드 등에 대한 Meta정보가 저장되는 영역이고 JVM에 의해 사용된다. <br>
Reflection을 사용하여 동적으로 클래스가 로딩되는 경우에 사용되는 공간이다. <br>
내부적으로 Reflection 기능을 자주 사용하는 Spring Framework를 이용할 경우 이 영역에 대한 고려가 필요하다. <br>

#### New/Young Generation <br>

Eden, Survivor1, Survivor2 영역이 있는데 새로 생성되는 객체가 저장되는 공간이다. <br>
Eden 영역이 가득차게 되면 Minor GC가 발생하면서 참조되지 않는 객체를 제거하고 Survivor1|Survivor2 영역으로 복사되고 나머지 New/Young영역은 삭제된다. <br>
Survivor1 혹은 Survivor2 둘 중 하나는 비어있는 상태여야 한다. <br>
Survivor영역에 데이터가 가득차거나 일정 age가 넘게되면 Old영역으로 복사된다. <br>

#### Old Generation <br>

Old 영역은 major GC에 의해 관리된다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[Java 클래스로더 훑어보기](https://homoefficio.github.io/2018/10/13/Java-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C%EB%8D%94-%ED%9B%91%EC%96%B4%EB%B3%B4%EA%B8%B0/) <br>
[JBee님의 자바가상머신, JVM이란 무엇일까](https://asfirstalways.tistory.com/158) <br>
