package servlet;

import dao.DAOManager;
import dao.EmployeeBean;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by bumskim on 2017. 6. 7..
 * this class is for getting PDF file with all employee's data
 */
@WebServlet(name = "getEmployeePDFServlet", urlPatterns = "/getPDF")
public class getEmployeePDFServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // set mime data, header
        resp.setContentType("application/pdf");

        resp.addHeader("Content-Disposition", "inline; filename=Documentation.pdf");

        // create pdf file and write content
        PDDocument pdf = doCreate();

        PDStream ps=new PDStream(pdf);

        InputStream is = ps.createInputStream();

        OutputStream responseOutputStream = resp.getOutputStream();

        // save in outputstream with created pdf document
        pdf.save(responseOutputStream);

        byte[] buf = new byte[4096];

        int len = -1;

        // transport data to client
        while ((len = is.read(buf)) != -1) {

            responseOutputStream.write(buf, 0, len);

        }

        responseOutputStream.flush();

        responseOutputStream.close();

        // close the document
        try {

            pdf.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private PDDocument doCreate() throws IOException {

        // create empty document
        PDDocument document = new PDDocument();

        // create empty page
        PDPage page = new PDPage();

        // add page into document
        document.addPage(page);

        ArrayList<EmployeeBean> list = null;

        try {

            list = DAOManager.DAO_MANAGER.getAllEmployees();

        } catch (Exception e) {

            System.out.println("get employee info failed");

        }

        // column : 6 / row : ?
        String[][] str = new String[list.size()][6];

        for(int i=0; i < list.size(); i++) {

            EmployeeBean temp = list.get(i);

            str[i][0] = temp.getEmpCode();

            str[i][1] = temp.getLName() + temp.getFName();

            str[i][2] = temp.getLKana() + temp.getFKana();

            str[i][3] = (temp.getSex() == 0 ? "male" : "female");

            try {

                str[i][4] = temp.getBirth().toString();

            } catch (Exception e) {

                str[i][4] = "";

            }

            str[i][5] = temp.getSectionName();

        }

        PDPageContentStream content = new PDPageContentStream(document, page);

        drawTable(page, content, 700, 100, str);

        // must close stream before save file
        content.close();

        return document;
    }

    /**
     * @param page
     * @param contentStream
     * @param y the y-coordinate of the first row
     * @param margin the padding on left and right of table
     * @param content a 2d array containing the table data
     * @throws IOException
     */
    public static void drawTable(PDPage page, PDPageContentStream contentStream,
                                 float y, float margin,
                                 String[][] content) throws IOException {

        final int rows = content.length;

        final int cols = content[0].length;

        final float rowHeight = 20f;

        final float tableWidth = page.getMediaBox().getWidth()-(2*margin);

        final float tableHeight = rowHeight * rows;

        final float colWidth = tableWidth/(float)cols;

        final float cellMargin=5f;

        //draw the rows
        float nexty = y ;

        for (int i = 0; i <= rows; i++) {

            contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);

            nexty-= rowHeight;

        }

        //draw the columns
        float nextx = margin;

        for (int i = 0; i <= cols; i++) {

            contentStream.drawLine(nextx,y,nextx,y-tableHeight);

            nextx += colWidth;

        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);

        float textx = margin+cellMargin;

        float texty = y-15;

        for(int i = 0; i < content.length; i++){

            for(int j = 0 ; j < content[i].length; j++){

                String text = content[i][j];

                contentStream.beginText();

                contentStream.moveTextPositionByAmount(textx,texty);

                contentStream.drawString(text);

                contentStream.endText();

                textx += colWidth;

            }

            texty-=rowHeight;

            textx = margin+cellMargin;

        }

    }

}
