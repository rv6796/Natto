package com.natto.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.natto.exception.InvalidInputException;
import com.natto.exception.UserAlreadyExistsException;
import com.natto.model.Role;
import com.natto.model.User;
import com.natto.service.UserService;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String emailAddress = request.getParameter("emailAddress");
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("passWord");
		try {
			if (name == null || name == "") {
				throw new InvalidInputException("User name should not be blank");
			} else if (emailAddress == null || emailAddress == "") {
				throw new InvalidInputException("Email address should not be blank");
			} else if (phoneNumber == null || phoneNumber == "") {
				throw new InvalidInputException("User mobile no should not be blank");
			} else if (password == null || password == "") {
				throw new InvalidInputException("User password should not be blank");
			}
			int roleid = Integer.parseInt(request.getParameter("RoleId"));
			Role role = new Role();
			role.setRoleId(roleid);

			User user = new User();
			user.setUserName(name);
			user.setUserEmailAddress(emailAddress);
			user.setUserPhoneNumber(phoneNumber);
			user.setUserPassWord(password);
			user.setRole(role);

			UserService service = new UserService();
			service.saveUser(user);

		} catch (InvalidInputException e) {
			request.setAttribute("msg", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (UserAlreadyExistsException e) {
			request.setAttribute("msg", "You have already register please login");
			RequestDispatcher dispatcher = dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
