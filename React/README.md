# React 정리

단일 책임 원칙을 지키면서 Component를 개발해야한다.

= 하나의 컴포넌트는 한 가지 일을 하는게 이성적이다.

Component가 비대해지면 보다 작은 하위 Component로 분리시켜야 한다.

DOM에 처음 렌더링 될 때를 **마운팅**이라고 부른다.

생성된 DOM이 삭제될 때 **언마운팅**이라고 부른다.

Props, State

→ State: 비공개면서 컴포넌트에 의해 완전히 제어된다.

→ Props(Properties의 줄임말)

React는 성능을 위해 여러 `setState()` 호출을 단일 업데이트로 한꺼번에 처리할 수 있다.

⇒ `this.prop` 와 `this.state` 가 비동기적으로 업데이트될 수 있기 때문에 다음 state를 계산할 때 해당 값에 의존하면 안된다.

### Component 호출 흐름

1. `ReactDOM.render()` 에 Component가 전달되었을 때 `constructor`를 호출한다.
2. `state` 를 초기화한다.
3. Component의 `render()` 를 호출하여 화면에 표시할 내용을 알게 된다.
4. Component가 DOM에 삽입되면 `componentDidMount()` 생명주기 메서드를 호출한다.
5. `state` 가 변경된 것을 인지하면 `render()` 메서드를 다시 호출한다.
6. DOM 업데이트를 한다.
7. Component가 DOM으로부터 삭제가 된다면 `componentWillUnmount()` 생명주기 메서드를 호출한다.

### State 선언 위치 찾기

→ state를 기반으로 렌더링하는 모든 컴포넌트를 찾는다.

→ 공통 소유 컴포넌트를 찾는다. (계층 구조 내에서 특정 state가 있어야 하는 모든 컴포넌트들의 상위에 있는 하나의 컴포넌트)

→ 공통 혹은 더 상위에 있는 컴포넌트가 state를 가져야 한다.

→ `state` 를 소유할 적절한 컴포넌트를 찾지 못하는 경우 state를 소유하는 컴포넌트를 하나 만들어서 공통 소유 컴포넌트의 상위 계층에 추가한다.

### Topdown(하향식), Buttomup(상향식)

→ 하향식: 상위 컴포넌트에서 하위 컴포넌트에 데이터를 props로 넘겨준다.

→ 상향식: 하위 컴포넌트에서 상위 컴포넌트로부터 props를 받아 데이터를 표시한 후 데이터의 변경이 발생하는 경우 상위 컴포넌트에게 이벤트를 전달한다.

### HOC (High order Component) 고차 컴포넌트

→ React API의 일부가 아닌 React 구성적 특성에서 나온 **패턴**이다.

→ Component를 가져와 New Component를 반환하는 **함수**이다.