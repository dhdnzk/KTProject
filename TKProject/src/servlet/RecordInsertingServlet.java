package servlet;

import dao.DAOManager;
import dao.EmployeeBean;
import filePath.Path;
import servlet.servlet.noticeSupport.NoticeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// TODO : Refactoring Class RecordInsertingServlet
// TODO : Add comment
@WebServlet(name = "recordInsertingServlet", urlPatterns =
		{"/recordInsertingServlet"})
public class RecordInsertingServlet extends HttpServlet {

    // TODO : Add comment
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException  {

        toRegistrationPage(request, response);
    }

	// TODO : Add comment
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException  {

		String logic = request.getParameter("button");

		if ( logic.equals("toRegistrationPage") || logic.equals("return")) {

			toRegistrationPage(request, response);

		}
		else if (logic.equals("registration") ) {

			registration(request, response);

		}
		else if (logic.equals("backToMenuPage")) {

			backToMenuPage(request, response);

		}

	}

	// TODO : Add comment
	private void registration(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException  {

		try{
			EmployeeBean employeeBean = new EmployeeBean();

			employeeBean.setLName(request.getParameter("l_name"));
			employeeBean.setFName(request.getParameter("f_name"));

			employeeBean.setLKana(request.getParameter("l_kana_name"));
			employeeBean.setFKana(request.getParameter("f_kana_name"));

			try {
                employeeBean.setSex(Byte.parseByte(request.getParameter("sex")));
            } catch(Exception e) {

            }

			try {
                Date birthDate = Date.valueOf(request.getParameter("birthday"));
                employeeBean.setBirth(birthDate);
            } catch(Exception e) {
			    employeeBean.setBirth(null);
            }

			employeeBean.setSectionCode(request.getParameter("section_code"));

			try {
                Date empDate = Date.valueOf (request.getParameter("emp_join"));
                employeeBean.setEmpDate(empDate);
            } catch(Exception e) {
			    employeeBean.setEmpDate(null);
            }

			DAOManager daoManager = new DAOManager();

			daoManager.addEmployees(employeeBean);

			new NoticeGenerator(request,
								"insert success : " + employeeBean.getLName(),
								"/recordInsertingServlet",
								"return");

			request.getRequestDispatcher(Path.BASE_VIEW + "success.jsp").forward(request, response);

		} catch(Exception e){

			new NoticeGenerator(request,
								"insert denied",
								"/menu",
								"return");

			request.getRequestDispatcher(Path.BASE_VIEW + "error.jsp").forward(request, response);

		}

	}


	// TODO : Add comment
	private void toRegistrationPage(HttpServletRequest request,	HttpServletResponse
			response) throws ServletException, IOException  {

		DAOManager daoManager = new DAOManager();

		ArrayList<String[]> departmentList = null;

		try {

			departmentList = daoManager.getDepartmentListFromMSectionTable();

		} catch (Exception e) {

			e.printStackTrace();

		}

		request.getSession().setAttribute("departmentList", departmentList );

		request.getRequestDispatcher(Path.BASE_VIEW + "registration.jsp").forward(request, response);
	}

	private void backToMenuPage(HttpServletRequest request, HttpServletResponse

			response) throws ServletException, IOException {

		request.getRequestDispatcher(Path.BASE_VIEW + "menu.jsp").forward(request, response);


	}

}
