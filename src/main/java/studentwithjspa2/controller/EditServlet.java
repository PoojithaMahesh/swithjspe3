package studentwithjspa2.controller;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspa2.dao.StudentDao;
import studentwithjspa2.dto.Student;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String address=req.getParameter("address");
		int id=Integer.parseInt(req.getParameter("id"));
		long phone=Long.parseLong(req.getParameter("phone"));
		ServletContext context=getServletContext();
		double fees=Double.parseDouble(context.getInitParameter("fees"));
		
		Student student=new Student();
		student.setAddress(address);
		student.setEmail(email);
		student.setFees(fees);
		student.setId(id);
		student.setName(name);
		student.setPassword(password);
		student.setPhone(phone);
		
		StudentDao dao=new StudentDao();
		Student dbStudent=dao.findStudent(id);
		List<Student> students=dao.getAllStudents();
		boolean value=true;
		
		for(Student student2:students) {
			
			if(student2.getEmail().equals(email)) {
//				it is the data to be updated 
				if(student.getId()==student2.getId()) {
					break;
				}else {
					value=false;
				}
			}
		}
		
		
		Cookie[] cookies=req.getCookies();
		String nameofthestudentwhochangedthedetails=null;
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("studentwhologgedin")) {
				nameofthestudentwhochangedthedetails=cookie.getValue();
				break;
			}
		}
		if(value) {
			req.setAttribute("name", nameofthestudentwhochangedthedetails);
			dao.updateStudent(student);
			req.setAttribute("list", dao.getAllStudents());
			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}else {
			req.setAttribute("message", "Sorry email already present");
			req.setAttribute("student", dbStudent);
			RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
			dispatcher.forward(req, resp);
		}
		
}
}