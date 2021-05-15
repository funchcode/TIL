# Spring Security  
Java 애플리케이션에 대한 인증 및 권한 부여에 초점을 맞춘 프레임워크이다.

애플리케이션 보안이란, 인증(당신은 누구인지)과 권한 부여(무엇을 할 수 있는지)라는 두 가지 문제로 볼 수 있다.  
Spring Security는 인증과 권한을 분리하도록 설계된 아키텍처를 갖고 있으며, 두 문제에 대한 전략과 확장 포인트를 갖고 있다.  


# Authentication (인증)
인증을 위한 주요 전략 인터페이스로 `AuthenticationManager`가 있다.  

# Filter  
필터는 체인을 형성하므로 순서가 지정되며, 필터의 순서는 매우 중요하다.  


Java 애플리케이션에 대한 인증 및 권한 부여에 초점을 맞춘 프레임워크이다.  
애플리케이션 보안[인증(Authentication), 인가(Authorization)]에 대한 기능을 제공한다.  
>   접근주체(Principle)  
    : 보호된 대상에 접근하려는 대상.  

>   인증(Authentication)  
    : 해당 사용자가 본인이 맞는지 확인하는 절차.  
  
>   인가(Authorization)  
    : 인증된 사용자가 요청한 자원에 접근 가능한지를 결정하는 절차.  

# Features  
- 인증 및 권한 부여에 대한 포괄적이고 확장 가능한 지원  

# Reference  
[https://spring.io/projects/spring-security](https://spring.io/projects/spring-security)
