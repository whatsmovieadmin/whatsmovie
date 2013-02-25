package com.webservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastore.User;
import com.datastore.UserUtil;

import whatsmovieweb.Utils;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}
		
			out = response.getWriter();
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			if ((pwd!=null)&&(email!=null)) {	

				if (Utils.existsUserWithThisEmail(email)) {
					
					User u=UserUtil.getUserById(email);
					
					if (u.getPassword().compareTo(pwd)==0) {
							
							out.write("<correct>Los datos son correctos</correct>");

					} else {
						out.write("<error>Contraseña incorrecta</error>");
					}
				} else {
					out.write("<error>Ningun usuario encontrado con el email: "+email+"</error>");
				}
			} else {
				out.write("<error>Parametros incorrectos</error>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}