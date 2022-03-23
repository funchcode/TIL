# implements, extends

## implements

```ts
interface Pingable {
    ping(): void;
}

class Sonar implements Pingable {
    ping() {
        // code...
    }
}
```

여러 개의 인터페이스 타입을 구현할 수 있다.

```ts
class C implements A, B {}
```

`optional Property`를 가진 인터페이스를 구현해도 해당 속성이 생성되지 않는다.

```ts
interface A {
    x: number;
    y?: number;
}
class C implements A {
    x = 0;
}

const instanceC = new C();
c.y = 10;       // Error !
```

# extends


[interface vs extends](https://medium.com/@martin_hotell/interface-vs-type-alias-in-typescript-2-7-2a8f1777af4c)