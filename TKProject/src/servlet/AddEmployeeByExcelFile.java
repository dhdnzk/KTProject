package servlet;

import dao.DAOManager;
import dao.EmployeeBean;
import filePath.Path;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by bumskim on 2017. 6. 7..
 */
@MultipartConfig
@WebServlet(name = "AddEmployeeByExcelFile", urlPatterns =
        "/AddEmployeeByExcelFile")
public class AddEmployeeByExcelFile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(Path.BASE_VIEW + "registrationByExcel.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean error = false;

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

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

            employee.setLKana(row.getCell(2).getStringCellValue().equals("") ? null : row.getCell(2).getStringCellValue());

            employee.setFKana(row.getCell(3).getStringCellValue().equals("") ? null : row.getCell(3).getStringCellValue());

            try {
                employee.setSex(row.getCell(4).getStringCellValue().equals("男") ? (byte)0 : (byte)1);
            } catch(NullPointerException e) {
                employee.setSex((byte)0);
            }

            try {
                employee.setBirth(new Date(row.getCell(5).getDateCellValue().getTime()));
            } catch(NullPointerException e) {
                employee.setBirth(null);
            }

            try {
                employee.setSectionName(row.getCell(6).getStringCellValue());
            } catch(NullPointerException e) {
                employee.setSectionCode("");
            }

            try {
                employee.setEmpDate(new Date(row.getCell(7).getDateCellValue().getTime()));
            } catch(NullPointerException e) {
                employee.setEmpDate(null);
            }

            ArrayList<String[]> list = null;
            try {
                list = DAOManager.DAO_MANAGER.getDepartmentListFromMSectionTable();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for(String[] name : list) {
                if(employee.getSectionName().equalsIgnoreCase(name[1])) {
                    employee.setSectionCode(name[0]);
                    break;
                }
            }


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
            request.getRequestDispatcher(Path.BASE_VIEW + "error.jsp").forward(request,response);
        } else {

            request.setAttribute("link","/recordInsertingServlet");

            request.setAttribute("message", "seccuss!");

            request.setAttribute("link_view", "return");

            request.getRequestDispatcher(Path.BASE_VIEW + "success.jsp").forward(request,response);

        }

    }

}
