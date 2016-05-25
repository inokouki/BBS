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

import kadai4.beans.Comment;
import kadai4.beans.SearchMessage;
import kadai4.beans.User;
import kadai4.beans.UserComment;
import kadai4.beans.UserMessage;
import kadai4.service.CommentService;
import kadai4.service.MessageSearchService;
import kadai4.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String searchCategory=null, searchTimeBefore = null, searchTimeAfter = null, deletestr = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		searchCategory = request.getParameter("searchCategory");
		searchTimeBefore = request.getParameter("searchTimeBefore");
		searchTimeAfter = request.getParameter("searchTimeAfter");
		deletestr = request.getParameter("delete");

		User user = (User) request.getSession().getAttribute("loginUser");

		List<UserMessage> contributions = new MessageService().getMessage();
		List<UserComment> comments = new CommentService().getComment();

		if (searchCategory != null || searchTimeBefore != null || searchTimeAfter != null) {
			List<SearchMessage> searches = new MessageSearchService().getMessage(searchCategory,
					searchTimeBefore, searchTimeAfter);
			request.setAttribute("searches", searches);
		}

		request.setAttribute("contributions", contributions);
		request.setAttribute("comments", comments);
		request.setAttribute("user", user);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		List<String> comments = new ArrayList<String>();

		if (deletestr == null) {
			if (isValid(request, comments) == true) {
				User user = (User)session.getAttribute("loginUser");

				Comment comment = new Comment();

				comment.setComment(request.getParameter("comment"));
				comment.setContributionId(request.getParameter("contribution_id"));
				comment.setUserId(user.getLoginId());

				new CommentService().register(comment);
			} else {
				session.setAttribute("errorMessages", comments);
			}
		} else {
			int deleteid = Integer.parseInt(deletestr);
			CommentService.deleteUser(deleteid);
		}

		response.sendRedirect("./");
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String comment = request.getParameter("comment");

		System.out.println("comment1:" + deletestr);

		int error = 0;

		if (searchCategory == null && searchTimeBefore == null && searchTimeAfter == null) {
			if (StringUtils.isEmpty(comment) == true && deletestr == null) {
				comments.add("コメント文を入力してください");
			} else if (comment.length() > 1000) {
				comments.add("コメント文は500文字以下です");
			}
		}

		if (searchCategory != null || (searchTimeBefore != null && searchTimeAfter != null)) {
			comments.removeAll(comments);
			error = 1;
		}


		if (searchTimeBefore == null && searchTimeAfter != null) {
			comments.add("無効な入力です");
		} else if (searchTimeBefore != null && searchTimeAfter == null) {
			comments.add("無効な入力です");
		}

		if (comments.size() == 0 && error == 0) {
			return true;
		} else {
			return false;
		}
	}
}