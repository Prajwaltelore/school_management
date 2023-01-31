package com.project.school;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveprinci")
public class InsertData extends HttpServlet {

	@Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
  {
	  int id = Integer.parseInt(req.getParameter("id"));
	  String name = req.getParameter("name");
	  int age = Integer.parseInt(req.getParameter("age"));
	  String mobno = req.getParameter("mobilenumber");
	  String email = req.getParameter("email");
	  String pass = req.getParameter("password");
	  
	  Principal p=new Principal();
	  p.setId(id);
	  p.setName(name);
	  p.setAge(age);
	  p.setMobilenumber(mobno);
	  p.setEmail(email);
	  p.setPassword(pass);
	  
	    Scanner sd = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(p);
		et.commit();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter() ;
	     pw.write("account created");
	     
		RequestDispatcher rd= req.getRequestDispatcher("Login.html");
		rd.forward(req,resp);
		
	  
  }
} 
