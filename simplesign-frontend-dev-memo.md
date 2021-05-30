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
