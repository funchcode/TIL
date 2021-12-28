# Gradle

그루비(Groovy)를 이용한 **빌드 자동화 시스템**이다.  
> 그루비(Groovy): 객체지향언어  

의존성 설정은 [group]:[name]:[version] 순서로 지정한다.  
version은 명시하는 것이 좋다. 이유인 즉슨, 버그 발생 시 어떤 모듈의 어떤 버전에서 발생하는지 빨리 파악하기 위해서다.  

## 버전 명시

```+``` 을 통해 동적 버전을 명시할 수 있다.

* ```junit:junit:5.+```

```latest.integration``` 을 통해 최신 버전을 명시할 수 있다.

* ```junit:junit:latest.integration```

## 캐시

의존성을 통해 다운로드한 라이브러리를 캐시하고 있다.  
같은 버전의 jar 모듈이 자주 변경되는 경우 즉각적으로 갱신되지 않아 불편할 수 있다.  

캐시 옵션을 직접 설정하여 제어가 가능하다.  

* ```{ changing = true }```

## 충돌방지전략

이행적종속으로 인해 발생할 수 있는 충돌을 Gradle 내에서 방지하는 전략을 갖고 있다.  

1. 가장 최신 의존성이 기본으로 사용
2. 충돌이 나는 경우 즉시 실패 발생  
3. 충돌 방지 전략 커스텀 제공

즉시 실패 전략 설정 방법

```xml
{
    resolutionStrategy {
        failOnVersionConflict()
    }
}
```

## Configurations

의존성 구성을 표현하는 구문이다.  

## 이행적종속 제거

특정 모듈을 가져올 때 의존하는 모듈도 가져오게 된다.  
이 때 의존하는 모듈을 가져오지 않도록 제어할 수 있다.  

```@jar``` 키워드 사용 또는 ```ext``` 맵 옵션 사용.  

> 원리: Repository에서 모듈을 가져올 때 jar와 xml파일을 기본적으로 갖고 오는데, 이 때 의존성 기술자 파일인 xml을 제외시켜 의존성을 분석할 수 없도록 하는 것이다.  

Configurations 단위, Dependency 단위에서 설정 가능하다.  

## 의존성 확인 방법

```gradle dependencies``` 명령어를 통해 확인할 수 있다.  
다른 방법으로는 ```task```를 정의해서 확인이 가능하다.  

## 멀티프로젝트 환경

Multi Project 환경에서 다른 프로젝트의 의존성을 상속 설정할 수 있다.  

## 파일 의존성 등록

레파지토리에 모듈을 등록하지 않고 의존성 추가가 가능하다.  
```files()```, ```fileTree()``` 메서드 사용

## Reference

* 🔗 [Gradle Dependencies 정리한 글](https://kwonnam.pe.kr/wiki/gradle/dependencies)