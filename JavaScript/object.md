# object type

## 객체 생성하는 방식

1. 생성자 문법

```java script
let user = new Object();
```

2. 객체 리터럴 문법

> literal: (형용사) 문자 그대로의  

```java script
let user = {};
```

## 복사

객체와 원시 타입의 근본적인 차이 중 하나는 객체는 **'참조에 의해(by reference)`** 저장되고 복사된다.

> 변수에 객체가 그대로 저장되는 것이 아니라, 객체가 저장되어있는 **메모리 주소**인 객체에 대한 **참조 값**이 저장된다.

### Object.assign (얕은 복사)

```javascript
Object.assign(dest, [src1, src2, src3...]);
```

src1, src2... 객체를 dest 객체에 복사한다. 마지막으로 dest 객체를 반환한다.

### 깊은 복사

프로퍼티에 원시 값이 아닌 다른 객체에 대한 참조 값이 있는 경우 공유하게 된다.

```javascript
let user = {
    nickname: 'funchcode',
    age: 28,
    birthday : {
        year: 1994,
        month: 3,
        day: 11,
    },
};

lset clone = Object.assign({}, user);

alert( user.birthday === clone.birthday );  // true, 같은 객체

user.birthday.day--;
alert( clone.birthday.day );                // 10
```

객체의 구조도 복사를 하기 위해서는 `user[key]` 각 값을 검사하면서 구조 복사를 해야한다.

이 행위를 깊은 복사라고 부른다.

## trailing

> trailing: (형용사) 질질 끌리는

객체 리터럴 문법으로 프로퍼티(키: 값) 정의 시 마지막 프로퍼티 끝을 `,(쉼표)`로 끝내는 것을 말한다.

끝에 `,(쉼표)`로 끝내는 경우 모든 프로퍼티가 유사한 형태로 보이기 때문에 **추가, 삭제, 이동**이 쉬워진다.

## const

**상수 객체는 수정될 수 있다.**

## computed property

객체를 만들 때 리터럴 안의 프로퍼티 키가 **대괄호**로 둘러싸여 있는 경우를 말한다.

```javascript
var customKey = prompt('', 'gv80');

let cars = {
    [customKey]: 70000000,
};

alert( cars.gv80 ); // customKey에 'gv80'이 할당되는 경우 70000000이 출력된다.
```

## 예약어

객체 프로퍼티 키 값으로 **예약어(for, let, return ...)** 사용 제약이 없다.

다만, `__proto__`는 사용할 수 없다.

## in 연산자

존재하지 않는 프로퍼티에 접근하는 경우 `undefined`를 반환한다.

따라서 프로퍼티가 존재하는 지에 대한 존재여부를 `undefined` 체크하여 확인할 수 있지만, `in 연산자`를 사용해서 확인할 수도 있다.

```javascript
const user = { nickname: 'funchcode', age: 28 };

alert( "nickname" in user );    // "nickname" 프로퍼티가 존재하므로 true 반환
alert( "address" in user );     // "address" 프로퍼티가 존재하지 않으므로 false 반환
```

> 프로퍼티 존재 여부를 undefined가 아닌 in 연산자로 확인하는 이유는 의도적으로 개발자가 프로퍼티 값으로 undefined를 할당했을 경우가 있을 수 있기 때문이다.

## for...in 반복문

`for...in` 반복문을 사용하면 객체의 모든 키를 순회할 수 있다.

## 객체 정렬 방식

**정수 프로퍼티**의 경우에만 정렬처리가 된다.

이외에는 추가한 순서대로 처리된다.
