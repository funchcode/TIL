# More than one fragment with the name [spring_web] was found. 에러

멀티 모듈 Spring Boot Gradle 프로젝트 war 빌드 후 Tomcat 9.0에 배포하여 실행하는 경우 아래 예외가 발생 함.

```console
 Caused by: java.lang.IllegalArgumentException: More than one fragment with the name [spring_web] was found. This is not legal with relative ordering. See section 8.2.2 2c of the Servlet specification for details. Consider using absolute ordering.
                at org.apache.tomcat.util.descriptor.web.WebXml.orderWebFragments(WebXml.java:2203)
                at org.apache.tomcat.util.descriptor.web.WebXml.orderWebFragments(WebXml.java:2162)
                at org.apache.catalina.startup.ContextConfig.webConfig(ContextConfig.java:1083)
                at org.apache.catalina.startup.ContextConfig.configureStart(ContextConfig.java:779)
                at org.apache.catalina.startup.ContextConfig.lifecycleEvent(ContextConfig.java:299)
                at org.apache.catalina.util.LifecycleBase.fireLifecycleEvent(LifecycleBase.java:123)
                at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5066)
                at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183)
                ... 10 more
```

`[spring-web]`의 요소가 여러 멤버에서 발견되어 발생하는 문제로 상대 경로가 아닌 절대 경로로 명시해야한다. 
> 'web.xml'이 내부에 있는 프로젝트인 경우 `<absolute-ordering />` 태그를 추가하면 된다고 한다.  
> [직접 확인하진 못함]

현 프로젝트에서 예외가 발생한 이유로 멀티 모듈 중 특정 하나의 모듈을 빌드 시 내부에서 의존하고 있는 의존 라이브러리를 모두 포함하도록 스크립트를 구성했기 때문에 다른 모듈이 맺고 있는 의존 라이브러리와 중복되어 이와 같은 예외가 발생한 것이다.