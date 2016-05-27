package kadai4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kadai4.beans.User;
import kadai4.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();
		if (request.getParameter("logout") == "true") {
			session.invalidate();
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(loginid, password);

		if (request.getParameter("logout") == "1") {
			session.invalidate();
			response.sendRedirect("login");
		}

		if (user != null) {
			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("・ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			session.setAttribute("loginid", loginid);
			session.setAttribute("password", password);

			response.sendRedirect("login");
		}
	}
}