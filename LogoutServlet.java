package kadai4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/logout.jsp" })
public class LogoutServlet extends HttpServlet {

	@Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);
		session.invalidate();

		response.sendRedirect("login");
  }
}