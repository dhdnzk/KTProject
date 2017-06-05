package servlet;

import dao.DAOManager;
import exception.DeleteFailedException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO : Refactoring Class RecordDeletionServlet
// TODO : Add comment
@WebServlet(name = "/recordDeletionServlet", urlPatterns =
		{"/recordDeletionServlet"})
public class RecordDeletionServlet extends HttpServlet {

	// TODO : Add comment
    // TODO : Refactoring method
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {

		String url = null;
		String link = null;
		String message = null;

		DAOManager daoManager = new DAOManager();

		try{

			daoManager.deleteEmployee(request.getParameter("code"));

			message = request.getParameter("l_name");

			url = "success_delete.html";

		}catch(DeleteFailedException e) {

			message = e.getMessage();

			url = "error.jsp";

			link = "emp_list.jsp";

			request.setAttribute("link","emp_list.jsp");
					request.setAttribute("link_view", "従業員一覧");

		} catch (Exception e) {

			e.printStackTrace();

		}
		request.setAttribute("message", message);

		RequestDispatcher rd = request.getRequestDispatcher(url);

		rd.forward(request, response);

	}

}
