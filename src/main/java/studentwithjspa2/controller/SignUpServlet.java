package studentwithjspa2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspa2.dao.StudentDao;
import studentwithjspa2.dto.Student;

public class SignUpServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String address=req.getParameter("address");
	long phone=Long.parseLong(req.getParameter("phone"));
	ServletContext context=getServletContext();
	
	double fees=Double.parseDouble(context.getInitParameter("fees"));
	
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	student.setFees(fees);	
	StudentDao dao=new StudentDao();
	List<Student> students =dao.getAllStudents();
	boolean value=false;
	for(Student dbStudent:students) {
		if(email.equals(dbStudent.getEmail())) {
			value=true;
			break;
		}
	}
	
//	value=true when that given email is already present
//	value=false when that email is not present in the database
	
	if(value) {
//		value=true when that given email is already present
	
		req.setAttribute("message", "Sorry email already exist Please give different email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);
	}else {
		dao.saveStudent(student);
		req.setAttribute("message", "SignedUpSuccessfully Please Login");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
	
	
	
	
	
	
}
}
