package servlet;

import dao.DAOManager;
import dao.EmployeeBean;
import filePath.Path;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import servlet.noticeSupport.NoticeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Created by bumskim on 2017. 6. 7..
 */
@MultipartConfig
@WebServlet(name = "AddEmployeeByExcelFile", urlPatterns =
        "/AddEmployeeByExcelFile")
public class AddEmployeeByExcelFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean error = false;

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        InputStream fileContent = filePart.getInputStream();

        XSSFWorkbook wb = null;

        try {

            wb = new XSSFWorkbook(fileContent);

        } catch (IOException e) {

            e.printStackTrace();

        }

        Sheet sh = wb.getSheetAt(0);

        for(Row row : sh) {

            if(row.getCell(0).getStringCellValue().equals("")) {

                break;

            }

            EmployeeBean employee = new EmployeeBean();

            employee.setLName(row.getCell(0).getStringCellValue());

            employee.setFName(row.getCell(1).getStringCellValue());

            try {

                DAOManager.DAO_MANAGER.addEmployees(employee);

            } catch (Exception e) {

                System.out.println("insert failed");

                error = true;

            }

        }

        try {

            fileContent.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        if(error) {

            new NoticeGenerator(request,
                    "insert failed",
                    "/recordInsertingServlet",
                    "return");

        } else {

            request.setAttribute("link","/recordInsertingServlet");

            request.setAttribute("message", "seccuss!");

            request.setAttribute("link_view", "return");

            request.getRequestDispatcher(Path.BASE_VIEW + "success.jsp").forward(request,response);

        }

    }

}
