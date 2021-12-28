## "목차" <br>
1. [root](#root-)
2. [bin](#bin-)
3. [dev](#dev-)
4. [etc](#etc-)
5. [home](#home-)
6. [lib](#lib-)
7. [media](#media-)
8. [mnt](#mnt-)
9. [proc](#proc-)
10. [root](#root-)
11. [tmp](#tmp-)
12. [usr](#usr-)
13. [var](#var-)

---
## root <br>

👉🏽 리눅스 시스템의 디렉토리 구조는 역 트리 구조를 하고 있다. <br>
<br>
리눅스의 명령어의 종류와 성격, 사용권한 등에 따라 각각의 디렉토리들로 구분된다. <br>
**리눅스 배포판**들은 리눅스 파일시스템 표준인 FSSTND(LINUX FILE System Standard)라는 표준을 준수하므로 대부분의 리눅스 배포판들은 그 기본 골격이 같다. <br>
> 🤔 리눅스 배포판 : <br> 레드햇, 우분투, 수세, 데비안, 페도라 등을 말한다.

root란 최상위 디렉토리 "/"를 말한다. <br>
모든 디렉토리의 최상의 디렉토리이며 모든 디렉토리의 시작점을 의미한다. <br>
경로를 표기할 때 절대 경로로 표기하기 위해서는 이 디렉토리("/")로부터 시작해야 한다. <br>

```bash
/
/bin
/sbin
/home
/tmp
/lib
/usr
/var
/etc
/dev
/boot
/mnt
/media
/proc
/lost+found
```
위의 디렉토리를 간략하게 또는 자세히 알아보도록 한다. <br>

---
## bin <br>

👉🏽 기본적인 명령어가 저장되는 디렉토리이다. <br>
👉🏽 **root 사용자와 일반 사용자**가 함께 사용할 수 있는 명령어 디렉토리이다. <br>
<br>

```bash
bash    cat     cp      df      dir     echo
grep    gzip    ip      kill    less    ln
login   ls      mkdir   more    mv      nano
netstat ping    ps      pwd     rm      rmdir
sh      sleep   tar     touch   umonut  ...
```
"/bin" 디렉토리에 있는 파일들 중 익숙한 파일들만 간략히 나열했다. <br>
file 명령어로 파일들을 확인했을 때 `ELF 64-bit LSB shared object - - -` 를 확인할 수 있었다. <br>
> 🤔 LSB : <br> Linux Standard Base 리눅스 기본 규격이라는 의미

---
## sbin <br>

👉🏽 주로 시스템 관리자들이 사용하는 시스템관리자용 명령어를 저장하고 있는 디렉토리이다. <br>
<br>

```bash
fdisk       ifconfig        init        route
halt        ethtool         e2fsck
```

"/sbin" 디렉토리에 있는 파일들 중 역시 익숙한 파일들만 간략히 나열했다. <br>
역시 `ELF 64-bit LSB shared object - - -`를 확인할 수 있었다. <br>

---
## boot <br>

👉🏽 리눅스 부트로더(boot loader)가 있는 디렉토리이다. <br>
👉🏽 GRUB와 같은 부트로더에 관한 파일(grub.conf 등)이 이 디렉토리에 존재한다. <br>

---
## dev <br>

👉🏽 시스템 디바이스(Device)파일을 저장하고 있는 디렉토리이다. <br>
<br>

"/dev" 디렉토리에 있는 파일을 `ls -al`을 했을 때 `crw-rw-rw-` 와 `brw-rw-rw`의 파일들이 있는 것을 볼 수 있다. <br>
> 🤔 파일 종류 : <br> d(Directory File), b(Block Device File), c(Character Device File), s(Symbolic Link File)

---
## etc <br>

👉🏽 시스템의 거의 모든 설정파일이 존재하는 디렉토리이다. <br>
<br>

▶︎ "/etc/ssh/" <br>
SSH 서비스, sshd 데몬에서 사용하는 각종 설정파일들이 존재하는 디렉토리이다. <br>

---
## home <br>

👉🏽 사용자의 홈 디렉토리 <br>

---
## lib <br>

👉🏽 커널이 필요로하는 커널모듈파일들과 프로그램(C, C++ 등)에 필요한 각종 라이브러리 파일들이 존재. <br>

---
## media <br>

👉🏽 DVD, CD-ROM, USB와 같은 탈부착이 가능한 장치들의 마운트포인트로 사용되는 디렉토리이다. <br>

---
## mnt <br>

👉🏽 /media 디렉토리와 비슷한 용도로 탈부착이 가능한 장치들의 마운트포인트로 사용. <br>

---
## proc <br>

👉🏽 "가상파일시스템"이라고 하는 곳으로 현재 메모리에 존재하는 모든 작업들이 파일형태로 존재하는 곳이다. <br>
<br>
디스크상에 존재하는 것이 아니라 메모리상에 존재하기 때문에 가상파일시스템이라고 부른다. <br>
실제 운용 상태를 정확하게 파악할 수 있는 중요한 정보를 제공하는 곳이다! <br>
여기에 존재하는 파일들 가운데 현재 실행중인 커널의 옵션 값을 즉시 변경할 수 있는 파라미터들이 있기 때문에 시스템 운용에 있어 매우 중요한 의미를 가진다. <br>

---
## root <br>

👉🏽 시스템 최고관리자인 root 사용자의 홈 디렉토리이다. <br>

---
## tmp <br>

👉🏽 시스템을 사용하는 모든 사용자들의 공용 디렉토리이다. <br>
<br>
Mysql에서 사용하는 mysql.sock등과 같은 소켓파일, 또는 아파치에서 사용하는 세션파일 등이 생성되기도 한다. <br>
웹 해킹에 유의해야한다. <br>

---
## usr <br>

👉🏽 시스템이 아닌 일반사용자들이 주로 사용하는 디렉토리이다. <br>
<br>
▶︎ "/usr/bin/" <br>
일반사용자들의 명령어들이 위치하고 있는 디렉토리이다. <br>
<br>
▶︎ "/usr/include/" <br>
C프로그램에 필요한 헤드파일(*.h)디렉토리이다. <br>
<br>
▶︎ "/usr/lib/" <br>
/lib에 들어가지 않은 라이브러리 디렉토리이다. <br>
<br>
▶︎ "/usr/sbin/" <br>
/bin에 제외된 명령어와 네트워크 관련 명령어가 들어있는 디렉토리이다. <br>
<br>
▶︎ "/usr/src/" <br>
프로그램 소스(주로 커널소스)가 저장되는 디렉토리이다. <br>
<br>
▶︎ "/usr/local/" <br>
MySQL, Apache, PHP 등과 같은 어플리케이션들을 소스로 컴파일설치할 때 사용되는 장소이다. <br>
<br>
▶︎ "/usr/share/man/" <br>
명령어들의 도움말을 주는 메뉴얼 페이지 디렉토리이다. <br>

---
## var <br>

👉🏽 시스템 운용 중에 생성되었다가 삭제되는 데이터를 일시적으로 저장하기 위한 디렉토리이다. <br>
<br>
▶︎ "/var/tmp/" <br>
/tmp와 같은 공용디렉토리다. 리눅스 시스템에서는 공용 디렉토리로는 "/tmp"와 "/var/tmp" 둘뿐이다. <br>
<br>
▶︎ "/var/log/" <br>
시스템로그파일이 저장되는 디렉토리이다. <br>

---
### ⭐️ 참조자료 (감사합니다:)) ⭐️ <br>
[흉내쟁이님의 리눅스 디렉토리 구조](https://webdir.tistory.com/101) <br>
