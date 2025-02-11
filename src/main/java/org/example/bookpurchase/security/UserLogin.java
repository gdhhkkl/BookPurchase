package org.example.bookpurchase.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 세션 관련 중복코드 제거하기위해 UserLogin 어노테이션 생성
 **/
@Target(ElementType.PARAMETER)//파라미터로 선언된 객체만 사용
@Retention(RetentionPolicy.RUNTIME)//런타인까지 남았는다.
public @interface UserLogin {
}
