## 2019년 4월 학습공책
> <h3>규칙</h3>
> 1. 학습한 내용을 대략적이라도 정리하고 지나칠 지식은 메모한다.<br/>
> 2. 부족한 개념은 정리하고 주말에 정리한다.<br/>
---

# 2019.04 - 1 👇🏾<br>
> <h2>@토이프로젝트</h2><br>
### **AWS(ECS: EC2 Container Service)에 대한 학습**<br>
Docker(도커)를 이용해 서비스를 구축하려면 여러가지 고려할 사항이 많은데 **ECS**는 인프라 환경을 좀 더 편리하게 운영하고 관리할 수 있게 해준다. <br>

<u>@"ECS의 구성요소"</u> <br>
1. Task Definition : 컨테이너 이미지, CPU/메모리 설정, port매핑, volumn설정 같은 것들
2. Task : Task Definition에서 정의된 대로 배포된 Container set을 Task라고 한다. ECS의 최소단위이다.
3. Service : Task들의 Lifecycle을 관리, Cluster에 몇 개나 배포할 것인지 결정하고 고가용성의 정책을 담당한다.
4. Container Instance : ECS는 컨테이너 배포를 EC2 인스턴스 기반에 올리도록 설계되어 있다. Task를 올리기 위해 등록된 EC2 Instance를 Container Instance라고 부른다.
5. Cluster : Task가 배포되는 Container Instance들을 논리적인 그룹으로 묶이게 되는데, 이 단위를 Cluster라고 부른다. <br>

<u>@"ELB : Elastic Load Balancing"</u> <br>
- 들어오는 애플리케이션 트래픽을 여러가지 대상으로 자동으로 분배시키는 역할을 한다.
- 자동 확장/축소, 강력한 보안을 갖고 있다. <br>

<u>@"ECS vs Kubernetes/Docker swarm"</u><br>
**Docker 이슈** : 대규모 컨테이너 관리를 위해 반드시 해결해야하는 문제들이 있다.
- host container를 어떻게 배포할 것인지에 대한 스케줄링 관련 문제
- 다른 host container에 배포된 container 사이의 통신 문제
- 동일 Port를 사용하는 컨테이너들을 동시 수용했을 때 host 포트 맵핑 이슈<br>
**Kubernetes/Docker swarm**
- overlay network 구조를 추가해 해결, host간 네트워크를 논리적으로 묶어서 연결<br>
**ECS Cluster**
- ELB 서비스를 통해 해결
- ELB의 밸런싱 그룹에 task들이 자동으로 들어가도록 설계되어 있다.(= service간의 통신은 ELB를 통해 통신하게 한다.)<br>

<u>@"Container 특성 상 DB와 같은 stateful한 서버는 EC2나 RDS를 이용해 연계하여 사용"</u><br>
컨테이너는 기존의 인프라 방식에 비해 생성과 소멸에 좀 더 유연한 성질이 있다.(= 언제든 삭제 될 수 있다.)<br>
그러므로 data store가 목적인 DB는 컨테이너의 컨셉과 상반된 형태이다.<br>

<u>@"ECR 추가적인 설명"</u><br>
**어떤 서비스인가? >** Docker Container의 이미지를 저장하는 Repository 서비스이다.<br>
기능은 Docker hub의 Repository와 동일하다.<br>
Docker Private Repository 구축하고 관리하는 수고를 AWS에 맡기는 Managed서비스이다.<br>

Container이미지를 S3에 저장되기 때문에 고가용성이 유지되고, IAM인증을 통해 Pull/Push에 대한 권한 관리가 가능하다.<br>

