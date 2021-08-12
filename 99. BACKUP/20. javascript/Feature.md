## "목차" <br>
1. [Java와 Javascript 다른 점](#java와-javascript-다른-점-)
2. [undefined와 null](#undefined와-null-)
3. [클로저](#클로저-)
4. [프로토타입기반 프로그래밍](#프로토타입기반-프로그래밍-)

---
## Java와 Javascript 다른 점 <br>

Javascript는 Java애플릿의 대체자로 만들어지게 된 배경으로 javascript라는 이름을 사용할 뿐 연관성이 없는 언어이다. <br>
- Java는 **type static** 언어인 반면 Javascript는 **none type**언어이면서 동적으로 자료형을 검사한다.
- Java는 Class 기반 컴파일 + 인터프린트 OOP언이이지만 Javascript는 prototype 기반의 인터프린트 언어이다.
- Javascript는 스크립트 언어의 특성 상 컴파일 없이 동작하기 때문에 언어를 번역할 수 있는 엔진이 동적으로 모든 사항을 처리하기 때문에 오류 검출에 용이하지 못한다.

---
## undefined와 null <br>   

Javascript는 undefined라는 특별한 형태가 존재한다. <br>
<br>
**undefined와 null의 차이**
- 모든 것이 Object로 통하는 javascript에서의 null은 값이 아닌 객체 참조의 연결을 해지하는 것을 말한다.
- 즉 null을 가진 경우에는 어떤 참조값이 존재하지 않음으로 비어있는 값을 가진 변수가 되는 것이다.
- 특별히 할당된 값이 없는 경우 일반적인 언어처럼 null이 아니고 javascript엔진에 의해 undefined가 할당 된다.
- 객체가 소유하지 않은 프로퍼티에 접근할 경우에도 undefined가 반환된다.

🏷 [javascript 타입에 대한 학습 필요](./Type.md) <br>   

---
## 클로저 <br>

클로저는 내부함수와 밀접한 관계를 가지고 있는 주제이다. <br>
내부함수는 외부함수의 지역변수에 접근할 수 있는데 외부함수의 실행이 끝나서 외부함수가 소멸되더라도 내부함수가 외부함수의 지역변수에 접근할 수 있다. <br>
<br>
클로저는 객체의 메소드에서도 사용할 수 있다. <br>
```javascript
function factory_movie(title) {
    return {
        get_title: function() {
            return title;
        },
        set_title: function(_title) {
            title = _title;
        }
    }
}
```
동일한 외부함수 안에서 만들어진 내부함수나 메소드는 외부함수의 지역변수를 공유한다. <br>
위의 코드를 보면 Private한 속성으로 title을 사용할 수 있음을 알 수 있다. <br>

---
## 프로토타입기반 프로그래밍 <br>

자바스크립트에서는 확장과 객체의 재사용을 가능하게 해주는 프로토타입 상속이라는 것이 있다. <br>
<br>
**프로토타입 기반 프로그래밍이란**
- 객체의 원형인 프로토타입을 이용하여 새로운 객체를 만들어내는 프로그래밍 기법인데 이렇게 만들어진 객체 역시 프로토타입을 가지고 있다.

```javascript
var A = function () {};
A.prototype.x = function() {
    console.log('hello');
};

var B = new A();
var C = new A();

B.x();      // 'hello'
C.x();      // 'hello'

A.prototype.x = function() {
    console.log('world');
}

B.x();      // 'world'
C.x();      // 'world'
```
위의 코드는 자바스크립트의 프로토타입 체인을 살펴볼 수 있는 코드이다. <br>
**__proto__**와 **prototype**의 차이점은 객체가 생성될 때 조상이었던 함수의 Prototype Object를 가리킨다. <br>
prototype은 함수를 정의하게 되면 함수만 생성되는 것이 아니라 해당 함수의 Prototype Object도 같이 생성된다. <br>
Protype Object는 일반적인 객체이므로 속성을 마음대로 추가/삭제할 수 있다. <br>
<br>
프로토타입 체인이란 프로토타입을 상속해서 만들어지는 객체들간의 연관관계를 의미한다. <br>
프로퍼티간의 **__proto__**를 이어가다보면 Object 객체의 Prototype Object에 다다른다. <br>

🏷 [javascript Prototype에 대한 학습 필요](./Prototype.md) <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[front-end 개발자 인터뷰 문제 - javascript 영역](http://insanehong.kr/post/front-end-developer-interview-javascript/) <br>
[생활코딩 클로저](https://opentutorials.org/course/743/6544) <br>
[오승환님의 프로토타입 이해하기](https://medium.com/@bluesh55/javascript-prototype-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-f8e67c286b67) <br>