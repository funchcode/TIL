## "목차" <br>
1. Arrows
2. let + const
3. Classes
4. Modules
5. Module Loaders
6. Promises
7. Reflect API
8. Tail Calls

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