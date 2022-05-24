
## Browser

### `X-Frame-Options`

지시자 중 `ALLOW-FROM uri`는 구식 브라우저에서만 동작한다.

- 원래는 `ALLOW-FROM` 지시자를 사용하는 이유로 지정한 도메인에서 페이지를 호출할 수 있게 허용해주기 때문에 사용했다.
- 현대적인 대안으로 `Content-Security-Policy`의 많은 지시자 중 `frame-src uri`로 적용하여 구현할 수 있다.

![x-frame-options-allow-from-mdn](/.assets/x-frame-options-allow-from.png)

사내 서버 개발에서 `ALLOW-FROM`을 적용하여 구현했었는데 브라우저 개발자 도구에 아래와 같은 에러 콘솔이 계속 출력됐었다.

![x-frame-options-allow-from-err](/.assets/x-frame-options-allow-from-err.png)

아래는 해결한 소스 코드이다.

```java
...
    public final void configure(HttpSecurity http) throws Exception {
        http.headers()
            // as-is
            // .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM http://localhost:3000"))
            // to-be
            .contentSecurityPolicy("frame-src http://localhost:3000;")
    }
...
```

---

## Securtiy

### 하이 재킹
