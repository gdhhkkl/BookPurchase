package org.example.bookpurchase.security;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.bookpurchase.dto.UserDto;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.bind.support.WebDataBinderFactory;

@Component
@RequiredArgsConstructor
public class UserLoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter){
        boolean isUserLoginAnnotation = methodParameter.getParameterAnnotation(UserLogin.class) != null;
        boolean isUserClass = UserDto.class.equals(methodParameter.getParameterType());
        return isUserLoginAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)throws Exception{
        return httpSession.getAttribute("user");
    }
}
