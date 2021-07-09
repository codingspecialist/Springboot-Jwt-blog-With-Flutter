package com.cos.authjwt.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cos.authjwt.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver{
	private final HttpSession session;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isPrincipalAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isPrincipalClass = User.class.equals(parameter.getParameterType());
		
		return isPrincipalAnnotation && isPrincipalClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		User principal = (User) session.getAttribute("principal");
		System.out.println("resolveArgument :  이거 걸림"+principal);
		return principal;
	}
}
