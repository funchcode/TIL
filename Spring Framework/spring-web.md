# 001. spring-web.md

## @ResponseStatus

`@ResponseStatus`는 Controller, Exception에 사용하며, Response 시에 Http Status 정보를 설정해준다.

## @ResponseHandler

`@ResponseHandler`는 Controller 단위로 사용하며 예외를 특정하여 핸들링 할 수 있도록 해준다.

> `@ResponseStatus`와 함께 사용하여 심플하게 코드를 작성할 수 있다.

```java
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("[handleMethodArgumentNotValidException()] error message -> {}", errMsg);
        return errMsg;
    }
```