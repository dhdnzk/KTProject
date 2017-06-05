package servlet;

import dao.DAOManager;
import filePath.Path;
import servlet.servlet.noticeSupport.NoticeGenerator;
import dao.support.SearchEmployee;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		showAllEmployees(request, response);

	}

	// TODO : Add comment
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			if (request.getParameter("search_mode") == null) {
				showAllEmployees(request, response);
			} else {

				int mode;

				String url;

				switch(request.getParameter("search_mode")) {
					case "code" :
						mode = SearchEmployee.SEARCH_CODE;
						break;

					case "name" :
						mode = SearchEmployee.SEARCH_KANJI;
						break;

					case "hurigana" :
						mode = SearchEmployee.SEARCH_NAME;
						break;

					case "section" :
						mode = SearchEmployee.SEARCH_SECTION;
						break;

					default :
						mode = 0;
						break;
				}

				DAOManager dao = new DAOManager();
				try {
					request.setAttribute("list", dao.searchEmployee(mode, request.getParameter("search")));

					url = "emp_list.jsp";

				} catch (Exception e) {
					new NoticeGenerator(request,
										"search failed",
										"/recordShowingServlet",
										"return");

					url = "error.jsp";


				}
				request.getRequestDispatcher(Path.BASE_VIEW + url).forward(request, response);
			}


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

			new NoticeGenerator(request,
								"can't get data from DB",
								"/menu",
								"return");

			nextPageUrl =  "error.jsp";

		}

		request.getRequestDispatcher(Path.BASE_VIEW + nextPageUrl).forward(request, response);

	}

}