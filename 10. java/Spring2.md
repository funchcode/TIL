### "INDEX"

1. [Controller 동작방법](#controller-동작방법)
2. RESTController 동작방법

---

## Controller 동작방법

![spring-controller](https://user-images.githubusercontent.com/38316862/60257374-48b62800-990e-11e9-8b21-e1e3cc5bfe33.png)

**Dispatcher Servlet**
클라이언트의 요청을 받아 컨트롤러에게 전달한다.
컨트롤러가 리턴한 결과 값을 View에 전달하여 알맞은 응답을 생성하도록 한다.

**Handler Mapping**
클라이언트의 요청 URL을 어떤 컨트롤러가 처리할지 결정한다.

**Handler Adapter**
Dispatcher의 처리요청을 변환해서 컨트롤러에게 전달.
그 응답결과를 Dispatcher Servlet이 요구하는 형식으로 변환.

**Controller**
클라이언트의 요청을 처리한 뒤 결과를 리턴한다.

**Model And View**
컨트롤러가 처리한 결과를 보여줄 뷰를 결정한다.

**View**
컨트롤러의 처리 결과 화면을 생성한다.

---

## RESTController 동작방법
