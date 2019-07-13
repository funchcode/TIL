Angular Guide

간단한 온라인 쇼핑몰 애플리케이션 만들기

---

개발환경

[StackBlitz 웹 환경](https://stackblitz.com/angular/pomnpxdepkm)

참고자료

[Angular 공식 홈페이지](https://angular.kr/start)

---

![StackBlitz 기본 페이지]()

![프로젝트 구조]()

![페이지 구조]()

1. 상품 정보 가져오기.

```JavaScript
// product-list.component.ts
import { product } from '../products';
```

`products.ts`파일은
상품 (상품명, 가격, 설명)이 들어있는 파일이다.

`product-list.component.ts`파일에서
import로 읽어들인다.

![products-ts 파일]()

2. list 컴포넌트에 상품명 노출하기.

```JavaScript
// product-list.component.html
<p *ngFor="let product of products">
  <h3> {{product.name}} </h3>
</p>
```

`*ngFor=`은
구조 디렉티브(structural directive) 중 하나이다.

> "structural directive" <BR>
> 일반적으로 엘리먼트를 추가하거나 제거, 변형하는 방식으로 DOM구조를 구성하는 디렉티브이다.

`{{}}`은
문자열 바인딩 문법(interpolation syntax)을 말한다.

```JavaScript
<h3>
  <a [title]="product.name + ' details'">
    {{ product.name }}
  </a>
</h3>
```
