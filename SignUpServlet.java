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

		User loginuser = (User) request.getSession().getAttribute("loginUser");

		request.setAttribute("loginUser", loginuser);

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
			user.setLoginId(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranchId(request.getParameter("branch_id"));
			user.setDepartmentId(request.getParameter("department_id"));

			new UserService().register(user);

			response.sendRedirect("/Kadai4/admin");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ユーザーIDを入力してください");
		} else if (StringUtils.length(login_id) < 6) {
			messages.add("ユーザーIDは6文字以上です");
		} else if (StringUtils.length(login_id) > 20) {
			messages.add("ユーザーIDは20文字以下です");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		} else if (StringUtils.length(password) < 6) {
			messages.add("パスワードは6文字以上です");
		} else if (StringUtils.length(password) > 255) {
			messages.add("パスワードは255文字以下です");
		}

		if (StringUtils.isEmpty(name)  == true ) {
			messages.add("名前を入力してください");
		} else if (StringUtils.length(name) > 10) {
			messages.add("名前は10文字以下です");
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}