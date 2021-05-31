# Simple-Sign 프론트 개발(React) 기술 노트

React CRA 프로젝트 생성

* ```npx create-react-app [프로젝트 명칭]```

TypeScript 추가

* CRA 프로젝트 생성 동시에 추가
  * ```npx create-react-app [프로젝트 명칭] --template typescript```
* 기존 CRA 프로젝트에 추가
  * ```yarn create react-app my-app --template typescript```

## npx

npm@5.2.+ 버전의 패키지 실행 도구이다.  

### npm/npx

```which npx``` 명령어로 현재 개발 환경에 설치되어 있는지 확인할 수 있다.

* 차이점
  * npx 새로운 패키지 관리 모듈이 아니라, npm을 좀 더 편하게 사용하기 위해 npm에서 제공해주는 도구이다.
* 등장한 이유
  * npm 모듈로 패키지를 설치할 때 전역/프로젝트 두 가지 범위설정 케이스가 있다.
  * 의존성 라이브러리들에 업데이트가 필요할 경우 일일이 찾아서 업데이트해줘야하는 단점이 있다.
  * npx는 일회성으로 원하는 패키지를 npm 레지스트리에 접근해서 실행시키고 설치하여 전역 또는 프로젝트 패키지를 간섭하지 않는다.

## yarn

프로젝트의 의존성을 관리하는 JavaScript 패키지 매니저다.  

* 등장한 이유
  * npm 저장소의 취약한 보안 이슈, 의존 패키지의 버저닝 이슈, 패키지가 증가함에 따라 빌드 성능 저하로 인해 등장하게 되었다.
* 성능
  * Yarn은 모든 패키지를 유저 디렉토리에 저장해 캐싱 처리하여 성능을 개선했다.

## TypeScript

Microsoft 사에서 개발.  

* 등장한 이유
  * 자바스크립트는 weakly typed 언어이기 때문에 값이 숫자인지, 문자인지, null 값인지 확인하지 못한다.
  * 타입을 명시하여 함수에서 정의한 파라미터의 의도를 명확히 알 수 있으며, 개발자의 실수를 방지할 수 있다.

### ts, tsx

* 차이점
  * 타입스크립트를 사용하는 자바스크립트 파일의 확장자는 **.ts**를 사용한다.
  * 리액트 컴포넌트인 경우에는 **.tsx** 확장자를 사용한다.

## CSS

* ```object-fit```
  * **contain** 콘텐츠의 가로세로비를 유지하면서 요소의 콘텐츠 박스 내부에 들어가도록 크기를 맞춤 조절한다.

* ```<img>```
  * 이미지 태그에는 ```position``` 설정을 할 수 없다.

* ```<div>```

* ```styled-components``` 라이브러리
  * CSS in Javascript, JS에 CSS 로직을 작성.

* 선택자
  * ```parent > child``` 선택자 child만 적용
  
* ```<input>```
  * placeholder 속성에 스타일을 지정할 수 있음.


## axios

REST 서버와 통신하기 위해 해당 모듈을 불러와 사용한다.

```> yarn add axios```  

Javascript built-in 라이브러리인 fetch API로 대체가능하다.  

* fetch API - axios와 비교했을 때 함수를 호출하는 방법, 세팅하는 설정 값 외에 큰 차이점은 없는 듯 보인다.

내부적으로 Promise API를 사용하여 HTTP 비동기 통신을 한다.

## Redux

## 프로젝트 구조

회원가입 페이지만 개발했을 뿐인데, Api 통신, 유틸성 함수, 타입과 관련된 코드가 많이 나타났다.  
**회원가입.tsx**에 몽땅 때려넣는 건 개발자로써 자존심이 상하기 때문에 남들은 React 프로젝트 구조를 어떻게 설계하는지 찾아봤다.

* MVC 패턴 방식 (뷰와 내부 로직 처리에 대한 기능을 분리하여 관리)
* Atom (화면 레이아웃에 해당하는 기능을 명확히 구분하여 컴포넌트를 작게 쪼개어 관리)

[2021.05.31]  
React 공식 문서에서는 이제 막 프로젝트를 시작한 단계에서 파일 구조에 대해 **5분 이상 투자하지 말라고** 권장한다.  
그 의견을 적극 수용하여 아래와 같은 틀을 잡아 일단 먼저 개발을 진행하고 추후에 존재하는 디자인 패턴 적용이 절실하게 필요한 시점이 오면 다시 찾아봐야겠다.
[리액트 공식 홈페이지(reactjs.org)](https://ko.reactjs.org/docs/faq-structure.html)  
> 현재 구성한 프로젝트 구조  
> * src  
>   * @types     [타입과 관련된 파일 모음]
>   * api        [Rest 서버와 통신하는 파일 모음]
>   * components [컴포넌트 모음]
>   * images     [리소스 파일 모음]
>   * pages      [페이지 모음]
>   * styles     [전역 CSS 설정 관련 파일 모음]
>   * utils      [유틸성 파일 모음]

## React Route

회원가입 페이지와 성공/실패 알림에 대한 Toast 컴포넌트 개발을 완료하고 나서, 성공 후 페이지 전환이 필요하게 되었다.  
React 페이지 전환을 검색해보니 연관 검색어로 'react route'가 가장 많이 나왔다.  

React는 **SPA**이다.  
> single page Application
  
[공식 홈페이지 reactrouter.com](https://reactrouter.com/web/guides/quick-start)  

설치  
> yarn add react-router-dom

주요 구성 요소   

1. Routers
2. Route Matchers
3. Navigation(or Route Changers)

### Routers

일반적으로 최상위 요소를 래핑하여, 라우터 사용을 선언한다.

```javascript
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {BrowserRouter} from 'react-router-dom';

ReactDOM.render(
    <React.StrictMode>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </React.StrictMode>
);
```

### Route Matchers

구성요소로 ```<Switch>```와 ```<Route>```가 존재한다.    
현재 URL과 일치하는 Route Path를 찾아 렌더링한다.  
일치하는 항목이 없을 경우 아무것도 렌더링하지 않는다.  

## Sass(SCSS)

로그아웃 화면을 적용한 후 로그인 화면을 만들다보니 로그아웃 컴포넌트에서 사용한 class 명과 CSS 속성 값 충돌이 발생했다.  
~~클래스 명 충돌 방지와 코드 중복을 최소화하기 위해 SCSS를 적용하기로 했다.~~  

Sass는 CSS의 전처리기이다. (**CSS Preprocessor**)  

> 브라우저는 CSS로만 동작한다.  

CSS 문법과 유사하며 + 선택자 중첩(Nesting), + 조건문, + 반복문, 다양한 단위(Unit) 연산 등의 기능을 사용할 수 있다.  

### SCSS

Sass(Syntactically Awesome Style Sheets)의 3버전에서 새롭게 등장했다.  
SCSS는 CSS 구문과 완전히 호환되도록 새로운 구문을 도입해서 만든 Sass의 확장판 상위집합(superset)이다.  

* 차이점
  * Sass는 들여쓰기로 유효범위(스코프)를 구분, SCSS는 ``{}`` 로 구분한다.

[heropy.blog](https://heropy.blog/2018/01/31/sass/)

## styled-components

자바스크립트의 태그가 지정된 템플릿 리터럴과 CSS의 기능을 사용하여 구성 요소에 반응하는 스타일을 제공하는 **CSS-in-JS** 스타일링을 위한 프레임워크이다.  

> 템플릿 리터럴이란 내장된 표현식을 허용하는 문자열 리터럴을 말하며, 백틱(`) 특수문자를 사용한다.      
> [MDN - Template literals](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Template_literals)
