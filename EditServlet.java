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
import kadai4.service.EditService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean flag = false;
	private int editid;
	private int inputid;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginuser = (User) request.getSession().getAttribute("loginUser");
		request.setAttribute("loginUser", loginuser);

		request.getRequestDispatcher("edit.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		if ( flag == false) {
			inputid = Integer.parseInt(request.getParameter("editid"));
			editid = inputid;
			flag = true;
		}

		String loginid = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branchid = request.getParameter("branch_id");
		String departmentid = request.getParameter("department_id");

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if(isValid(request, messages) == true) {
			User edituser = new User();
			edituser.setLoginId(request.getParameter("login_id"));
			edituser.setPassword(request.getParameter("password"));
			edituser.setName(request.getParameter("name"));
			edituser.setDepartmentId(request.getParameter("branch_id"));
			edituser.setDepartmentId(request.getParameter("department_id"));

			new EditService().editer(editid, loginid, password, name, branchid, departmentid, edituser);

			response.sendRedirect("/Kadai4/admin");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("edit");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginid = request.getParameter("login_id");
		String checkpassword = request.getParameter("checkpassword");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (flag == true) {
			if (StringUtils.isEmpty(loginid) == true) {
				messages.add("ユーザーIDを入力してください");
			} else if (StringUtils.length(loginid) < 6) {
				messages.add("ユーザーIDは6文字以上です");
			} else if (StringUtils.length(loginid) > 20) {
				messages.add("ユーザーIDは20文字以下です");
			}
		}

		if (StringUtils.length(password) != 0) {
			if (StringUtils.length(password) < 6) {
				messages.add("パスワードは6文字以上です");
			} else if (StringUtils.length(password) > 255) {
				messages.add("パスワードは255文字以下です");
			}
			if (password == checkpassword) {
				messages.add("パスワードが一致しません");
			}
		}

		if (flag == true) {
			if (StringUtils.isEmpty(name)  == true ) {
				messages.add("名前を入力してください");
			} else if (StringUtils.length(name) > 10) {
				messages.add("名前は10文字以下です");
			}
		}

		if (request.getParameter("branch_id") == "0") {
			messages.add("支店を選んでください");
		}
		if (request.getParameter("department_id") == "0") {
			messages.add("部署・役職を選んでください");
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}