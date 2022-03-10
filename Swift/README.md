# Swift

## 기본 문법

### 클래스와 구조체

#### 공통점

1. **값**을 저장하기 위해 프로퍼티를 정의한다.
2. **기능**을 제공하기 위해 메소드를 정의한다.
3. `subscript` 문법을 이용 가능하다.
4. 초기 상태를 설정할 수 있는 `initializer` 정의.
5. 기본 구현에서 기능 확장이 가능하다.

#### 차이점

대표적인 차이점으로 클래스는 **참조 타입**이며, 구조체는 **값 타입**이다.  

### 참조 카운트(Reference counting)

Swift에서는 앱의 메모리 사용을 관리하기 위해 **ARC(Auto Reference Couning)을** 사용한다.  

자동으로 참조 횟수를 관리하기 때문에 개발자가 메모리 관리에 신경 쓸 필요가 없는 장점이 있다.  

### Subscript

**클래스, 구조체, 열거형**에서 스크립트를 정의해서 사용할 수 있다.  

하나의 타입에 **여러 서브 스크립트**를 정의할 수 있으며, **오버로드**도 가능하다.  

서브 스크립트는 **읽고 쓰기(Read-Write) 또는 읽기 전용(Read Only)로**만 정의가 가능하다.

```swift
// Read-Write
subscript(idx: Int) -> Int {
    get {
    }
    set(newValue) {
    }
}

// Read Only
subscript(idx: Int) -> Int {
}
```

