package com.tgi.sd.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class TokenSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, AuthenticationException e)
			throws IOException, ServletException {
		httpServletResponse.setContentType("application/json");
		httpServletResponse.getWriter().append(
				"{\"statusFlag\":\"Error\",\"ErrorMessage\":\""
						+ e.getMessage() + "\"}");

	}
}