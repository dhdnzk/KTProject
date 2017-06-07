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
public enum DAOManager {

    DAO_MANAGER;

    /**
     *
     * tryLogin
     * When failed login, exception is thrown to this method from
     * UserDAO.tryLogin method. by throwing the exception again to the AddEmployeeByExcelFile,
     * it predicate login failed and execute next process.
     *
     * @param id : get user id from user input on web.
     * @param password : get user password from user input on web.
     * @throws Exception :
     */
    public void tryLogin (String id, String password) throws Exception {

        UserDAO userDAO = UserDAO.USER_DAO;

        userDAO.tryLogin(id, password);

    }

    // TODO : add comment
    public ArrayList<EmployeeBean> getAllEmployees() throws Exception {

        EmployeeDAO employeeDao = EmployeeDAO.EMPLOYEE_DAO;

        return employeeDao.getAllEmployees();

    }

    public ArrayList<String> getAllSectionCodes() throws Exception {

        EmployeeDAO employeeDAO = EmployeeDAO.EMPLOYEE_DAO;

        return employeeDAO.getAllSectionCodes();

    }

    public ArrayList<String> getAllSectionNames() throws Exception {

        EmployeeDAO employeeDAO = EmployeeDAO.EMPLOYEE_DAO;

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

        EmployeeDAO employeeDAO = EmployeeDAO.EMPLOYEE_DAO;

        return employeeDAO.getDepartmentListFromMSectionTable();

    }

    // TODO : add comment
    public void addEmployees (EmployeeBean employeeBean) throws Exception {

        EmployeeDAO employeeDao = EmployeeDAO.EMPLOYEE_DAO;

        employeeDao.addEmployee(employeeBean);

    }

    // TODO : add comment
    public void deleteEmployees(String[] codeList) throws Exception {

        EmployeeDAO employeeDao = EmployeeDAO.EMPLOYEE_DAO;

        employeeDao.deleteEmployees(codeList);

    }

    //TODO : add comment
    public ArrayList<EmployeeBean> searchEmployee(int mode, String keyword) throws Exception {
        EmployeeDAO dao = EmployeeDAO.EMPLOYEE_DAO;
        return dao.employeeSearchByString(mode, keyword);
    }


}

