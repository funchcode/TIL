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

## 에러 핸들링

### defer

호출되고 있는 함수가 일찍 종료되거나 에러로 인해 종료되더라도 특정 함수의 호출을 보장한다.

```go
func main() {
    defer fmt.Println("잘가")
    fmt.Println("안녕")
    fmt.Println("밥묵자")
}

// output 
/*
안녕
밥묵자
잘가
*/
```

### panic

프로그램에서 panic을 만나면 현재 실행되고 있는 함수를 중단하고 에러 메세지를 출력한 뒤 크래시를 발생시킨다.

* 지연 호출(defer)은 실행된다.

### recover

recover로 panic 상태의 프로그램을 복구할 수 있다.

단, 같은 함수 내에서 recover 호출 라인 전에 panic 라인이 호출되면 recover은 호출되지 않는다.

```go
func main() {
    panic("oh no") // 실행되어 프로그램 종료
    recover()
}
```

defer를 사용해서 recover을 보장 받을 수 있도록 처리할 수 있다.

```go
func calmDown() {
    recover()
}
func main() {
    defer calmDown()
    panic("oh no")
}
```

## 고루틴

고루틴을 사용하면 멀티 프로세스의 이점을 최대한 활용하여 프로그램이 실행되도록 만들 수 있다.

**동시성(Concurrentcy)**를 활용하면 프로그램이 한 작업을 멈추고 다른 작업을 수행할 수 있도록 만들 수 있다.

동시성을 지원하도록 작성된 프로그램은 여러 작업을 동시에 실행하는 **병렬성(parallelism)**도 지원할 수 있다.

Go에서는 동시에 실행되는 작업을 **고루틴**이라고 부르는데 다른 프로그래밍 언어의 스레드(Thread)와 유사한 개념이다.

다른 점은 _고루틴은 스레드보다 좀 더 적은 메모리를 사용_하며 좀 더 빠른 시작 및 종료 시간을 갖기 때문에 한 번에 더 많은 고루틴을 실행할 수 있다.

```go
func a() {
    for i := 0; i < 50; i++ {
        fmt.Print("A")
    }
}
func b() {
    for i := 0; i < 50; i++ {
        fmt.Print("B")
    }    
}
func main() {
    go a()    // 고루틴 실행
    go b()    // 고루틴 실행
    time.Sleep(time.Second) // "main이 종료되면 a, b 함수 실행 여부와 상관없이 종료"되는 것을 방지하기 위한 코드
    fmt.Println("END")
}
/* output
aaaaabbbbaabaaabbabaabbabb(...생략)END
*/
```

위의 코드에서는 3개(main, a, b)의 고루틴이 실행되고 있다고 볼 수 있다.

출력된 output 결과를 봤을 때 _실행 시점을 직접 제어할 수 없고 고루틴 간의 전환을 보장 받을 수 없는 것_으로 보인다.

그리고 go문은 반환 값과 함께 사용할 수 없다.

## 채널

고루틴끼리 서로 통신할 수 있는 방법으로 채널(channel)을 사용할 수 있다.

채널을 사용하면 한 고루틴에서 다른 고루틴으로 값을 전달할 수 있고 수신한 고루틴이 값을 사용하기 전에 송신 고루틴이 값을 보냈음을 보장할 수 있다.

### 채널에 값 전달하기

채널에 값을 전달할 때 `<-` 연산자를 사용하여 전달한다.

**사용 형태:** `{값을 보낼 채널} <- {값}`

채널은 현재 고루틴의 모든 작업을 중지하는 블로킹(blocking)이 발생한다.

어떤 채널에 대한 값을 송신 연산은 다른 고루틴이 해당 체널에서 값을 가져가기 전까지 송신 고루틴을 블로킹한다.

그 반대의 상황

수신 연산은 다른 고루틴이 해당 채널에 값을 보내기 전까지 수신 고루틴을 블로킹한다.

이러한 동작 방식을 통해 고루틴은 자기 자신의 행동을 **동기화(synchronize)**할 수 있다.

```go
func abc(channel chan string) {
    channel <- "a"
    channel <- "b"
    channel <- "c"
}
func def(channel chan string) }
    channel <- "d"
    channel <- "e"
    channel <- "f"
} 
func main() {
    // 두 개의 채널 생성
    channel1 := make(chan string)
    channel2 := make(chan string)
    // 각 채널을 고루틴에서 실행되는 함수로 전달
    go abc(channel1)
    go def(channel2)
    fmt.Print(<-channel1)
    fmt.Print(<-channel2)
    fmt.Print(<-channel1)
    fmt.Print(<-channel2)
    fmt.Print(<-channel1)
    fmt.Print(<-channel2)
}
/* output
    adbecf
*/
```

## 번외 <a href="#extra" id="extra"></a>

### if 문에서의 초기화문 <a href="#extra-if" id="extra-if"></a>

```go
if {초기화문}; {조건식} {
    // code
}
```

if문에 초기화문을 넣을 수 있다.

if문 스코프 안에서 사용할 변수를 선언할 수 있다.

주로 err 변수를 초기화문에서 선언한다.

