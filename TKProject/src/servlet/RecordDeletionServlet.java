package servlet;

import dao.DAOManager;
import filePath.Path;
import exception.DeleteFailedException;
import servlet.servlet.noticeSupport.NoticeGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// TODO : Refactoring Class RecordDeletionServlet
// TODO : Add comment
@WebServlet(name = "/recordDeletionServlet", urlPatterns =
		{"/recordDeletionServlet"})
public class RecordDeletionServlet extends HttpServlet {

	// TODO : Add comment
    // TODO : Refactoring method
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {

		DAOManager daoManager = new DAOManager();

		RequestDispatcher rd = null;

		try{

			String[] checkedList = request.getParameterValues("code");

			if(checkedList == null || checkedList.length == 0) {

				throw new DeleteFailedException();

			}

				daoManager.deleteEmployees(checkedList);

				new NoticeGenerator(request,
						"delete success : ",
						"/recordShowingServlet",
						"return");

				rd = request.getRequestDispatcher(Path.BASE_VIEW + "success.jsp");

		}catch(DeleteFailedException e) {

			new NoticeGenerator(request,
								"delete failed",
								"/recordShowingServlet",
								"return");

			rd = request.getRequestDispatcher(Path.BASE_VIEW + "error.jsp");

		} catch (Exception e) {

			e.printStackTrace();

		}

		rd.forward(request, response);

	}

}
