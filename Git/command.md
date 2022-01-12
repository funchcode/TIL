# command 정리

## commit

### HEAD

#### ~(틸트)

- 상위 차수를 선택할 때 사용.

#### ^(캐럿)

- 동일 차수 내에서 각기 다른 상위 커밋을 선택할 때 사용.

### 수정

#### 가장 최근 commit 수정

```powershell
git commit --amend
```

> amend : (동사) 개정[수정]하다.

이미 remote 저장소에 push한 경우 아래와 같은 편법을 사용할 수 있다.  

```powershell
git push --force [remote branch]
```

### 취소

```powershell
git reset --[options] [commit]
```

- options
  - soft: index 보존(add, staged 상태), 워킹 디렉토리 파일 보존.
  - mixed: index 취소, 워킹 디렉토리 파일 보존. (default)
  - hard: index 취소, 워킹 디렉토리 파일 제거.
- commit
  - HEAD: 현재 브랜치 마지막 커밋 스냅샷(가장 최근 커밋)