<u>@"EC2 인스턴스 생성 후 젠킨스 설치하기"</u><br>
1. SecurityGroup을 설정한다 (= 젠킨스를 사용하는 포트번호가 8080번이다.)
- SSH 포트를 설정을 안해서 에러발생. EC2인스턴스에 접근하려면 SSH포트를 열어줘야한다.
2. pem을 통해 인스턴스에 접속, yum패키지 매니저 최신으로 업데이트
3. AWS EC2 Linux에는 자동으로 java 7이 설치되어 있다. 8버전으로 재설치.
- 자바 9는 젠킨스와 사용못한다.
4. yum이 어디서 jenkins를 설치해야 할 지 알 수 있도록 Jenkins repository를 추가한다.
```console
sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
```
5. Jenkins를 설치할 때 파일들이 신뢰할 수 있는 source로부터 제공됨을 증명하기 위해 로컬 GPG 키링에 Jenkins GPG key를 추가해준다.
```console
sudo rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
```
🚨궁금증# GPG key가 무엇인가?🚨
👉🏽 사용자정의 rpm을 안전하게 배포하기 위해서 사용한다. 배포자가 패키지 서명을 하고 사용자는 공개키를 받아서(wget) 사용한다.<br>
6. Jenkins를 설치한다.
```console
sudo yum install jenkins
```
- 에러 : ```No more mirrors to try```
    + 시스템에서 오래된 yum캐시 삭제 : ```yum clean all```
    + 유효한 저장소를 나열할 수 있는가 : ```yum repolist```
터미널에 ```Complete!```를 확인했으면 된거다.
7. Jenkins 서버 시작.
```console
sudo service jenkins start
```
8. 8080 port가 LISTEN상태인지 확인
```console
netstat -na | grep 8080 
```

> 🚫 상위 내용을 그대로 적용시 **Error** 발생. 새로운 발상 필요 아래 발상 정리.
> 1. EC2와 ECS의 차이 [ECS가 하나의 서비스인가, EC2에서 안에서 구동되는 서비스인가]
> 2. Jenkins 속도 EC2 성능 문제인가 [EC2 인스턴스 최상급은 무엇인가]
> 3. ECS와 docker는 분리된 서비스인가, 하나의 서비스인가
> 4. ECS와 S3서비스의 연동과 image 저장 방법
> 5. ECS에서 배포는 어디에서 적용되는 것인가 [blue-green deploy 구현 위치]

<u>@"1. EC2와 ECS의 차이"</u><br>
AWS홈페이지를 보면 **AWS Fargate**용어가 나온다. Fargate와 EC2 중에 택하여 ECS를 사용한다.
- Fargate : 서버 또는 클러스트를 관리할 필요없이 컨테이너를 실행할 수 있도록 지원하는 ECS를 위한 컴퓨팅 엔진이다.
- EC2 : 자신이 관리하는 AWS EC2 인스턴스 클러스터에서 컨테이너화된 애플리케이션을 실행할 수 있다.

<u>@"EC2에 Jenkins 설치"</u><br>
> 🚫 에러발생
> Failed to connect to repository : Error performing command: git ls-remote -h [url] HEAD
> > EC2에 git을 설치해서 해결했다. 
```sudo yum install git```<br>

<u>@"흐름 다시정리"</u><br>
> Git(master/develop) - Jenkins(-Slack) - AWS S3 - AWS Deploy - AWS EC2<br>


EC2인스턴스에서 Jenkins(default port:8080) 변경하는 방법. <br>

```
sudo vim /etc/sysconfig/jenkins
```
에서 JENKINS_PORT를 변경하고 재시작<br>

AWS와 도커를 함께 사용하려면 ECS를 사용해야하는데 내가 하는 프로젝트의 규모가 ECS를 모두 누려보기에는 적합하지 않다 판단하여 도커를 사용하지 않는 쪽으로 방향을 잡음.<br>

Elastic IP를 EC2에 적용하지 않아 요금이 청구되었음. 프리티어 만료된줄 알았음.<br>
> 🚫 에러발생
> Execution failed for task ':compileJava'. Could not find tools.jar.<br>
```sudo yum install java-1.8.0-openjdk-devel```을 설치해서 해결<br>
**/usr/lib/jvm/java-1.8.0----------/lib/tools.jar** 가 존재하지 않았음.<br>

> <h2>@리눅스</h2>

### **리눅스 완벽 입문서 대한 학습**<br>
리눅스 명령어는 쉘에 내장되어 있다.(=쉘 함수이다.) <br>
```
man
```
명령어에 섹션번호가 존재한다.(=목차) <br>
세미콜론으로 각 명령어를 구분해서 한 줄에 표현가능하다. <br>

---
# 2019.04 - 2 👇🏾<br>
> <h2>@토이프로젝트</h2>

