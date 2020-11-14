## "목차" <br>
1. Arrows
2. let + const
3. Classes
4. Modules
5. Promises
6. Reflect API
7. Tail Calls

---
## Arrows <br>

Arrows(화살표)함수는 ```=>```문법을 사용하는 축약형 함수이다. <br>
일반 함수의 자신을 호출하는 객체를 가리키는 dynamic this와는 달리 arrows함수는 코드의 상위 스코프를 가리키는 lexical this이다. <br>
콜백함수를 사용하면 this가 상위컨텍스트를 가지게 된다. <br>

---
## let + const <br>

블록 유효 범위를 갖는 새로운 변수 선언 방법이다. <br>
const는 재할당 및 재선언이 불가능하다. <br>
하지만 let은 var와 유사하게 동작하지만, 차이점으로는 var의 유효 범위는 전체 외부 함수까지이지만 let은 변수를 선언한 블록과 그 내부 블록들에서 유효하다. <br>
<br>
var는 function-scoped이고 const, let은 block-scoped이다. <br>
```javascript
// var는 재할당 재선언 모두 가능
var a = "hello";
var a = "wolrd";

// let은 재할당은 가능하지만 재선언은 불가능하다.
let b = "super";
b = "magic";
let b = "wow"; // Uncaught SyntaxError: Identifier 'b' has already been declare

// const는 재할당, 재선언 모두 불가능하다.
const c = "power";
c = "over"; // Uncaught TypeError: Assignment to constant variable.
const c = "whelming"; // Uncaught SyntaxError: Identifier 'c' has already been declared
```

---
## Classes <br>

EC6 클래스는 프로토타입 기반 객체지향 패턴을 더 쉽게 사용할 수 있는 대체제이다. <br>
```javascript
// 익명 클래스
var Foo = class {
    constructor() {}
    bar(){
        return "Hello world!";
    }
}

var instance = new Foo();
instance.bar();     // "Hello world!"
Foo.name;   // ""


var Foo = class NamedFoo {
    constructor() {}
    whoIsThere() {
        return NamedFoo.name;
    }
}
var bar = new Foo();
bar.whoIsThere();   // "NamedFoo"
Foo.name;   // "NamedFoo"
```

---
## Modules <br> 

자바스크립트는 예전부터 리소스(웹 페이지를 구성하는 자원)관리가 어려웠다고 한다. <br>
현재 웹 페이지에서는 해당 페이지에 필요한 모든 파일을 미리 불러와야하고, 그 파일들이 사용하는 변수가 겹치지 않나 살펴봐야한다. <br>
<br>
스크립트를 로딩하는 순서도 매우 중요하다. 필요한 파일을 먼저 로딩하지 않으면 에러가 발생한다. <br>
> 예를 들어, jQuery로딩보다 jQuery 코드가 먼저 나왔을 때 발생하는 $ is not defined가 있다.

<br>
또한, 자바스크립트는 패키지와 파일끼리 서로 의존하고 있는 경우가 많다. <br>
> 예를 들어 파일 A와 파일 B가 있다고 가정한다. <br> 파일 A는 jQuery와 font-awesome을 사용하고 <br> 파일 B는 facebookSDK와 kakaoAPI를 사용한다. <br> 사용한다는 것은 의존하고 있다는 말이다. <br>대부분의 프로그래 언어에서는 원칙적으로 파일 A에서는 kakao에 접근하면 안되고, 파일 B에서는 jQuery에 접근할 수 없어야 한다.


하지만 웹에서는 처음 시작할 때 모든 스크립트를 로딩하기 때문에 다른 파일도 모든 패키지에 접근할 수 있게된다. <br>
<br>
모든 패키지에 접근할 수 있게 되는 위험의 코드이다. <br>
```javascript
// A.js
$ = null;

// B.js
$('button').on('click', ()=> {
    /*codes*/
});
```
A와 B파일을 순차적으로 로딩할 경우 B.js에서 에러가 발생한다. <br>
그 이유인 즉슨, A에서 의도적으로 jQuery 전역변수인 $값을 변경했기 때문이다. <br>
<br>
위와 같은 문제 때문에 어떤 패키지가 필요로 한다고 선언하여 의존성을 관리하게 끔 **모듈 시스템**이 등장하였다. <br>
<br>
모듈을 불러오기 위해서는 ```import```를 사용하고 모듈을 다른 파일로 보내기 위해서는 ```export```를 사용한다. <br>
<br>
```javascript
// Example1
const a = 1;
const b = 2;
export { a };
export const c = 3;
export default b;

// Example2
import d, {a, c as e} from './Example1';
console.log(a, d,  e);      // 1, 2, 3
```
위의 Example1.js 코드를 보면 3가지 방식으로 export를 하고 있다. <br>
a는 객체로 담아서 보내고, c는 선언 및 초기화를 동시에, b는 default 키워드가 붙었는데 기본이라는 의미로 괄호를 사용하지 않아도 import할 수 있다. 변수 명도 마음대로 받을 수 있다. <br>
<br>
```javascript
import * as namespace from './Example1';
console.log(namespace);     //  {a : 1, c : 3, default : 2}
```
위의 코드를 보면 export를 한 것들을 다 모아서 import 해준다는 것을 알 수 있다. <br>

---
## Promise <br>

Promise를 사용하면 비동기 메서드를 마치 동기 메서드처럼 값을 반환할 수 있다. <br>
다만, 최종 결과를 반환하는 대신, Promise를 반환하여 미래의 어떤 시점에 결과를 제공한다. <br>
IE에 호환되지 않는다. IE에서는 promise polyfill이나 async polyfill을 사용해야한다. <br>
<br>
Promise는 3가지 상태를 가지는데, <br>
- pending(대기) : 이행되거나 거부되지 않은 상태
- fulfilled(이행) : 연산이 성공적으로 완료된 상태
- reject(거부) : 연산이 실패한 상태

fulfilled상태일 때 then()을 사용하면 처리 결과를 받을 수 있다. <br>
reject상태일 때는 then.catch() 받을 수 있다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[ZeroCho님의 ES2015(ES6) 모듈 시스템](https://www.zerocho.com/category/ECMAScript/post/579dca4054bae71500727ab9) <br>
[MDN 사이트의 Promise Docs](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Promise)