package servlet;

import dao.DAOManager;
import dao.support.EmployeeSearchingOption;
import filePath.Path;
import servlet.noticeSupport.NoticeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		if (request.getParameter("search_mode").isEmpty()) {
			showAllEmployees(request, response);
		} else {

			int mode;

			String url;

			switch(request.getParameter("search_mode")) {
				case "code" :
					mode = EmployeeSearchingOption.SEARCH_BY_CODE;
					break;

				case "name" :
					mode = EmployeeSearchingOption.SEARCH_BY_NAME;
					break;

				case "hurigana" :
					mode = EmployeeSearchingOption.SEARCH_BY_KANA;
					break;

				case "section" :
					mode = EmployeeSearchingOption.SEARCH_BY_SECTION;
					break;

				default :
					mode = EmployeeSearchingOption.SEARCH_BY_DEFAULT;
					break;
			}

			DAOManager dao = DAOManager.DAO_MANAGER;
			try {

				request.setAttribute("employeeList", dao.searchEmployee(mode, request.getParameter("search")));

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

		String nextPageUrl;

		DAOManager daoManager = DAOManager.DAO_MANAGER;

		try {

			request.setAttribute("employeeList", daoManager.getAllEmployees());
			request.setAttribute("departmentNameList", daoManager.getAllSectionNames());

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