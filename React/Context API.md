# Context API

## 생성

```javascript
const LoginAccount = createContext({isLogin: false});
export default LoginAccount;
```

## 사용

```javascript
function AccountBox () {
    return (;
        <LoginAccount.Consumer>
            {
                value => (
                    <div>
                        {value.isLogin ? '인증 완료' : '인증실패'}
                    </div>
                )
            }
        </LoginAccount.Consumer>
    );
}
```

## 수정

```javascript
<LoginAccount.Provider value={{isLogin: true}}>
    <App/>
</LoginAccount.Provider>
```

