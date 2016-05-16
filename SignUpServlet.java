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

import org.apache.commons.lang.StringUtils;

import kadai4.beans.User;
import kadai4.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if(isValid(request, messages) == true) {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLogin_Id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));

			System.out.println("user: " + user);

			new UserService().register(user);

			response.sendRedirect("/home");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		System.out.println("login_id: " + login_id + ", password: " + password);

		if(StringUtils.isEmpty(login_id) == true) {
			messages.add("ユーザーIDを入力してください");
		}
		if(StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		// TODOアカウントがすでに利用されていないか、メールアドレスがすでに登録されていないかなどの確認も必要

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}