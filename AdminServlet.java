package kadai4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kadai4.beans.User;
import kadai4.service.AdminService;

@WebServlet(urlPatterns = { "/admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginuser = (User) request.getSession().getAttribute("loginUser");

		List<User> users = new AdminService().getUser();

		request.setAttribute("users", users);
		request.setAttribute("loginUser", loginuser);

		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		int editid = Integer.parseInt(request.getParameter("stop"));

		List<User> users = new AdminService().editAvailableUser(editid);

		request.setAttribute("users", users);

		response.sendRedirect("/Kadai4/admin");
	}
}