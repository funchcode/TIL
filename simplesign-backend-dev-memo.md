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

## QueryDSL

동적으로 쿼리를 생성하기 위해서 Query DSL을 사용해서 조회 API 기능을 개발했다.

조회할 데이터에 대해 페이징 처리가 필요하여 Pageable 객체를 사용했고 QuerydslRepositorySupport 객체를 상속받아 기능을 구현했다.

내가 기대한 동작은 동적으로 생성한 Query에 Pageable 객체를 주입하여 fetch()할 경우 페이징 처리가 된 리스트가 반환되는 것으로 기대했다.

하지만, 동적으로 생성한 쿼리에 대해 정상적으로 데이터를 가져오기는 하는데 totalPage 부분에서 원하는 동작을 하지 않았다.

조회한 데이터에 대한 페이지 정보는 new PageImpl<>() 객체를 사용하여 가져오도록 구현했는데, 이 객체는 가져올 데이터의 size와 limit절을 제거하여 조회한 전체 데이터 카운트 정보를 넘겨 최종적으로 페이지 처리에 대한 계산이 완료된 데이터를 넘겨받을 수 있다.

이 때, 동적으로 생성한 쿼리를 fetch()하는 부분에서 totalPage 즉 limit절을 제거한 데이터를 따로 fetch()해서 그 값을 PageImpl() 객체 파라미터로 넘겨줘야한다.

나는 이 부분에 실수하여 이상한 totalPage 값을 리턴받았다.