### **'tutorial' 브랜치로 스프링부트 튜토리얼 시작**<br>
@"SpringBoot - Actuator"<br>
Actuator : 작동기, Springboot Application 상태확인이 가능케해준다.<br>

@"@RequestBody"<br>
HTTP 요청 Body를 자바 객체로 전달 받을 수 있다.<br>

@"@ResponseBody"<br>
자동으로 JSON으로 직렬화한다.<br>

@"@RestController"<br>
사용 시에 리턴 값에 자동으로 @ResponseBody가 붙는다.<br>

@".gitignore 파일"<br>
커밋대상에서 제외시켜준다. 무시목록을 설정하는 개념.<br>

@"Lombok"<br>
생성자 자동 생성 가능 (매개변수에 따라 이름이 다르다.)<br>

@"@GeneratedValue"<br>
autoIncrement<br>

@"@Builder"<br>
파라미터 순서 필요없고 명시적으로 값을 추가하기 때문에 가독성에 좋고, 리팩토링에 유연하다.<br>

- Entity 클래스 값 변경 필요시 Setter가 아닌 메서드를 통해 변경시키도록 해야한다.<br>
- ibatis/Mybatis = DAO<br>
- JPA = Repository<br>

@"import static"<br>
정적 메소드를 더욱 쉽게 사용가능하게 한다.<br>

@"BDD"<br>
: Behaviour-Driven-Development<br>
given: 환경구축, when: 행위선언, then: 결과검증<br>

- Entity(Core한)와 DTO(VIEW)의 역할 분리 확실하게 해야한다.<br>

@"Handlebars"<br>
의존성 주입 덕에 문자열 반환 시 앞과 뒤에 문자열을 자동으로 추가해준다.<br>

@"JS에서 변수에 함수"<br>
Scope관리와 의존성 관리를 용이하기 위해 사용한다.<br>

- 데이터의 수정/삭제/등록의 경우 JPA를 이용하고 조회와 같은 경우 querydsl을 이용한다.

@"Travis / AWS Codedeploy와 Jenkins"<br>
Travis와 AWS Codedeploy같은 경우 설치형이 아니고 Jenkins는 설치형이다.<br>
설치형이 아닌 경우 .yml파일로 관리해야한다. 설치형은 플러그인을 활용하면 된다.<br>

@"Git 특정 branch만 클론하는 방법"
``` git clone -b [브랜치명] --single-branch [저장소URL] ```

> 🚫 에러발생 <br>
> MariaDB 세팅 중 Illegal mix of collations (latin1_swedish_ci,IMPLICIT) and (utf8_general_ci,COERCIBLE) for operation '=' 오류 <br>

```console
ALTER DATABASE [database] CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE [table] CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
```

---
# 2019.04 - 3 👇🏾 <br>
### DataBase MariaDB 정리
**RDB(Relational DataBase) :**<br>
특징1) 관계형 데이터 모델의 데이터베이스이다.<br>
특징2) 2차원 테이블로 표현가능하다.  <br>
특징3) 확장이 용이하고 SQL(Structed Query Language)를 사용한다. <br>
특징4) 트랜잭션과 ACID를 지원한다.<br>

**ERD(Entity Relationship Diagram) :** <br>
테이터와 데이터의 관계를 표식한다. 나는 ERDCloud를 사용했다. <br>
방법1) Logical 직관적으로 표기한다.[한글로 칼럼들을 표기] <br>
방법2) Physical 내용 그 자체를 모두 표기한다. <br>

**Type :** <br>
: TINYINT : 매우작은 정수 1byte <br>
: SMALLINT : 작은 정수 2byte <br>
: MEDIUMINT : 3byte <br>
: INT : 4byte <br>
: BIGINT : 8byte <br>
: DOUBLE : 8byte <br>

**DECIMAL TYPE :** <br>
DECIMAL은 숫자데이터이지만 내부적으로 String으로 저장한다. <br>
DECIMAL(B,F)인데 F가 0일 경우 소수점 이하는 저장되지 않는다. <br>
동작시엔 CHAR 형태로 동작한다. <br>

