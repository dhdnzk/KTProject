package servlet;

import filePath.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bumskim on 2017. 6. 5..
 *
 */
@WebServlet(name = "menuServlet", urlPatterns = "/menu")
public class menuServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {

            session.getAttribute("id");

            request.getRequestDispatcher(Path.BASE_VIEW + "menu.jsp").forward(request, response);

        } catch(NullPointerException e) {

            request.getRequestDispatcher(Path.BASE_VIEW + "login.jsp").forward(request, response);

        }

    }

}
