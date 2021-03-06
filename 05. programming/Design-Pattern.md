# 목차
1. [디자인 패턴이란](#디자인-패턴이란)
2. [전략패턴:Strategy](#전략패턴:Strategy)
3. [템플릿 메서드 패턴(Template Method)](#템플릿 메서드 패턴(Template Method)-)
4. [상태 패턴(State)](#상태 패턴(State)-)
5. [데코레이터 패턴(Decorator)](#데코레이터 패턴(Decorator)-)
6. [프록시 패턴(Proxy)](#프록시 패턴(Proxy)-)
7. [팩토리 메서드 패턴(Factory Method)](#팩토리 메서드 패턴(Factory Method)-)

## 참고자료
* 📚 개발자가 반드시 정복해야 할 객체 지향과 디자인 패턴

# 디자인 패턴이란
소프트웨어를 설계할 때 특정 맥락에서 자주 발생하는 고질적인 문제들이 또 발생했을 때 재사용할 수 있는 해결책을 말한다.  

# 전략패턴:Strategy
## 정의
동일 계열의 알고리즘을 정의하고, 각 알고리즘을 캡슐화하여, 이들을 상호교환 가능하도록 만든다.  
알고리즘을 사용하는 클라이언트와 상관없이 독립적으로 알고리즘을 다양하게 변경할 수 있게한다.
## 전략패턴이 필요한 상황
👇 

## 전략패턴 적용

## 장점

> __Reference__  
> * 🔗 [참고자료의 '개발자가 반드시 정복해야...' 책을 보고 정리한 글](https://velog.io/@kyle/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-%EC%A0%84%EB%9E%B5%ED%8C%A8%ED%84%B4%EC%9D%B4%EB%9E%80)  
> * 🔗 ['헤드 퍼스트 디자인 패턴' 책을 보고 정리한 글](https://johngrib.github.io/wiki/strategy-pattern/)

서로 다른 정책들이 한 코드에서 발생할 경우 IF-ELSE의 코드로 정책들이 추가되는 경우 적용한다. <br>
정책들을 추상화 시켜 전략타입(Strategy)를 만들고 해당 전략 정책에 대한 인터페이스를 만들면 된다. <br>
해당 전략 기능 자체의 책임을 가지고 있는 객체를 Context라고 부른다. <br>
<br>
전략패턴에서 콘텍스트는 사용할 전략을 직접 선택하지 않고 DI를 통해 전략을 전달한다. <br>
전략패턴을 이용했을 경우 이점은 콘텍스트 코드의 변경없이 새로운 전략을 추가할 수 있다는 점인데 이는 SOLID원칙에서 O(Open-Close)개방 폐쇄 정책을 만족시키는 구조라고 볼 수 있다. <br>
<br>
비슷한 코드를 실행하는 IF-ELSE 블록과 동일한 기능의 알고리즘의 변경이 필요할 때 전략패턴을 사용한다. <br>

---
## 템플릿 메서드 패턴(Template Method) <br>

실행 과정/단계는 동일한데 각 단계 중 일부의 구현이 다를 경우에 사용할 수 있는 패턴이다. <br>
실행 과정을 구현한 상위 클래스 <br>
실행 과정의 일부 단계를 구현한 하위 클래스 <br>
모든 하위 타입에 동일하게 적용되는 실행 과정을 제공하기 때문에 템플릿 메서드라고 부른다. <br>
템플릿 메서드를 사용하면 코드가 중복되는 것을 방지해주고 유지보수를 용이하게 해준다. <br>
<br>
템플릿 메서드의 특징으로 상위 클래스가 흐름의 주체가 된다. <br>
일반적인 경우 하위 타입이 상위 타입의 기능을 재사용할지 여부를 결정하기 때문에 하위 타입에서 흐름제어를 하는데, 템플릿 메서드의 경우 상위 타입의 템플릿 메서드가 모든 실행 흐름을 제어하고 하위 타입의 메서드는 템플릿 메서드에서 호출되는 구조를 가지고 있다. <br>

---
## 상태 패턴(State) <br>

기능이 상태에 따라 다르게 동작해야 할 경우, 사용할 수 있는 패턴이다. <br>
상태패턴에서 중요한 점은 상태 객체가 기능을 제공한다는 점이다. <br>
<br>
상태 패턴의 장점은 새로운 상태가 추가되더라도 콘텍스트 코드가 받는 영향은 최소화 된다는 점이다. <br>
상태 패턴을 적용하지 않았을 경우에는 상태가 추가될 때마다 IF-ELSE 조건문을 이용한 방식이므로 코드가 복잡해져서 유지보수를 어렵게 만들지만, 상태 패턴을 적용할 경우 상태가 많아지더라도 코드의 복잡도는 증가하지 않기 때문에 유지보수에 유리하다. <br>
하지만 상태를 추가할 때마다 클래스의 개수도 증가하는 점이 있다. <br>
<br>
상태 패턴을 이용할 때 상태 변경은 누가 하는 가를 결정해야 한다. <br>
콘텍스트의 상태변경을 콘텍스트에서 하게 된다면 상태 변경 규칙이 자주 변경될 시에 콘텍스트의 상태변경처리 코드가 복잡해질 가능성이 높다. <br>
상태 개수가 적고 상태 변경 규칙이 거의 바뀌지 않을 경우 사용해야한다. <br>
또는 상태 객체에서 콘텍스트의 상태를 변경하는 경우가 있는데, 콘텍스트에 영향을 주지 않으면서 상태를 추가하거나 변경 규칙을 바꿀 수 있게 된다. <br>
하지만, 상태 구현 클래스가 많아질수록 상태 변경 규칙을 파악하기 어렵다. <br>

---
## 데코레이터 패턴(Decorator) <br>

객체의 결합을 통해 기능을 동적으로 유연하게 확장할 수 있게 해주는 패턴이다. <br>
즉, 기본기능에 추가할 수 있는 기능의 종류가 많은 경우에 각 추가 기능을 Decorator클래스로 정의한 후 필요한 Decorator 객체를 조합함으로써 추가 기능의 조합을 설계하는 방식이다. <br>
<br>
데코레이터 패턴이 갖는 구성요소는 <br>
1. ConcreteComponent : 기본 기능을 구현하는 클래스이다.
2. Component : 기본 기능을 뜻하는 ConcreteComponent와 추가 기능을 의미하는 Decorator의 공통 기능을 정의한 클래스이다. 즉 클라이언트는 Component를 통해 실체 객체를 이용한다.
3. Decorator : 많은 수가 존재하는 구체적인 Decorator의 공통 기능을 제공한다.
4. ConcreteDecorator : Decorator의 하위 클래스로 기본 기능에 추가되는 개별적인 기능을 뜻한다.

🏷 추가 학습 필요하다. <br>

---
## 프록시 패턴(Proxy) <br>

Proxy의 해석은 대리자, 대변인이라는 뜻이다. <br>
흐름제어를 할 뿐, 결과값을 조작하거나 변경시키면 안된다. <br>
인터페이스로 목적을 가진 메서드를 정의한 후 Real Subject 해당 인터페이스를 가지고 의도한 목적을 구현한다. <br>
그리고 Proxy 객체를 작성하는데 만들어 놓은 인터페이스를 받고 실체 객체를 가지고와 실체 객체를 멤버로 가지고 있다가 메소드의 호출 전, 후에 원하는 동작을 정의한 후 실체 객체를 호출한다. <br>

🏷 추가 학습 필요하다. <br>

---
## 팩토리 메서드 패턴(Factory Method) <br>

기본적으로 팩토리는 공장이란 뜻을 내포하고 있는데 팩토리 메서드 패턴도 무언가를 위한 공장이라고 생각하면 된다. <br>
**객체를 만들어내는 부분을 서브 클래스에 위임하는 패턴**이라고 말하는데, <br>
즉, new 키워드를 호출하는 부분을 서브 클래스에게 위임하는 것이다. 결국 팩토리 메서드 패턴은 객체를 만들어내는 공장을 만들어내는 패턴이라고 이해하면 된다. <br>
<br>
팩토리 메서드는 클래스간의 결합도를 낮추기 위해 사용한다. <br>
클래스의 변경점이 생겼을 경우 다른 클래스에 얼마나 영향을 미치는 가인데 팩토리 메서드를 사용하면 효율적인 코드 제어를 할 수 있고 의존성을 제거할 수 있다. <br>

