package servlet;

import dao.DAOManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//TODO : Refactoring Class RecordShowingServlet
// TODO : Add comment
@WebServlet(name = "/recordShowingServlet", urlPatterns =
		"/recordShowingServlet")
public class RecordShowingServlet extends HttpServlet {

	// TODO : Add comment
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		showAllEmployees(request, response);

	}

	// TODO : Add comment
	private void showAllEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String nextPageUrl;

		DAOManager daoManager = new DAOManager();

		try {

			session.setAttribute("list", daoManager.getAllEmployees());

			nextPageUrl = "emp_list.jsp";

		} catch (Exception e) {

			session.setAttribute("message", "データベースに接続できませんでした");

			session.setAttribute("url", "menu.jsp");

			nextPageUrl =  "error.jsp";

		}

		request.getRequestDispatcher(nextPageUrl).forward(request, response);

	}

}