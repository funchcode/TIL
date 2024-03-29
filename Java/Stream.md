# 001. 스트림- Stream

스트림은 **단일 방향**으로 **연속적**으로 흘러가는 것을 의미한다.

물이 높은 곳에서 낮은 곳으로 흐르듯이 데이터는 출발지에서 나와 도착지로 흘러간다는 개념이다. 

* 입력

  * 키보드
  * 마우스
  * 파일
  * 네트워크

* 출력

  * 모니터
  * 파일
  * 네트워크상의 프로그램

## Java.io 패키지

자바에서 데이터 입출력은 Java.io 패키지에서 제공한다.

1. 바이트 단위 입출력 스트림
* 그림
* 멀티미디어
* 문자
> 모든 종류의 데이터들을 주고받을 수 있다.

2.  문자 단위 입출력 스트림
* 문자

### Java.io 패키지 주요 클래스

<table>
  <tr>
    <th><b>Java.io 패키지 주요 클래스</b></td>
    <th><b>설명</b></td>
  </tr>
  <tr>
    <td>File</td>
    <td>파일 시스템의 파일 정보를 얻기 위한 클래스</td>
  </tr>
  <tr>
    <td>Console</td>
    <td>콘솔로부터 문자를 입력하기 위한 클래스</td>
  </tr>
  <tr>
    <td>InputStream/OutputStream</td>
    <td>바이트 단위 입출력을 위한 최상위 입출력 스트림 클래스</td>
  </tr>
  <tr>
    <td>FileInputStream/FileOutputStream</td>
    <td rowspan="5">바이트 단위 입출력을 위한 하위 클래스 스트림</td>
  </tr>
  <tr>
    <td>DataInputStream/DataOutputStream</td>
  </tr>
  <tr>
    <td>ObjectInput/ObjectOutputStream</td>
  </tr>
  <tr>
    <td>PrintStream</td>
  </tr>
  <tr>
    <td>BufferedInputStream/BufferedOutputStream</td>
  </tr>
  <tr>
    <td>Reader/Writer</td>
    <td>문자 단위 입출력을 위한 최상위 입출력 스트림 클래스</td>
  </tr>
  <tr>
    <td>FileReader/FileWriter</td>
    <td rowspan="4">문자 단위 입출력을 위한 하위 스트림 클래스</td>
  </tr>
  <tr>
    <td>InputStreamReader/OutputStreamWriter</td>
  </tr>
  <tr>
    <td>PrintWriter</td>
  </tr>
  <tr>
    <td>BufferedReader/BufferedWriter</td>
  </tr>
</table>