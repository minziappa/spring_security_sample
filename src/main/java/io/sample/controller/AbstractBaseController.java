package io.sample.controller;

import io.sample.bean.ExtendUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractBaseController {

	final Locale LOCALE = Locale.JAPAN;
	final Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);

	public void handleLogin(HttpSession session) throws Exception {

		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(obj instanceof ExtendUser) {
			ExtendUser extendUser = (ExtendUser) obj;
			session.setAttribute("admin", extendUser.getUsername());
			session.setMaxInactiveInterval(100*60);
		}

	}

	public void handleValidator(List<ObjectError> errorList) throws IOException {

        for(int i=0; i< errorList.size();i++) {

            ObjectError error = errorList.get(i);
            logger.error("DefaultMessage = " + error.getDefaultMessage());
            logger.error("ObjectName = " + error.getObjectName());

            Object[] codes = error.getCodes();
            for(int m=0; m < codes.length; m++) {
            	String str = (String) codes[m];
            	logger.error("codes = " + str);
            }
        }

	}

	public boolean handleIphone(HttpSession session) throws Exception {

		Boolean blnIphone = (Boolean)session.getAttribute("blnIphone");

		return blnIphone;
	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e, HttpServletResponse response, 
			HttpServletRequest request) throws IOException {

		Enumeration<?> en = (Enumeration<?>) request.getParameterNames();

		int i = 0;
        while(en.hasMoreElements()) {
        	i++;
            String name = (String)en.nextElement();
            String value = request.getParameter(name);
            logger.error("Exception:" + i + " parameter is name=" + name + ", value=" + value);
        }
        
        logger.error("Exception's trace:", e);

		response.setContentLength(0);
		response.setStatus(500);
	}

	@ExceptionHandler(SQLException.class)
	public void handleException(SQLException e, HttpServletResponse response, 
			HttpServletRequest request) throws IOException {

		Enumeration<?> en = (Enumeration<?>) request.getParameterNames();

		int i = 0;
        while(en.hasMoreElements()) {
        	i++;
            String name = (String)en.nextElement();
            String value = request.getParameter(name);
            logger.error("SQLException:" + i + " parameter is name=" + name + ", value=" + value);
        }

        logger.error("SQLException's trace:", e);

		response.setContentLength(0);
		response.setStatus(500);
	}

}