**CHAR vs VARCHAR :** <br>
CHAR은 고정형, VARCHAR은 가변형이다. 즉 용량을 가변적으로 처리한다. <br>
VARCHAR는 가변형인데 잘라서 저장한다. 즉 검색이 필요한 경우 복잡하다. <br>
칼럼의 최대 길이가 정확히 정해진 경우 CHAR 타입을 사용하여 검색 시 효율을 높일 수 있다. <br>

**VARCHAR vs TINYTEXT :** <br>
공통점으로 둘 다 최대길이가 255이다. <br>
차이점은 최대길이를 설정할 수 있느냐없느냐인데 최대길이를 지정해서 제한을 줄 수 있다. <br>

**TEXT vs BLOB :** <br>
TEXT는 문자를 저장하고 BLOB는 데이터를 저장한다(파일이나 이미지). <br>
공통점으로 큰 데이터를 저장한다. <br>

**ENUM vs SET :** <br>
둘 다 열거형 데이터를 저장하는데 쓰인다. <br>
ENUM은 데이터 1개를 뽑아내는 경우이고 SET은 Bit연산이 가능해서 여러개 선택 가능하고 비트연산이 가능하다. <br>
ENUM은 저장하는 데이터와 표현하는 데이터가 다르다. <br>
s
---
# 2019.04 - 3 👇🏾 <br>
### Interview 준비
**web.xml :**<br>
Deploy할 때 Servlet의 정보를 설정한다. ```<servlet-mapping>```은 url을 서블릿에 연결 <br>

**DispatcherServlet :**<br>
SpringContainer를 생성해서 Controller life cycle을 관리한다. <br>
클라이언트의 요청을 처음으로 받는 서블릿인데 클라이언트의 요청을 controller에 보내는 역할을 한다. <br>

**HandlerMapping :**<br>
어떤 url이 받을지 판단 후에 ViewResolver가 prefix-suffix를 적용한다.

**ContextLoader :**<br>
Controller가 공유하는 Bean들을 포함하는 Spring Container를 생성한다. 생성한 Bean을 참조할 수 있게 해준다. <br>

**annotation-driven(servlet-context) :**<br>
Annotation을 활성화하겠다. <br>

**context: component-scan base-package :**<br>
해당 패키지를 스캔하여 Annotation이 달린 것을 bean으로 생성하여 Container에 담는다. <br>

**FileUpload :**<br>
common-fileupload를 메이븐에 추가하고 springframework.multipart.support.StandardServletMultipartResolver를 추가한다. <br>

**Spring-Security :**<br>
Spring 기반의 어플리케이션의 보안 즉 인증과 권한을 담당하는 프레임워크이다. <br>
filter 기반으로 동작하기 때문에 Spring MVC와 분리되어 관리 및 동작한다. <br>
1. 접근주체(Principle) : 보호된 대상에 접근하는 주체를 말한다.
2. 인증(Authentication) : 현재 유저가 누구인지 확인한다.
3. 인가(Authorize) : 현재 유저가 접근할 수 있는 권한이 있는지 검사한다.
4. 권한 : 인증된 주체가 애플리케이션의 동작을 수행할 수 있도록 허락되어 있는지 결정한다.
<br>
1. 유저가 로그인 시도
2. DB에 있는 유저이면 UserDetails로 꺼내서 Session을 생성한다.
3. 세션저장소인 SecurityContextHolder에 저장된다.
4. 유저에게 SessionID와 함께 응답한다.
5. 이후 요청 시 JSSESSIONID를 확인하여 Authentication을 준다.

모든 접근 주체는 Authentication을 생성한다. SecurityContext에 보관되고 사용된다. <br>

사용법 :
- 의존성을 추가한다.
- web.xml에 SpringSecurityFilterChain을 등록한다.
    + HttpSessionEventPublisher : 동시 로그인을 제한한다.
    + DelegatingFilterProxy : 모든 요청은 이 프록시 필터를 거친다. 인증/인가를 수행한다.

