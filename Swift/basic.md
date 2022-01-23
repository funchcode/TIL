# 기초 정리

## self

인스턴스 메서드 내에서 프로퍼티를 읽어올 경우에는 아래와 같은 형식으로 참조해야 한다.

```swift
self.프로퍼티 명
```

여기서 self는 클래스나 구조체의 인스턴스 자신을 가리킨다.  

## @State

뷰(View)가 접근 가능하도록 값을 가지고 있는 프로퍼티 래퍼(Property Wrapper)이다. (Swift 5.1부터 지원)  
뷰에서 해당 프로퍼티의 값을 수정하거나 읽기가 가능하다.  

@State는 뷰 외부에서는 사용이 불가능하다.  
뷰 내부에서만 사용 가능한 `private` 형태이다.  

## Property Wrapper 

Swift 5.1부터 추가된 기능이다.  
`Annotation`을 사용하여 감싸져(Wrapper)있는 값을 사용하는 개념이다.

예시

```swift
@WhateverDouble
var age:Int         // WhateverDouble Annotation을 선언한 age 프로퍼티
```

ㄴ 값 할당

```swift
age = 20
```

ㄴ 이 때 age의 값은 곧바로 20이 할당되는 것이 아니라 선언한 Annotation으로 인해 할당은 `@WhateverDouble` 내부 처리를 거친다.

```swift
// @WhateverDouble 내부 코드
@propertyWrapper
class WhateverDouble {
    private(set) var value: Int = 0

    // propertyWrapper에서는 wrappedValue를 반드시 구현해야한다.
    var wrappedValue: Int {
        get { value }
        set { value = newValue * 2 }
    }
}
```

ㄴ `@propertyWrapper` Annotation으로 구현이 가능하고 `wrappedValue`를 반드시 구현해야 한다.  

```swift
print(age)      // 40
```

ㄴ 값을 꺼내올 때는 PropertyWrapper의 `get`을 호출하여 값을 가져온다.


## extension

> 확장

기존 클래스, 구조체 또는 열거형 타입에 새로운 기능을 추가한다.  
확장은 타입에 새로운 기능을 추가할 수 있지만 기존 기능을 오버라이딩 할 수 없다.

확장을 통해 얻을 수 있는 이점
1. 계산 속성과 계산 정적 속성 추가
2. 인스턴스 메서드와 타입 메서드 정의
3. 새로운 이니셜라이저 제공
4. 서브스크립트 정의
5. 기존 타입에 프로토콜 적용
6. 새로운 중첩 타입 정의와 사용

선언 방법 

```swift
extension SomeType {
    // new functionality
}
```

ㄴ `extension` 키워드로 확장을 선언한다.

