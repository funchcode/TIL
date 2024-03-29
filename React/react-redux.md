# react-redux



## Redux

리액트 상태 관리 라이브러리이다. 내부적으로 Context API를 사용한다.

### Action

`type` 프로퍼티를 반드시 가지고 있어야하며, `type`은 액션 이름으로 사용된다.

### Reducer

변화를 일으키는 함수이다.

현재 상태와 요청 액션을 파라미터로 받아 새로운 상태를 만들어 반환하는 역할을 수행한다.

### Store

프로젝트에 리덕스를 적용하기 위해 스토어를 만든다.

한 개의 프로젝트는 단 하나의 스토어만 가질 수 있다.

### Dispatch

> `Store(스토어)`의 내장함수 중 하나이다.

액션을 발생시키는 역할을 수행한다.

### Subscribe

> `Store(스토어)`의 내장함수 중 하나이다.

액션이 `dispatch`되어 상태가 업데이트될 떄마다 호출된다.

### 규칙

#### 단일 스토어

하나의 애플리케이션 안에는 하나의 스토어만이 존재한다.

#### 읽기 전용 상태

상태를 업데이트할 때 기존의 객체는 건드리지 않고 새로운 객체를 생성해줘야한다.

> 불변성을 유지하는 이유는 내부적으로 데이터가 변경되는 것을 감지하기 위해 얕은 비교 검사를 한다.

[리덕스 공식 홈페이지](https://ko.redux.js.org/introduction/getting-started/)