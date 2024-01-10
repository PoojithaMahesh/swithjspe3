package studentwithjspa2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspa2.dao.StudentDao;
import studentwithjspa2.dto.Student;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	StudentDao studentDao=new StudentDao();
	Student dbStudent=studentDao.findStudent(id);
	
	HttpSession session=req.getSession();
	String name=(String)session.getAttribute("studentwhologgedin");
	
	if(name!=null) {
//		he is a vlid student
		req.setAttribute("student", dbStudent);
		RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
		dispatcher.forward(req, resp);
	}else {
//		name==null
//		when they have copied the url
		req.setAttribute("message", "Hey scammer please just go and login first");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
	
	
	}
}
