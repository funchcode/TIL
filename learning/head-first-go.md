# ▪️ Head First Go

## 구조체(struct) <a href="#go-struct" id="go-struct"></a>

### 포인터를 사용한 구조체 전달 <a href="#go-struct-pointer" id="go-struct-pointer"></a>

Go에서는 매개변수의 값으로 인자의 복사본을 사용하는 "pass-by-value"으로 동작한다.

구조체 크기만큼 메모리를 사용하는데 구조체를 전달할 때 "pass-by-value" 방식으로 전달하면 구조체가 복사되어 메모리 상에 복사한 갯수만큼 메모리를 사용하게 된다.

이를 해결하기 위해 구조체의 포인터를 사용할 수 있다.

```go
<...code...>
```



### 사용자 정의 타입을 외부로 노출할 땐 대문자를 사용 <a href="#go-struct-customtype" id="go-struct-customtype"></a>

Go에서는 접근 제한자가 별도로 존재하지 않는다.

이름이 "대문자"로 시작하는지 "소문자"로 시작하는지에 따라 외부 패키지에 공개, 비공개를 결정한다.

구조체 필드 노출 여부도 마찬가지로 "대/소문자"로 결정된다.



### 구조체 임베딩(익명 구조체 필드) <a href="#go-struct-anonymous" id="go-struct-anonymous"></a>

#### 익명 구조체 필드를 사용하지 않았을 때의 코드를 살펴본다.

```go
package store
type Address struct {
    City       string
    State      string
    PostalCode string
}

type Customer struct {
    Name        string
    Age         int
    HomeAddress Address // 구조체 필드 선언
}

package main
func main() {
    customer := store.Customer{Name: "Rex", Age: "31"}
    customer.HomeAddress.City = "Azhoko"
    customer.HomeAddress.State = "KK"
    customer.HomeAddress.PostalCode = "99999"
}

```

항상 구조체 필드 이름을 통해 필드에 접근해야 한다.

#### 익명 구조체 필드, 임베딩을 시킨 코드를 살펴보자.

```go
package store
type Customer struct {
    Name string
    Age  int
    Address // 임베딩(embedded)
}

package main
func main() {
    customer := store.Customer{Name: "Rex", Age: "31"}
    customer.City = "Azhoko"
    customer.State = "KK"
    customer.PostalCode = "99999"    
}
```

`Customer` 구조체에 속해 있는 것처럼 접근이 가능하다.



### 기본 타입으로 정의한 사용자 정의 타입

```go
var moneyWon int = 1000
var moneyDollar int = 1000
```

위의 money1과 money2는 실제 의도는 money1은 원화 money2는 달러이다.

변수의 네이밍 말고 좀 더 명확하게 구분하고 싶다.

```go
type Won int
type Dollar int

func main() {
    var moneyWon Won
    var moneyDollar Dollar
    moneyWon = Won(1000)
    moneyDollar = Dollar(1000)
}
```

기본 타입을 사용자 정의 타입으로 정의 함으로써 명확하게 구분할 수 있게 됐다.



## 오버로딩(Overloading)

다른 언어에서는 매개변수 타입만 다르고 동일한 이름으로 메서드(함수)를 선언할 수 있는 오버로딩 개념이 존재한다.

반면에 Go에서는 오버로딩 개념이 없다.

관련 링크: [https://go.dev/doc/faq#overloading](https://go.dev/doc/faq#overloading)



## 메서드 정의

메서드 정의는 함수 정의 방법과 유사하다.

차이점은 메서드를 정의할 때 **리시버 매개변수(receiver parameter)**를 추가로 선언해야 한다는 점이다.

```go
func (t CustomType) helloWorld() { // (t CustomType) 리시버 매개변수
    fmt.Println("헬로월드")
}
```

Go는 다른 언어의 "self"나 "this" 대신 리시버 매개변수를 사용한다.

포인터 리시버 매개변수

리시버 매개변수도 "pass-by-value"로 복사본을 받는다.

```go
type Number int

func (n Number) Double() {
    n *= 2
}

func main() {
    number := Number(4)
    number.Double()
    fmt.Println(number) // 8이 아닌 4가 출력된다.
}
```

```go
func (n *Number) Double() { // 리시버 매개변수를 포인터 타입으로 변경
    *n *= 2
}
```



## 인터페이스

자바에서는 클래스가 어떤 인터페이스를 구현하고 있다는 것을 명시적으로 작성하는데 Go는 인터페이스를 선언하는 방식이 완전히 다르다.

```go
// 인터페이스 선언
type Player interface {
    Play(song string)
    Stop()
}
// TapePlayer 타입 선언
type TapePlayer struct {
}
// TapeRecorder 타입 선언
type TapeRecoder struct {
}
// 인터페이스 구현 (TapePlayer는 Player 인터페이스를 만족한다.)
func (t TapePlayer) Play(song string) {
}
func (t TapePlayer) Stop() {
}
// 인터페이스 구현 (TapeRecoder는 Player 인터페이스를 만족한다.)
func (t TapeRecoder) Play(song string) {
}
func (t TapeRecoder) Stop() {
}

func playList(device player, song string) {
    device.Play(song)
    device.Stop()
}

func main() {
    var player Player
    player = TapePlayer{}
    playList(player, "국힙")
    player = TapeRecoder{}
    playList(player, "캐롤")
}
```

**인터페이스**가 가진 메서드를 타입에서 동일하게 메서드를 구현하고 있다면 해당 타입은 인터페이스를 만족한다고 Go에서는 판단한다.

### 타입 단언(type assertion)

구체 타입의 값이 인터페이스 타입의 변수에 할당되었을 때 타입 단언(type assertion)을 사용하면 구체 타입의 값을 가져올 수 있다.

```go
var player Player = TapeRecoder{}
var tapeRecoder TapeRecoder = player.(TapeRecoder) // 타입 단언문
```

#### 타입 단언 실패 시 패닉 방지하기

두 번째 반환 값을 통해서 성공 실패 여부를 확인할 수 있는 방법을 이용한다.

```go
var player Player = TapeRecoder{}
tapeRecoder, ok := player.(TapeRecoder) // 타입 단언문
if ok {
    tapeRecoder.Recode()
} else {
    fmt.Println("Player was not a TapeRecoder")
}
```

런타입 환경에서 시스템 panic이 발생하는 것을 방지해야 한다.

### "error" 인터페이스

Go의 error 타입은 인터페이스이다.

```go
type error interface {
    Error() string
}
```

{% hint style="info" %}
error 타입은 int와 string과 같은 "미리 정의된 식별자"이다.

유니버스 블록(universe block)의 일부로 패키지에 관계없이 어느 곳에서나 사용할 수 있다.
{% endhint %}

### 빈 인터페이스

메서드 정으가 없는 인터페이스는 그 어떤 타입이라도 만족하는 인터페이스가 된다.

```go
type Anything interface {}
```



