# Could not build Objective-C module 에러

> 모든 프로젝트가 그러하듯 빌딩과 세팅이 가장 어려운 듯 하다.  
> 야심차게 시작한 Swift 프로젝트에 Firestore 라이브러리를 임포트하다가 며칠을 날렸다.

## 환경

- Xcode version: 13.2.1  
- iOS Deployment Target: 15.2  
- use SwiftUI

## 현상

`Firestore`를 사용하기 위해 Firebase 홈페이지 가이드와 같이 Swift Package에서 `firebase-ios-sdk` 라이브러리를 임포트를 했다.  
그런데 해당 라이브러리에는 `Firestore`를 호출할 수 없어서 `Cocoapods`를 사용하여 `Firebase/Firestore` 종속성을 선언했다.  
라이브러리 종속성 선언 후 프로젝트 빌드 시 `Could not build Object-C 'Firebase' module`, `redefinition of module 'firebase'`, `Double-quoted include in framework header...` 등 다방면에서 에러가 터졌다.  

## 원인

위의 에러들을 웹 검색을 통해 나온 해결 방법을 적용했는데 프로젝트를 빌드하면  
`Could not build Object-C 'Firebase' module`,  
`redefinition of module 'firebase'`  
같은 에러가 또 다시 발생했다.  
`redefinition of module 'firebase'`  
위의 에러를 해결하기 위해 종속성까지 제거했는데도 말이다.  

이전 빌드 캐시가 남아있어서 발생하는 문제였다.

## 해결 방법

1. 실행되고 있는 Xcode를 종료한다.

2. Derived Data를 제거한다.
> [Xcode] 내 탐색: Xcode > Preference > Locations > DerivedData  
> [console] 탐색: `cd ~/Library/Developer/Xcode/DerivedData`

3. 문제가 발생하는 프로젝트에 아래 목록의 파일을 제거한다.
- `[프로젝트].xcworkspace`
- Podfile.lock
- Pods

4. Cocoapods 종속성을 다시 다운로드받는다.  
- `pod install`

5. 생성된 `[프로젝트].xcworkspace`을 연다.

6. 프로젝트 빌드 진행

7. 완료