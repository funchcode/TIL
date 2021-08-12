### "INDEX"

1.

---

## HTTP Method

주소(URI)를 자원이라고 판단하고 메서드(HTTP Method)를 동사라고 보는 개발 방식인 **REST**;에서 주로 사용된다.

#### GET Method
특정 자원(Resource)의 표시를 요청한다.
이 메서드를 사용하는 요청은 오직 데이터만 받기만 한다.

#### HEAD Method
GET 메소드의 요청과 동일한 응답을 요구하지만, 응답 본문(Body)를 포함하지 않는다.
Header만 가져올 때 사용한다.

#### POST Method
특정 자원(Resource)에 엔티티를 제출할 때 쓰인다.

#### PUT Method
POST와 유사한 전송 구조를 가지고 있다.
요청된 자원을 수정(Update)한다.

#### DELETE Method
특정 자원(Resource)를 삭제한다.

#### CONNECT Method
동적으로 터널 모드로 교환, Proxy 기능을 요청시 사용한다.

#### OPTIONS Method
웹 서버에서 지원되는 메소드의 종류를 확인할 경우 사용한다.

#### TRACE Method
원격지 서버에 루프백 메세지를 호출하기 위해 테스트용으로 사용한다.

#### PATCH Method
PUT과 유사하게 요청된 자원을 수정할 때 사용한다.
PUT의 경우 자원 전체를 갱신하는 의미지만, PATCH는 해당자원의 일부를 교체하는 의미로 사용한다.
