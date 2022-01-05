# Web Browser storage

## Local Storage

> Window Storage API

키/값 쌍으로 데이터를 저장

**유효기간 없이** 데이터를 저장

`Cookie`와 다르게 웹 요청 시 데이터를 전송하지 않는다.

- 이는 네트워크 트래픽 비용을 줄이는 효과를 얻을 수 있다.

저장 공간이 아래 `[Session Storage, Cookie Storage]` 보다 크다.

## Session Storage

각 각의 출처에 대하여 독립적인 저장 공간을 페이지 세션이 유지되는 동안 제공한다.

저장 공간이 `Cookie Storage`보다 크다. (최대 5MB)

## IndexedDB

♦ 정리 필요

## Cookie Storage

        하나의 사이트에서 저장할 수 있는 쿠키 최대 수는 **20개**이다.

만료일, 지속시간(duration)을 명시할 수 있고 만료된 쿠키는 웹 요청 시 제외된다.
