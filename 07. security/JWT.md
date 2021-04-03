# JWT (JSON Web Token)  

클라이언트와 서버, 서비스와 서비스 사이에서 정보를 JSON 객체로 안전하게 전송하기 위한 전송수단(RFC7519)이다.  
JWT는 비밀(HMAC) 또는 RSA, ECDSA를 사용하는 공개/개인 키 쌍을 사용하여 서명할 수 있다.  
'서명'된 토큰으로 안에 포함된 클레임의 무결성을 검증할 수 있다.  

# 사용  
<b>Authorization: </b> JWT를 사용하는 가장 일반적인 시나리오이다.  

<b>정보 교환: </b> 서명 값을 통해 헤더와 페이로드의 정보가 무결한지 검증할 수 있으므로 정보를 교환하는 당사자간에 정보를 안전하게 전송할 수 있는 안전한 방법이다.

# 구조  

JWT 토큰은 URL-safe한 문자열이며 아래와 같은 구조를 갖고 있다.  
```HEADER.PAYLOAD.SIGNATURE```  

## HEADER 
```
{
    "alg" : "HS256",
    "typ" : "JWT"
}
```  
헤더(Header)에는 일반적으로 토큰 유형과 사용중인 서명 알고리즘의 두 부분으로 구성된다.  

## PAYLOAD  
```
{
    "nam" : "funch",
    "iat": 1516239022
}
```  
페이로드(Payload)에 있는 속성들을 클레임 셋(Claim Set)이라고 부른다.  
해당 부분에 원하는 데이터(클레임)을 정의할 수 있는데, 누구나 데이터를 읽을 수 있기 때문에 비밀 정보를 넣으면 안된다.

## SIGNATURE  
```
HMACSHA256(base64UrlEncode({header} + "." + base64UrlEncode({payload}), {secret}))
```  
점(.)으로 헤더와 페이로드를 구분하여 연결한 값을 'secret' 문자열과 'alg'에 정의한 알고리즘으로 서명한 값이다.

## JWT
```JWT = Encoded(HEADER) + "." + Encoded(PAYLOAD) + "." + SIGNATURE```  


---
## 레퍼런스
[https://jwt.io/introduction](https://jwt.io/introduction)