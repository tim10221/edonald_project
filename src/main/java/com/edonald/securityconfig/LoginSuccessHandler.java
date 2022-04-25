package com.edonald.securityconfig;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String auth = authentication.getAuthorities().toString();
		System.out.println("auth  -- " + auth);
		String url="";
		if(auth.equals( "[ROLE_MEMBER]")) {
			url="/ed/deliverHome";
		}else if(auth.equals("[ROLE_STOREADMIN]")) {
			url="/storeadmin/";
		}else if(auth.equals("[ROLE_HEADADMIN]")) {
			url = "/headadmin/";
	}
		response.sendRedirect(url);
		
	}

}
