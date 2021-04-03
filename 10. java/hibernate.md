Spring Boot + Hibernate  

Repostiroy: springboot-starter-jpa  



---

공부하기 위해 사용한 데이터베이스 벤더명은 'MySQL'이다.  

*serverTimezone*  

현재 데이터베이스 타임존 확인 방법.  
> SELECT @@global.time_zone, @@session.time_zone;  
별도의 설정이 없을 경우 'SYSTEM'으로 조회된다.  

Timezone(타임존)에 정의할 수 있는 값과 그 의미.  
>   UTC(Coordinated Universal Time: 세계 협정 시)  
    KST(우리나라 표준 시)

