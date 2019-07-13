## <img src="../img/angular-icon.svg" width="30"> Angular 특징


---

### "INDEX"

1. [1. Angular 소개](1.-angular-소개)
2. [2. Angular와 AngularJS 차이점](2.-angular와-angularjs-차이점)
3. [3. Angular 장점](3.-angular-장점)
4. [4. 브라우저 지원 범위](4.-브라우저-지원-범위)

---

## 1. Angular 소개

Single Page Application 개발을 위한 구글의 오픈소스 자바스크립트 프레임워크이다.<BR>
웹 어플리케이션 + 모바일 웹 + 네이티브 모바일 + 데스크탑 애플리케이션 기능 지원

정적 타입을 제공하는 TypeScript를 주력 언어로 채택했다.

> Angular 1 버전을 AngularJS, Angular 2 버전 이상을 Angular라고 부른다.
<BR>

## 2. Angular와 AngularJS 차이점

|AngularJS|Angular|
|-----|-----|
|Controller와 $scope 기반|컴포넌트 기반 개발(CBD)|
|angular.module와 jQlite|향상된 모듈 시스템, DOM 제어 기능 제공, API 단순화|

**Angular 특징**<BR>
정적 타이핑과 ECMAScript6 스펙을 충족시키기 위해 TypeScript로 재작성되었다.<BR>
선택적 데이터 바인딩을 지원하고<BR>
디렉티브(directive), 서비스(Services), 의존성 주입(dependency Injection)을 간소화하였다.<BR>
TypeScript 도입(정적 타입, 인터페이스, 제네릭) = 타입 체크 지원 기능 제공.<BR>

AngularJS와 호환성이 없는 브레이킹 체인지를 다수 포함하고 있다.
<BR>

## 3. Angular 장점

**컴포넌트 기반 개발**<BR>
: Component Based Development는 개발 생산성을 높이며 대규모 애플리케이션에 적합하다.

**TypeScript 도입**<BR>
: 강력한 정적 타이핑 그리고 ECMAScript6의 클래스와 모듈 등과 7의 데코레이터를 지원.<BR>
: 사용하는 이유는 다양한 도구의 지원을 받을 수 있는 것이다.<BR>
: 명시적인 정적 타입 지정은 코드의 가독성을 높이고 예측가능하며 컴파일 단계에서 오류를 포착할 수 있다.<BR>
: 모듈, 클래스, 인터페이스 등의 강력한 객체 지향 프로그래밍을 지원받을 수 있다.

**Angular CLI**<BR>
: 간편한 개발 환경 구축을 지원한다.<BR>
: 명령어로 프로젝트 생성, 빌드, 테스트, 구성요소 추가 등을 간편하게 할 수 있다.

**Digest Loop로 인한 성능저하 문제 해결**<BR>
: AngularJS의 단점 중 하나로 Digest Loop로 인한 성능저하인데 Model의 변화를 View에 반영시키는 과정을 말한다.<BR>
: 양방향 데이터 바인딩을 위해서는 watcher가 추가되어야 하는데 watcher에 대해 Digest Loop가 실행되기 때문에 watcher가 늘어날수록 성능이 떨어진다.<BR>
: Angular에서는 성능적으로 개선되었다.

**AoT 컴파일**<BR>
: ngIf, ngFor, ngSwitch와 같은 구조 디렉티브(Structural directive)를 브라우저가 실행 가능한 코드로 변환하는 과정을 런타임에서 하지 않고 사전 컴파일해서 실행 속도를 향상시키는 기법을 사용한다.<BR>
: JIT 컴파일러가 필요 없어서 프레임워크 크기를 줄일 수 있다.

**Lazy Loading**<BR>
: 모든 모듈을 한꺼번에 로딩하지 않고 필요한 시점에 필요한 모듈만을 로딩하는 방식을 이용한다.<BR>
: 불필요한 모듈을 로딩하는 낭비를 방지하여 페이지 로딩 속도를 높인다.

**코드 최적화**<BR>
: 지속적으로 코드 최적화가 이루어지고 있다.
<BR>

## 4. 브라우저 지원 범위

Angular는 대부분의 모던 브라우저를 지원하지만 IE는 9 이상을 지원한다.
