package com.project.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/loginvalidate")
public class LoginPrincipal extends HttpServlet {

	@Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
	  {
		Scanner sd = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		  int id = Integer.parseInt(req.getParameter("id"));
		  String name = req.getParameter("name");
		  String pass = req.getParameter("password");
		  
		  Query q =em.createQuery("select u from Principal u where u.name=?1 and u.pass=?2");
		  q.setParameter(1, name);
		  q.setParameter(2, pass);
		  
		  List<Principal> principal=q.getResultList();
		  
		  if(principal.size()>0)
		  {
			  resp.setContentType("text/html");
				PrintWriter pw = resp.getWriter() ;
				pw.write("login successfull");
				
			  RequestDispatcher rd= req.getRequestDispatcher("choice.html");
				rd.forward(req,resp);
		  }
		  else
		  {
			  PrintWriter pw =resp.getWriter();
			  pw.write("invalid");
			  RequestDispatcher rd= req.getRequestDispatcher("Login.html");
				rd.include(req,resp);
			  
		  }
	  }	  
}
