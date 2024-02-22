---
description: 네이버, 카카오 로그인 API 기능 구현 내용을 정리한다.
---

# \[OAuth] Kakao, Naver 모듈화 정리

> 로그인 동의 화면을 호출하는 과정 이후의 작업에 대해 정리한다.

## 분석

네이버, 카카오 로그인 동의 화면에서 사용자가 액션을 하는 경우 아래와 같은 데이터가 응답된다.

### Callback 파라미터

#### 네이버

* code
* state
* error
* error\_description

#### 카카오

* code
* state
* error
* error\_description



### 토큰 받기

#### 네이버

기본 정보

<table data-header-hidden><thead><tr><th width="151"></th><th></th></tr></thead><tbody><tr><td>GET/POST</td><td><a href="https://nid.naver.com/oauth2.0/token">https://nid.naver.com/oauth2.0/token	</a></td></tr></tbody></table>

본문

<table data-header-hidden><thead><tr><th width="170"></th><th width="221"></th><th></th></tr></thead><tbody><tr><td>grant_type</td><td>authorization_code<br>refresh_token<br>delete</td><td>required</td></tr><tr><td>client_id</td><td>앱 ID </td><td>required</td></tr><tr><td>client_secret</td><td>앱 Secret </td><td>required</td></tr><tr><td>code</td><td></td><td></td></tr><tr><td>state</td><td></td><td></td></tr></tbody></table>

응답

<table data-header-hidden><thead><tr><th width="234"></th><th></th></tr></thead><tbody><tr><td>token_type</td><td>Bearer와 MAC</td></tr><tr><td>access_token</td><td>접근 토큰</td></tr><tr><td>refresh_token</td><td>갱신 토큰</td></tr><tr><td>expires_in</td><td>유효 기간</td></tr><tr><td>error</td><td></td></tr><tr><td>error_description</td><td></td></tr></tbody></table>

#### 카카오

기본 정보

<table data-header-hidden><thead><tr><th width="91"></th><th></th></tr></thead><tbody><tr><td>POST</td><td><a href="https://kauth.kakao.com/oauth/token">https://kauth.kakao.com/oauth/token	</a></td></tr></tbody></table>

헤더

<table data-header-hidden><thead><tr><th width="153"></th><th></th></tr></thead><tbody><tr><td>Content-type</td><td><code>Content-type: application/x-www-form-urlencoded;charset=utf-8</code><br></td></tr></tbody></table>

본문

<table data-header-hidden><thead><tr><th width="183"></th><th></th></tr></thead><tbody><tr><td>grant_type</td><td>authorization_code</td></tr><tr><td>client_id</td><td>내 애플리케이션 Key</td></tr><tr><td>client_secret</td><td>내 애플리케이션 Secret</td></tr><tr><td>code</td><td>인가 코드</td></tr><tr><td>redirect_uri</td><td>리다이렉트된 URI</td></tr></tbody></table>

응답

| token\_type                 | bearer로 고정  | required |
| --------------------------- | ----------- | -------- |
| access\_token               | 액세스 토큰      | required |
| id\_token                   |             |          |
| expires\_in                 | 액세스 토큰 만료시간 | required |
| refresh\_token              | 리프레스 토큰     | required |
| refresh\_token\_expires\_in | 리프레스 만료시간   | required |
| scope                       |             |          |



### 사용자 정보 가져오기
