# OAuth 2.0

특정한 프로그램을 지칭하는게 아니라 일종의 **규격**이다.

> Facebook, Google, Naver 등은 OAuth라는 규격에 맞춰 인증 및 권한을 대행관리 해준다.

## 주요 용어

1. Authentication

- **인증**, 접근 자격이 있는지 검증하는 단계

2. Authorization

- **인가**, 자원에 접근할 권한을 부여
- 인가가 완료되면 리소스 접근 권한이 담긴 `Access Token`이 클라이언트에게 부여된다

3. Access Token

- 리소스 서버에게서 리소스 소유자의 보호된 자원을 획득할 때 사용되는 만료 기간이 있는 토큰

4. Refresh Token

- `Access Token` 만료 시 이를 갱신하기 위한 용도로 사용하는 토큰
- 만료 기간 (Refresh Token > Access Token)

## OAuth 2.0 프로토콜을 구성하는 4가지 역할

1. Resource Owner

- **리소스 소유자**, 보호된 자원에 접근할 수 있는 자격을 부여해주는 주체
- 개념적으로는 리소스 소유자가 자격을 부여하는 것이지만 일반적으로 권한 서버가 리소스 소유자와 클라이언트 사이에서 중개 역할을 수행하게 된다.

2. Client

- 보호된 자원을 사용하려고 접근 요청을 하는 **애플리케이션**이다.

3. Resource Server

- 사용자의 보호된 자원을 호스팅하는 서버

4. Authorization Server

- **권한 서버**, 인증/인가를 수행하는 서버로 클라이언트의 접근 자격을 확인하고 `Access Token`을 발급하여 권한을 부여하는 역할 수행

---

## 레퍼런스

[한컴인텔리전스 블로그](https://blog.naver.com/mds_datasecurity/222182943542)
