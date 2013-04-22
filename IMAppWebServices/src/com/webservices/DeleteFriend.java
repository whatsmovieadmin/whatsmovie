package com.webservices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import whatsmovieweb.Utils;

import com.datastore.User;
import com.datastore.UserUtil;

@SuppressWarnings("serial")
public class DeleteFriend extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String friendName = request.getParameter("friendName");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}
		if(friendName.trim().length()==0)
		{
		    friendName=null;
		}
		
			out = response.getWriter();
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			if ((pwd!=null)&&(email!=null)&&(friendName!=null)) {	

				if (Utils.existsUserWithThisEmail(email)) {
					
					User u=UserUtil.getUserById(email);
					
					if (u.getPassword().compareTo(pwd)==0) {
						
							if(Utils.areFriends(u, friendName))
							{
								List<String> amigos=u.getAmigos();
								
								List<String> amigos2=new Vector<String>();
								
								for(int i=0;amigos.size()>i;i++)
								{
									if(amigos.get(i).compareTo(friendName)!=0)
									{
										amigos2.add(amigos.get(i));
									}
								}
								
								u.setAmigos(amigos2);
								
								UserUtil.updateUser(u);
								
								out.write("<correct>Amigo borrado correctamente</correct>");
							}
							else
							{
								out.write("<error>Este usuario no es tu amigo</error>");
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
