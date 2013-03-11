package com.webservices;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import whatsmovieweb.Utils;

import com.datastore.User;
import com.datastore.UserUtil;

@SuppressWarnings("serial")
public class Edit extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String npwd = request.getParameter("npwd");
		String rnpwd = request.getParameter("rnpwd");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}
		if(npwd.trim().length()==0)
		{
		    npwd=null;
		}
		if(rnpwd.trim().length()==0)
		{
		    rnpwd=null;
		}
		
			out = response.getWriter();
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			if ((pwd!=null)&&(email!=null)&&(npwd!=null)&&(rnpwd!=null)) {	

				if (Utils.existsUserWithThisEmail(email)) {
					
					User u=UserUtil.getUserById(email);
					
					if (u.getPassword().compareTo(pwd)==0) {
						
						if(npwd.compareTo(rnpwd)==0)
						{
							UserUtil.updateUserPassword(email, npwd);
							
							out.write("<correct>Los datos son correctos</correct>");
						}
						else
						{
							out.write("<error>Las constraseñas no coinciden.</error>");
						}

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