### Switch문 <a href="#extra-switch" id="extra-switch"></a>

switch 문을 사용하는 방법은 다른 언어와 거의 흡사한데 한 가지 다른 점이 있다.

`break` 문을 사용하지 않는다. `case`가 포함하고 있는 코드가 실행된 후 switch 문을 빠져나온다.

`break` 문을 사용하는 다른 언어에서 `break` 문을 빠뜨리는 실수로 인해 의도하지 않은 버그를 만든 적이 있을 것이다.

Go는 이러한 상황을 피하기 위해 의도적으로 `break` 키워드를 넣지 않았다.



`case`의 코드가 실행된 이후 switch문에서 빠져나오지 않고 다음 `case`코드를 수행하는 방법으로 `fallthrough` 키워드를 사용할 수 있다.

### 룬(rune) 타입 <a href="#extra-rune" id="extra-rune"></a>

Go의 기본 타입 중에 `rune` 타입이 엄청 생소했다.

`rune` 타입이 생긴 이유를 이해하기 위해서는 컴퓨터에서 사용한 문자에 대한 역사를 살펴봐야 한다.

현대 운영체제가 나오기 이전에는 컴퓨터에서 사용하는 문자는 표준 영어 알파벳(52자)만 사용했다.

**하나의 바이트**로 모두 표현이 가능했고 동일한 바이트 값이 다른 시스템에서도 동일한 문자로 변환하는 것을 보장하기 위해 **ASCII**라는 표준이 사용되었다.



하지만, 영어 알파벳 말고도 세상에는 수 많은 문자 체계가 존재했고 이를 모두 표현하기 위해 4바이트 값의 집합인 **유니코드(Unicode)** 표준이 추가되었다.

_Go는 rune 타입을 사용해서 유니코드의 값을 표현_한다.



Go에서 문자열을 다룰 때 주의해야 하는 점들이 있다.

#### len(문자열) <a href="#rune-len" id="rune-len"></a>

len 함수를 사용해서 문자열 길이를 구할 때 문자 수가 아니라 바이트 단위로 반환한다.

```go
asciiString := "ABCDE" // 알파벳은 1바이트씩 차지한다.
utf8String := "가나다라마" // 한글은 유니코드 문자로 3바이트씩 차지한다.
fmt.Println(len(asciiString))    // 5
fmt.Println(len(utf8String))    // 15
```

문자열의 길이를 구하고 싶다면 **unicode/utf8** 패키지의 **RuneCountInString** 함수로 구해야 한다.

```go
fmt.Println(utf8.RuneCountInString(asciiString)) // 5
fmt.Println(utf8.RuneCountInString(utf8String)) // 5
```

#### 바이트 슬라이스 <a href="#rune-byteslice" id="rune-byteslice"></a>

바로 위에서 언급한 것과 같이 바이트 단위로 사용할 땐 주의해야 한다.

```go
asciiBytes := []byte(asciiString)
utf8Bytes := []byte(utf8String)
asciiBytesPartial := asciiBytes[3:]
utf8BytesPartial := utf8Bytes[3:]
fmt.Println(string(asciiBytesPartial))    // DE
fmt.Println(string(utf8BytesPartial))    // 나다라마
```

바이트 슬라이스가 아니라 룬 슬라이스로 변환하여 사용해야 한다.

```go
asciiRunes := []rune(asciiString)
utf8Runes := []rune(utf8String)
asciiRunesPartial := asciiRunes[3:]
utf8RunesPartial := utf8Runes[3:]
fmt.Println(string(asciiRunesPartial))    // DE
fmt.Println(string(utf8RunesPartial))    // 라마
```

#### for...range 루프 <a href="#rune-for-range" id="rune-for-range"></a>

두 가지 주의할 점이 있다.

첫번째로 문자열을 바이트 슬라이스로 for...range 루프 사용을 주의해야 한다.

두번째로는 range에서 반환하는 첫번째 인자 값이 바이트 인덱스 값이라는 것이다.

```go
for index, currentByte := range asciiBytes {    // ascii bytes
    fmt.Printf("%d: %s\n", index, string(currentByte))
    /*
    0: A
    1: B
    2: C
    3: D
    4: E
    */
}
for index, currentByte := range utf8Bytes {    // unicode bytes
    fmt.Printf("%d: %s\n", index, string(currentByte))
    /*
    0: ê
    1: °
    2: 
    3: ë
    4:
    ... 
    */
}
```

위는 바이트 슬라이스로 for문을 순회했을 때의 코드이다.

아래 코드는 문자열을 순회했을 때의 코드인데 `index` 값도 주의깊게 봐야한다.

```go
for index, currentRune := range asciiString {    // ascii string
    fmt.Printf("%d: %s\n", index, string(currentRune))
    /*
    0: A
    1: B
    2: C
    3: D
    4: E
    */
}
for index, currentRune := range utf8String {    // unicode string
    fmt.Printf("%d: %s\n", index, string(currentByte))
    /*
    0: 가
    3: 나
    6: 다
    9: 라
    12: 마
    */
}
```

