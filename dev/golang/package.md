---
description: 레이어드 아키텍쳐(Layered Architecture) 구조 외에 다른 아키텍쳐를 경험해 보기 위해 사이드 프로젝트로 진행해본다.
---

# \[package] 헥사고날 아키텍처로 프로젝트 구조 잡기

> 해당 포스트는 지속적으로 업데이트될 예정입니다.

## 헥사고날 아키텍처(Hexagonal Architecture)

유지보수성과 확장성이 있는 프로젝트를 구축해보고자 한다.

{% hint style="info" %}
'다담따 서비스'에 도입
{% endhint %}

## 실제로 적용해보기 <a href="#example" id="example"></a>

### 외부에 공개할 코드와 공개하지 않을 코드를 구분한다. <a href="#example-01" id="example-01"></a>

```
project
ㄴ /cmd
ㄴ /docs
ㄴ /internal
ㄴ /pkg
go.mod
go.sum
...
```

공개할 코드는 `pkg` 패키지로 관리한다.

공개하지 않을 코드는 `internal` 패키지로 관리한다.

### 헥사고날 아키텍처를 위한 구조 <a href="#example-02" id="example-02"></a>

```
...
ㄴ /internal
    ㄴ /adapter
        ㄴ /user
            ㄴ /command
                ㄴ model.go
                ㄴ controller.go
            ㄴ /persistence
                ㄴ model.go
                ㄴ repository.go
    ㄴ /domain
        ㄴ /user
            ㄴ model.go
            ㄴ service.go
ㄴ /pkg
    ㄴ port
        ㄴ /user
            ㄴ model.go
            ㄴ in.go
            ㄴ out.go
...
```

사용자(user) 도메인을 개발해야 할 때 기본적으로 위와 같은 구조로 설정하도록 했다.

#### 1) 의존 방향 <a href="#example-02-1" id="example-02-1"></a>

"/internal/adapter" 기준

* `pkg`를  의존한다.

"/internal/domain" 기준

* `pkg`를 의존한다.

domain을 직접적으로 의존하지 않기 때문에 domain 정책 변경에서 자유롭다.

#### 2) 데이터 struct 관리 <a href="#example-02-2" id="example-02-2"></a>

도메인 비즈니스 로직 호출(in) 또는 외부 어댑터(out) 호출 하기 위한 데이터 struct는 `pkg`에 정의한다.

<pre class="language-go"><code class="lang-go">// 위치 pkg/port/user/model.go
package user
type User struct {
    Id  string
    Pwd string
    ...
}
type UserDetail struct {
    User
    CreatedAt time.Time
}
// 위치 pkg/port/user/in.go
package user
type OAuthUserUseCase interface {
    SignIn(code, state string) (user *UserDetail, err error)
}
// 위치 pkg/port/user/out.go
package user
type LoadUserPort interface {
    GetUser(id string) (*UserDetail, error)
}
type WriteUserPort interface {
    Save(user *User) (*UserDetail, error)
<strong>}
</strong></code></pre>



API 요청, 응답에 대한 데이터 struct는 `internal/adapter/{domain}/command`에 정의한다.

```go
// 위치 internal/adapter/user/command/model.go
package command
type UserSignInRequest struct {
    Id  string `json:"id"`
    Pwd string `json:"pwd"`
}
// 위치 internal/adapter/user/command/controller.go
package command
var oauthUserService pkguser.OAuthUserUseCase
func UserController(router *gin.Engine, oauthUserUseCase pkguser.OAuthUserUseCase) {
    oauthUserService = oauthUserUseCase
    userApi := router.Group("/v1/users")
    {
        userApi.GET("/signin", signIn)
    }
}
func signIn(ctx *gin.Context) {
    var requestBody UserSignInRequest
    if err := ctx.ShouldBindJson(&requestBody); err != nil {
        ...
    }
    ...
}
```



Persistence 관련 데이터 struct는 `internal/adapter/{domain}/persistence`에 정의한다.

```go
// 위치 internal/adapter/user/persistence/model.go
package persistence
type UserEntity struct {
    Id        string `gorm:"id"`
    Pwd       string `gorm:"pwd"`
    ...
    CreatedAt time.Time `gorm:"created_at"`
}
func (u *UserEntity) ToUserDetail() *pkguser.UserDetail {
    return &pkguser.UserDetail {
        User:    {
            Id:    u.Id,
            Pwd:   u.Pwd,
            ...
        },
        CreatedAt: u.CreatedAt,
    }
}
// 위치 internal/adapter/user/persistence/repository.go
package persistence
type rdbRepository struct {
    Db *gorm.DB
}
func NewRdbRepository(Db *gorm.DB) *rdbRepository {
    return &rdbRepository {
        Db: Db,
    }
}
```



Domain 관련 데이터 struct는 `internal/domain/{domain}`에 정의한다.

```go
// 위치 internal/domain/user/model.go
package user
type User struct {
    Id           string
    Pwd          string
    ...
    CreatedAt    time.Time
}
...User 관련 Functions...
```



`pkg`를 제외한 각 `model.go`에 정의된 데이터 struct에서는 `pkg`데이터 struct로 mapping 해주는 매퍼를 만들어서 관리하면 편리하다.

`pkg`데이터 struct에서 변경이 발생하는 경우 관련매퍼들만 수정해주면 된다.

domain struct에 변경이 발생하더라도 다른 영역에 영향을 주지 않는다. (=정책 변경에 자유롭다.)

***

참고 자료

* [https://github.com/thombergs/buckpal](https://github.com/thombergs/buckpal)