나는 UserDetailsService를 상속받아 user객체를 가져와 반환하는 클래스를 만들었고 AuthenticationSuccessHandler와 AccessDeniedHandler를 상속받아서 구현했다. <br>
PasswordEncoding으로는 BcryptPasswordEncoding을 사용했다. <br>
@PreAuthorize를 사용하여 권한 설정을 했다. 이 어노테이션은 함수를 실행하기 전에 권한을 검사하는 역할을 한다. <br>
isAuthenticated() : 현재 사용자가 익명이 아니다. <br>
isRememberme(), hasRole(""), hasAnyRole("","") <br>
@PostAuthorize는 함수를 실행하고 응답을 하기 직전에 권한을 검사하는 어노테이션이다. <br>
파라미터에 접근할 수 있는 접두문자로 "#"이 있다. <br>

**HikariCP :**<br>
pom.xml 설정, properties 설정, default JDBC Pool <br>

**Mybatis :**<br>
마이바티스는 JDBC로 처리하는 상당부분의 코드와 파라미터 설정 및 결과 매핑을 대신해준다. <br>
VO에 List 타입이 있고 Mybatis를 사용하기 위해 mapper.xml에 resultMap을 생성하고 collection을 이용하여 리턴을 만들었다. <br>

**MediaType.APPLICATION_OCTET_SPREAM_VALUE :**<br>
MediaType은 전송하는 데이터 타입이고 그 뒤는 파일 내용 전송을 의미한다. <br>

**ISO-8859-1 Encoding :**<br>
아스키 코드는 7비트 인코딩이고 8번째 비트를 활용해서 만든 확장 인코딩이다. HTML 문서의 기본 인코딩이다. <br>

**log4j :**<br>
오픈소스이다. 속도에 최적화되어 있다. <br>
FATAL > ERROR > WARN > INFO > DEBUG > TRACE <br>

**CSRF 공격 :**<br>
웹 어플리케이션의 취약점 중 하나로 인터넷 사용자가 자신의 의지와 무관하게 공격자가 의도한 행위를 특정 웹 사이트에 요청하게 만드는 공격 <br>

**CSRF TOKEN :**<br>
Spring에서 ```<input type hidden name={_csrf.parameterName} value={_csrf.token}>```을 사용한다. <br>
사용하지 않으면 403 접근 권한 오류와 springsecurity의 forbidden page로 이동한다. <br>
사용자의 세션에 임의의 난수를 저장하고 사용자의 요청마다 해당 난수 값을 포함시켜 전송하고 요청받을 때 세션에 저장된 Token값과 요청 파라미터에 전달되는 토큰 값이 일치하는 지 검증한다. <br>

**Google VISION API :**<br>
머신러닝 기술을 이용한 API이다. <br>
사물 / 로고 / 텍스트 / 랜드마크 / 얼굴탐지 / 유사이미지검색 / 불건전컨텐츠감지 의 기능을 갖고 있다. <br>
사용시에 key가 필요하다. (비공개키를 JSON으로 받을 수 있다.) <br>
boundingPoly로 사람의 얼굴을 감지했다. <br>

**Node.js :**<br>
가장 큰 특징으로 JavaScript 언어를 사용하여 백/프론트 엔드 영역을 모두 사용할 수 있게 되었다. <br>
크롬 V8 엔진을 기반으로 비동기 IO를 지원한다. <br>
단점으로는 싱글스레드로 처리되기 때문에 하나의 작업 시간이 길어지면 전체 성능이 낮아진다. <br>
또한, 가독성이 좋지 않기 때문에 유지보수가 어렵고 실행해야 에러를 알 수 있다.(컴파일이 없다) <br>

**Express :**<br>
Node.js를 위한 빠르고 간편한 웹 프레임워크이다. <br>
http와 connect 컴포넌트 기반으로 하는 웹 프레임워크이다. <br>

**SocketIO :**<br>
실시간으로 상호작용하는 웹 서비스를 만드는 기술인 웹 소켓을 쉽게 사용할 수 있게 하는 모듈이다. <br>
emit()으로 전달하고 on()으로 전달 받는다. <br>
클라이언트와 서버 간의 양방향 통신을 지원하는 특징이 있다. <br>
클라이언트에서 io()시에 io.on('connection')가 되고 io.on('disconnection')시 로그아웃을 의미한다. <br>
특정 소켓을 제외한 모든 소켓에 메세지를 전달하기 위해서는 broadcast를 사용하면 된다. <br>
특정 소켓에만 메세지를 전달하기 위해 to()를 사용하면 된다. <br>

