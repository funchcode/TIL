# Simple-Sign 백엔드 개발 기술 노트

## lombok

* ```@Value```
  * 생성한 클래스를 값 객체로 운용하고 싶을 때 해당 어노테이션 사용.
  * 어노테이션을 설정할 경우 모든 필드는 ```private final```하며, ```@Getter```, ```@AllArgsConstructor```, ```@ToString```하게 객체가 정의된다.

## HTTP Authentication

HTTP에서 액세스 제어와 인증을 위한 '프레임워크'를 제공한다.

인증 방식 여러 개 ?
Basic
Bearer
Digest
HOBA
Mutual
AWS4-HMAC-SHA256

서버와 인증을 하기 위해서는 클라이언트는 'Authentication Request Header'에 인증 정보를 포함함으로써 인증을 수행할 수 있다.

<Basic 인증 스킴>
Base64를 이용하여 사용자 ID와 패스워드 쌍으로 인증 정보를 전달한다.
전달하는 정보는 Base64 디코딩을 통해 패스워드 평문을 확인할 수 있으니 보안에 취약하다. 따라서 Https/TLS와 함께 사용되어야 안전하다.

<Bearer 인증 스킴>

OAuth ?
인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단이다.

[Bearer 액세스 토큰을 보내는 세가지 방법]
1. Authorization Request Header
2. Form-Encoded Body Parameter
3. URI Query Parameter
   ㄴ 보안이 취약하여 사용하면 안된다. URL에 그대로 노출됨으로.

## QueryDSL

동적으로 쿼리를 생성하기 위해서 Query DSL을 사용해서 조회 API 기능을 개발했다.

조회할 데이터에 대해 페이징 처리가 필요하여 Pageable 객체를 사용했고 QuerydslRepositorySupport 객체를 상속받아 기능을 구현했다.

내가 기대한 동작은 동적으로 생성한 Query에 Pageable 객체를 주입하여 fetch()할 경우 페이징 처리가 된 리스트가 반환되는 것으로 기대했다.

하지만, 동적으로 생성한 쿼리에 대해 정상적으로 데이터를 가져오기는 하는데 totalPage 부분에서 원하는 동작을 하지 않았다.

조회한 데이터에 대한 페이지 정보는 new PageImpl<>() 객체를 사용하여 가져오도록 구현했는데, 이 객체는 가져올 데이터의 size와 limit절을 제거하여 조회한 전체 데이터 카운트 정보를 넘겨 최종적으로 페이지 처리에 대한 계산이 완료된 데이터를 넘겨받을 수 있다.

이 때, 동적으로 생성한 쿼리를 fetch()하는 부분에서 totalPage 즉 limit절을 제거한 데이터를 따로 fetch()해서 그 값을 PageImpl() 객체 파라미터로 넘겨줘야한다.

나는 이 부분에 실수하여 이상한 totalPage 값을 리턴받았다.


## AWS

EC2 인스턴스 생성할 때 볼륨 크기를 프리티어는 최대 30GB를 누릴 수 있다.

```bash
ssh -i [키페어파일] [ec2-user@public DNS]
```

## 키페어 파일 에러

```bash
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ WARNING: UNPROTECTED PRIVATE KEY FILE! @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

Permissions 0644 for 'ubuntu@example.com/id_rsa' are too open.
It is required that your private key files are NOT accessible by others
```

키페어 SSH 개인키는 중요한 정보이기 때문에 다른 소유자가 읽을 수 있으면 안된다. 따라서, 위와 같은 에러가 발생.

group과 other의 모든 권한을 막아주면 현상이 해결된다.

```bash
chmod 0400 [키페어파일]
```

## 보안패치

생성한 EC2 서버는 CentOS.

`sudo yum update`

## JAVA 설치

설치 가능한 패키지 목록 확인.

`sudo yum list java*`

JDK 1.8 설치.

`sudo yum install java-1.8.0-openjdk-devel.x86_64`

- java-*-devel: JDK 패키지

## 계정 생성

`sudo adduser -m [계정]`

- -m: 사용자 홈 디렉토리 생성

`sudo passwd [계정]` : 계정 비밀번호 설정

## SFTP 연결

Cyberduck FTP Client를 사용해서 EC2 계정에 접속했다.

새로 생성한 계정으로 접속 요청 시 접근 실패가 발생.

이유인 즉슨, 키페어 설정을 해주지 않아서다.

생성한 계정 홈 디렉토리에 .ssh 를 생성한다.

`~/ec2-user/.ssh/authorized_keys` 파일을 그대로 복사한다.

## .tar 압축해제

yum 패키지 관리 도구에서 apache-tomcat 8 버전 설치 패키지가 존재하지 않음.

apache 홈페이지에서 8버전 tar 파일을 다운로드 받아 EC2 서버에 SFTP로 업로드 진행.

`tar -zcvf [.tar/.gz파일]`

- -z: gzip으로 압축하거나 해제.
- -x: tar 압축을 풂.
- -v: 묶거나 풀 때 과정을 화면에 출력.
- -f: 파일 이름을 지정.
