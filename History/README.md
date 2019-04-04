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

AWS와 도커를 함께 사용하려면 ECS를 사용해야하는데 내가 하는 프로젝트의 규모가 ECS를 모두 누려보기에는 적합하지 않다 판단하여 도커를 사용하지 않는 쪽으로 방향을 잡음.<br>

Elastic IP를 EC2에 적용하지 않아 요금이 청구되었음. 프리티어 만료된줄 알았음.<br>

> <h2>@리눅스</h2><br>
### **리눅스 완벽 입문서 대한 학습**<br>
리눅스 명령어는 쉘에 내장되어 있다.(=쉘 함수이다.) <br>
```man``` 명령어에 섹션번호가 존재한다.(=목차) <br>
세미콜론으로 각 명령어를 구분해서 한 줄에 표현가능하다. <br>

---

