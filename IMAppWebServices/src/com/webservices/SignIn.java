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
public class SignIn extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String rpwd = request.getParameter("rpwd");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}
		if(rpwd.trim().length()==0)
		{
		    rpwd=null;
		}
		if(name.trim().length()==0)
		{
		    name=null;
		}
		
			out = response.getWriter();
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			if ((pwd!=null)&&(email!=null)&&(rpwd!=null)&&(name!=null)) {
				
				if (!Utils.existsUserWithThisEmail(email)) {
					
					if (!Utils.existsUserWithThisName(name)) {
						
						if (pwd.compareTo(rpwd)==0) {
							
								User user=new User(email, pwd, name);
								
								UserUtil.addUser(user);
								
								out.write("<correct>El usuario ha sido correctamente registrado</correct>");
	
						} else {
							out.write("<error>Las constraseñas no coinciden.</error>");
						}
					} else {
						out.write("<error>Ya existe un usuario con el nombre: "+name+"</error>");
					}
				} else {
					out.write("<error>Ya existe un usuario con el email: "+email+"</error>");
				}
			} else {
				out.write("<error>Parametros incorrectos.</error>");
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