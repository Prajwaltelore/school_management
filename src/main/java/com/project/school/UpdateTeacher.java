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

@WebServlet("/updatet")
public class UpdateTeacher extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Scanner sd = new Scanner(System.in);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String subject = req.getParameter("subject");
		String salary = req.getParameter("salary");
		
		Teacher t = new Teacher();
		 t.setId(Integer.parseInt(id));
	     t.setName(name);
	     t.setSalary(Double.parseDouble(salary));
	     t.setSubject(subject);
	     
	     et.begin();
		em.persist(t);
		et.commit();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter() ;
		pw.write("updated successfully");
		
		RequestDispatcher rd = req.getRequestDispatcher("teachermenu.html") ;
		rd.include(req, resp);
}
}