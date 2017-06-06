package dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * DAOManager
 * Class to management and control dao classes.
 * All of accesses to database MUST through this class and it's methods.
 *
 * @author NekoToken
 * @version 1.0.0
 */
public class DAOManager {

    /**
     *
     * tryLogin
     * When failed login, exception is thrown to this method from
     * UserDAO.tryLogin method. by throwing the exception again to the servlet,
     * it predicate login failed and execute next process.
     *
     * @param id : get user id from user input on web.
     * @param password : get user password from user input on web.
     * @throws Exception :
     */
    public void tryLogin (String id, String password) throws Exception {

        UserDAO userDAO = new UserDAO();

        userDAO.tryLogin(id, password);

    }

    // TODO : add comment
    public ArrayList<EmployeeBean> getAllEmployees() throws Exception {

        EmployeeDAO employeeDao = new EmployeeDAO();

        return employeeDao.getAllEmployees();

    }

    public ArrayList<String> getAllSectionCodes() throws Exception {

        EmployeeDAO employeeDAO = new EmployeeDAO();

        return employeeDAO.getAllSectionCodes();

    }

    public ArrayList<String> getAllSectionNames() throws Exception {

        EmployeeDAO employeeDAO = new EmployeeDAO();

        return employeeDAO.getAllSectionNames();

    }

    public ArrayList<ArrayList<String>> getAllSectionInfo() throws Exception {

        ArrayList<ArrayList<String>> allSectionInfo = new ArrayList<>();

        allSectionInfo.add(getAllSectionCodes());

        allSectionInfo.add(getAllSectionNames());

        return allSectionInfo;

    }

    // TODO : add comment
    public ArrayList<String[]> getDepartmentListFromMSectionTable() throws Exception {

        EmployeeDAO employeeDAO = new EmployeeDAO();

        return employeeDAO.getDepartmentListFromMSectionTable();

    }

    // TODO : add comment
    public void addEmployees (EmployeeBean employeeBean) throws Exception {

        EmployeeDAO employeeDao = new EmployeeDAO();

        employeeDao.addEmployee(employeeBean);

    }

    // TODO : add comment
    public void deleteEmployee (String code) throws Exception {

        EmployeeDAO employeeDao = new EmployeeDAO();

        employeeDao.deleteEmployee(code);

    }

    //TODO : add comment
    public List<EmployeeBean> searchEmployee(int mode, String keyword) throws Exception {
        EmployeeDAO dao = new EmployeeDAO();
        return dao.employeeSearchByString(mode, keyword);
    }


}

