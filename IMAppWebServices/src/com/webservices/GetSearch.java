package com.webservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import whatsmovieweb.Utils;

import com.datastore.User;
import com.datastore.UserUtil;

@SuppressWarnings("serial")
public class GetSearch extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String textSearch = request.getParameter("textSeach");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}

		if(textSearch.trim().length()==0)
		{
		    textSearch=null;
		}
		
		out = response.getWriter();
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

		if ((pwd!=null)&&(email!=null)) {	

			if (Utils.existsUserWithThisEmail(email)) {
				
				User u=UserUtil.getUserById(email);
				
				if (u.getPassword().compareTo(pwd)==0) {
					
					if(textSearch!=null)
					{
						List<String> allUsers=UserUtil.listNameUsers();
						
						String usuarios = "";
						
						int aux=0;
						
						for(int i=0; allUsers.size()>i;i++)
						{
							if((allUsers.get(i).toLowerCase().compareTo(textSearch.toLowerCase()))==0)
							{
								aux=aux+1;
								usuarios+="<user>"+allUsers.get(i)+"</user>";
							}
						}
						
						String aux2=aux+"";
						out.write("<correct>%"+aux2+"/%"+usuarios+"</correct>");
					}
					else
					{
						out.write("<error>Introducca texto para buscar</error>");
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