**fs Module :**<br>
Sync가 붙은 것은 동기적이라는 의미이다. Sync가 붙으면 해당 작업이 끝아야 다른 작업을 할 수 있다. <br>
예외 발생 원인으로는 권한, 파일 존재여부, 용량 초과 등이 있을 수 있다. <br>
예외 처리하는 방법 :
1. 동기적 : try ~ catch구문을 사용하여 예외를 처리한다.
2. 비동기적 : callback함수의 매개변수 err에 전달된다.

**Firebase :**<br>
NoSQL 즉 데이터베이스이다. <br>
key-value의 형식이고 JSON 방식으로 데이터가 저장되고 관리된다. <br>
나는 실시간 동기화를 지원하는 실시간데이터베이스를 사용했다. firebase.database() <br>
내가 만든 테이블 :
1. 메세지 테이블
2. 읽지 않음 카운트 테이블
3. 채팅방 테이블
4. 유저 테이블
5. 유저가 가진 방 테이블

주로 만들어진 방을 기준으로 전체 테이블이 상호작용하도록 설계했었다. <br>

**Promise 기법 :**<br>
자바스크립트 비동기 처리에 사용되는 객체이다. <br>
```
new Promise()   resolve()   then()
```
Promise 기법에는 중요한 3가지의 상태가 존재한다. <br>
1. Pending(대기) : 비동기 로직이 아직 처리안된 상태를 말한다.
2. Fulfilled : 비동기처리가 완료되어 프로미스가 결과값을 반환함을 말한다.
3. Rejected : 비동기처리가 실패이거나 오류발생을 말한다.

new Promise()가 Pending 상태 <br>
resolve()가 Fulfilled상태 then()을 사용하면 처리 결과를 받을 수 있다. <br>
reject()가 Rejected상태이고 실패처리 결과 값은 then.catch()로 받을 수 있다. <br>

예외처리 방법 :
1. then().catch()로 분기를 이용하여 예외처리를 하는 방법
2. then(handleSuccess, handleError)의 두번째 인자를 사용하여 예외처리를 하는 방법

**EXIF :**<br>
image, thumbnail, exif, gps 등의 사진 속에 담긴 데이터를 받을 수 있다. <br>
나는 EXIF gps 데이터를 추출하여 GoogleMaps에 데이터를 활용하였는데 모바일로 사진을 촬영시에 gps가 켜지지 않은 상태에서는 gps 데이터가 존재하지 않았다. <br>

**NPM :**<br>
node packaged manager 이다. <br>
모듈의 의존성을 JSON을 사용하여 한꺼번에 관리할 수 있다. <br>
"scripts"는 run을 통해서 실행할 것들을 의미하고 "dependencies"는 설치할 모듈들을 의미한다. <br>

**Webpack :**<br>
Web에서 사용하는 모든 자원을 번들링해주는 도구이다. 네트워크 요청을 최소화시켜준다. <br>
플러그인이 있는데 번들링 될 시에 추가적인 작업을 더 진행한다는 개념이다. <br>
번들링된 파일 디버깅 시에 코드가 변환되어있거나 추가되어 있기 때문에 디버깅이 어렵다 그렇기 때문에 따로 설정을 해줘야한다. <br>
Webpack에서는 devtools를 제공한다. <br>
개발/배포 환경의 공통 작업을 묶고 난독코드변환 작업같은 것은 배포환경에서만 필요한 것이니 분리할 필요가 있다. <br>
webpack-merge를 통해 분리할 수 있다. <br>
```
공통작업.js

const merge = require('webpack-merge')
const baseConfig = require('공통')

module.export = merge(baseConfig, 필요한 작업)
```
webpack으로 설정되어 있는 소스코드를 수정하기 위해서는 수정 후 다시 빌드 및 새로고침해야한다. <br>
webpack-dev-server를 이용하면 시간을 절약할 수 있다. <br>
devServer:와 plugin에 HotModuleReplacementPlugin을 사용하면된다. <br>

