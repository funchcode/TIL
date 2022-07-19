# HISTORY

## Branch Commit 포인터 이동 적용 사례

> 회사에서 **브랜치** 관리를 [ `1.0.x` , `1.1.x`, `2.0.x` … ]의 형태로 관리하고 있음.
예로 `1.0.x` 에서 작업 후 릴리즈하는 경우에는 **태그**로 [ `1.0.1`, `1.0.2` … ] 관리하도록 구성했음.
문제 상황으로 브랜치 `1.0.x` 에서 `1.1.x` 로 전환한 시점에 Commit의 내용이 `1.0.x` 가 아닌 `1.1.x`에 해당하는 문제가 있어서 `1.0.x`의 Commit 포인터를 직전 Commit 위치로 이동해야했음
> 

### ► 적용 구체적인 순서

1. 소스 클론
    1. `git clone [Repository Address]`
2. 브랜치(1.0.x) 체크아웃
    1. `git checkout -t origin 1.0.x`
3. 이동할 Commit 위치 확인
    1. `git log`
4. Commit 포인터 이동
    1. `git reset —hard [commit address]`
5. Remote Repository 푸시
    1. `git push -f origin 1.0.x`

```text
// 작업 전
1. 9ffc1aba14a5cea6740ce3be5411950cd693615e    
2. 15f420b9e8f056f93e55edf562617eb667c2064b    // branch(1.1.x), tag(1.1.1)
3. 70c4767247ea6aafafe17e2b6030a248fb0c91f0    // branch(1.0.x)
4. c059931613136690c7e21206d560d2b831694dc9    // tag(1.0.1)
5. 8290e1658d67ba811f20b47d2ea3cfee377b6206

// 작업 후
1. 9ffc1aba14a5cea6740ce3be5411950cd693615e    
2. 15f420b9e8f056f93e55edf562617eb667c2064b    // branch(1.1.x), tag(1.1.1)
3. 70c4767247ea6aafafe17e2b6030a248fb0c91f0    
4. c059931613136690c7e21206d560d2b831694dc9    // branch(1.0.x), tag(1.0.1)
5. 8290e1658d67ba811f20b47d2ea3cfee377b6206
```