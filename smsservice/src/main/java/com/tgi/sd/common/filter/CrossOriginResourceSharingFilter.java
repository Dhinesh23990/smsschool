package com.tgi.sd.common.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CrossOriginResourceSharingFilter implements Filter {

  public CrossOriginResourceSharingFilter() {
  }

  public void init(FilterConfig fConfig) throws ServletException {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
	  try {
	    Properties prop = new Properties();
	    prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/props/cors.properties"));
	    if (getValidationRequired(prop.getProperty("request.validation.set"))) {
	      if (!Arrays.asList((prop.getProperty("request.header.origin").split(","))).contains(
	          ((HttpServletRequest) request).getHeader("Origin"))) {
	    	  
	        ((HttpServletResponse) response).sendError(1,
	            "corpserror");
	
	      }
	    }
	    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin",
	        prop.getProperty("response.access.control.allow.origin"));
	
	    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials",
	        prop.getProperty("response.access.control.allow.credentials"));
	
	    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods",
	        prop.getProperty("response.access.control.allow.methods"));
	
	    ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers",
	        prop.getProperty("response.access.control.allow.headers"));
	
	    chain.doFilter(request, response);
    
	  }
	  catch (Exception ee) {
		  System.out.println("here-exception");
		  ee.printStackTrace();
	  }
  }

  private Boolean getValidationRequired(String isRequired) {

    if (Integer.valueOf(isRequired) != 1) {
      return false;
    }
    return true;

  }
}