**bundling :**<br>
여러 개의 파일 중에 종속성이 존재하는 파일을 하나의 파일로 묶어 패키징을 시키는 과정을 의미한다. <br>
번들링을 하면 네트워킹 요청 횟수를 줄일 수 있고 중복된 소스코드도 최소화하며 모듈 개념을 사용한다. <br>

**JPA :**<br>
Java Persistence API를 말한다. <br>
JPA는 ORM 표준 기술로 Hibernate, OpenJPA 등과 같은 구현체가 있고 이에 표준 인터페이스가 JPA이다. <br>
장점으로는
- 객체지향적으로 데이터를 관리할 수 있기 때문에 비즈니스 로직에 집중할 수 있으며, 객체지향 개발이 가능하고 빠른 개발이 가능하게 한다. <br>
단점으로는
- 어렵다. 잘 이해되지 않는 상태에서 사용시에 데이터 손실을 발생시킬 수 있다. <br>

**ORM :**<br>
Object Relation Mapping을 말한다. <br>
RDB 테이블을 객체지향적으로 사용하기 위한 기술이다. <br>
RDB 테이블은 객체지향적 특성인 상속, 다형성, 레퍼런스, 오브젝트가 없고 자바와 같은 언어로 접근이 어렵다. <br>
때문에 ORM이 만들어진 것이고 객체지향적으로 다룰 수 있도록 된 것이다. <br>

**queryDSL :**<br>
JPA의 다양한 조회기능을 사용하기에는 한계가 있다. <br>
querydsl 정적타입을 이용해서 sql과 같은 쿼리를 생성할 수 있도록 해주는 프레임워크이다. <br>
플루언트(Pluent) API를 이용해서 쿼리를 생성할 수 있다. <br>
'타입안전성'과 '일관성'의 원칙을 따른다. <br>
도메인 타입의 프로퍼티를 반영해서 쿼리를 생성한다. <br>
JPAQUERY와 HIBERNATEQUERY는 JPQLQUERY를 상속받는다. <br>
조인을 이용하려면 JPASubQuery를 사용하면 된다. <br>

**홈페이지 취약점 :**<br>
1. Directory Listing
- 서버설정의 취약점으로 발생한다. 브라우징하는 모든 파일이 보여지고 다운로드 및 수정/삭제가 가능하기 때문에 유포, 유출, 조작의 위험이 있다.
- Linux에서는 httpd.conf 파일에서 Indexes 구문을 지우고 저장하면 된다.
<br>
2. File download
- 웹 상에서 파일을 사용자의 컴퓨터로 다운로드 받는 것을 말하는데 방문자에게 허용된 파일 외의 다른 파일을 다운로드 가능하게 함으로써 유출이 될 수 있다.
- "../" 코드 등을 필터링해야한다.
<br>
3. XSS(Cross Site Scripting)
- 사이트를 교차해서 스크립트를 발생시키는 취약점을 말한다.
- 웹에서 스크립트를 언어를 삽입하여 개발자가 의도하지 않은 기능을 작동시키는 것이다.
- 완벽한 방어책이 존재하지 않는다.
- <태그와 >태그를 &lt &gt로 치환하거나 특정태그(화이트태그)만 가능하도록 한다.
<br>
4. File upload
- 게시판 등 업로드 파일에 대한 규제가 없을 경우 발생한다.
- 확장자 화이트리스트 방식으로 방어
- 업로드 폴더 스크립트 실행권한을 제거하는 방어
- 파일이름 난수로 저장하는 방어
- 웹 서버가 아닌 데이터베이스에 저장하는 방어
- 파일업로드 장소를 물리적으로 분리시키는 방어 책이 있다.
<br>
5. SQL Injection
- DB와 연동된 웹애플리케이션 URL이나 입력 폼에 SQL문을 삽입하여 DB로부터 정보를 열람할 수 있는 취약점을 말한다.
- 데이터베이스 접근권한 최소화, 특수문자, 특수구문 필터링을 해서 방어한다.

**DOM :**<br>
The Document Object Model을 말한다. <br>
HTML, XML 문서의 프로그래밍 인터페이스이다. 문서의 구조화된 표현을 제공하며 프로그래밍 언어가 DOM 구조에 접근할 수 있는 방법을 제공한다. <br>

