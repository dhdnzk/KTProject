package dao;

import exception.DeleteFailedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionManager.*;
import dao.support.SearchEmployee;

// TODO : Add comment
enum  EmployeeDAO {

    EMPLOYEE_DAO;

    // TODO : Add commentint
    ArrayList<EmployeeBean> employeeSearchByString(int mode, String search) throws Exception {

        String sql = "SELECT * FROM m_employee JOIN m_section ON m_employee.section_code = m_section.section_code WHERE ";

        switch(mode) {
            case SearchEmployee.SEARCH_CODE:
                sql += "emp_code";
                break;

            case SearchEmployee.SEARCH_KANJI:
                sql += "CONCAT(l_name, f_name)";
                break;

            case SearchEmployee.SEARCH_NAME:
                sql += "CONCAT(l_kana_name, f_kana_name)";
                break;

            case SearchEmployee.SEARCH_SECTION:
                sql += "section_name";
                break;

            default:
                throw new Exception();
        }

        sql += " LIKE '%" + search + "%'";

        ResultSet resultSet = ConnectionManager.getInstance().getConnectionStatement().executeQuery(sql);

        ArrayList<EmployeeBean> employeeBeanArrayList = fromResultSetToArrayList(resultSet);

        ConnectionManager.invalidate();

        return employeeBeanArrayList;

    }

    // TODO : Add comment
    ArrayList<EmployeeBean> getAllEmployees() throws Exception {

        String sql = "SELECT * FROM m_employee";

        ResultSet resultSet = ConnectionManager.getInstance()
                .getConnectionStatement().executeQuery(sql);

        ArrayList<EmployeeBean> employeeBeanArrayList = fromResultSetToArrayList(resultSet);

        ConnectionManager.invalidate();

        return employeeBeanArrayList;

    }

    // TODO : Add comment
    void addEmployee (EmployeeBean employeeBean) throws Exception {

        String sqlQuery = "INSERT INTO m_employee (l_name, f_name, " +
                "l_kana_name, f_kana_name, sex, birth_day, section_code, " +
                "emp_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = ConnectionManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1, employeeBean.getLName());

        preparedStatement.setString(2, employeeBean.getFName());

        preparedStatement.setString(3, employeeBean.getLKana());

        preparedStatement.setString(4, employeeBean.getFKana());

        preparedStatement.setByte(5, employeeBean.getSex());

        preparedStatement.setDate(6, employeeBean.getBirth());

        preparedStatement.setString(7, employeeBean.getSectionCode());

        preparedStatement.setDate(8, employeeBean.getEmpDate());

        try {
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            throw new Exception();
        } finally {
            ConnectionManager.invalidate();
        }
    }

    // TODO : Create QUERY sentence to delete the selected record.
    // TODO : Add comment
    // FIXME : what is the meaning of parameter?
    void deleteEmployees(String[] codeList) throws
            Exception {

        String sql = "DELETE FROM m_employee WHERE emp_code = ?";

        Connection con = ConnectionManager.getInstance().getConnection();

        for(String codeName : codeList) {

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, codeName);

            if(pstmt.execute()) {

                throw new DeleteFailedException();

            }

        }

    }

    /**
     *
     * @return ArrayList<String> :
     * Return a array list which is composed of emp_sys_db.m_section's records.
     * This method is for getting current department list which is saved in
     * database. Via request object instance, "registration.jsp" file references
     * this list to make "<select>"tag.
     *
     * FIXME :
     * @throws Exception : This method just throws all of exceptions.
     * I don't have any idea to handle this exceptions yet.
     * If you have a good idea, please fix it.
     */
    ArrayList<String[]> getDepartmentListFromMSectionTable() throws Exception {

        String query = "SELECT section_name, section_code from emp_sys_db.m_section";

        ResultSet resultSet = ConnectionManager.getInstance()
                .getConnectionStatement().executeQuery(query);

        ArrayList<String[]> sectionList = new ArrayList<String[]>();

        while(resultSet.next()) {
            String[] str = {resultSet.getString("section_code"), resultSet.getString("section_name")};
            sectionList.add(str);
        }

        ConnectionManager.invalidate();
        return sectionList;

    }

    public ArrayList<String> getAllSectionCodes() throws Exception {

        String query = "SELECT section_code from emp_sys_db.m_section";

        Statement statement = ConnectionManager.getInstance()
                .getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<String> sectionCodeList = new ArrayList<>();

        while(resultSet.next()) {

            sectionCodeList.add(resultSet.getString("section_code"));

        }

        return sectionCodeList;

    }

    public ArrayList<String> getAllSectionNames() throws Exception {

        String query = "SELECT section_name from emp_sys_db.m_section";

        Statement statement = ConnectionManager.getInstance().getConnection()
                .createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<String> sectionNameList = new ArrayList<>();

        while(resultSet.next()) {

            sectionNameList.add(resultSet.getString("section_name"));

        }

        return sectionNameList;

    }

    private ArrayList<EmployeeBean> fromResultSetToArrayList(ResultSet resultSet) throws SQLException {

        ArrayList<EmployeeBean> employeeBeanArrayList = new ArrayList<>();

        while(resultSet.next()){

            EmployeeBean employeeBean = new EmployeeBean();

            employeeBean.setEmpCode(resultSet.getString("emp_code"));

            employeeBean.setLName(resultSet.getString("l_name"));

            employeeBean.setFName(resultSet.getString("f_name"));

            employeeBean.setLKana(resultSet.getString("l_kana_name"));

            employeeBean.setFKana(resultSet.getString("f_kana_name"));

            employeeBean.setSex(resultSet.getByte("sex"));

            employeeBean.setBirth(resultSet.getDate("birth_day"));

            employeeBean.setSectionCode(resultSet.getString("section_code"));

            employeeBean.setEmpDate(resultSet.getDate("emp_date"));

            employeeBean.setUpdateDate(resultSet.getTimestamp("update_date"));

            employeeBeanArrayList.add(employeeBean);

        }

        return employeeBeanArrayList;

    }
}
