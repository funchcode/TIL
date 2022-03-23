# TypeScript

> README에 마구잡이로 정리한 이 후 파일로 분리하자.

__Tree-shaking__  

사용하지 않는 코드를 삭제하는 것을 말한다.  
나무를 흔들면 죽은 잎사귀들이 떨어지는 모습에 착안해 Tree-shaking이라고 부른다.

__Union Type__  

JavaScript의 `OR연산자`와 같이 __'A'이거나 'B'이다__ 라는 의미의 타입이다.  

```typescript
function logText(text: string | number) {
  // ...
}
```

__Intersection Type__  

여러 타입을 만족하는 하나의 타입을 말한다.  

```typescript
interface Person {
    name: string;
    age: number;
}

interface Developer {
    name: string;
    skill: number;
}

type Capt = Person & Developer;
```

