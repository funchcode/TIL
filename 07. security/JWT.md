# JWT (JSON Web Token)  

클라이언트와 서버, 서비스와 서비스 사이에서 통신 과정 중 인가(Authorization)를 위해 사용되는 토큰이다.  

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
헤더(Header)에는 JWT를 어떻게 검증하는지에 대한 정보가 포함되어있다.  

## PAYLOAD  
```
{
    "name" : "funch",
    "iat": 1516239022
}
```  
페이로드(Payload)에 있는 속성들을 클레임 셋(Claim Set)이라고 부른다.  

## SIGNATURE  
```
HMACSHA256(base64UrlEncode({header} + "." + base64UrlEncode({payload}), {secret}))
```  
점(.)으로 헤더와 페이로드를 구분하여 연결한 값을 'secret' 문자열과 'alg'에 정의한 알고리즘으로 서명한 값이다.

## JWT
```JWT = Encoded(HEADER) + "." + Encoded(PAYLOAD) + "." + SIGNATURE```  
