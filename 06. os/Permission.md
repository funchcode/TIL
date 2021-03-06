## "목차"

1. [사용자 및 그룹 설정](#사용자-및-그룹-설정)
2. [파일 권한 설정](#파일-권한-설정)
3. []()

---

## 사용자 및 그룹 설정

👉🏽 사용자와 그룹 설정 관련 명령어들을 알고 익히면 된다.

`useradd [옵션] [사용자명]`의 명령어를 통해 사용자 계정을 추가할 수 있다.

> **옵션 값**

추가된 계정은 `/etc/passwd`에서 확인 가능한데 새로 추가한 계정은 맨 하단에 위치한 것을 알 수 있다.
해당 계정의 패스워드는 `/etc/shadow`(CentOS)에서 확인가능하나 모든 패스워드는 암호화되어 저장되어있다.

```console

```
새로운 사용자를 추가했을 때 나타나는 `/etc/passwd` 파일 내용이다.

---

## 파일 권한 설정

👉🏽 `ls` 명령어 시 출력되는 텍스트 해석과 읽기+쓰기+실행 권한이 핵심이다.
