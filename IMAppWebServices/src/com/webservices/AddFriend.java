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
public class AddFriend extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/xml");
		PrintWriter out = null;
		
		try {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String newFriend = request.getParameter("newFriend");
		
		if(pwd.trim().length()==0)
		{
		    pwd=null;
		}
		
		if(email.trim().length()==0)
		{
		    email=null;
		}
		if(newFriend.trim().length()==0)
		{
		    newFriend=null;
		}
		
			out = response.getWriter();
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			if ((pwd!=null)&&(email!=null)&&(newFriend!=null)) {	

				if (Utils.existsUserWithThisEmail(email)) {
					
					User u=UserUtil.getUserById(email);
					
					if (u.getPassword().compareTo(pwd)==0) {
						
						if(Utils.existsUserWithThisName(newFriend))
						{
							if(!Utils.areFriends(u, newFriend))
							{
								List<String> amigos=u.getAmigos();
								
								amigos.add(newFriend);
								
								u.setAmigos(amigos);
								
								UserUtil.updateUser(u);
								
								out.write("<correct>Añadido nuevo amigo</correct>");
							}
							else
							{
								out.write("<error>Este usuario ya es tu amigo</error>");
							}
						}
						else
						{
							out.write("<error>Ningun usuario encontrado con el nombre: "+newFriend+"</error>");
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