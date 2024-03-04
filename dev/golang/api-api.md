# \[API] 네이버 커머스 API 인증 연계

스마트스토어 주문/결제 건 데이터를 가져오기 위해서 네이버 커머스 API 연계 작업을 진행하고 있다.

쇼핑몰 내에 PG 결제 시스템이 구현되어 있어도 좋지만 네이버 쇼핑을 이용하면 전략적으로 더 좋다는 생각으로 네이버 커머스 API 연동을 고려하게 되었다.



네이버 커머스 API를 사용하려면 인증 토큰을 받아서 토큰 정보를 가지고 데이터에 접근할 수 있다.

인증 토큰을 받는 방법은 네이버 커머스 API에서 여러 언어(Java, Node, Python 등)로 샘플 코드를 제공하고 있다.

하지만, 나는 서버를 Go 언어로 개발하고 있어서 Go 샘플 코드가 필요했지만 사이트에서는 제공하고 있지 않았다.



## Go 내장 모듈 bcrypt

여러 언어로 제공한 샘플코드를 보면 공통적으로 bcrypt 모듈로 해싱한 것을 확인할 수 있었고 해싱한 데이터는 `패스워드` 와 솔트 값으로 `비밀키` 조합으로 생성됐으며 어려워 보이지 않았다.



하지만, Go 내장 모듈인 bcrypt에서는 해싱할 데이터(패스워드)에 salt로 사용할 비밀키 문자열을 받는 함수를 제공하고 있지 않았다.

보안상의 이유로 문자열을 받아 처리하지 않고 내부적으로 무작위 Salt 값을 생성하여 보안성을 높이고 있기 때문이었다.



## jameskeane/bcrypt

사실 블로그에 글을 적기 훨씬 이전에 네이버 커머스 API 인증을 사용해 본 적이 있다.

당시에도 같은 상황이 발생해서 이런저런 시도를 많이 해봤던 걸로 기억이 얼핏 난다.



그 때의 Repository에서 어떤 라이브러리를 최종적으로 사용했는지 확인했을 때 `jameskeane/bcrypt` 라이브러리로 커밋되어 있었다.

그래서 이번에도 같은 라이브러리로 적용하고 샘플코드를 정리해서 네이버 커머스 API에 Go 샘플코드를 등록해달라고 요청할 계획이었으나 테스트를 진행해보니 샘플 코드의 테스트 케이스 결과 값과는 다른 값이 나왔다.

어딘가에 문제가 있단 것으로 이해하긴 했는데 처음에는 Go 버전 문제라고 Go 버전을 바꿔가며 테스트를 진행했으나 결국 해결하진 못했다.

> 원인을 파악해보겠다고 집요하게 분석을 했으나 결론은 bcrypt 구현 방식에 대한 지식이 있어야 정확한 원인 분석이 가능하다고 판단했다.



여러 시도 끝에 라이브러리 개발자에게 직접 문의도 해봤다.

친절하게 답변을 받았는데 결론적으로 타 언어 라이브러리와 본인이 개발한 라이브러리의 차이로 bcrypt 구현의 차이인 것 같다고 했다.

아래 해결한 라이브러리에서 언급이 되겠지만 네이버 커머스 API에서 제공하는 언어에서 사용하는 bcrypt 모듈은 OpenBSD를 구현한 라이브러리여서 그런 것 같다는 답변이었다.

> 이전  사이드 프로젝트에선어떻게 사용할 수 있었는지에 대한 의문은 해결하지 못했다.



## commerce-api-naver/commerce-api discussions <a href="#commerce-api-discussions" id="commerce-api-discussions"></a>

네이버 커머스 팀에 아래와 같이 직접 문의를 했다.



<figure><img src="../../.gitbook/assets/image (4).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../.gitbook/assets/image (5).png" alt=""><figcaption></figcaption></figure>

과연... 네이버 커머스 API에 Go 샘플코드로 추가될 진 모르겠다.



## absagar/go-bcrypt

이 라이브러리는 C 호출하여 bcrypt 해싱 함수를 제공하고 있다.

위에서 언급한 것과 같이 OpenBSD를 구현한 라이브러리다.

<figure><img src="../../.gitbook/assets/image (6).png" alt=""><figcaption><p><a href="https://pkg.go.dev/github.com/absagar/go-bcrypt">https://pkg.go.dev/github.com/absagar/go-bcrypt</a></p></figcaption></figure>

Go로 개발된 bcrypt 오픈소스를 찾아보려고 했지만 유일한 대안은 이 라이브러리 뿐인 것 같다.

**CGO**를 사용해야 하기 때문에 빌드할 때 설정해줘야 하는 것들이 있어서 불편한 점은 있지만 Go 언어 안에서 해결할 수 있어서 다행이다.



## 최종 코드

```go
import (
	"encoding/base64"
	"fmt"
	"strconv"
	"testing"

	"github.com/absagar/go-bcrypt"
)

func TestBcrypt(t *testing.T) {
	clientId := "aaaabbbbcccc"
	clientSecret := "$2a$10$abcdefghijklmnopqrstuv"
	timestamp := 1643961623299
	// 밑줄로 연결하여 password 생성
	password := clientId + "_" + strconv.Itoa(timestamp)
	// bcrypt 해싱
	hashed, _ := bcrypt.Hash(password, clientSecret)
	// base64 인코딩
	fmt.Println(base64.StdEncoding.EncodeToString([]byte(hashed)))
}
```

