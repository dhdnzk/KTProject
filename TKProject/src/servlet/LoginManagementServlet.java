package servlet;

import dao.DAOManager;
import directory.Directories;
import servlet.servlet.noticeSupport.NoticeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * LoginManagementServlet
 * This class is responsible for login & logout.
 * It decide what method to call, by referencing input tag' value attribute.
 *
 * @author NekoToken
 * @version 1.0.0
 */
@WebServlet(name = "loginManagementServlet", urlPatterns =
        "/loginManagementServlet")
public class LoginManagementServlet extends HttpServlet {

    // TODO : Add comment
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String submit = request.getParameter("button");

        if (submit.equals("login")) {

            doLogin(request, response);

        }
        else if (submit.equals("logout")) {

            doLogout(request, response);

        }

    }

    // TODO : Add comment
    private void doLogin(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        try {

            String id = request.getParameter("id");

            String password = request.getParameter("password");

            DAOManager daoManager = new DAOManager();

            daoManager.tryLogin(id, password);

            request.getSession().setAttribute("id", id);

            request.getRequestDispatcher(Directories.BASE_VIEW + "menu.jsp").forward(request, response);

        } catch (Exception e) {

            request.getSession().invalidate();

            new NoticeGenerator(request,
                                "login denied",
                                "/",
                                "login page");

            request.getRequestDispatcher(Directories.BASE_VIEW + "error.jsp").forward(request, response);

        }

    }

    // TODO : Add comment
    private void doLogout(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

            request.getSession().invalidate();

            new NoticeGenerator(request,
                                "logout success",
                                "/",
                                "login page");

            request.getRequestDispatcher(Directories.BASE_VIEW + "success.jsp").forward(request, response);
    }

}
