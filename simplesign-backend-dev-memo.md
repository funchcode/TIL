# Simple-Sign 백엔드 개발 기술 노트

## lombok

* ```@Value```
  * 생성한 클래스를 값 객체로 운용하고 싶을 때 해당 어노테이션 사용.
  * 어노테이션을 설정할 경우 모든 필드는 ```private final```하며, ```@Getter```, ```@AllArgsConstructor```, ```@ToString```하게 객체가 정의된다.

## HTTP Authentication

HTTP에서 액세스 제어와 인증을 위한 '프레임워크'를 제공한다.

인증 방식 여러 개 ?
Basic
Bearer
Digest
HOBA
Mutual
AWS4-HMAC-SHA256

서버와 인증을 하기 위해서는 클라이언트는 'Authentication Request Header'에 인증 정보를 포함함으로써 인증을 수행할 수 있다.

<Basic 인증 스킴>
Base64를 이용하여 사용자 ID와 패스워드 쌍으로 인증 정보를 전달한다.
전달하는 정보는 Base64 디코딩을 통해 패스워드 평문을 확인할 수 있으니 보안에 취약하다. 따라서 Https/TLS와 함께 사용되어야 안전하다.

<Bearer 인증 스킴>

OAuth ?
인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단이다.

[Bearer 액세스 토큰을 보내는 세가지 방법]
1. Authorization Request Header
2. Form-Encoded Body Parameter
3. URI Query Parameter
   ㄴ 보안이 취약하여 사용하면 안된다. URL에 그대로 노출됨으로.
