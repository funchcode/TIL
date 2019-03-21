## 주말을 이용하여 Github Blog에 꼭 정리하자.
> <h3>규칙</h3>
> 1. **Network** 관련 대략적인 개념 정리를 해 놓는 창고이다.<br/>
> 2. <span style="color:red"><b><u>밑줄</u></b></span> : 좀 더 학습할 필요가 있는 주제(블로그에 정리)<br/>
---

<span><h3>Network</h3></span>
[HTTP의 GET과 POST비교](#http의-get과-post비교) <br/>
---

> <span><h3>HTTP의 GET과 POST비교</h3></span>

둘 다 HTTP 프로토콜을 이용해서 서버에 무언가를 요청할 때 사용하는 방식이다.<br/>
🚨HTTP프로토콜을 이용하는 다른 요청방식이 뭐가 있을까 ?<br/>

- **HTTP가 뭐냐**<br/>
**HyperText Transfer Protocol**의 약자로 인터넷 상에서 데이터를 주고 받기 위한 서버/클라이언트 모델을 따르는 프로토콜이다.<br/>
애플리케이션 레벨의 프로토콜로 TCP/IP위에서 작동한다.<br/>

- **GET 방식**<br/>
요청하는 데이터가 **HTTP Request Message**의 Header 부분의 url에 담겨서 전송된다.<br/>
url에 ?가 붙고 데이터가 나열되어 보내게 된다.<br/>
url 공간에 담겨지기 때문에 전송할 수 있는 데이터의 크기가 제한적이다.<br/>
데이터가 그대로 url에 노출되기 때문에 보안 상 GET방식은 적절하지 않다.<br/>

-**POST 방식**<br/>
POST방식의 request는 HTTP Message의 Body부분에 데이터가 담겨서 전송된다.<br/>
전송할 수 있는 데이터 크기가 GET 방식보다 크고 보안면에서도 낫다.(보안적인 면에서는 암호화를 해줘야한다.)<br/>
🚨암호화를 하는 방법은 뭐가 있을까 ?<br/>

- **GET과 POST방식의 차이는 ?**<br/>
GET은 가져오는 것인데, 서버에서 어떤 데이터를 가져와서 보여준다거나 하는 용도로 쓰인다.<br/>
반면에 POST는 서버의 값이나 상태를 변경하거나 추가하기 위해서 사용된다.<br/>
GET방식의 요청은 브라우저에서 Caching 할 수 있다.<br/>
🚨브라우저 Caching이란 ?<br/>

- **🤔HTTP프로토콜을 이용하는 다른 방식**<br/>
주소를 자원이라고 보고 메서드를 동사라고 보는 개발 방식~~("**REST**")~~에서 사용된다.<br/>
**PUT :** '집어넣다'. 전체 수정 및 대체<br/>
**PATCH :** '고치다'. 부분 수정 <br/>
**DELETE :** '지우다'. 제거 요청 <br/>
**HEAD :** 요청에서 헤더만 가져올 때 사용 <br/>
**OPTIONS :** CORS 상황에서 사용된다. 다른 도메인 서버에 OPTIONS 요청을 날린 뒤, 그 서버가 요청을 허용하면 실제 요청을 날리는 형식.<br/>
**TRACE :** 핑퐁테스트 같은 곳에서 쓰인다.<br/>
**CONNECT :** 양방향 통신을 할 때 쓰인다.<br/>

- **🤔암호화를 하는 방법**<br/>
**암호화를 하는 이유**: url에 노출되지 않더라도 패킷 모니터링 프로그램을 이용하면 내용이 그대로 드러난다.<br/>
**암호화 종류**: SEED, PKI 암호화 알고리즘을 사용하거나, <u>SSL</u>을 이용<br/>

---
> <span><h3>TCP, UDP 비교</h3></span>
데이터를 보내기 위한 프로토콜이다.<br/>

- **UDP (User Datagram P) : 사용자 데이터그램 프로토콜**<br/>
UDP는 **비연결형 프로토콜**이다.<br/>
일반적으로 오류의 검사나 수정이 필요 없는 애플리케이션에서 수행할 것으로 가정한다.<br/>
흐름제어, 오류(혼잡)제어 또는 손상된 세그먼트의 수신에 대한 재전송을 하지 않는다.<br/>
🚨흐름제어, 오류제어란 무엇일까 ?<br/>
수신자가 메세지를 수신했는지 확인할 수 없고, 메세지 도착순서를 보장할 수 없다.<br/>
정보를 주고 받을 때 정보를 보내거나 받는다는 신호절차를 거치지 않는다.<br/>
UDP 헤더의 **CheckSum**필드를 통해 최소한의 오류만 검출한다.<br>
🚨CheckSum이 하는 일은 무엇일까 ?<br/>
TCP보다는 속도가 빠르다.<br/>
신뢰성보다는 연속성이 중요한 서비스에 사용된다. 예를 들면 실시간 서비스(streaming)에 자주 사용된다.<br/>

- **TCP (Transmission Control P) : 전송제어프로토콜**<br/>
연결형 서비스로 가상회선방식을 이용한다.<br/>
3-way-handshaking과정을 통해 연결을 설정하고 4-way-handshaking을 통해 연결을 해제한다.<br/>
🚨3-way-handshaking은 어떻게 동작하는 가 ?<br/>
UDP보다 속도는 느리지만, 높은 신뢰성을 보장한다.<br/>
1. 서버소켓은 연결만을 담당한다.
2. 연결과정에서 반환된 클라이언트 소켓은 데이터의 송수신에 사용된다형 서비스로 가상 회선 방식을 제공한다.
3. 서버와 클라이언트는 1대 1로 연결된다.
4. 스트림 전송으로 전송 데이터의 크기는 무제한이다.

- **🤔흐름제어, 혼잡제어**
**흐름제어(flow control)**<br/>
데이터를 송신하는 곳과 수신하는 곳의 데이터 처리 속도를 조절하여 수신자의 버퍼 오버플로우를 방지하는 것이다.<br/>

**혼잡제어(congesion control)**<br/>
네트워크 내의 패킷 수가 넘치지 않도록 제어하는 것을 말하는데, 정보의 소통량이 과다하면 패킷을 조금만 전송하여 혼잡 붕괴 현상이 일어나는 것을 방지한다.<br/>
🚨패킷(Packet)이란 ? <br/>

- **🤔CheckSum필드**
중복검사의 한 형태로, 오류 정정을 통해 송신된 자료의 무결성을 보호하는 단순한 방법이다.<br/>
통신에서는 순환 중복 검사(CRC)를 체크섬이라고 말하기도 한다.<br/>
🚨순환 중복 검사(CRC)는 뭘까 ?<br/>
1의 보수를 취하고 그 합에 대한 결과도 같이 전송, 수신 측에서도 합을 해보고 비교하는 방식으로 오류 검출<br/>
간단한 방식이기는 하나, 워드의 순서가 바꾸어지는 오류에 대한 검출은 하지 못한다.<br/>

- **🤔패킷 Packet**
인터넷 내에서 데이터를 보내기 위한 경로 배정을 효율적으로 하기 위해 데이터를 여러 개의 조각들로 나누어 전송을 하는데, 이 조각을 패킷이라고 한다.<br/>