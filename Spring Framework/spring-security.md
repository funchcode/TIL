# Spring-Security

## 객체 분석

### FrameOptionsConfig

`X-Frame-Options` 헤더는 `<frame>, <iframe>, <object>`에서 렌더링 할 수 있는지 여부를 의미한다.

`X-Frame-Options` 헤더는 HTTP 응답 헤더에 속한다.

- 3가지 설정
  - `X-Frame-Options: deny` 거부
    - Spring Security의 **기본 값**
  - `X-Frame-Options: sameorigin` 동일한 사이트에서만 허용
  - `X-Frame-Options: allow-from https://-----.---/` 지정한 URI에서만 허용

### SessionManagementConfigurer

♦ 내용 필요

### CsrfConfigurer

> CSRF: Cross-Site Request Fogery

♦ 내용 필요

### CorsConfigurer

> CORS: Cross-Origin Resource Sharing
> `CORS`는 웹 어플리케이션 도메인이 다른 도메인의 리소스에 대해서 접근이 허용되는지 **체크**하는 메커니즘이다.  
> 웹 어플리케이션은 리소스를 요청하는 서버의 **프로토콜, 도메인, 포트**가 다를 경우, `Cross-Origin Http Request` 요청을 실행한다.  
> 보안상의 이유로 브라우저는 `Cross-Origin Http Request`에 대해 **Same-Origin Policy**를 적용하여 동작한다.  
>
> - 요청의 종류
>   - Simple Request
>   - Preflight Request

`corsConfigurationSource`를 빈으로 등록하여 제어할 수 있다.

`CorsConfigurer.class` 내부 로직에서 `corsConfigurationSource` 이름의 컨텍스트가 존재하는지 검색한다.

```java
...
@Override
public final configure(HttpSecurity http) throw Exception {
    http
        .headers()
        .cors();                                                    // corsConfigurationSource 호출
}

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("https://funchcode.me");         // 허용 도메인
    configuration.addAllowedMethod("*");                            // 허용 HTTP 메서드
    configuration.addAllowedHeader("*");                            // 허용 HTTP 헤더
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);         // URI 패턴 별 CORS 제어
    return source;
}
...
```