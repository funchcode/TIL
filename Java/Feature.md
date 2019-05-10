## "목차" <br>
1. [특징](##특징)
2. [객체 지향 언어](##객체-지향-언어)
3. [함수적 스타일 코딩 지원](##함수적-스타일-코딩-지원)
4. [메모리 자동 관리](##메모리-자동-관리)
5. [다양한 애플리케이션 개발 가능](##다양한-애플리케이션-개발-가능)
6. [멀티스레드 쉽게 구현 가능](##멀티스레드-쉽게-구현-가능)
7. [동적 로딩 지원](##동적-로딩-지원)
8. [오픈소스 라이브러리 풍부](##오픈소스-라이브러리-풍부)
9. [높은 이식성](##높은-이식성)

---
## 특징 <br>

썬마이크시스즈에서 최초 발표한 언어이다. <br>
오우크(Oak)언어에서부터 시작해서 인터넷 프로그래밍 언어로 발전하면서 자바라는 이름으로 변경됐다. <br>
2010년 오라클에서 썬을 인수하여 Java 관리,개발,배포를 주관하고 있다. <br>

---
## 객체 지향 언어 <br>

자바는 처음부터 OOP 개발용 언어로 설계된 언어이다. <br>
프로그래밍에 필요한 데이터를 추상화시켜 상태와 행위를 가진 객체를 만들고 객체들 간의 유기적인 상호작용(메시지)를 통해 로직을 구성하여 프로그래밍을 가능케한다. <br>
🏷 [OOP 제대로 정리필요](../Principle/OOP.md)

---
## 함수적 스타일 코딩 지원 <br>

자바 8부터 함수적 프로그래밍을 위해 **람다식**을 지원한다. <br>
<br>
**함수적 스타일이란** <br>
자바 코드가 매우 간결해지는 장점과 컬렉션의 요소를 필터링하거나 원하는 결과를 쉽게 집계할 수 있기 때문에 사용한다. <br>
람다식의 형태는 매개 변수를 가진 코드 블록이지만, 런타임 시에 익명 구현 객체를 생성한다. <br>
```java
Runnable runnable = new Runnable() {
    public void run() {};
}

// 람다식을 적용하면
Runnable runnable = ()->{};
```
사용목적으로는 인터페이스가 가지고 있는 메서드를 간편하게 즉흥적으로 구현해서 사용하기 위한 목적을 갖고 있다. <br>

---
## 메모리 자동 관리 <br>

C언어의 경우 개발자가 메모리를 직접 관리해줘야 하지만, 자바에서는 가비지 컬렉터(garbage collector)에 의해 사용하지 않는 객체는 자동으로 메모리에서 제거된다. <br>
🏷 GC 동작원리 제대로 정리 필요 <br>

---
## 다양한 애플리케이션 개발 가능 <br>

자바언어로 **콘솔 프로그램, UI 어플리케이션, 서버 어플리케이션, 모바일 앱 등 등** 다양한 프로그램을 개발할 수 있다. <br>
<br>
Java SE : 기본 에디션 <br>
- 자바 프로그램을 실행시키는 최소한의 JVM을 정의한다.
- 구현체: JDK(Java Development Kit)

Java EE : 서버 애플리케이션 개발을 위한 에디션 <br>
- 웹 어플리케이션, 웹 서비스, 분산 컴포넌트 등 개발을 위한 도구와 API 정의
- 구현체: WAS(Web Application Server) Tomcat 등

Java ME : 임베디드, 모바일 장비 개발을 위한 도구와 API <br>

---
## 멀티 스레드 쉽게 구현 가능 <br>

멀티 스레드가 필요한 경우 <br>
- 동시에 여러가지 작업을 하는 경우
- 대용량 작업을 빨리 처리할 경우

🏷 멀티 스레드 제대로 정리 필요 <br>

---
## 동적 로딩 지원 <br>

미리 객체를 만들지 않고, 필요한 시점에 동적으로 로딩해서 객체를 생성할 수 있다. <br>
유지 보수 시에는 특정 객체만 쉽게 수정 및 교체해서 사용이 가능하다. <br>
🏷 동적 로딩 제대로 정리 필요 <br>

---
## 오픈소스 라이브러리 풍부 <br>

자바는 오픈소스 언어이고 자바를 이용해서 만들어진 라이브러리가 굉장히 많다. <br>
라이브러리를 이용하면 시간비용을 낮출 수 있고 어플리케이션을 만들기 용이하다. <br>
[라이브러리 사이트](https://www.theopensourcery.com/)<br>

---
## 높은 이식성 <br>

이식성이 높은 이유는 자바는 JVM(Java Virtual Machine)위에서 구동되기 때문이다. <br>
즉 운영체제에 상관없이 자바 애플리케이션이 구동된다. <br>
<br>
다른 언어(C)와의 차이점이라고 하면 C언어는 운영체제의 종류에 따라 타입의 크기가 달라지지만 자바는 타입의 크기가 고정되어 있다. <br>