package studentwithjspa2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspa2.dao.StudentDao;
import studentwithjspa2.dto.Student;
@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	StudentDao dao=new StudentDao();
	List<Student> list=dao.getAllStudents();
	String studentName=null;
	boolean value=false;
	String studentPassword=null;
	
	for(Student student:list) {
		if(email.equals(student.getEmail())) {
			value=true;
			studentPassword=student.getPassword();
			studentName=student.getName();
			break;
		}
	}
	
//	create a cookie
	Cookie cookie=new Cookie("studentwhologgedin",studentName );
	resp.addCookie(cookie);
//	create A HTTPSESSION
	HttpSession httpSession=req.getSession();
	httpSession.setAttribute("studentwhologgedin", studentName);
	
	
	
	if(value) {
//		email is present
		if(password.equals(studentPassword)) {
//			login success
			req.setAttribute("list", list);
			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}else {
//			password is invalid
			req.setAttribute("message", "Invalid Password");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
	}else {
//		email is not present
		req.setAttribute("message", "InvalidEmail");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
}
}
