## 주말을 이용하여 Github Blog에 꼭 정리하자.
> <h3>규칙</h3>
> 1. **Linux** 관련 대략적인 개념 정리를 해 놓는 창고이다.<br/>
> 2. <span style="color:red"><b><u>밑줄</u></b></span> : 좀 더 학습할 필요가 있는 주제(블로그에 정리)<br/>
---

> <span><h3>Ubuntu</h3></span>
sudo apt-get install apache2<br/>
apt-get update<br/>
계정추가 : sudo adduser<br/>
sudo권한부여 : sudo visudo, 사용자명 ALL=(ALL:ALL) ALL, 파일 덮어쓰기<br/>
인코딩 관련 : sudo locale-gen ko_KR.EUC-KR ko_KR.UTF-8<br/>
해당 계정 인코딩 설정 : sudo nano .bash_profile, LANG & LANGUAGE<br/>
반영 : source .bash_profile	<br/>
현재 운영체제 비트 : getconf LONG_BIT<br/>
etc/profile : 모든 사용자<br/>
root/ : root<br/>
사용자 : /home/사용자<br/>
java -cp : ClassPath로 참조하려는 클래스가 위치한 경로를 설정하는 옵션이다.<br/>
kill -9 : 사용중인 프로세스 제거<br/>
ufw : 방화벽 설정 명령어<br/>
su : 계정변경<br/>

---
## Linux Redirection
입출력(I/O) 방향지정을 말한다.<br/>
- stdout(표준출력) / stderr(표준오류)를 설정할 수 있다.

**결과 화면 출력 대신 파일에 출력하는 키워드**
">" Redirection 연산자를 사용.<br/>
- 특징 : 파일을 덮어쓴다.
- 해결책 : **">>"**사용하여 이어서 쓴다.
Redirection 순서는 매우 중요하다.<br/>

> 표준오류 출력은 어떻게 제어하는가 ?<br/>
> **2>** 표준오류 출력을 설정할 수 있다.

> 출력을 제거하고 싶다면 ?<br/>
> **/dev/null(비트 버킷)**으로 보낸다.

**명령어 연결하여 사용할 수 있는 키워드**
"|" PipeLine 연산자 사용.<br/>
- 함께 사용하면 유용한 명령어.
    - **sort** : 정렬
    - **uniq** : 중복제거(-d:중복내용만 보기)
    - **grep** : 일치하는 라인 출력
    - **wc** : 라인, 단어갯수, 파일크기 출력
    - **head** : 파일의 앞 부분
    - **tail** : 파일의 뒷 부분

🚨 좀 더 공부가 필요한 것들 : (1)쉘 파일디스크립터, (2)cat명령어, (3)tee명령어 🚨