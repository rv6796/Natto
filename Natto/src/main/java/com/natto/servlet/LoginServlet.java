package com.natto.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.natto.dao.UserDao;
import com.natto.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("passWord");
		UserDao dao= new UserDao();
		User user =dao.getUserPojoByEmailAddress(emailAddress);
		
		if(user!=null && password.equals(user.getUserPassWord()))
		{
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/newsfeed.jsp");
		requestDispatcher.forward(request, response);
		
	}else
	{
		System.out.println("Login Fail");
}
}
}