**JSON :**<br>
JavaScript Object Notation을 말한다. <br>
JavaScript 객체 문법을 따르는 문자 기반의 데이터포맷이다. <br>
MIME 타입은 application/json이고 문자열형태로 존재한다. <br>
따라서 엑세스하기 위해서는 Native Json객체로 변환할 필요가 있다. <br>

**MVC 패턴 :**<br>
애플리케이션을 Model - View - Controller 세 가지 역할로 구분한 개발 방법론이다. <br>
1. 사용자가 웹 사이트에 접속한다.
2. Controller는 사용자가 요청한 웹 페이지를 서비스하기 위해 Model을 호출한다.
3. Model은 데이터베이스나 파일과 같은 데이터소스에 제어한 후 결과를 리턴한다.
4. Controller는 Model이 리턴한 결과를 View에 반영한다.
5. 데이터가 반영된 View를 사용자가 본다.

**비동기 :**<br>
Asynchronous, 기다리는 부분없이 즉시 다음 작업을 수행할 수 있도록하는 것을 말한다. <br>
자원을 효율적으로 사용할 수 있지만 복잡하다. <br>

**Process :**<br>
운영체제로부터 자원을 할당받아 실행되고 있는 프로그램의 인스턴스이다. <br>
독립된 메모리 영역을 받는다.(Stack, Heap, Data, Code) <br>
최소 한 개의 메인 스레드를 가지고 있다.

**Thread :**<br>
프로세스 내에서 실행되는 여러 흐름의 단위를 말한다. <br>
스레드는 프로세스 내에서 stack만 할당 받고 Code, Data, Heap영역은 공유한다. <br>
자바에서 Thread는 JVM에 의해 관리된다. <br>

**멀티 프로세싱 :**<br>
하나의 응용프로그램을 여러 개의 프로세스로 구성하여 각 프로세스가 하나의 작업을 처리하도록 하는 것을 말한다. <br>
하나의 프로세스가 죽더라도 다른 영향이 확산되지 않는 반면에 ContextSwitching에 의해 오버헤드가 걸린다. <br>

**멀티스레드 :**<br>
하나의 응용프로그램을 여러 스레드로 구성하여 각 스레드로 하여금 하나의 작업을 처리하도록 하는 것을 말한다. <br>
웹 서버는 대표적인 멀티스레드 응용 프로그램이다. <br>
시스템 자원을 효율적으로 사용가능하고 처리 비용이 감소하는 반면에 자원 공유의 문제가 발생한다. <br>
하나의 스레드에 문제가 발생하면 전체 프로세스가 영향을 받는다. <br>

**콜백(Callback) :**<br>
다시 불러서 실행하는 것을 말한다. 비동기적으로 콜백함수를 호출하는 함수와 비동기적으로 호출되기를 원하는 콜백함수가 필요하다. <br>

**스코프(Scope) :**<br>
함수가 함수 선언식으로 될 때는 현재 스코프의 최상단에 호이스팅 된다. <br>
하지만 함수 표현식으로 선언되면 호이스팅 되지 않는다. <br>

**클로저(Closure) :**<br>
외부 함수의 변수에 접근할 수 있기 때문에 사이드 이펙트를 제어하거나 private 변수를 생성할 때 사용한다. <br>

**NoSQL :**<br>
여러 해석이 있지만 Not Only SQL이 적합하다고 한다. <br>
성능에 특화된 목적을 위해 분산 저장 시스템이라 볼 수 있다. <br>
기존 관계형 DBMS가 갖고 있는 특성 뿐 아니라 다른 특성들도 부가적으로 지원한다는 의미이다. <br>
데이터의 저장 및 검색을 위한 특화된 매커니즘을 제공한다. <br>
특성 :
- 조인 기능이 없고 트랜잭션 ACID 미보장
- 데이터의 스키마/속성들을 다양하게 수용 및 동적 정의 가능(Schema-less)
- 데이터베이스 중단 없는 서비스와 자동복구기능지원
- 다수가 OpenSource로 제공된다.
- key-value, column family, document

🚨**WhatIsThis**🚨 <br>
- ACID(원자성,일관성,고립성,지속성) 좀 더 세부적으로 찾기
- SPRING, Lombok, JPA 정리











