개인 프로젝트 개발을 진행하면서 유니크한 문자열을 획득할 수 있는 방법을 찾게 되었고, 프로젝트에 적용한 해결책으로 java.util.UUID를 사용하며 공부한 내용을 기록한다.  

# UUID  
Universally Unique IDentifier의 약어이며 '범용 고유 식별자'라고 번역된다.  

## 포멧  
UUID는 총 36개의 문자로 구성되어있다.  
> 32개의 문자 + 4개의 하이픈  
> {8자리}-{4자리}-{4자리}-{4자리}-{12자리}  
> 550e8400-e29b-41d4-a716–446655440000  

36개의 문자로 나올 수 있는 경우의 수는 총 <b>'340,282,366,920,938,463,463,374,607,431,768,211,456'<b>개(약 340간 개) 사용 가능하다.

---
## Reference  
[https://medium.com/@jang.wangsu](https://medium.com/@jang.wangsu/ios-swift-uuid%EB%8A%94-%EC%96%B4%EB%96%A4-%EC%9B%90%EB%A6%AC%EB%A1%9C-%EB%A7%8C%EB%93%A4%EC%96%B4%EC%A7%80%EB%8A%94-%EA%B2%83%EC%9D%BC%EA%B9%8C-22ec9ff4e792)
